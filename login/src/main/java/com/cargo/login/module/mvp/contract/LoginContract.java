package com.cargo.login.module.mvp.contract;

import com.cargo.basecommon.base.BaseResponse;
import com.cargo.basecommon.base.IModel;
import com.cargo.basecommon.base.IView;
import com.cargo.login.module.mvp.entity.request.HolderBean;
import com.cargo.login.module.mvp.entity.request.LoginRequest;
import com.cargo.login.module.mvp.entity.response.CarOrderResponse;
import com.cargo.login.module.mvp.entity.response.LoginResponse;
import com.cargo.login.module.mvp.entity.response.PersonalInfoResponse;

import java.util.List;

import io.reactivex.Observable;


public interface LoginContract {

    interface View extends IView<BaseResponse> {
        void onFail(String s);
        void getPerTokenSuccess(String token);
        void getHolderListSuccess(List<HolderBean> holderList);
        void getOfficialTokenSuccess(String token);
    }

    interface Model extends IModel {

        /**
         * 获取短信验证码
         * @param mobile
         * @return
         */
        Observable<BaseResponse> getVerifyCode(String mobile);

        /**
         * 获取临时Token
         * @return
         */
        Observable<BaseResponse<LoginResponse>> getPreToken(String mobile,String verifyCode);

        /**
         * 获取货代列表
         * @param mobile 手机号
         * @return
         */
        Observable<BaseResponse<List<HolderBean>>> getHolderList(String mobile);

        /**
         * 获取正式Token
         * @param mobile 手机号
         * @return
         */
        Observable<BaseResponse<LoginResponse>> getOfficialToken(String mobile);


    }
}
