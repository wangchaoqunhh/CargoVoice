package com.lwb.cargovoice.module.mvp.contract;

import com.cargo.basecommon.base.BaseResponse;
import com.cargo.basecommon.base.IModel;
import com.cargo.basecommon.base.IView;
import com.lwb.cargovoice.module.mvp.entity.response.FreightListResponse;

import java.util.List;

import io.reactivex.Observable;

public interface FreightListContract {

    interface View extends IView<FreightListResponse> {
        void onSuccess(List<FreightListResponse> responses);
    }

    interface Model extends IModel {

        Observable<BaseResponse<List<FreightListResponse>>> freightList(int pageNumber, int pageSize);

        Observable<BaseResponse<List<FreightListResponse>>> freightImportList(int pageNumber, int pageSize);

        Observable<BaseResponse<List<FreightListResponse>>> freightExportList(int pageNumber, int pageSize);

    }
}

