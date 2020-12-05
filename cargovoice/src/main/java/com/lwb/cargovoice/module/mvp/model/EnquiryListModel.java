package com.lwb.cargovoice.module.mvp.model;

import android.content.Context;

import com.cargo.basecommon.base.BaseResponse;
import com.cargo.basecommon.http.RetrofitHelper;
import com.lwb.cargovoice.module.api.CargoVoiceApi;
import com.lwb.cargovoice.module.mvp.contract.EnquiryListContract;
import com.lwb.cargovoice.module.mvp.entity.request.OneTouchRequest;
import com.lwb.cargovoice.module.mvp.entity.response.EnquiryListResponse;
import com.lwb.cargovoice.module.mvp.entity.response.OneTouchResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class EnquiryListModel implements EnquiryListContract.Model {

    private Context mContext;

    public EnquiryListModel(Context context) {
        this.mContext = context;
    }

    @Override
    public Observable<BaseResponse<List<EnquiryListResponse>>> enquiryList(int pageNumber, int pageSize) {
        return RetrofitHelper.getInstance(mContext)
                .getRetrofit()
                .create(CargoVoiceApi.class)
                .enquiryList(pageNumber, pageSize)//调用相应的方法
                .subscribeOn(Schedulers.io())//请求时切换线程到io线程
                .observeOn(AndroidSchedulers.mainThread());//处理结果时切换到主线程(ui线程)
    }

    @Override
    public Observable<OneTouchResponse> oneTouch(OneTouchRequest request) {
        return RetrofitHelper.getInstance(mContext)
                .getRetrofit()
                .create(CargoVoiceApi.class)
                .oneTouch(request)//调用相应的方法
                .subscribeOn(Schedulers.io())//请求时切换线程到io线程
                .observeOn(AndroidSchedulers.mainThread());//处理结果时切换到主线程(ui线程)
    }

}
