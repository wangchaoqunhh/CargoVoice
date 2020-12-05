package com.lwb.cargovoice.module.mvp.contract;

import com.cargo.basecommon.base.BaseResponse;
import com.cargo.basecommon.base.IModel;
import com.cargo.basecommon.base.IView;
import com.lwb.cargovoice.module.mvp.entity.response.BookingSpaceDetailsResponse;

import io.reactivex.Observable;


public interface BookingSpaceDetailsContract {

    interface View extends IView<BookingSpaceDetailsResponse> {
    }

    interface Model extends IModel {
        Observable<BaseResponse<BookingSpaceDetailsResponse>> bookingSpaceDetails(String id);

    }
}

