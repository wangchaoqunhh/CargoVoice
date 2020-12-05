package com.cargo.disability;

import com.cargo.basecommon.base.BaseApplication;
import com.cargo.basecommon.utils.WechatUtils;

public class AppApplication extends BaseApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        //初始化微信
        WechatUtils.initWechat(this);
        //chushihu
//        SpeechUtility.createUtility(AppApplication.this, SpeechConstant.APPID + "=5f9eabdc");
    }


}
