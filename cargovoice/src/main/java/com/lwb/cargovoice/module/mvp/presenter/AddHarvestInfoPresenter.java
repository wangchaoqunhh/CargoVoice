package com.lwb.cargovoice.module.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import com.cargo.basecommon.base.BaseResponse;
import com.cargo.basecommon.base.IPresenter;
import com.cargo.basecommon.base.IView;
import com.cargo.basecommon.base.RxJavaObserver;
import com.lwb.cargovoice.module.mvp.contract.AddHarvestInfoContract;
import com.lwb.cargovoice.module.mvp.entity.response.AddHarvestInfoResponse;
import com.lwb.cargovoice.module.mvp.model.AddHarvestInfoModel;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class AddHarvestInfoPresenter implements IPresenter {
    private Context mContext;
    private CompositeDisposable mCompositeDisposable;
    private AddHarvestInfoContract.View mView;
    private AddHarvestInfoContract.Model mModel;

    public AddHarvestInfoPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate() {
        //用来存放RxJava的订阅关系,请求结束时需要清理掉这个订阅,否则会产生内存泄漏
        mCompositeDisposable = new CompositeDisposable();
        mModel = new AddHarvestInfoModel(mContext);
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
        mView = (AddHarvestInfoContract.View) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    /**
     * 查询 旧的添加 地址
     *
     * @param type 1 发活人 2收货人
     */
    public void organization(String type) {
        RxJavaObserver<BaseResponse<List<AddHarvestInfoResponse>>> observer = new RxJavaObserver<BaseResponse<List<AddHarvestInfoResponse>>>(mCompositeDisposable, mView) {
            @Override
            public void onNext(BaseResponse<List<AddHarvestInfoResponse>> response) {
                if (response != null) {
                    if (response.getRespCode() == 0) {
                        mView.organization(response.getRespData());
                    } else if (response.getRespCode() == 401) {
                        mView.onError(response.getRespMsg());
                    } else {
                        mView.onError(response.getRespMsg());
                    }
                }
            }
        };

        switch (type) {
            case "1":
                mModel.organization()
                        .subscribe(observer);
                break;
            case "2":
                mModel.organizationConsignee()
                        .subscribe(observer);
                break;
        }

    }
}