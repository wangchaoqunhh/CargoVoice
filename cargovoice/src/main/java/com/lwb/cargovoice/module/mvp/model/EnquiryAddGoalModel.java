package com.lwb.cargovoice.module.mvp.model;

import android.content.Context;

import com.cargo.basecommon.base.BaseResponse;
import com.cargo.basecommon.bean.EnquiryAddGoalRequest;
import com.cargo.basecommon.http.RetrofitHelper;
import com.lwb.cargovoice.module.api.CargoVoiceApi;
import com.lwb.cargovoice.module.mvp.contract.EnquiryAddGoalContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class EnquiryAddGoalModel implements EnquiryAddGoalContract.Model {

    private Context mContext;

    public EnquiryAddGoalModel(Context context) {
        this.mContext = context;
    }

    @Override
    public Observable<BaseResponse<String>> enquiryCreate(EnquiryAddGoalRequest request) {
        return RetrofitHelper.getInstance(mContext)
                .getRetrofit()
                .create(CargoVoiceApi.class)
                .enquiryCreate(request)//调用相应的方法
                .subscribeOn(Schedulers.io())//请求时切换线程到io线程
                .observeOn(AndroidSchedulers.mainThread());//处理结果时切换到主线程(ui线程)
    }

    @Override
    public Observable<BaseResponse<String>> bookingSpaceCreate(EnquiryAddGoalRequest request) {
        return RetrofitHelper.getInstance(mContext)
                .getRetrofit()
                .create(CargoVoiceApi.class)
                .bookingSpaceCreate(request)//调用相应的方法
                .subscribeOn(Schedulers.io())//请求时切换线程到io线程
                .observeOn(AndroidSchedulers.mainThread());//处理
    }
}
