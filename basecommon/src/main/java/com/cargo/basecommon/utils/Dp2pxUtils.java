package com.cargo.basecommon.utils;

import android.content.Context;

public class Dp2pxUtils {

    public static int dp2px(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
