package com.cargo.basecommon.http;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Base64;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.cargo.basecommon.utils.SPUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;


import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;


public class RetrofitLogInterceptor implements Interceptor {

    private static final String TAG = "李文博";
    private final Context context;
    private String headType;


    public RetrofitLogInterceptor(Context context, String headType) {
        this.headType = headType;
        this.context = context;
    }

    public void setHeadType(String headType) {
        this.headType = headType;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Charset UTF8 = Charset.forName("UTF-8");
        //添加请求头
        Request original = null;
        // 打印请求报文
        if ("".equals(headType)) {
            original = addNormalHead(chain);
        } else if ("1".equals(headType)) {
            //获取临时Token
            original = addGetTemporaryHead(chain);
        } else if ("2".equals(headType)) {
            //获取正式Token
            original = addOfficialHead(chain);
        } else if ("3".equals(headType)) {
            //无请求头
            original = chain.request();
        }
        RequestBody requestBody = original.body();
        String reqBody = null;
        if (requestBody != null) {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);

            Charset charset = UTF8;
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            reqBody = buffer.readString(charset);
        }
        Log.e(TAG, String.format("发送请求\nmethod：%s\nurl：%s\nheaders: %s\nbody：%s",
                original.method(), original.url(), original.headers(), reqBody));

        // 打印返回报文
        // 先执行请求，才能够获取报文
        Response response = chain.proceed(original);
        ResponseBody responseBody = response.body();

        String respBody = null;
        if (responseBody != null) {
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.buffer();

            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(UTF8);
                } catch (UnsupportedCharsetException e) {
                    e.printStackTrace();
                }
            }
            respBody = buffer.clone().readString(charset);
        }
        Log.e(TAG, String.format("收到响应\n%s %s\n请求url：%s\n请求body：%s\n响应body：%s",
                response.code(), response.message(), response.request().url(), reqBody, respBody));
        int code = response.code();
        if (code == 401) {
            ARouter.getInstance()
                    .build("/login/LoginActivity")
                    .navigation();
        }
        return response;
    }

    /**
     * 添加获取临时Token的请求头
     *
     * @param chain
     * @return
     */
    private Request addGetTemporaryHead(Chain chain) {
        Request request = chain.request();
        //获取临时token
        String authorization = String.format("Basic %s", "UFJFOlBSRUNSVA==");
        Request.Builder requestBuilder = request.newBuilder()
                .header("PreAuthorization", authorization);
        Request build = requestBuilder.build();
        return build;
    }

    /**
     * 获取正式Token的请求头
     *
     * @param chain
     * @return
     */
    private Request addOfficialHead(Chain chain) {
        Request request = chain.request();
        String clientId = (String) SPUtils.get(context, "clientId", "");
        String clientSecret = (String) SPUtils.get(context, "clientSecret", "");
        String base64 = clientId + ":" + clientSecret;
        String preAuthorization = String.format("Basic %s", Base64.encodeToString(base64.getBytes(), Base64.NO_WRAP));
        String token = (String) SPUtils.get(context, "token", "");
        String authorization = String.format("Bearer %s", token);
        Request.Builder requestBuilder = request.newBuilder()
                .header("PreAuthorization", preAuthorization)
                .header("Authorization", authorization);

        Request build = requestBuilder.build();
        return build;
    }

    /**
     * 正常情况下的请求头
     *
     * @param chain
     * @return
     */
    private Request addNormalHead(Chain chain) {
        Request request = chain.request();
        //获取正式/临时token
        String token = (String) SPUtils.get(context, "token", "");
        String authorization = String.format("Bearer %s", token);
        Request.Builder requestBuilder = request.newBuilder()
                .header("Authorization", authorization)
                .header("Content-type", "application/json")
                .header("charset", "UTF-8");
        Request build = requestBuilder.build();
        return build;
    }

    /*public String getMyPackageName(Context context) {
        PackageManager manager = context.getPackageManager();
        String packageName = "0.1";
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            packageName = info.packageName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageName;
    }
*/
}
