package com.lwb.cargovoice.module.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import com.cargo.basecommon.base.BaseResponse;
import com.cargo.basecommon.base.IPresenter;
import com.cargo.basecommon.base.IView;
import com.cargo.basecommon.base.PagingViewRxJavaObserver;
import com.cargo.basecommon.view.PagingView;
import com.lwb.cargovoice.module.mvp.contract.BillListContract;
import com.lwb.cargovoice.module.mvp.entity.response.BillListResponse;
import com.lwb.cargovoice.module.mvp.entity.response.FreightListResponse;
import com.lwb.cargovoice.module.mvp.model.BillListModel;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class BillListPresenter implements IPresenter {
    private Context mContext;
    private CompositeDisposable mCompositeDisposable;
    private BillListContract.View mView;
    private BillListContract.Model mModel;

    public BillListPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate() {
        //用来存放RxJava的订阅关系,请求结束时需要清理掉这个订阅,否则会产生内存泄漏
        mCompositeDisposable = new CompositeDisposable();
        mModel = new BillListModel(mContext);
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
        mView = (BillListContract.View) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {
    }

    public void billList(PagingView<BillListResponse> pagingView, int pageNumber, int pageSize, int type) {
        PagingViewRxJavaObserver<BaseResponse<List<BillListResponse>>, BillListResponse> pagingViewRxJavaObserver = new PagingViewRxJavaObserver<BaseResponse<List<BillListResponse>>, BillListResponse>(mCompositeDisposable, mView, pagingView) {
            @Override
            public void onNext(BaseResponse<List<BillListResponse>> response) {
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
        };

        switch (type){
            case 0:
                mModel.billList(pageNumber, pageSize)
                        .subscribe(pagingViewRxJavaObserver);
                break;
            case 1:
                mModel.billOpenList(pageNumber, pageSize)
                        .subscribe(pagingViewRxJavaObserver);
                break;
            case 2:
                mModel.billClosedList(pageNumber, pageSize)
                        .subscribe(pagingViewRxJavaObserver);
                break;
        }

    }
}