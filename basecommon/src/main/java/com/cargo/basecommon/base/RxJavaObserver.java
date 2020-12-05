package com.cargo.basecommon.base;

import com.cargo.basecommon.view.PagingView;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class RxJavaObserver<T> implements Observer<T> {

    private CompositeDisposable mCompositeDisposable;
    private IView mView;

    public RxJavaObserver(CompositeDisposable mCompositeDisposable, IView mView) {
        this.mCompositeDisposable = mCompositeDisposable;
        this.mView = mView;
    }

    public RxJavaObserver() {
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (mCompositeDisposable != null)
            mCompositeDisposable.add(d);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (mView != null) {
            mView.onError(e.getMessage());
        }
    }

    @Override
    public void onComplete() {

    }
}
