package com.lwb.cargovoice.module.mvp.contract;

import com.cargo.basecommon.base.BaseResponse;
import com.cargo.basecommon.base.IModel;
import com.cargo.basecommon.base.IView;
import com.cargo.basecommon.bean.EnquiryAddGoalRequest;
import com.lwb.cargovoice.module.mvp.entity.response.EnquiryAddGoalResponse;

import io.reactivex.Observable;


public interface EnquiryAddGoalContract {

    interface View extends IView<EnquiryAddGoalResponse> {

    }

    interface Model extends IModel {
        Observable<BaseResponse<String>> enquiryCreate(EnquiryAddGoalRequest request);

        Observable<BaseResponse<String>> bookingSpaceCreate(EnquiryAddGoalRequest request);

    }
}
