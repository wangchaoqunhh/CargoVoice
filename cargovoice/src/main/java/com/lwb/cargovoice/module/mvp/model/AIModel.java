package com.lwb.cargovoice.module.mvp.model;

import android.content.Context;

import com.cargo.basecommon.base.BaseResponse;
import com.cargo.basecommon.http.RetrofitHelper;
import com.lwb.cargovoice.module.api.CargoVoiceApi;
import com.lwb.cargovoice.module.mvp.contract.AIContract;
import com.lwb.cargovoice.module.mvp.entity.request.AIRequest;
import com.lwb.cargovoice.module.mvp.entity.response.AIResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class AIModel implements AIContract.Model {

    private Context mContext;

    public AIModel(Context context) {
        this.mContext = context;
    }


    @Override
    public Observable<List<AIResponse>> aiHook(AIRequest request) {
        return RetrofitHelper.getInstance(mContext)
                .getRetrofit()
                .create(CargoVoiceApi.class)
                .aiHook(request)//调用相应的方法
                .subscribeOn(Schedulers.io())//请求时切换线程到io线程
                .observeOn(AndroidSchedulers.mainThread());//处理结果时切换到主线程(ui线程)
    }


}
