package com.lwb.cargovoice.view;

import android.content.Context;
import android.content.res.TypedArray;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cargo.basecommon.base.BaseFragmentActivity;
import com.lwb.cargovoice.R;

import java.math.BigDecimal;

public class PlusMinusView extends LinearLayout {
    private Context mContext;
    private TextView tv_name;
    private TextView tv_num;
    /**
     * 是否需要小数
     */
    private boolean isDecimals;

    public PlusMinusView(Context context) {
        super(context);
        init(context);
    }

    public PlusMinusView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
        initTypeArray(context, attrs);
    }

    public PlusMinusView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        initTypeArray(context, attrs);
    }

    public PlusMinusView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
        initTypeArray(context, attrs);
    }

    private void initTypeArray(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.PlusMinusView);

        String name = array.getString(R.styleable.PlusMinusView_plus_minus_name);
        tv_name.setText(name);
    }

    private void init(Context context) {
        mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.cargovoice_plus_minus_view, null);
        tv_name = view.findViewById(R.id.tv_name);
        ImageView iv_plus = view.findViewById(R.id.iv_plus);
        tv_num = view.findViewById(R.id.tv_num);
        ImageView iv_minus = view.findViewById(R.id.iv_minus);

        tv_num.setOnClickListener(v -> {
            AddNumDialog addNumDialog = AddNumDialog.newInstance();
            addNumDialog.setDecimals(isDecimals);
            addNumDialog.setBack(num -> {
                tv_num.setText(num);
                if (onBackNum != null) {
                    onBackNum.onBackNum(tv_num.getText().toString());
                }
            });
            addNumDialog.show(((BaseFragmentActivity) mContext).getSupportFragmentManager(), "");
        });

        iv_plus.setOnClickListener(v -> {
            double i = Double.parseDouble(tv_num.getText().toString());
            BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(i));
            BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(1));
            i = bigDecimal1.subtract(bigDecimal2).doubleValue();
            if (i <= 0) {
                tv_num.setText("0");
            } else {
                if (isDecimals) {
                    tv_num.setText(i + "");
                } else {
                    tv_num.setText((int) i + "");
                }
            }
            if (onBackNum != null) {
                onBackNum.onBackNum(tv_num.getText().toString());
            }
        });

        iv_minus.setOnClickListener(v -> {
            double i = Double.parseDouble(tv_num.getText().toString());
            if (i >= 9999999) {
                return;
            }
            BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(i));
            BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(1));
            i = bigDecimal1.add(bigDecimal2).doubleValue();
            if (isDecimals) {
                tv_num.setText(i + "");
            } else {
                tv_num.setText((int) i + "");
            }
            if (onBackNum != null) {
                onBackNum.onBackNum(tv_num.getText().toString());
            }
        });

        addView(view, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    public void setNum(String num) {
        if (TextUtils.isEmpty(num)) {
            tv_num.setText("0");
        } else {
            tv_num.setText(num);
        }
    }

    /**
     * isDecimals 是否需要小数
     * onBackNum 点击完加减 或者 输入完， 把数字返回给调用者
     */
    public void setOnBackNum(boolean isDecimals, OnBackNum onBackNum) {
        this.onBackNum = onBackNum;
        this.isDecimals = isDecimals;
    }

    private OnBackNum onBackNum;

    public interface OnBackNum {
        void onBackNum(String num);
    }
}
