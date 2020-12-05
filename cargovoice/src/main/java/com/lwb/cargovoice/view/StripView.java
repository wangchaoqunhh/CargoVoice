package com.lwb.cargovoice.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lwb.cargovoice.R;

public class StripView extends LinearLayout {
    private Context mContext;
    private TextView tv_left;
    private TextView tv_right;
    private TextView tv_name;
    private String name;
    private String left;
    private String right;

    public StripView(Context context) {
        super(context);
        init(context);
    }

    public StripView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initTypeArray(context, attrs);
        init(context);
    }

    public StripView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTypeArray(context, attrs);
        init(context);
    }

    public StripView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initTypeArray(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.cargovoice_strip_view, null);
        tv_left = view.findViewById(R.id.tv_left);
        tv_right = view.findViewById(R.id.tv_right);
        tv_name = view.findViewById(R.id.tv_name);

        tv_name.setText(name);
        tv_left.setText(left);
        tv_right.setText(right);

        addView(view, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    private void initTypeArray(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.StripView);

        name = array.getString(R.styleable.StripView_sv_name);
        left = array.getString(R.styleable.StripView_sv_left_text);
        right = array.getString(R.styleable.StripView_sv_right_text);
    }

    public void setName(String tv_name) {
        this.tv_name.setText(tv_name);
    }

    public void setLeft(String left) {
        this.left = left;
        tv_left.setText(left);
        tv_left.setVisibility(VISIBLE);
        tv_right.setVisibility(GONE);
    }

    public void setRight(String right) {
        this.right = right;
        tv_right.setText(right);
        tv_left.setVisibility(GONE);
        tv_right.setVisibility(VISIBLE);
    }
}
