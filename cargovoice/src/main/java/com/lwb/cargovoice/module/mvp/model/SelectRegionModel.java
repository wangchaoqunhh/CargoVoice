package com.lwb.cargovoice.module.mvp.model;

import android.content.Context;

import com.cargo.basecommon.base.BaseResponse;
import com.cargo.basecommon.http.RetrofitHelper;
import com.lwb.cargovoice.module.api.CargoVoiceApi;
import com.lwb.cargovoice.module.mvp.contract.SelectRegionContract;
import com.lwb.cargovoice.module.mvp.entity.response.SelectRegionBean;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class SelectRegionModel implements SelectRegionContract.Model {

    private Context mContext;

    public SelectRegionModel(Context context) {
        this.mContext = context;
    }

    @Override
    public Observable<BaseResponse<List<SelectRegionBean>>> locationCountry() {
        return RetrofitHelper.getInstance(mContext)
                .getRetrofit()
                .create(CargoVoiceApi.class)
                .locationCountry()//调用相应的方法
                .subscribeOn(Schedulers.io())//请求时切换线程到io线程
                .observeOn(AndroidSchedulers.mainThread());//处理结果时切换到主线程(ui线程)
    }

    @Override
    public Observable<BaseResponse<List<SelectRegionBean>>> locationProvince(String countryCode) {
        return RetrofitHelper.getInstance(mContext)
                .getRetrofit()
                .create(CargoVoiceApi.class)
                .locationProvince(countryCode)//调用相应的方法
                .subscribeOn(Schedulers.io())//请求时切换线程到io线程
                .observeOn(AndroidSchedulers.mainThread());//处理结果时切换到
    }

    @Override
    public Observable<BaseResponse<List<SelectRegionBean>>> locationCity(String countryCode, String provinceCode) {
        return RetrofitHelper.getInstance(mContext)
                .getRetrofit()
                .create(CargoVoiceApi.class)
                .locationCity(countryCode, provinceCode)//调用相应的方法
                .subscribeOn(Schedulers.io())//请求时切换线程到io线程
                .observeOn(AndroidSchedulers.mainThread());//处理结果时切换到
    }


    // @Override
    // public Observable<BaseResponse<> xxx(String name) {
//     return RetrofitHelper.getInstance(mContext)
//             .getRetrofit()
//             .create(CarChooseApi.class)
//             .getCarByName(name)//调用相应的方法
//             .subscribeOn(Schedulers.io())//请求时切换线程到io线程
//             .observeOn(AndroidSchedulers.mainThread());//处理结果时切换到主线程(ui线程)
// }
}
