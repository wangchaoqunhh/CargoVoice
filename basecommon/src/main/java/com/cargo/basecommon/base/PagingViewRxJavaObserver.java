package com.cargo.basecommon.base;

import com.cargo.basecommon.view.PagingView;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class PagingViewRxJavaObserver<T,R> implements Observer<T> {

    private CompositeDisposable mCompositeDisposable;
    private IView mView;

    private PagingView<R> pagingView;

    public PagingViewRxJavaObserver(CompositeDisposable mCompositeDisposable, IView mView, PagingView<R> pagingView) {
        this.mCompositeDisposable = mCompositeDisposable;
        this.mView = mView;
        this.pagingView = pagingView;
    }

    public PagingViewRxJavaObserver() {
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
        if (pagingView != null) {
            pagingView.onError();
        }
    }

    @Override
    public void onComplete() {

    }
}
