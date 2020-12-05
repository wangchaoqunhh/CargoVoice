package com.cargo.login.module.mvp.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.cargo.basecommon.base.BaseFragmentActivity;
import com.cargo.basecommon.base.BaseResponse;
import com.cargo.basecommon.utils.AirToast;
import com.cargo.basecommon.utils.InputMethodUtil;
import com.cargo.basecommon.utils.MatchUtil;
import com.cargo.basecommon.utils.SPUtils;
import com.cargo.login.R;
import com.cargo.login.R2;
import com.cargo.login.module.mvp.contract.LoginContract;
import com.cargo.login.module.mvp.entity.request.HolderBean;
import com.cargo.login.module.mvp.presenter.LoginPresenter;
import com.cargo.login.module.mvp.ui.fragment.LogoutDialogFragment;
import com.cargo.login.view.BottomHolderDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zhouwei.mzbanner.MZBannerView.dpToPx;

@Route(path = "/login/MainActivity")
public class MainActivity extends BaseFragmentActivity<LoginContract.View, LoginPresenter> implements LoginContract.View {

    @BindView(R2.id.iv_drop_main)
    ImageView ivDrop;
    @BindView(R2.id.ll_holder_name)
    LinearLayout llName;
    @BindView(R2.id.tv_name)
    TextView tvName;
    @BindView(R2.id.tv_time)
    TextView tvTime;
    @BindView(R2.id.tv_apm)
    TextView tvApm;
    @BindView(R2.id.tv_date)
    TextView tvDate;
    @BindView(R2.id.wv)
    WebView webView;
    @BindView(R2.id.rl_time)
    RelativeLayout rlTime;
    @BindView(R2.id.ll_button)
    LinearLayout llButton;
    @BindView(R2.id.ll_button2)
    LinearLayout llButton2;
    @BindView(R2.id.iv_bg)
    ImageView ivBg;
    private String tel;


