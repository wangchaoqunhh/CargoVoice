package com.cargo.login.module.mvp.presenter;


import android.content.Context;
import android.content.Intent;

import com.alibaba.android.arouter.launcher.ARouter;
import com.cargo.basecommon.base.BaseResponse;
import com.cargo.basecommon.base.IPresenter;
import com.cargo.basecommon.base.IView;
import com.cargo.login.module.mvp.contract.LoginContract;
import com.cargo.login.module.mvp.entity.request.HolderBean;
import com.cargo.login.module.mvp.entity.request.LoginRequest;
import com.cargo.login.module.mvp.entity.response.LoginResponse;
import com.cargo.login.module.mvp.model.LoginModel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class LoginPresenter implements IPresenter {
    private Context mContext;
    private CompositeDisposable mCompositeDisposable;
    private LoginContract.View mView;

    public LoginPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate() {
        //用来存放RxJava的订阅关系,请求结束时需要清理掉这个订阅,否则会产生内存泄漏
        mCompositeDisposable = new CompositeDisposable();
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
        mView = (LoginContract.View) view;
    }


    public void getVerifyCode(String mobile) {
        new LoginModel(mContext)
                .getVerifyCode(mobile)
                .subscribe(new Observer<BaseResponse>() {//观察者模式订阅
                    @Override
                    public void onError(Throwable e) {//请求失败调用
                        e.printStackTrace();
                        mView.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseResponse response) {//请求成功调用
                        if (response != null) {
                            if (response.getRespCode() == 0){
                                mView.onSuccess(response);
                            }else if (response.getRespCode() == 401){
                                ARouter.getInstance()
                                        .build("/login/LoginActivity")
                                        .navigation();
                            }else{
                                mView.onFail(response.getRespMsg());
                            }
                        }
                    }
                });
    }

    public void getPreToken(String mobile, String verifyCode) {
       /* LoginRequest request = new LoginRequest();
        request.setMobile(mobile);
        request.setVerifyCode(verifyCode);*/
        new LoginModel(mContext)
                .getPreToken(mobile,verifyCode)
                .subscribe(new Observer<BaseResponse<LoginResponse>>() {//观察者模式订阅
                    @Override
                    public void onError(Throwable e) {//请求失败调用
                        e.printStackTrace();
                        mView.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseResponse<LoginResponse> response) {//请求成功调用
                        if (response != null) {
                            if (response.getRespCode() == 0){
                                mView.getPerTokenSuccess(response.getRespData().getAccess_token());
                            }else if (response.getRespCode() == 401){
                                ARouter.getInstance()
                                        .build("/login/LoginActivity")
                                        .navigation();
                            }else{
                                mView.onFail(response.getRespMsg());
                            }
                        }
                    }
                });
    }


    public void getHolderList(String mobile) {
        new LoginModel(mContext)
                .getHolderList(mobile)
                .subscribe(new Observer<BaseResponse<List<HolderBean>>>() {//观察者模式订阅
                    @Override
                    public void onError(Throwable e) {//请求失败调用
                        e.printStackTrace();
                        mView.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseResponse<List<HolderBean>> response) {//请求成功调用
                        if (response != null) {
                            if (response.getRespCode() == 0){
                                mView.getHolderListSuccess(response.getRespData());
                            }else if (response.getRespCode() == 401){
                                ARouter.getInstance()
                                        .build("/login/LoginActivity")
                                        .navigation();
                            }else{
                                mView.onFail(response.getRespMsg());
                            }
                        }
                    }
                });
    }

    public void getOfficialToken(String mobile) {
        new LoginModel(mContext)
                .getOfficialToken(mobile)
                .subscribe(new Observer<BaseResponse<LoginResponse>>() {//观察者模式订阅
                    @Override
                    public void onError(Throwable e) {//请求失败调用
                        e.printStackTrace();
                        mView.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BaseResponse<LoginResponse> response) {//请求成功调用
                        if (response != null) {
                            if (response.getRespCode() == 0){
                                mView.getOfficialTokenSuccess(response.getRespData().getAccess_token());
                            }else if (response.getRespCode() == 401){
                                ARouter.getInstance()
                                        .build("/login/LoginActivity")
                                        .navigation();
                            }else{
                                mView.onFail(response.getRespMsg());
                            }
                        }
                    }
                });
    }


    @Override
    public void attachIncomingIntent(Intent intent) {

    }
}
