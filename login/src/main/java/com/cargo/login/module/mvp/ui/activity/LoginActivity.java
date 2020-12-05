package com.cargo.login.module.mvp.ui.activity;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.cargo.basecommon.base.BaseFragmentActivity;
import com.cargo.basecommon.base.BaseResponse;
import com.cargo.basecommon.utils.ActivityCollector;
import com.cargo.basecommon.utils.AirToast;
import com.cargo.basecommon.utils.InputMethodUtil;
import com.cargo.basecommon.utils.ListUtils;
import com.cargo.basecommon.utils.MatchUtil;
import com.cargo.basecommon.utils.SPUtils;
import com.cargo.login.R;
import com.cargo.login.R2;
import com.cargo.login.contentObserver.SmsObserver;
import com.cargo.login.module.mvp.contract.LoginContract;
import com.cargo.login.module.mvp.entity.request.HolderBean;
import com.cargo.login.module.mvp.presenter.LoginPresenter;
import com.cargo.login.utils.VerificationCountDownTimer;
import com.cargo.login.view.BottomHolderDialog;
import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.Manifest.permission.READ_SMS;
import static android.Manifest.permission.RECEIVE_SMS;

@Route(path = "/login/LoginActivity")
public class LoginActivity extends BaseFragmentActivity<LoginContract.View, LoginPresenter> implements LoginContract.View {


    @BindView(R2.id.tv_login)
    TextView tvLogin;
    @BindView(R2.id.et_tel)
    EditText etTel;
    @BindView(R2.id.et_verification)
    EditText etVerification;
    @BindView(R2.id.tv_verification)
    TextView tvVerification;
    @BindView(R2.id.ll_agree)
    LinearLayout llAgree;

    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;

