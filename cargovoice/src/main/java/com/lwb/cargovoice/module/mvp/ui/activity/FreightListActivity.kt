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
import com.lwb.cargovoice.adapter.FreightListAdapter
import com.lwb.cargovoice.module.mvp.contract.FreightListContract
import com.lwb.cargovoice.module.mvp.entity.response.FreightListResponse
import com.lwb.cargovoice.module.mvp.presenter.FreightListPresenter
import com.lwb.cargovoice.utils.ScreenUtil
import kotlinx.android.synthetic.main.cargovoice_activity_freight_list.*

/**
 * 我的货运列表
 */
@Route(path = "/cargovoice/FreightListActivity")
class FreightListActivity : BaseActivity<FreightListContract.View?, FreightListPresenter?>(), FreightListContract.View {

    private var mPagingView: PagingView<FreightListResponse>? = null
    val list = arrayListOf<FreightListResponse>()
    val adapter = FreightListAdapter(R.layout.cargovoice_item_freight_list, list)

    /**
     * 0 全部(没有全部了)
     * 1 进口
     * 2 出口
     */
    var type: Int = 2

    override fun init() {
        initData()
        initListener()
    }

    private fun initListener() {
        adapter.setOnItemClickListener { adapter, view, position ->
            FreightDetailsActivity.launchActivity(mContext, list.get(position).id)
        }

        tv_exit.setOnClickListener {
            tv_exit.isSelected = true
            tv_import.isSelected = false
            type =  2
            mPagingView?.newRequest()
        }

        tv_import.setOnClickListener {
            tv_exit.isSelected = false
            tv_import.isSelected = true
            type =  1
            mPagingView?.newRequest()
        }
    }

    private fun initData() {
        //默认选中出口
        tv_exit.isSelected = true
        //mPagingView 脚布局
        val footView = LayoutInflater.from(mContext).inflate(R.layout.cargovoice_pagingview_foot_layout, null)
        footView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtil.dp2px(mContext, 104F))
        mPagingView = findViewById(R.id.paging_view)
        mPagingView
                ?.setAdapter(adapter)
                ?.setOnRequest { mPresenter?.freightList(mPagingView, mPagingView?.pageNumber!!, mPagingView?.pageSize!!, type) }
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
        mPresenter = FreightListPresenter(mContext)
    }

    override fun setLayout(): Int = R.layout.cargovoice_activity_freight_list

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

    override fun onSuccess(response: FreightListResponse?) {
    }

    override fun onError(result: String) {}

    override fun onSuccess(responses: List<FreightListResponse>?) {
        mPagingView?.onSuccess(responses)
    }

    companion object {
        fun launchActivity(context: Context) {
            val intent = Intent(context, FreightListActivity::class.java)
            context.startActivity(intent)
        }
    }
}