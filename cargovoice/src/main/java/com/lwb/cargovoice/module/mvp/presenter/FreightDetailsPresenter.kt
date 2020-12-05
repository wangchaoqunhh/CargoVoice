package com.lwb.cargovoice.module.mvp.presenter

import android.content.Context
import android.content.Intent
import com.cargo.basecommon.base.BaseResponse
import com.cargo.basecommon.base.IPresenter
import com.cargo.basecommon.base.IView
import com.cargo.basecommon.base.RxJavaObserver
import com.lwb.cargovoice.module.mvp.contract.FreightDetailsContract
import com.lwb.cargovoice.module.mvp.entity.response.EnquiryDetailsResponse
import com.lwb.cargovoice.module.mvp.entity.response.FreightDetailsResponse
import com.lwb.cargovoice.module.mvp.model.FreightDetailsModel
import io.reactivex.disposables.CompositeDisposable

class FreightDetailsPresenter(private val mContext: Context) : IPresenter {
    private var mCompositeDisposable: CompositeDisposable? = null
    private var mView: FreightDetailsContract.View? = null
    private var mModel: FreightDetailsContract.Model? = null
    override fun onCreate() {
        //用来存放RxJava的订阅关系,请求结束时需要清理掉这个订阅,否则会产生内存泄漏
        mCompositeDisposable = CompositeDisposable()
        mModel = FreightDetailsModel(mContext)
    }

    override fun onStart() {}
    override fun onStop() {
        mCompositeDisposable!!.dispose()
    }

    override fun onPause() {}

    override fun attachView(view: IView<*>?) {
        mView = view as FreightDetailsContract.View?
    }

    override fun attachIncomingIntent(intent: Intent) {

    }

    fun freightDetails(id: String?) {
        mModel?.freightDetails(id)
                ?.subscribe(object : RxJavaObserver<BaseResponse<FreightDetailsResponse?>?>(mCompositeDisposable, mView) {
                    override fun onNext(response: BaseResponse<FreightDetailsResponse?>) {
                        if (response != null) {
                            if (response.respCode == 0) {
                                mView?.onSuccess(response.respData)
                            } else if (response.respCode == 401) {
                                response.respMsg
                            } else {
                            }
                        }
                    }
                })
    }
}