package com.lwb.cargovoice.module.mvp.contract;

import com.cargo.basecommon.base.BaseResponse;
import com.cargo.basecommon.base.IModel;
import com.cargo.basecommon.base.IView;
import com.lwb.cargovoice.module.mvp.entity.response.AddHarvestInfoResponse;

import java.util.List;

import io.reactivex.Observable;


public interface AddHarvestInfoContract {

    interface View extends IView<AddHarvestInfoResponse> {
        void organization(List<AddHarvestInfoResponse> responses);
    }

    interface Model extends IModel {

        Observable<BaseResponse<List<AddHarvestInfoResponse>>> organization();

        Observable<BaseResponse<List<AddHarvestInfoResponse>>> organizationConsignee();

    }
}

