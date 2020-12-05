package com.cargo.login.module.mvp.model;

import android.content.Context;

import com.cargo.basecommon.base.BaseResponse;
import com.cargo.basecommon.http.RetrofitHelper;
import com.cargo.login.module.api.LoginApi;
import com.cargo.login.module.mvp.contract.LoginContract;
import com.cargo.login.module.mvp.entity.request.HolderBean;
import com.cargo.login.module.mvp.entity.request.LoginRequest;
import com.cargo.login.module.mvp.entity.response.CarOrderResponse;
import com.cargo.login.module.mvp.entity.response.LoginResponse;
import com.cargo.login.module.mvp.entity.response.PersonalInfoResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class LoginModel implements LoginContract.Model {

    private Context mContext;

    public LoginModel(Context context) {
        this.mContext = context;
    }


    /**
     * 获取短信验证码
     * @param mobile 手机号
     * @return
     */
    @Override
    public Observable<BaseResponse> getVerifyCode(String mobile) {
        return RetrofitHelper.getInstance(mContext,"3")
                .getRetrofit()
                .create(LoginApi.class)
                .getVerifyCode(mobile)//调用相应的方法
                .subscribeOn(Schedulers.io())//请求时切换线程到io线程
                .observeOn(AndroidSchedulers.mainThread());//处理结果时切换到主线程(ui线程)
    }

    /**
     * 获取临时Token
     * @param request 手机号，验证码
     * @return
     */
    @Override
    public Observable<BaseResponse<LoginResponse>> getPreToken(String mobile,String verifyCode) {
        return RetrofitHelper.getInstance(mContext,"1")
                .getRetrofit()
                .create(LoginApi.class)
                .getPreToken(mobile,verifyCode)//调用相应的方法
                .subscribeOn(Schedulers.io())//请求时切换线程到io线程
                .observeOn(AndroidSchedulers.mainThread());//处理结果时切换到主线程(ui线程)
    }

    /**
     * 获取货代列表
     * @param mobile 手机号
     * @return
     */
    @Override
    public Observable<BaseResponse<List<HolderBean>>> getHolderList(String mobile) {
        return RetrofitHelper.getInstance(mContext)
                .getRetrofit()
                .create(LoginApi.class)
                .getHolderList(mobile)//调用相应的方法
                .subscribeOn(Schedulers.io())//请求时切换线程到io线程
                .observeOn(AndroidSchedulers.mainThread());//处理结果时切换到主线程(ui线程)
    }

    /**
     * 获取正式Token
     * @param mobile 手机号
     * @return
     */
    @Override
    public Observable<BaseResponse<LoginResponse>> getOfficialToken(String mobile) {
        return RetrofitHelper.getInstance(mContext,"2")
                .getRetrofit()
                .create(LoginApi.class)
                .getOfficialToken(mobile)//调用相应的方法
                .subscribeOn(Schedulers.io())//请求时切换线程到io线程
                .observeOn(AndroidSchedulers.mainThread());//处理结果时切换到主线程(ui线程)
    }
}
