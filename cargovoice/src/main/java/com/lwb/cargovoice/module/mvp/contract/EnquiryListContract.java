package com.lwb.cargovoice.module.mvp.contract;

import com.cargo.basecommon.base.BaseResponse;
import com.cargo.basecommon.base.IModel;
import com.cargo.basecommon.base.IView;
import com.lwb.cargovoice.module.mvp.entity.request.OneTouchRequest;
import com.lwb.cargovoice.module.mvp.entity.response.EnquiryListResponse;
import com.lwb.cargovoice.module.mvp.entity.response.OneTouchResponse;

import java.util.List;

import io.reactivex.Observable;


public interface EnquiryListContract {

    interface View extends IView<EnquiryListResponse> {
        void onSuccess(List<EnquiryListResponse> responses);

        void oneTouch();

        void oneTouchFail(String mas);
    }

    interface Model extends IModel {
        Observable<BaseResponse<List<EnquiryListResponse>>> enquiryList(int pageNumber, int pageSize);

        Observable<OneTouchResponse> oneTouch(OneTouchRequest request);
    }
}
