package com.cargo.basecommon.http;

import android.content.Context;

import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 用于retrofit初始化
 */
public class RetrofitHelper {

    private Context mContext;
    OkHttpClient okHttpClient;
    GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder().create());
    //单例 RetrofitHelper
    private static RetrofitHelper retrofitHelper = null;
    private Retrofit mRetrofit = null;
    private String headType;

    private RetrofitHelper(Context context, String headType) {
        mContext = context;
        this.headType = headType;
        init();
    }


    /**
     * retrofit创建
     */
    private void init() {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new RetrofitLogInterceptor(mContext, headType))
                .build();
        //http://39.106.225.64:9095/
        //http://101.200.62.57:9900/
        //http://60.205.223.142:9900/

        //https://cvv.enosome.com/
        String url = "https://cvv.enosome.com:9111/";
        mRetrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static RetrofitHelper getInstance(Context context) {
        if (retrofitHelper == null) {
            retrofitHelper = new RetrofitHelper(context, "");
        } else if (!"".equals(retrofitHelper.headType)) {
            retrofitHelper = null;
            retrofitHelper = new RetrofitHelper(context, "");
        }
        return retrofitHelper;
    }

    /**
     *
     * @param context
     * @param headType 1:获取临时Token   2:获取正式Token
     * @return
     */
    public static RetrofitHelper getInstance(Context context, String headType) {
        if (retrofitHelper == null) {
            retrofitHelper = new RetrofitHelper(context, headType);
        } else if (!headType.equals(retrofitHelper.headType)) {
            retrofitHelper = null;
            retrofitHelper = new RetrofitHelper(context, headType);
        }
        return retrofitHelper;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

}
