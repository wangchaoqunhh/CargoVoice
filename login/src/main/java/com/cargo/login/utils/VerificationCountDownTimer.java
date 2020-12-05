package com.cargo.login.utils;

import android.os.CountDownTimer;


public abstract class VerificationCountDownTimer extends CountDownTimer{

    public static long curMillis =0;

    /**
     * 类中的构造函数
     * @param millisInFuture
     * @param countDownInterval
     */
    public VerificationCountDownTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }


    /**
     * 当前任务完成的时候回调
     */
    @Override
    public abstract void onFinish ();
    /**
     * 当前任务每完成一次倒计时间隔时间时回调
     * @param millisUntilFinished
     */
    @Override
    public abstract void onTick (long millisUntilFinished);


    public void timerStart(boolean isWhole){
        //如果是完整的一次倒计时，保存开始时间
        if(isWhole) {
            VerificationCountDownTimer.curMillis= System.currentTimeMillis();
        }
        this.start();
    }
}
