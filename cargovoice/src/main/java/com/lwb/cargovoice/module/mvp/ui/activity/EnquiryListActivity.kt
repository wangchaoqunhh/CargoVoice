package com.lwb.cargovoice.module.mvp.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import butterknife.BindView
import com.alibaba.android.arouter.facade.annotation.Route
import com.cargo.basecommon.base.BaseApplication
import com.cargo.basecommon.base.BaseFragmentActivity
import com.cargo.basecommon.bean.EnquiryAddGoalRequest
import com.cargo.basecommon.utils.AirToast
import com.cargo.basecommon.utils.LocalJsonResolutionUtils.getGsonBeanByFileNameCode
import com.cargo.basecommon.view.PagingView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.lwb.cargovoice.R
import com.lwb.cargovoice.R2
import com.lwb.cargovoice.adapter.EnquiryListAdapter
import com.lwb.cargovoice.constant.Constant.Enquiry_BookingSpace
import com.lwb.cargovoice.module.mvp.contract.EnquiryListContract
import com.lwb.cargovoice.module.mvp.entity.response.EnquiryListResponse
import com.lwb.cargovoice.module.mvp.presenter.EnquiryListPresenter
import com.lwb.cargovoice.module.mvp.ui.activity.EnquiryDetailsActivity.Companion.launchActivity
import com.lwb.cargovoice.utils.ScreenUtil
import com.lwb.cargovoice.view.AddSuccessDialog
import com.lwb.cargovoice.view.BottomCarriageDialog
import com.lwb.cargovoice.view.BottomTimeDialog
import kotlinx.android.synthetic.main.cargovoice_activity_enquiry_list.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * 我的询价 列表
 */
@Route(path = "/cargovoice/EnquiryListActivity")
class EnquiryListActivity : BaseFragmentActivity<EnquiryListContract.View?, EnquiryListPresenter?>(), EnquiryListContract.View {
    //提交时候弹窗
    private var mAddSuccessDialog: AddSuccessDialog? = null

    @JvmField
    @BindView(R2.id.paging_view)
    var mPagingView: PagingView<EnquiryListResponse>? = null

    lateinit var mRequest: EnquiryAddGoalRequest

    private var enquiryListAdapter: EnquiryListAdapter? = null
    private val list: List<EnquiryListResponse?>? = arrayListOf()

    private var bottomCarriageDialog: BottomCarriageDialog? = null

    override fun setLayout(): Int {
        return R.layout.cargovoice_activity_enquiry_list
    }

    override fun init() {
        //注册EventBus
        EventBus.getDefault().register(this)
        initData()
        initListener()
    }

    private fun initData() {
        //将添加状态 设置成 询价的
        Enquiry_BookingSpace = 1
        enquiryListAdapter = EnquiryListAdapter(R.layout.cargovoice_item_enquiry_list, list)

        //当点 添加按钮 时候，实例化 大对象 ，用来给后台传
        EnquiryAddGoalRequest.newInstance(application as BaseApplication)
        mRequest = (application as BaseApplication).request

        //mPagingView 脚布局
        val footView = LayoutInflater.from(mContext).inflate(R.layout.cargovoice_pagingview_foot_layout, null)
        footView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtil.dp2px(mContext, 104F))

        mPagingView
                ?.setAdapter(enquiryListAdapter)
                ?.setOnRequest { mPresenter?.enquiryList(mPagingView, mPagingView?.pageNumber!!, mPagingView?.pageSize!!) }
                ?.setFootView(footView)
        mPagingView?.startRequest()

