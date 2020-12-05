package com.lwb.cargovoice.module.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import com.cargo.basecommon.base.BaseResponse;
import com.cargo.basecommon.base.IPresenter;
import com.cargo.basecommon.base.IView;
import com.cargo.basecommon.base.RxJavaObserver;
import com.lwb.cargovoice.module.mvp.contract.BookingSpaceDetailsContract;
import com.lwb.cargovoice.module.mvp.entity.response.BookingSpaceDetailsResponse;
import com.lwb.cargovoice.module.mvp.entity.response.EnquiryDetailsResponse;
import com.lwb.cargovoice.module.mvp.model.BookingSpaceDetailsModel;

import io.reactivex.disposables.CompositeDisposable;

public class BookingSpaceDetailsPresenter implements IPresenter {
    private Context mContext;
    private CompositeDisposable mCompositeDisposable;
    private BookingSpaceDetailsContract.View mView;
    private BookingSpaceDetailsContract.Model mModel;

    public BookingSpaceDetailsPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate() {
        //用来存放RxJava的订阅关系,请求结束时需要清理掉这个订阅,否则会产生内存泄漏
        mCompositeDisposable = new CompositeDisposable();
        mModel = new BookingSpaceDetailsModel(mContext);
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
        mView = (BookingSpaceDetailsContract.View) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {
    }

    public void bookingSpaceDetails(String id) {
        mModel.bookingSpaceDetails(id)
                .subscribe(new RxJavaObserver<BaseResponse<BookingSpaceDetailsResponse>>(mCompositeDisposable, mView) {
                    @Override
                    public void onNext(BaseResponse<BookingSpaceDetailsResponse> response) {
                        if (response != null) {
                            if (response.getRespCode() == 0) {
                                mView.onSuccess(response.getRespData());
                            } else if (response.getRespCode() == 401) {
                                response.getRespMsg();
                            } else {

                            }
                        }

                    }
                });
    }
}