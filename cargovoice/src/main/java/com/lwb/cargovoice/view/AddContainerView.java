package com.lwb.cargovoice.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lwb.cargovoice.R;

import static com.lwb.cargovoice.utils.ExpandingFunKt.fmtMicrometer;

public class AddContainerView extends LinearLayout {
    private Context mContext;
    private RelativeLayout mRlAddContainerInfo;
    private RelativeLayout mRlContainerInfo;
    private TextView mTvContainerType;
    private TextView mTvContainerNum;

    private String type;
    private String num;

    public AddContainerView(Context context) {
        super(context);
        init(context);
    }

    public AddContainerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AddContainerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public AddContainerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.cargovoice_add_container_info, null);
        mRlAddContainerInfo = view.findViewById(R.id.rl_add_container_info);
        mRlContainerInfo = view.findViewById(R.id.rl_container_info);
        mTvContainerType = view.findViewById(R.id.tv_container_type);
        mTvContainerNum = view.findViewById(R.id.tv_container_num);

        addView(view, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        settingShow();
    }

    private void settingShow() {
        if (TextUtils.isEmpty(type) && TextUtils.isEmpty(num)) {
            mRlAddContainerInfo.setVisibility(VISIBLE);
            mRlContainerInfo.setVisibility(GONE);
        } else {
            mRlAddContainerInfo.setVisibility(GONE);
            mRlContainerInfo.setVisibility(VISIBLE);
        }
    }

    public AddContainerView setContainerTypeAndNum(String type, String num) {
        this.type = type;
        this.num = num;

        if (!TextUtils.isEmpty(type)) {
            this.mTvContainerType.setText(mContext.getString(R.string.total_number_of_boxes) + ":" + type);
        }
        if (!TextUtils.isEmpty(num)) {
            this.mTvContainerNum.setText(mContext.getString(R.string.the_total_number_of_boxes) + ":" + fmtMicrometer(mContext, num));
        }
        settingShow();
        return this;

    }

    public AddContainerView setAddContainerListener(OnClickListener mOnClickListener) {
        mRlAddContainerInfo.setOnClickListener(mOnClickListener);
        mRlContainerInfo.setOnClickListener(mOnClickListener);
        return this;
    }

}
