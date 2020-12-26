package com.cargo.basecommon.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.util.DisplayMetrics;

import com.cargo.basecommon.R;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.Locale;

import static com.cargo.basecommon.constant.Constant.isEnglist;

public class SwitchLanguageUtil {

//               changeAppLanguage(Locale.SIMPLIFIED_CHINESE);
//                changeAppLanguage(Locale.TRADITIONAL_CHINESE);
//                changeAppLanguage(Locale.US);
//                changeAppLanguage(Locale.JAPAN);

    /**
     * @param locale 中文 Locale.SIMPLIFIED_CHINESE
     *               英文 Locale.US
     */
    public static void switchLanguage(Context context, Locale locale) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        Configuration configuration = context.getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
        } else {
            configuration.locale = locale;
        }
        context.getResources().updateConfiguration(configuration, metrics);

        if (locale == Locale.SIMPLIFIED_CHINESE) {
            isEnglist = false;
        } else if (locale == Locale.US) {
            isEnglist = true;
        }

        ClassicsHeader.REFRESH_HEADER_PULLDOWN = context.getString(R.string.pull_down_to_refresh);
        ClassicsHeader.REFRESH_HEADER_REFRESHING = context.getString(R.string.Refreshing);
        ClassicsHeader.REFRESH_HEADER_LOADING = context.getString(R.string.Loading);
        ClassicsHeader.REFRESH_HEADER_RELEASE = context.getString(R.string.RefreshImmediatelyAfterRelease);
        ClassicsHeader.REFRESH_HEADER_FINISH = context.getString(R.string.RefreshComplete);
        ClassicsHeader.REFRESH_HEADER_FAILED = context.getString(R.string.RefreshFailed);
        ClassicsHeader.REFRESH_HEADER_LASTTIME = isEnglist ? "'Last update' M-d HH:mm" : "上次更新 M-d HH:mm";

        ClassicsFooter.REFRESH_FOOTER_PULLUP = context.getString(R.string.PullUpToLoadMore);
        ClassicsFooter.REFRESH_FOOTER_RELEASE = context.getString(R.string.ReleaseAndLoadImmediately);
        ClassicsFooter.REFRESH_FOOTER_LOADING = context.getString(R.string.Loading);
        ClassicsFooter.REFRESH_FOOTER_REFRESHING = context.getString(R.string.Refreshing);
        ClassicsFooter.REFRESH_FOOTER_FINISH = context.getString(R.string.LoadingCompleted);
        ClassicsFooter.REFRESH_FOOTER_FAILED = context.getString(R.string.FailedToLoad);
        ClassicsFooter.REFRESH_FOOTER_ALLLOADED = context.getString(R.string.NoMoreData);
    }
}