    private VerificationCountDownTimer mTimer;

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };
    private SmsObserver mSmsObserver;
    private ArrayList<HolderBean> mHolderList;
    private int mIndex;

    @Override
    protected void init() {
        SPUtils.put(mContext, "token", "");
        PermissionsUtil.requestPermission(this, new PermissionListener() {
            @Override
            public void permissionGranted(@NonNull String[] permissions) {
                //注册内容提供者
                fixedPhone();
            }

            @Override
            public void permissionDenied(@NonNull String[] permissions) {
            }
        }, READ_SMS, RECEIVE_SMS);
        fullScreen(this);
        initCountDownTimer();
        initListener();
    }

    private void initListener() {
        llAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,AboutActivity.class));
            }
        });
        etTel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (MatchUtil.matchTel(etTel.getText().toString()) && etVerification.getText().toString().length() == 6) {
                    tvLogin.setBackgroundResource(R.drawable.shape_rectangle_r12_c005);
                    tvLogin.setTextColor(ContextCompat.getColor(mContext, R.color.c101));
                } else {
                    tvLogin.setBackgroundResource(R.drawable.shape_rectangle_r12_c14e);
                    tvLogin.setTextColor(ContextCompat.getColor(mContext, R.color.c100_60));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etVerification.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (MatchUtil.matchTel(etTel.getText().toString()) && etVerification.getText().toString().length() == 6) {
                    tvLogin.setBackgroundResource(R.drawable.shape_rectangle_r12_c005);
                    tvLogin.setTextColor(ContextCompat.getColor(mContext, R.color.c101));
                } else {
                    tvLogin.setBackgroundResource(R.drawable.shape_rectangle_r12_c14e);
                    tvLogin.setTextColor(ContextCompat.getColor(mContext, R.color.c100_60));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void fullScreen(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
                Window window = activity.getWindow();
                View decorView = window.getDecorView();
                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
                //导航栏颜色也可以正常设置
//                window.setNavigationBarColor(Color.TRANSPARENT);
            } else {
                Window window = activity.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus;
//                attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }
    }

    @Override
    protected void initPresenter() {
        mPresenter = new LoginPresenter(mContext);
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_login_by_password;
    }


    @OnClick({R2.id.tv_verification, R2.id.tv_login})
    public void onViewClicked(View view) {
        int i = view.getId();
        InputMethodUtil.closeIfNeeded(this);
        if (!MatchUtil.matchTel(etTel.getText().toString())) {
            //手机号验证未通过
            AirToast.showToast("手机号码有误");
            return;
        }
        if (i == R.id.tv_login) {
            if (etVerification.getText().toString().length() == 6) {
                //获取临时Token
                mPresenter.getPreToken(etTel.getText().toString(), etVerification.getText().toString());
            } else {
                AirToast.showToast("验证码输入有误");
            }
        }
        if (i == R.id.tv_verification) {
            //获取验证码
            if (VerificationCountDownTimer.curMillis + 60000 < System.currentTimeMillis()) {
                getVerification();
            }
        }

    }


    private void initCountDownTimer() {
        if (VerificationCountDownTimer.curMillis + 60000 > System.currentTimeMillis()) {
            setContDownTimer(VerificationCountDownTimer.curMillis + 60000 - System.currentTimeMillis());
            mTimer.timerStart(false);
        }
    }

    /**
     * 获取验证码
     */
    private void getVerification() {
        setContDownTimer(60 * 1000);
        mTimer.timerStart(true);
        //获取验证码接口
        mPresenter.getVerifyCode(etTel.getText().toString());
    }

    private void setContDownTimer(long l) {
        mTimer = new VerificationCountDownTimer(l, 1000) {
            @Override
            public void onFinish() {
                tvVerification.setText("获取验证码");
            }

            @Override
            public void onTick(long millisUntilFinished) {
                tvVerification.setText("重新发送" + millisUntilFinished / 1000);
            }
        };
    }

    /**
     * 没有手机号码
     */
    private void fixedPhone() {
        mSmsObserver = new SmsObserver(this, new Handler(),
                result -> {
                    etVerification.setText(result);
                });
        // 注册短信变化监听
        this.getContentResolver().registerContentObserver(
                Uri.parse("content://sms/"), true, mSmsObserver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }


    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            ActivityCollector.finishAll();
            finish();
        }
    }


    @Override
    public void onSuccess(BaseResponse loginResponse) {

    }

    @Override
    public void onError(String result) {
        AirToast.showToast(result);
    }


    @Override
    public void onFail(String s) {
        AirToast.showToast(s);
    }

    /**
     * 获取临时Token成功
     *
     * @param token
     */
    @Override
    public void getPerTokenSuccess(String token) {
        SPUtils.put(mContext, "token", token);
        //获取货代列表
        mPresenter.getHolderList(etTel.getText().toString());
    }

    @Override
    public void getHolderListSuccess(List<HolderBean> holderList) {
        if (!ListUtils.isEmpty(holderList)) {
            this.mHolderList = (ArrayList<HolderBean>) holderList;
            if (holderList.size() == 1) {
                //只有一个公司,直接调用获取正式Token接口
                SPUtils.put(mContext, "clientId", holderList.get(0).getClientId());
                SPUtils.put(mContext, "clientSecret", holderList.get(0).getClientSecret());
                SPUtils.put(mContext, "clientName", holderList.get(0).getHolderName());
                SPUtils.put(mContext, "tel", etTel.getText().toString());
                mIndex = 0;
                mPresenter.getOfficialToken(etTel.getText().toString());
            } else {
                //弹出公司选择弹窗
                BottomHolderDialog dialog = BottomHolderDialog.getInstant(holderList);
                dialog.setOnHolderClickListener((holderBean, position) -> {
                    SPUtils.put(mContext, "clientId", holderBean.getClientId());
                    SPUtils.put(mContext, "clientSecret", holderBean.getClientSecret());
                    SPUtils.put(mContext, "clientName", holderBean.getHolderName());
                    SPUtils.put(mContext, "tel", etTel.getText().toString());
                    mPresenter.getOfficialToken(etTel.getText().toString());
                    mIndex = position;
                    dialog.dismiss();
                });
                dialog.show(getSupportFragmentManager(), "");
            }
        }
    }

    @Override
    public void getOfficialTokenSuccess(String token) {
        SPUtils.put(mContext, "token", token);
        //跳转到首页
        Intent intent = new Intent(mContext, MainActivity.class);
        intent.putExtra("index", mIndex);
        intent.putExtra("tel", etTel.getText().toString());
        intent.putParcelableArrayListExtra("list", mHolderList);
        startActivity(intent);
        finish();
    }
}
