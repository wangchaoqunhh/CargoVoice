package com.lwb.cargovoice.module.mvp.presenter;


import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.cargo.basecommon.base.BaseResponse;
import com.cargo.basecommon.base.IPresenter;
import com.cargo.basecommon.base.IView;
import com.cargo.basecommon.base.PagingViewRxJavaObserver;
import com.cargo.basecommon.base.RxJavaObserver;
import com.cargo.basecommon.view.PagingView;
import com.google.gson.Gson;
import com.lwb.cargovoice.module.mvp.contract.EnquiryListContract;
import com.lwb.cargovoice.module.mvp.entity.request.OneTouchRequest;
import com.lwb.cargovoice.module.mvp.entity.response.EnquiryListResponse;
import com.lwb.cargovoice.module.mvp.entity.response.OneTouchResponse;
import com.lwb.cargovoice.module.mvp.model.EnquiryListModel;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;


public class EnquiryListPresenter implements IPresenter {
    private Context mContext;
    private CompositeDisposable mCompositeDisposable;
    private EnquiryListContract.View mView;
    private EnquiryListContract.Model mModel;

    public EnquiryListPresenter(Context context) {
        this.mContext = context;
        mModel = new EnquiryListModel(context);
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
        mView = (EnquiryListContract.View) view;
    }


    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void enquiryList(PagingView<EnquiryListResponse> pagingView, int pageNumber, int pageSize) {
        mModel.enquiryList(pageNumber, pageSize)
                .subscribe(new PagingViewRxJavaObserver<BaseResponse<List<EnquiryListResponse>>, EnquiryListResponse>(mCompositeDisposable, mView, pagingView) {
                    @Override
                    public void onNext(BaseResponse<List<EnquiryListResponse>> response) {
                        if (response != null) {
                            if (response.getRespCode() == 0) {
                                mView.onSuccess(response.getRespData());
                            } else if (response.getRespCode() == 401) {
                                pagingView.onError();
                            } else {
                                pagingView.onError();
                            }
                        }

                    }
                });
    }


    public void oneTouch(String id) {
        OneTouchRequest request = new OneTouchRequest();
        request.setId(id);
        mModel.oneTouch(request)
                .subscribe(new RxJavaObserver<OneTouchResponse>(mCompositeDisposable, mView) {
                    @Override
                    public void onNext(OneTouchResponse response) {
                        if (response != null && response.getErr_code() == 0) {
                            mView.oneTouch();
                        } else if (response != null) {
                            mView.oneTouchFail(response.getErr_msg());
                        } else {
                            mView.oneTouchFail("");
                        }
                    }
                });
    }
}
