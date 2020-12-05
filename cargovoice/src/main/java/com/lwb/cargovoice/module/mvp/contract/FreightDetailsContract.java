package com.lwb.cargovoice.module.mvp.contract;

import com.cargo.basecommon.base.BaseResponse;
import com.cargo.basecommon.base.IModel;
import com.cargo.basecommon.base.IView;
import com.lwb.cargovoice.module.mvp.entity.response.FreightDetailsResponse;

import io.reactivex.Observable;


public interface FreightDetailsContract {

    interface View extends IView<FreightDetailsResponse> {
    }

    interface Model extends IModel {
        Observable<BaseResponse<FreightDetailsResponse>> freightDetails(String id);

    }
}

