package com.lwb.cargovoice.module.mvp.presenter;


import android.content.Context;
import android.content.Intent;

import com.cargo.basecommon.base.BaseResponse;
import com.cargo.basecommon.base.IPresenter;
import com.cargo.basecommon.base.IView;
import com.cargo.basecommon.base.RxJavaObserver;
import com.cargo.basecommon.bean.EnquiryAddGoalRequest;
import com.lwb.cargovoice.module.mvp.contract.EnquiryAddGoalContract;
import com.lwb.cargovoice.module.mvp.model.EnquiryAddGoalModel;

import io.reactivex.disposables.CompositeDisposable;


public class EnquiryAddGoalPresenter implements IPresenter {
    private Context mContext;
    private CompositeDisposable mCompositeDisposable;
    private EnquiryAddGoalContract.View mView;
    private EnquiryAddGoalContract.Model mModel;

    public EnquiryAddGoalPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate() {
        //用来存放RxJava的订阅关系,请求结束时需要清理掉这个订阅,否则会产生内存泄漏
        mCompositeDisposable = new CompositeDisposable();
        mModel = new EnquiryAddGoalModel(mContext);
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
        mView = (EnquiryAddGoalContract.View) view;
    }


    @Override
    public void attachIncomingIntent(Intent intent) {

    }


    public void enquiryCreate(EnquiryAddGoalRequest request) {
        mModel.enquiryCreate(request)
                .subscribe(new RxJavaObserver<BaseResponse<String>>(mCompositeDisposable, mView) {
                    @Override
                    public void onNext(BaseResponse<String> response) {
                        if (response != null) {
                            if (response.getRespCode() == 0) {
                                mView.onSuccess(null);
                            } else if (response.getRespCode() == 401) {
                                mView.onError(response.getRespMsg());
                            } else {
                                mView.onError(response.getRespMsg());
                            }
                        }
                    }
                });
    }

    public void bookingSpaceCreate(EnquiryAddGoalRequest request) {
        mModel.bookingSpaceCreate(request)
                .subscribe(new RxJavaObserver<BaseResponse<String>>(mCompositeDisposable, mView) {
                    @Override
                    public void onNext(BaseResponse<String> response) {
                        if (response != null) {
                            if (response.getRespCode() == 0) {
                                mView.onSuccess(null);
                            } else if (response.getRespCode() == 401) {
                                mView.onError(response.getRespMsg());
                            } else {
                                mView.onError(response.getRespMsg());
                            }
                        }
                    }
                });
    }
}
