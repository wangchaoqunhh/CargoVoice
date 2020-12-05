package com.lwb.cargovoice.module.mvp.model;

import android.content.Context;

import com.cargo.basecommon.base.BaseResponse;
import com.cargo.basecommon.http.RetrofitHelper;
import com.lwb.cargovoice.module.api.CargoVoiceApi;
import com.lwb.cargovoice.module.mvp.contract.BillListContract;
import com.lwb.cargovoice.module.mvp.entity.response.BillListResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class BillListModel implements BillListContract.Model {

    private Context mContext;

    public BillListModel(Context context) {
        this.mContext = context;
    }

    @Override
    public Observable<BaseResponse<List<BillListResponse>>> billList(int pageNumber, int pageSize) {
        return RetrofitHelper.getInstance(mContext)
                .getRetrofit()
                .create(CargoVoiceApi.class)
                .billList(pageNumber, pageSize)//调用相应的方法
                .subscribeOn(Schedulers.io())//请求时切换线程到io线程
                .observeOn(AndroidSchedulers.mainThread());//处理结果时切换到主线程(ui线程)
    }

    @Override
    public Observable<BaseResponse<List<BillListResponse>>> billClosedList(int pageNumber, int pageSize) {
        return RetrofitHelper.getInstance(mContext)
                .getRetrofit()
                .create(CargoVoiceApi.class)
                .billClosedList(pageNumber, pageSize)//调用相应的方法
                .subscribeOn(Schedulers.io())//请求时切换线程到io线程
                .observeOn(AndroidSchedulers.mainThread());//处理结果时切换到主线程(ui线程)
    }

    @Override
    public Observable<BaseResponse<List<BillListResponse>>> billOpenList(int pageNumber, int pageSize) {
        return RetrofitHelper.getInstance(mContext)
                .getRetrofit()
                .create(CargoVoiceApi.class)
                .billOpenList(pageNumber, pageSize)//调用相应的方法
                .subscribeOn(Schedulers.io())//请求时切换线程到io线程
                .observeOn(AndroidSchedulers.mainThread());//处理结果时切换到主线程(ui线程)
    }

}
