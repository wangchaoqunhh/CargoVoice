package com.lwb.cargovoice.module.mvp.contract;

import com.cargo.basecommon.base.BaseResponse;
import com.cargo.basecommon.base.IModel;
import com.cargo.basecommon.base.IView;
import com.lwb.cargovoice.module.mvp.entity.response.SelectRegionBean;

import java.util.List;

import io.reactivex.Observable;


public interface SelectRegionContract {

    interface View extends IView<SelectRegionBean> {
        void onSuccess(List<SelectRegionBean> response,String type);
    }

    interface Model extends IModel {
        Observable<BaseResponse<List<SelectRegionBean>>> locationCountry();

        Observable<BaseResponse<List<SelectRegionBean>>> locationProvince(String countryCode);

        Observable<BaseResponse<List<SelectRegionBean>>> locationCity(String countryCode, String provinceCode);

    }
}

