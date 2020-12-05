package com.cargo.login.module.mvp.contract;

import com.cargo.basecommon.base.BaseResponse;
import com.cargo.basecommon.base.IModel;
import com.cargo.basecommon.base.IView;
import com.cargo.login.module.mvp.entity.request.HolderBean;

import io.reactivex.Observable;
import retrofit2.http.Body;


public interface RegisterContract {

    interface View extends IView<BaseResponse> {
        void onResetSuccess();
    }

    interface Model extends IModel {

        Observable<BaseResponse> register(HolderBean request);
        Observable<BaseResponse> resetPwd(@Body HolderBean request);


    }
}
