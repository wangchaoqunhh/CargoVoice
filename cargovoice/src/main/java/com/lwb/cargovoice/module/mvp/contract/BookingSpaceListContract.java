package com.lwb.cargovoice.module.mvp.contract;

import com.cargo.basecommon.base.BaseResponse;
import com.cargo.basecommon.base.IModel;
import com.cargo.basecommon.base.IView;
import com.lwb.cargovoice.module.mvp.entity.response.BookingSpaceListResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Query;


public interface BookingSpaceListContract {

    interface View extends IView<BookingSpaceListResponse> {
        void onSuccess(List<BookingSpaceListResponse> response);
    }

    interface Model extends IModel {
        Observable<BaseResponse<List<BookingSpaceListResponse>>> bookingSpaceList(int pageNumber, int pageSize);

    }
}

