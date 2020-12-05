package com.lwb.cargovoice.module.mvp.contract;

import com.cargo.basecommon.base.BaseResponse;
import com.cargo.basecommon.base.IModel;
import com.cargo.basecommon.base.IView;
import com.lwb.cargovoice.module.mvp.entity.response.BillListResponse;

import java.util.List;

import io.reactivex.Observable;


public interface BillListContract {

    interface View extends IView<BillListResponse> {
        void onSuccess(List<BillListResponse> responses);
    }

    interface Model extends IModel {

        Observable<BaseResponse<List<BillListResponse>>> billList(int pageNumber, int pageSize);

        //s-012 账单列表 已结
        Observable<BaseResponse<List<BillListResponse>>> billClosedList(int pageNumber, int pageSize);

        //s-012 账单列表 未结
        Observable<BaseResponse<List<BillListResponse>>> billOpenList(int pageNumber, int pageSize);
    }
}

