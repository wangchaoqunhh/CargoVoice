package com.cargo.basecommon.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.cargo.basecommon.R;
import com.cargo.basecommon.utils.ActivityCollector;

import butterknife.ButterKnife;

public abstract class BaseActivity<V extends IView, P extends IPresenter> extends Activity {

    protected P mPresenter;
    protected Context mContext;

    public Bundle getSavedInstanceState() {
        return savedInstanceState;
    }

    private Bundle savedInstanceState;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        int layout = setLayout();
        if (layout != 0) {
            // 默认禁止屏幕旋转一直保持竖屏状态
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            setContentView(layout);//设置布局
        }
        mContext = this;
        ButterKnife.bind(this);
        ActivityCollector.addActivity(this);
        initPresenter();
        if (mPresenter != null) {
            mPresenter.onCreate();
            mPresenter.attachView((V) this);
        }
        init();
        Log.e("创建了Activity", this.getClass().getName());
    }

    protected abstract void init();

    protected abstract void initPresenter();

    protected abstract int setLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    /**
     * 获取点击事件
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (isHideInput(view, ev)) {
                HideSoftInput(view.getWindowToken());
                view.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 判定是否需要隐藏
     */
    private boolean isHideInput(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
            if (ev.getX() > left && ev.getX() < right && ev.getY() > top && ev.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 隐藏软键盘
     */
    private void HideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
