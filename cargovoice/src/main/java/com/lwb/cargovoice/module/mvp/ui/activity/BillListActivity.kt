package com.lwb.cargovoice.module.mvp.ui.activity

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.alibaba.android.arouter.facade.annotation.Route
import com.cargo.basecommon.base.BaseActivity
import com.cargo.basecommon.view.PagingView
import com.lwb.cargovoice.R
import com.lwb.cargovoice.adapter.BillListAdapter
import com.lwb.cargovoice.module.mvp.contract.BillListContract
import com.lwb.cargovoice.module.mvp.entity.response.BillListResponse
import com.lwb.cargovoice.module.mvp.presenter.BillListPresenter
import com.lwb.cargovoice.utils.ScreenUtil
import kotlinx.android.synthetic.main.cargovoice_activity_bill_list.*

@Route(path = "/cargovoice/BillListActivity")
class BillListActivity : BaseActivity<BillListContract.View?, BillListPresenter?>(), BillListContract.View {

    private lateinit var mPagingView: PagingView<BillListResponse>
    private val list = arrayListOf<BillListResponse>()
    private val billListAdapter = BillListAdapter(R.layout.cargovoice_item_bill_list, list)

    /**
     * 0
     * 1 未结
     * 2 已结
     */
    private var type = 0

    override fun init() {
        initData()
        initListener()
    }

    private fun initListener() {
        tv_unsettled_account.setOnClickListener {
            tv_unsettled_account.isSelected = !tv_unsettled_account.isSelected
            tv_closed_account.isSelected = false
            type = if (tv_unsettled_account.isSelected) 1 else 0
            mPagingView?.newRequest()
        }
        tv_closed_account.setOnClickListener {
            tv_unsettled_account.isSelected = false
            tv_closed_account.isSelected = !tv_closed_account.isSelected
            type = if (tv_closed_account.isSelected) 2 else 0
            mPagingView?.newRequest()
        }
    }

    private fun initData() {
        //mPagingView 脚布局
        val footView = LayoutInflater.from(mContext).inflate(R.layout.cargovoice_pagingview_foot_layout, null)
        footView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtil.dp2px(mContext, 104F))
        mPagingView = findViewById(R.id.paging_view)
        mPagingView
                ?.setAdapter(billListAdapter)
                ?.setOnRequest { mPresenter?.billList(mPagingView, mPagingView!!.pageNumber, mPagingView!!.pageSize, type) }
                ?.setFootView(footView)
        mPagingView?.startRequest()

        //初始化webView
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
        mPresenter = BillListPresenter(mContext)
    }

    override fun setLayout(): Int {
        return R.layout.cargovoice_activity_bill_list
    }

    override fun onDestroy() {
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

    override fun onSuccess(response: BillListResponse) {}

    override fun onError(result: String) {}

    override fun onSuccess(responses: List<BillListResponse>?) {
        mPagingView?.onSuccess(responses)
        //未结总计
        var num = 0.00
        list?.forEach {
            num += it.outstandingAmount?.toDouble()!!
        }
        tv_prices.text = num.toString()
    }

    companion object {
        fun launchActivity(context: Context) {
            val intent = Intent(context, BillListActivity::class.java)
            context.startActivity(intent)
        }
    }
}