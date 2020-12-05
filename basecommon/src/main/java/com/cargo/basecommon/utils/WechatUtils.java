package com.cargo.basecommon.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WechatUtils {

    // APP_ID 替换为你的应用从官方网站申请到的合法appID
    private static  String APP_ID ;

    // IWXAPI 是第三方app和微信通信的openApi接口
    public static IWXAPI api;

    public static void initWechat(Context context) {

        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = null;

        try {
            packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            if (false){
                APP_ID = "wxf4314e92c8e87e51";
            }else{
                APP_ID = "wx284ff0508864a379";
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(context, APP_ID, true);

        // 将应用的appId注册到微信
        api.registerApp(APP_ID);

        //建议动态监听微信启动广播进行注册到微信
        context.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // 将该app注册到微信
                api.registerApp(APP_ID);
            }
        }, new IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP));


    }
}
