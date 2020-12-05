package com.cargo.basecommon.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.cargo.basecommon.bean.EnquiryAddGoalRequest;
import com.cargo.basecommon.utils.AirToast;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

public class BaseApplication extends Application {

    /**
     * 添加我的询价 和 我的订舱 时用
     */
    private EnquiryAddGoalRequest mRequest;

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化路由
        initARouter();
        //初始化Toast
        AirToast.initToast(this);
        SpeechUtility.createUtility(BaseApplication.this, SpeechConstant.APPID + "=5f9eabdc");
    }

    private void initARouter() {
        ARouter.openLog();
        ARouter.openDebug();// 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.init(this);
    }

    public EnquiryAddGoalRequest getRequest() {
        return mRequest;
    }

    public void setRequest(EnquiryAddGoalRequest mRequest) {
        this.mRequest = mRequest;
    }
}
