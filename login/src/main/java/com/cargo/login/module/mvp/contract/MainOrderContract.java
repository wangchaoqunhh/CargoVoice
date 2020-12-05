package com.cargo.login.module.mvp.contract;

import com.cargo.basecommon.base.BaseResponse;
import com.cargo.basecommon.base.IModel;
import com.cargo.basecommon.base.IView;
import com.cargo.login.module.mvp.entity.response.CarOrderResponse;

import java.util.List;

import io.reactivex.Observable;


public interface MainOrderContract {

    interface View extends IView<List<CarOrderResponse>> {
    }

    interface Model extends IModel {

        Observable<BaseResponse<List<CarOrderResponse>>> getOrderList(String owner);

    }
}
