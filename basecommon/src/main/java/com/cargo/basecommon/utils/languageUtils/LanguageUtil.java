package com.cargo.basecommon.utils.languageUtils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;

import com.cargo.basecommon.R;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.Locale;

import static com.cargo.basecommon.constant.Constant.isEnglist;

/**
 * Created by dumingwei on 2018/5/31 0031.
 */
public class LanguageUtil {

    private static final String TAG = "LanguageUtil";

    /**
     * @param context
     * @param newLanguage 想要切换的语言类型 比如 "en" ,"zh"
     */
    @SuppressWarnings("deprecation")
    public static void changeAppLanguage(Context context, String newLanguage) {
        if (TextUtils.isEmpty(newLanguage)) {
            return;
        }
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        //获取想要切换的语言类型
        Locale locale = getLocaleByLanguage(newLanguage);
        configuration.setLocale(locale);
        // updateConfiguration
        DisplayMetrics dm = resources.getDisplayMetrics();
        resources.updateConfiguration(configuration, dm);
    }

    public static Locale getLocaleByLanguage(String language) {
        Locale locale = Locale.SIMPLIFIED_CHINESE;
        if (language.equals(LanguageType.CHINESE.getLanguage())) {
            locale = Locale.SIMPLIFIED_CHINESE;
        } else if (language.equals(LanguageType.ENGLISH.getLanguage())) {
            locale = Locale.ENGLISH;
        } else if (language.equals(LanguageType.THAILAND.getLanguage())) {
            locale = Locale.forLanguageTag(language);
        }
        Log.d(TAG, "getLocaleByLanguage: " + locale.getDisplayName());
        return locale;
    }

    public static Context attachBaseContext(Context context, String language) {
//        Log.d(TAG, "attachBaseContext: " + Build.VERSION.SDK_INT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResources(context, language);
        } else {
            return context;
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static Context updateResources(Context context, String language) {
        Resources resources = context.getResources();
        Locale locale = LanguageUtil.getLocaleByLanguage(language);

        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        configuration.setLocales(new LocaleList(locale));
        return context.createConfigurationContext(configuration);
    }

    public static void changeLanguage(Context context, String language) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            LanguageUtil.changeAppLanguage(context, language);
        }
        SpUtil.getInstance(context).putString(SpUtil.LANGUAGE, language);
    }

    public static void updateData(Context context,String language){
        if (language.equals("ch")) {
            isEnglist = false;
        } else if (language.equals("en")) {
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
