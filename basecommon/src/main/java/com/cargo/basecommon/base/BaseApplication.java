package com.cargo.basecommon.base;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import com.alibaba.android.arouter.launcher.ARouter;
import com.cargo.basecommon.bean.EnquiryAddGoalRequest;
import com.cargo.basecommon.utils.AirToast;
import com.cargo.basecommon.utils.languageUtils.LanguageUtil;
import com.cargo.basecommon.utils.languageUtils.SpUtil;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

public class BaseApplication extends Application {
    private static Context context;
    /**
     * 添加我的询价 和 我的订舱 时用
     */
    private EnquiryAddGoalRequest mRequest;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        // 初始化路由
        initARouter();
        //初始化Toast
        AirToast.initToast(this);
        SpeechUtility.createUtility(BaseApplication.this, SpeechConstant.APPID + "=5f9eabdc");

        /**
         * 对于7.0以下，需要在Application创建的时候进行语言切换
         */
        String language = SpUtil.getInstance(this).getString(SpUtil.LANGUAGE);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            LanguageUtil.changeAppLanguage(this, language);
        }
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

    public static Context getContext() {
        return context;
    }
}
