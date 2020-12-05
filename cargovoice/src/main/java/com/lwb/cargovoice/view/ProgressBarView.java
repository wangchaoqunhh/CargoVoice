package com.lwb.cargovoice.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lwb.cargovoice.R;

public class ProgressBarView extends View {
    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private ValueAnimator valueAnimator;
    private int mCurrentProgress;

    public ProgressBarView(Context context) {
        super(context);
    }

    public ProgressBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ProgressBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initPaint() {
        if (mPaint == null) {
            mPaint = new Paint();
            mPaint.setStrokeWidth(mHeight);
            //抗锯齿
            mPaint.setAntiAlias(true);
            mPaint.setDither(true);

            //设置透明度（数值为0-255）
            mPaint.setAlpha(100);
            //设置画笔的画出的形状
            mPaint.setStrokeJoin(Paint.Join.ROUND);
            //设置画笔类型
            mPaint.setStyle(Paint.Style.FILL);
            //设置线冒 圆
            mPaint.setStrokeCap(Paint.Cap.ROUND);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initPaint();
//        mPaint.setColor(getResources().getColor(R.color.c127));
//        canvas.drawLine(0, mHeight / 2, mWidth, mHeight / 2, mPaint);
//
//        mPaint.setColor(getResources().getColor(R.color.c005));
//        canvas.drawLine(0, mHeight / 2, mCurrentProgress, mHeight / 2, mPaint);
        mPaint.setColor(getResources().getColor(R.color.c127));
        canvas.drawLine(mHeight / 2, mHeight / 2, mWidth - mHeight / 2, mHeight / 2, mPaint);

        mPaint.setColor(getResources().getColor(R.color.c005));
        if (mCurrentProgress - mHeight / 2 >= mHeight / 2) {
            canvas.drawLine(mHeight / 2, mHeight / 2, mCurrentProgress - mHeight / 2, mHeight / 2, mPaint);
        }
        startAnimator();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    private void startAnimator() {
        if (valueAnimator == null) {
            valueAnimator = ValueAnimator.ofInt(0, mWidth);
            valueAnimator.setDuration(7000);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mCurrentProgress = (int) animation.getAnimatedValue();
                    invalidate();
                    if (mCurrentProgress == mWidth) {
                        destroyAnimator();
                    }
                }
            });
            valueAnimator.start();
        }
    }

    private void destroyAnimator() {
        if (valueAnimator != null) {
            valueAnimator.removeAllListeners();
            valueAnimator.removeAllUpdateListeners();
            valueAnimator.end();
        }
    }
}
