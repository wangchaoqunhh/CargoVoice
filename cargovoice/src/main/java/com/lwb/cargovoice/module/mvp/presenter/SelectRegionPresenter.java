package com.lwb.cargovoice.module.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import com.cargo.basecommon.base.BaseResponse;
import com.cargo.basecommon.base.IPresenter;
import com.cargo.basecommon.base.IView;
import com.cargo.basecommon.base.PagingViewRxJavaObserver;
import com.cargo.basecommon.base.RxJavaObserver;
import com.cargo.basecommon.view.PagingView;
import com.lwb.cargovoice.module.mvp.contract.SelectRegionContract;
import com.lwb.cargovoice.module.mvp.entity.response.EnquiryListResponse;
import com.lwb.cargovoice.module.mvp.entity.response.SelectRegionBean;
import com.lwb.cargovoice.module.mvp.model.SelectRegionModel;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class SelectRegionPresenter implements IPresenter {
    private Context mContext;
    private CompositeDisposable mCompositeDisposable;
    private SelectRegionContract.View mView;
    private SelectRegionContract.Model mModel;

    public SelectRegionPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate() {
        //用来存放RxJava的订阅关系,请求结束时需要清理掉这个订阅,否则会产生内存泄漏
        mCompositeDisposable = new CompositeDisposable();
        mModel = new SelectRegionModel(mContext);
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
        mView = (SelectRegionContract.View) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {
    }

    public void location(PagingView<SelectRegionBean> pagingView, String countryCode, String provinceCode, String type) {
//        RxJavaObserver<BaseResponse<List<SelectRegionBean>>> rxJavaObserver = new RxJavaObserver<BaseResponse<List<SelectRegionBean>>>(mCompositeDisposable, mView) {
//            @Override
//            public void onNext(BaseResponse<List<SelectRegionBean>> response) {
//                if (response != null) {
//                    if (response.getRespCode() == 0) {
//                        mView.onSuccess(response.getRespData(), type);
//                    } else if (response.getRespCode() == 401) {
//                        mView.onError(response.getRespMsg());
//                    } else {
//                        mView.onError(response.getRespMsg());
//                    }
//                }
//            }
//        };

        PagingViewRxJavaObserver<BaseResponse<List<SelectRegionBean>>, SelectRegionBean> pagingViewRxJavaObserver =
                new PagingViewRxJavaObserver<BaseResponse<List<SelectRegionBean>>, SelectRegionBean>(mCompositeDisposable, mView, pagingView) {

                    @Override
                    public void onNext(BaseResponse<List<SelectRegionBean>> response) {
                        if (response != null) {
                            if (response.getRespCode() == 0) {
                                mView.onSuccess(response.getRespData(), type);
                            } else if (response.getRespCode() == 401) {
                                pagingView.onError();
                            } else {
                                pagingView.onError();
                            }
                        }
                    }
                };

        switch (type) {
            case "1":
                mModel.locationCountry()
                        .subscribe(pagingViewRxJavaObserver);
                break;
            case "2":
                mModel.locationProvince(countryCode)
                        .subscribe(pagingViewRxJavaObserver);
                break;
            case "3":
                mModel.locationCity(countryCode, provinceCode)
                        .subscribe(pagingViewRxJavaObserver);
                break;
        }

    }
}