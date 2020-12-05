package com.lwb.cargovoice.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lwb.cargovoice.R;
import com.lwb.cargovoice.utils.ScreenUtil;

public class CommonToolbar extends LinearLayout {
    private Context mContext;
    private TextView tvTitle;
    private ImageView ivBack;
    private RelativeLayout mRlBack;

    public CommonToolbar(Context context) {
        super(context);
    }

    public CommonToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
        initTypeArray(context, attrs);
    }

    public CommonToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        initTypeArray(context, attrs);
    }

    public CommonToolbar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
        initTypeArray(context, attrs);
    }

    public void init(Context context) {
        mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.title_common_toolbar, null);
        ivBack = view.findViewById(R.id.iv_common_toolbar_back);
        mRlBack = view.findViewById(R.id.rl_back);
        tvTitle = view.findViewById(R.id.tv_common_toolbar_title);

        mRlBack.setOnClickListener(v -> {
            Activity activity = (Activity) context;
            activity.finish();
        });

        addView(view, new LayoutParams(LayoutParams.MATCH_PARENT, ScreenUtil.dp2px(mContext, 68)));
    }

    private void initTypeArray(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CommonToolbar);

        String title = array.getString(R.styleable.CommonToolbar_title);
        tvTitle.setText(title);
    }

    public String getTitle() {
        return tvTitle.getText().toString();
    }

    public void setTvTitle(String title) {
        this.tvTitle.setText(title);
    }
}
