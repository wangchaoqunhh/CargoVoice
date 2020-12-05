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
import com.alibaba.android.arouter.facade.annotation.Route
import com.cargo.basecommon.base.BaseApplication
import com.cargo.basecommon.base.BaseFragmentActivity
import com.cargo.basecommon.bean.EnquiryAddGoalRequest
import com.cargo.basecommon.utils.AirToast
import com.cargo.basecommon.utils.LocalJsonResolutionUtils
import com.cargo.basecommon.view.PagingView
import com.lwb.cargovoice.R
import com.lwb.cargovoice.adapter.BookingSpaceListAdapter
import com.lwb.cargovoice.constant.Constant.Enquiry_BookingSpace
import com.lwb.cargovoice.module.mvp.contract.BookingSpaceListContract
import com.lwb.cargovoice.module.mvp.entity.response.BookingSpaceListResponse
import com.lwb.cargovoice.module.mvp.presenter.BookingSpaceListPresenter
import com.lwb.cargovoice.utils.ScreenUtil
import com.lwb.cargovoice.view.BottomCarriageDialog
import com.lwb.cargovoice.view.BottomTimeDialog
import kotlinx.android.synthetic.main.cargovoice_activity_booking_space_list.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

@Route(path = "/cargovoice/BookingSpaceListActivity")
class BookingSpaceListActivity : BaseFragmentActivity<BookingSpaceListContract.View?, BookingSpaceListPresenter?>(), BookingSpaceListContract.View {
    lateinit var mRequest: EnquiryAddGoalRequest

    private var mPagingView: PagingView<BookingSpaceListResponse>? = null
    private val list = arrayListOf<BookingSpaceListResponse>()
    private val adapter = BookingSpaceListAdapter(R.layout.cargovoice_item_booking_space_list, list)

    private var bottomCarriageDialog: BottomCarriageDialog? = null

    override fun init() {
        //注册EventBus
        EventBus.getDefault().register(this)
        initData()
        initListener()
    }

    private fun initListener() {
        adapter.setOnItemClickListener { adapter, view, position ->
            BookingSpaceDetailsActivity.launchActivity(mContext, list.get(position).id)
        }

        item_add.setOnClickListener {
            bottomCarriageDialog = BottomCarriageDialog().setBack(object : BottomCarriageDialog.Back {
                // 1 海 2空
                override fun onBackMode(haiOrKong: String?) {
                    val json = LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "transportMode（运输方式）.json", if (haiOrKong == "1") "SEA" else "AIR")
                    mRequest.businessInfo.transportModeCode = json.code
                    mRequest.businessInfo.transportModeDesc = json.nameCn
                }

                override fun onOrigin() {
                    // 跳添加起飞页
                    AddOriginActivity.launchActivity(this@BookingSpaceListActivity, 1000)
                }

                override fun onTime() {
                    if (TextUtils.isEmpty(mRequest.businessInfo.transportModeCode)) {
                        AirToast.showToast("请选择运输方式")
                        return
                    }
                    if (TextUtils.isEmpty(mRequest.locationInfo.portOfOriginName)) {
                        AirToast.showToast("请选择起运岗和目的岗")
                        return
                    }
                    val dialog = BottomTimeDialog
                            .backSelectTime {
                                mRequest.locationInfo.departureDate = it?.year + "-" + it?.month + "-" + it?.day
                                bottomCarriageDialog?.tvETDTime?.setText(it?.year + "年" + it?.month + "月" + it?.day + "日 " + it?.week)
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

    private fun initData() {
        //将添加状态 设置成 订舱的
        Enquiry_BookingSpace = 2

        //当点 添加按钮 时候，实例化 大对象 ，用来给后台传
        EnquiryAddGoalRequest.newInstance(application as BaseApplication)
        mRequest = (application as BaseApplication).request

        //mPagingView 脚布局
        val footView = LayoutInflater.from(mContext).inflate(R.layout.cargovoice_pagingview_foot_layout, null)
        footView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtil.dp2px(mContext, 104F))
        mPagingView = findViewById(R.id.paging_view)
        mPagingView
                ?.setAdapter(adapter)
                ?.setOnRequest { mPresenter?.bookingSpaceList(mPagingView, mPagingView?.pageNumber!!, mPagingView?.pageSize!!) }
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

    override fun initPresenter() {
        mPresenter = BookingSpaceListPresenter(mContext)
    }

    override fun setLayout(): Int = R.layout.cargovoice_activity_booking_space_list

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

    override fun onSuccess(response: MutableList<BookingSpaceListResponse>?) {
        mPagingView?.onSuccess(response)
    }

    override fun onSuccess(response: BookingSpaceListResponse) {}

    override fun onError(result: String) {}

    @Subscribe
    fun resultEventBus(result: String?) {
        if (result == "1") {
            mPagingView?.newRequest()
        }
    }

    companion object {
        fun launchActivity(context: Context) {
            val intent = Intent(context, BookingSpaceListActivity::class.java)
            context.startActivity(intent)
        }
    }
}