        webView?.let { webView ->
            webView.loadUrl("file:///android_asset/dist/index.html")
            val settings: WebSettings = webView.getSettings()
            settings.javaScriptEnabled = true
            webView.setBackgroundColor(0)
            webView.background = ContextCompat.getDrawable(mContext, R.color.c101)
            webView.background.alpha = 0
            webView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    view.loadUrl(url)
                    return false
                }
            }
            webView.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView, url: String) {
                    super.onPageFinished(view, url)
                    webView.setVisibility(View.VISIBLE)
                }
            }
        }

    }

    private fun initListener() {
        enquiryListAdapter?.setOnItemClickListener { adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int ->
            launchActivity(mContext, list?.get(position)?.id)
        }

        //一键订舱按钮
        enquiryListAdapter?.setOnItemChildClickListener { adapter, view, position ->
            if (view.id == R.id.tv_booking) {
                mPresenter?.oneTouch(list?.get(position)?.id)
                //点提交按钮 出弹窗
                mAddSuccessDialog = AddSuccessDialog()
                        .setCountDownFinishListener {
                            mPagingView?.newRequest()
                        }
                mAddSuccessDialog?.show(supportFragmentManager, "")
            }
        }

        item_add?.setOnClickListener {
            bottomCarriageDialog = BottomCarriageDialog()
            bottomCarriageDialog?.setBack(object : BottomCarriageDialog.Back {
                // 1 海 2空
                override fun onBackMode(haiOrKong: String?) {
                    val json = getGsonBeanByFileNameCode(mContext, "transportMode（运输方式）.json", if (haiOrKong == "1") "SEA" else "AIR")
                    mRequest.businessInfo.transportModeCode = json.code
                    mRequest.businessInfo.transportModeDesc = json.nameCn
                }

                override fun onOrigin() {
                    // 跳添加起飞页
                    AddOriginActivity.launchActivity(this@EnquiryListActivity, 1000)
                }

                override fun onTime() {
                    if (TextUtils.isEmpty(mRequest.businessInfo.transportModeCode)) {
                        AirToast.showToast(getString(R.string.please_select_shipping_method))
                        return
                    }
                    if (TextUtils.isEmpty(mRequest.locationInfo.portOfOriginName)) {
                        AirToast.showToast(getString(R.string.please_select_starting_post_and_destination_post))
                        return
                    }
                    val dialog = BottomTimeDialog
                            .backSelectTime {
                                mRequest.locationInfo.departureDate = it?.year + "-" + it?.month + "-" + it?.day
                                bottomCarriageDialog?.tvETDTime?.setText(it?.year + getString(R.string.year) + it?.month + getString(R.string.month) + it?.day + getString(R.string.day) + it?.week)
                                //去大表单页
                                EnquiryAddGoalActivity.launchActivity(mContext)
                                bottomCarriageDialog?.dismiss()
                            }
                    dialog.show(supportFragmentManager, "")
                }

            })
            bottomCarriageDialog?.show(supportFragmentManager, "底部小弹窗")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1000) {
                bottomCarriageDialog?.setOrigin(mRequest.locationInfo.portOfOriginName + "(" + mRequest.locationInfo.portOfOriginCode + ")")
                bottomCarriageDialog?.setGoal(mRequest.locationInfo.portOfDestinationName + "(" + mRequest.locationInfo.portOfDestinationCode + ")")
            }
        }
    }

    override fun initPresenter() {
        mPresenter = EnquiryListPresenter(mContext)
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        super.onDestroy()
//        destroyWebView()
    }

//    private fun destroyWebView() {
//        rl_webview_parent.removeAllViews()
//        if (webView != null) {
//            webView?.let { webView ->
//                webView.clearHistory()
//                webView.clearCache(true)
//                webView.loadUrl("about:blank") // clearView() should be changed to loadUrl("about:blank"), since clearView() is deprecated now
//                webView.freeMemory()
//                webView.pauseTimers()
//                this.webView = null // Note that mWebView.destroy() and mWebView = null do the exact same thing
//            }
//        }
//    }

    override fun onSuccess(responses: List<EnquiryListResponse>?) {
        mPagingView?.onSuccess(responses)
    }

    override fun onSuccess(enquiryListResponse: EnquiryListResponse?) {

    }

    override fun onError(result: String?) {
        result?.let {
            AirToast.showToast(result)
        }
    }

    override fun oneTouch() {
        mAddSuccessDialog?.commitSucceed()
    }

    override fun oneTouchFail(mas: String?) {
        mAddSuccessDialog?.commitFail()
    }

    @Subscribe
    fun resultEventBus(result: String?) {
        if (result == "1") {
            mPagingView?.newRequest()
        }
    }

    companion object {
        fun launchActivity(context: Context) {
            val intent = Intent(context, EnquiryListActivity::class.java)
            context.startActivity(intent)
        }
    }
}