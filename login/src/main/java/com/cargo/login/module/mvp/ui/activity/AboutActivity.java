package com.cargo.login.module.mvp.ui.activity;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cargo.basecommon.R;
import com.cargo.basecommon.R2;
import com.cargo.basecommon.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = "/basecommon/AboutActivity")
public class AboutActivity extends BaseActivity {
    @BindView(R2.id.webview)
    WebView webview;

    @Override
    protected void init() {
        //访问网页
        webview.loadUrl("https://www.enosome.com/agreement.html");
        //系统默认会通过手机浏览器打开网页，为了能够直接通过WebView显示网页，则必须设置
        webview.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            // 链接跳转都会走这个方法
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);// 强制在当前 WebView 中加载 url
                return true;
            }
        });
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected int setLayout() {
        return R.layout.activity_user_agreement;
    }


    @OnClick(R2.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
