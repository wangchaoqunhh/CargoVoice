package com.lwb.cargovoice.module.mvp.contract;

import com.cargo.basecommon.base.BaseResponse;
import com.cargo.basecommon.base.IModel;
import com.cargo.basecommon.base.IView;
import com.lwb.cargovoice.module.mvp.entity.request.AIRequest;
import com.lwb.cargovoice.module.mvp.entity.response.AIResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;


public interface AIContract {

    interface View extends IView<List<AIResponse>> {
        void onFail(String s);

    }

    interface Model extends IModel {

        /**
         * @return
         */
        Observable<List<AIResponse>> aiHook(AIRequest request);

    }
}
