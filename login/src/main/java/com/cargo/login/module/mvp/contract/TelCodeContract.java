package com.cargo.login.module.mvp.contract;

import com.cargo.basecommon.base.BaseResponse;
import com.cargo.basecommon.base.IModel;
import com.cargo.basecommon.base.IView;
import com.cargo.login.module.mvp.entity.request.GetTelCodeRequest;

import io.reactivex.Observable;


public interface TelCodeContract {

    interface View extends IView<BaseResponse> {
        void onGetCodeSuccess();
        void verificationFailed(String s);
    }

    interface Model extends IModel {

        Observable<BaseResponse> checkTelCode(GetTelCodeRequest request);

        Observable<BaseResponse> getTelCode(GetTelCodeRequest request);

    }
}