    @Override
    protected void init() {
        webView.loadUrl("file:///android_asset/dist/index.html");
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setBackgroundColor(0);
        webView.getBackground().setAlpha(0);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webView.setVisibility(View.VISIBLE);
            }
        });
        Glide.with(mContext)
                .load(ContextCompat.getDrawable(mContext, R.mipmap.ic_main_bg))
                .apply(new RequestOptions()
                        .transforms(new CenterCrop(), new RoundedCorners(dpToPx(12))))
                .into(ivBg);
        tel = (String) SPUtils.get(mContext, "tel", "");
        tvName.setText((String) SPUtils.get(mContext, "clientName", ""));
        mPresenter.getHolderList(tel);
        findViewById(R.id.iv_power).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogoutDialogFragment logoutDialogFragment = LogoutDialogFragment.newInstance();
                logoutDialogFragment.show(getSupportFragmentManager(), "dialog");
                ;
            }
        });

        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        int heightPixels = outMetrics.heightPixels - dpToPx(225);
        //飞机图片32%
        RelativeLayout.LayoutParams rlTimeParams = (RelativeLayout.LayoutParams) rlTime.getLayoutParams();
        rlTimeParams.height = (int) (heightPixels * 0.32);
        rlTime.setLayoutParams(rlTimeParams);
        //公司名称12%
        RelativeLayout.LayoutParams llHolderNameParams = (RelativeLayout.LayoutParams) llName.getLayoutParams();
        llHolderNameParams.height = (int) (heightPixels * 0.12);
        llName.setLayoutParams(llHolderNameParams);
        //按钮栏① 24%
        RelativeLayout.LayoutParams llButton1Params = (RelativeLayout.LayoutParams) llButton.getLayoutParams();
        llButton1Params.height = (int) (heightPixels * 0.24);
        llButton.setLayoutParams(llButton1Params);
        //按钮栏② 24%
        RelativeLayout.LayoutParams llButton2Params = (RelativeLayout.LayoutParams) llButton2.getLayoutParams();
        llButton2Params.height = (int) (heightPixels * 0.24);
        llButton2.setLayoutParams(llButton2Params);

    }

    private void initTime() {
        Calendar calendar = Calendar.getInstance();
        //月
        int month = calendar.get(Calendar.MONTH) + 1;
        //日
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        //获取系统时间
        //小时
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        String hourStr;
        if (hour < 10) {
            hourStr = "0" + hour;
        } else {
            hourStr = hour + "";
        }
        //分钟
        int minute = calendar.get(Calendar.MINUTE);
        String minuteStr;
        if (minute < 10) {
            minuteStr = "0" + minute;
        } else {
            minuteStr = minute + "";
        }
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        String weekStr = "";
        switch (week) {
            case 1:
                weekStr = "日";
                break;
            case 2:
                weekStr = "一";
                break;
            case 3:
                weekStr = "二";
                break;
            case 4:
                weekStr = "三";
                break;
            case 5:
                weekStr = "四";
                break;
            case 6:
                weekStr = "五";
                break;
            case 7:
                weekStr = "六";
                break;


        }
        tvTime.setText(hourStr + ":" + minuteStr);
        //上下午
        if (Calendar.AM == calendar.get(Calendar.AM_PM)) {
            tvApm.setText("AM");
        } else {
            tvApm.setText("PM");
        }
        tvDate.setText(month + "月" + day + "日 星期" + weekStr);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initTime();
    }

    @Override
    protected void initPresenter() {
        mPresenter = new LoginPresenter(mContext);
    }

    @Override
    protected int setLayout() {
        return R.layout.login_activity_main;
    }

    @Override
    public void onFail(String s) {

    }

    @Override
    public void getPerTokenSuccess(String token) {

    }

    @Override
    public void getHolderListSuccess(List<HolderBean> holderList) {
        if (holderList.size() == 1) {
            ivDrop.setVisibility(View.GONE);
        } else {
            ivDrop.setVisibility(View.VISIBLE);
            llName.setOnClickListener(v -> {
                //弹出公司选择弹窗
                BottomHolderDialog dialog = BottomHolderDialog.getInstant(holderList);
                dialog.setOnHolderClickListener((holderBean, position) -> {
                    SPUtils.put(mContext, "clientId", holderBean.getClientId());
                    SPUtils.put(mContext, "clientSecret", holderBean.getClientSecret());
                    SPUtils.put(mContext, "clientName", holderBean.getHolderName());
                    tvName.setText(holderBean.getHolderName());
                    mPresenter.getOfficialToken(tel);
                    dialog.dismiss();
                });
                dialog.show(getSupportFragmentManager(), "");
            });

        }
    }

    @Override
    public void getOfficialTokenSuccess(String token) {
        SPUtils.put(mContext, "token", token);
    }

    @Override
    public void onSuccess(BaseResponse baseResponse) {

    }

    @Override
    public void onError(String result) {

    }

    @OnClick({R2.id.ll_my_enquiry, R2.id.ll_my_booking_space, R2.id.ll_my_freight,
            R2.id.ll_fast_enquiry, R2.id.ll_fast_booking_space, R2.id.ll_bill_query, R2.id.cv_record})
    public void onViewClicked(View view) {
        int i = view.getId();
        //我的询价
        if (i == R.id.ll_my_enquiry) {
            ARouter.getInstance()
                    .build("/cargovoice/EnquiryListActivity")
                    .navigation();
        }
        //我的订舱
        if (i == R.id.ll_my_booking_space) {
            ARouter.getInstance()
                    .build("/cargovoice/BookingSpaceListActivity")
                    .navigation();
        }
        //我的货运
        if (i == R.id.ll_my_freight) {
            ARouter.getInstance()
                    .build("/cargovoice/FreightListActivity")
                    .navigation();
        }
        //快速询价
        if (i == R.id.ll_fast_enquiry) {
            ARouter.getInstance()
                    .build("/cargovoice/EnquiryAddGoalActivity")
                    .withInt("Enquiry_BookingSpace", 1)
                    .navigation();
        }
        //快速订舱
        if (i == R.id.ll_fast_booking_space) {
            ARouter.getInstance()
                    .build("/cargovoice/EnquiryAddGoalActivity")
                    .withInt("Enquiry_BookingSpace", 2)
                    .navigation();
        }
        //账单查询
        if (i == R.id.ll_bill_query) {
            ARouter.getInstance()
                    .build("/cargovoice/BillListActivity")
                    .navigation();
        }
        if (i == R.id.cv_record) {
            requestPermissions();
        }
    }

    /**
     * 获取权限
     */
    private void requestPermissions() {
        Log.e("李文博", "requestPermissions");
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                int permission = ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.RECORD_AUDIO);
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.RECORD_AUDIO}, 0x0010);
                }else{
                    ARouter.getInstance()
                            .build("/cargovoice/AIActivity")
                            .navigation();
                }
            }
        } catch (Exception e) {
            Log.e("李文博", e.toString());
        }
    }

    /**
     * 权限回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.e("李文博", "startListening");
            ARouter.getInstance()
                    .build("/cargovoice/AIActivity")
                    .navigation();
        } else {
            Log.e("李文博", "error权限缺失");
            AirToast.showToast("权限缺失");
//            finish();
        }
        return;
    }
}
