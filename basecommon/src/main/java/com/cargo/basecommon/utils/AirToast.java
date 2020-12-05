package com.cargo.basecommon.utils;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cargo.basecommon.R;

public class AirToast {
    private static Context mContext;
    private static LayoutInflater inflater;
    private static View myToastView;
    private static TextView msgView;
    private static final int DEFAULT_TIME_DELAY = 50;
    private static AirToast mToastUtil;
    private static volatile boolean isHaveInitUtil = false;
    private static Toast toast;
    private static Handler handler = new Handler();

    public static Boolean getIsHaveInited() {
        return isHaveInitUtil;
    }

    public static AirToast initToast(Context context) {
        isHaveInitUtil = true;
        if (mToastUtil == null) {
            mToastUtil = new AirToast(context);
        }

        return mToastUtil;
    }

    public static AirToast getInstance() {
        if (mToastUtil == null) {
            throw new NullPointerException("请在Application中初始化ToastUtil");
        } else {
            return mToastUtil;
        }
    }

    private AirToast(Context context) {
        mContext = context;
        this.init(context);
    }

    private void init(Context context) {
        inflater = LayoutInflater.from(context);
        myToastView = inflater.inflate(R.layout.toast_layout, (ViewGroup)null);
        msgView = myToastView.findViewById(R.id.tv_msg_text);
    }

    public static void showToast(String msg) {
        showToast(msg, myToastView, 81, 0, 120, 0);
    }

    public static void showToast(String msg, int duration) {
        showToast(msg, myToastView, 81, 0, 120, duration);
    }

    public static void showToast(String msg, View toastView, int gravity, int xOffset, int yOffset, int duration) {
        if (toast == null) {
            toast = new Toast(mContext);
        }

        handler.postDelayed(() -> {
            msgView.setText(msg);
            toast.setView(toastView);
            toast.setGravity(gravity, dp2Px(mContext, xOffset), dp2Px(mContext, yOffset));
            toast.setDuration(duration);
            toast.show();
        }, 50L);
    }

    public static Toast showToastWithReturn(String msg, int duration) {
        return showToastWithReturn(msg, myToastView, 81, 0, 120, duration);
    }

    public static Toast showToastWithReturn(String msg, View toastView, int gravity, int xOffset, int yOffset, int duration) {
        showToast(msg, duration);
        return toast;
    }

    public static Integer dp2Px(Context context, int dp) {
        Float scale = context.getResources().getDisplayMetrics().density;
        Integer scaleInt = scale.intValue();
        return dp * scaleInt;
    }

    public static void showOneToast(@NonNull CharSequence message, int duration) {
        showToast(message.toString(), myToastView, 81, 0, 120, duration);
    }

    public static void cancelToast() {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }

    }
}

