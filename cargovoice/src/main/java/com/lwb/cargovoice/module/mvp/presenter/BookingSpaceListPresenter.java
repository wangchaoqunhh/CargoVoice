package com.lwb.cargovoice.module.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import com.cargo.basecommon.base.BaseResponse;
import com.cargo.basecommon.base.IPresenter;
import com.cargo.basecommon.base.IView;
import com.cargo.basecommon.base.PagingViewRxJavaObserver;
import com.cargo.basecommon.view.PagingView;
import com.lwb.cargovoice.module.mvp.contract.BookingSpaceListContract;
import com.lwb.cargovoice.module.mvp.entity.response.BookingSpaceListResponse;
import com.lwb.cargovoice.module.mvp.entity.response.EnquiryListResponse;
import com.lwb.cargovoice.module.mvp.model.BookingSpaceListModel;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class BookingSpaceListPresenter implements IPresenter {
    private Context mContext;
    private CompositeDisposable mCompositeDisposable;
    private BookingSpaceListContract.View mView;
    private BookingSpaceListContract.Model mModel;

    public BookingSpaceListPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate() {
        //用来存放RxJava的订阅关系,请求结束时需要清理掉这个订阅,否则会产生内存泄漏
        mCompositeDisposable = new CompositeDisposable();
        mModel = new BookingSpaceListModel(mContext);
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
        mView = (BookingSpaceListContract.View) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {
    }

    public void bookingSpaceList(PagingView<BookingSpaceListResponse> pagingView, int pageNumber, int pageSize) {
        mModel.bookingSpaceList(pageNumber, pageSize)
                .subscribe(new PagingViewRxJavaObserver<BaseResponse<List<BookingSpaceListResponse>>, BookingSpaceListResponse>(mCompositeDisposable, mView, pagingView) {
                    @Override
                    public void onNext(BaseResponse<List<BookingSpaceListResponse>> response) {
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
}