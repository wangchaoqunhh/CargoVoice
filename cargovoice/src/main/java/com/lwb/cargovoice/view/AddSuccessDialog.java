package com.lwb.cargovoice.view;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.lwb.cargovoice.R;

import java.util.Timer;
import java.util.TimerTask;

public class AddSuccessDialog extends DialogFragment {

    private String succeedText;
    private String failText;

    private CountDownFinishListener mCountDownFinishListener;
    private TextView mMessage;
    private ImageView mIvImg;
    private ProgressBarView mPbv;

//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            super.handleMessage(msg);
//            if (msg.arg1 == 0) {
//                if (mCountDownFinishListener != null) {
//                    mCountDownFinishListener.countDownFinishListener();
//                }
//            }
//            mMessage.setText("添加成功" + msg.arg1 + "后退出");
//        }
//    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cargovoice_add_success_dialog_view, null);
        mIvImg = view.findViewById(R.id.iv_img);
        mPbv = view.findViewById(R.id.pbv);
        mMessage = view.findViewById(R.id.tv_message);

        succeedText = getString(R.string.submitted_successfully);
        failText = getString(R.string.submission_failed_please_try_again);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
//        new Thread(() -> {
//            for (int i = 3; i >= 0; i--) {
//                try {
//                    Message obtain = Message.obtain();
//                    obtain.arg1 = i;
//                    handler.sendMessage(obtain);
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

    }

    //成功调用
    public void commitSucceed() {
        mPbv.setVisibility(View.GONE);
        mIvImg.setImageDrawable(getActivity().getDrawable(R.drawable.svg_ic_undraw_folder_39kl_succeed));
        mMessage.setText(succeedText);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (mCountDownFinishListener != null) {
                    mCountDownFinishListener.countDownFinishListener();
                }
                dismiss();
            }
        }, 1500);
    }

    //失败调用
    public void commitFail() {
        mPbv.setVisibility(View.GONE);
        mIvImg.setImageDrawable(getActivity().getDrawable(R.drawable.svg_ic_undraw_folder_39kl_fail));
        mMessage.setText(failText);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                dismiss();
            }
        }, 1500);
    }

    public AddSuccessDialog setCountDownFinishListener(CountDownFinishListener mCountDownFinishListener) {
        this.mCountDownFinishListener = mCountDownFinishListener;
        return this;
    }

    @Override
    public void onStart() {
        super.onStart();

        Window window = getDialog().getWindow();
        getDialog().setCanceledOnTouchOutside(true);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        window.setWindowAnimations(R.style.dialogWindowAnim);
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.dimAmount = 0f;
//        wlp.width = ScreenUtil.dp2px(getActivity(), 240);
//        wlp.height = ScreenUtil.dp2px(getActivity(), 240);
        wlp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wlp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setAttributes(wlp);

        //设置点击外头不取消
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);

        getDialog().setOnKeyListener((dialog, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                return true;
            }
            return false;
        });
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    public void dismissAllowingStateLoss() {
        super.dismissAllowingStateLoss();
    }

    public interface CountDownFinishListener {
        void countDownFinishListener();
    }
}
