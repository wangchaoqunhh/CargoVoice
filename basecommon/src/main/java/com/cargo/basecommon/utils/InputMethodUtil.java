package com.cargo.basecommon.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class InputMethodUtil {
    private InputMethodUtil() {
        throw new UnsupportedOperationException("Cannot be instantiated");
    }

    public static void changeState(@NonNull Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)activity.getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.toggleSoftInput(0, 2);
        }

    }

    public static void show(@NonNull EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager)editText.getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(editText, 2);
        }

    }

    public static void show(@NonNull Activity activity) {
        InputMethodManager imm = (InputMethodManager)activity.getSystemService("input_method");
        if (imm != null) {
            imm.toggleSoftInput(0, 2);
        }

    }

    public static void hide(@NonNull Activity activity) {
        InputMethodManager imm = (InputMethodManager)activity.getSystemService("input_method");
        if (imm != null) {
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }

    }

    public static void hide(@NonNull Context context, @NonNull View view) {
        InputMethodManager imm = (InputMethodManager)context.getSystemService("input_method");
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }

    public static boolean isShowing(@NonNull Activity activity) {
        View view = activity.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager)activity.getSystemService("input_method");
            if (inputMethodManager != null) {
                return inputMethodManager.isActive() && activity.getWindow().getCurrentFocus() != null;
            }
        }

        return false;
    }

    public static void closeIfNeeded(@NonNull Activity activity) {
        if (isShowing(activity)) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService("input_method");
            if (imm != null) {
                imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
            }
        }

    }
}

