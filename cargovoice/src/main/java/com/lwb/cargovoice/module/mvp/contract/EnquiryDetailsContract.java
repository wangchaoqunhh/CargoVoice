package com.lwb.cargovoice.module.mvp.contract;

import com.cargo.basecommon.base.BaseResponse;
import com.cargo.basecommon.base.IModel;
import com.cargo.basecommon.base.IView;
import com.lwb.cargovoice.module.mvp.entity.request.OneTouchRequest;
import com.lwb.cargovoice.module.mvp.entity.response.EnquiryDetailsResponse;
import com.lwb.cargovoice.module.mvp.entity.response.OneTouchResponse;

import io.reactivex.Observable;


public interface EnquiryDetailsContract {

    interface View extends IView<EnquiryDetailsResponse> {
        void oneTouch(OneTouchResponse response);
    }

    interface Model extends IModel {
        Observable<BaseResponse<EnquiryDetailsResponse>> enquiryDetails(String id);

        Observable<OneTouchResponse> oneTouch(OneTouchRequest request);
    }
}

