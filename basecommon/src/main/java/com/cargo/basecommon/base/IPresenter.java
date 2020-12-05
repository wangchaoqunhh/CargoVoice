package com.cargo.basecommon.base;

import android.content.Intent;


public interface IPresenter {
    void onCreate();

    void onStart();

    void onStop();

    void onPause();

    void attachView(IView view);

    void attachIncomingIntent(Intent intent);
}
