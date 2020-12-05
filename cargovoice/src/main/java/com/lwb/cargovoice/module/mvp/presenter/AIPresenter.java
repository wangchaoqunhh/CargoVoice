package com.lwb.cargovoice.module.mvp.presenter;


import android.content.Context;
import android.content.Intent;

import com.alibaba.android.arouter.launcher.ARouter;
import com.cargo.basecommon.base.BaseResponse;
import com.cargo.basecommon.base.IPresenter;
import com.cargo.basecommon.base.IView;
import com.cargo.basecommon.utils.ListUtils;
import com.lwb.cargovoice.module.mvp.contract.AIContract;
import com.lwb.cargovoice.module.mvp.entity.request.AIRequest;
import com.lwb.cargovoice.module.mvp.entity.response.AIResponse;
import com.lwb.cargovoice.module.mvp.model.AIModel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class AIPresenter implements IPresenter {
    private Context mContext;
    private CompositeDisposable mCompositeDisposable;
    private AIContract.View mView;

    public AIPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate() {
        //用来存放RxJava的订阅关系,请求结束时需要清理掉这个订阅,否则会产生内存泄漏
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        mCompositeDisposable.dispose();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(IView view) {
        mView = (AIContract.View) view;
    }


    public void aiHook(AIRequest request) {
        new AIModel(mContext)
                .aiHook(request)
                .subscribe(new Observer<List<AIResponse>>() {//观察者模式订阅
                    @Override
                    public void onError(Throwable e) {//请求失败调用
                        e.printStackTrace();
                        mView.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(List<AIResponse> response) {//请求成功调用
                        if (response != null) {
                            mView.onSuccess(response);
                        }
                    }
                });
    }


    @Override
    public void attachIncomingIntent(Intent intent) {

    }
}
