package com.cargo.login.module.mvp.ui.activity;


import android.os.Handler;
import android.os.Message;

import android.text.TextUtils;
import android.view.KeyEvent;

import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.cargo.basecommon.utils.ActivityCollector;
import com.cargo.basecommon.base.BaseActivity;
import com.cargo.basecommon.utils.AirToast;
import com.cargo.basecommon.utils.SPUtils;
import com.cargo.basecommon.view.CircleProgressbar;
import com.cargo.login.R;
import com.cargo.login.R2;
import com.cargo.login.module.mvp.contract.MainOrderContract;
import com.cargo.login.module.mvp.entity.response.CarOrderResponse;

import java.util.List;

import butterknife.BindView;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class SplashActivity extends BaseActivity{
 /*   @BindView(R2.id.skipView)
    CircleProgressbar skipView;
    private boolean isClick;
*/

    Handler han = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                onDegreeFinish();
            }
        }
    };
    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    protected void init() {
/*
        skipView.setOutLineColor(Color.TRANSPARENT);
        skipView.setInCircleColor(ContextCompat.getColor(mContext,R.color.black33));
        skipView.setProgressColor(ContextCompat.getColor(mContext,R.color.colorAccent));
        skipView.setProgressLineWidth(5);
        skipView.setProgressType(CircleProgressbar.ProgressType.COUNT);
        skipView.setTimeMillis(3000);
        skipView.reStart();

        skipView.setCountdownProgressListener(1, (what, progress) -> {
            if (what == 1 && progress == 100 && !isClick) {
                onDegreeFinish();
            }
        });

        skipView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClick = true;
                onDegreeFinish();
            }
        });
*/
        Message message = Message.obtain();
        message.what = 1;
        han.sendMessageDelayed(message, 2000);

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            ActivityCollector.finishAll();
            finish();
        }
    }

    private void onDegreeFinish() {
        if (!TextUtils.isEmpty((String) SPUtils.get(this, "token", ""))) {
            ARouter.getInstance()
                    .build("/login/MainActivity")
                    .navigation();
        } else {
            ARouter.getInstance()
                    .build("/login/LoginActivity")
                    .navigation();
        }
        finish();
    }


    @Override
    protected int setLayout() {
        return R.layout.activity_splash;
    }

}
