package com.lwb.cargovoice.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lwb.cargovoice.R;
import com.lwb.cargovoice.utils.ScreenUtil;

public class BottomBut extends LinearLayout {
    private Context mContext;
    private TextView tvButText;
    public RelativeLayout rlBut;

    public BottomBut(Context context) {
        super(context);
    }

    public BottomBut(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
        initTypeArray(context, attrs);
    }

    public BottomBut(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        initTypeArray(context, attrs);
    }

    public BottomBut(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
        initTypeArray(context, attrs);
    }

    public void init(Context context) {
        mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.cargovoice_bottom_but_view, null);
        tvButText = view.findViewById(R.id.tv_but_text);
        rlBut = view.findViewById(R.id.rl_but);

        addView(view, new LayoutParams(LayoutParams.MATCH_PARENT, ScreenUtil.dp2px(mContext, 56)));
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(l);
    }

    private void initTypeArray(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BottomBut);

        String title = array.getString(R.styleable.BottomBut_but_text);
        tvButText.setText(title);
    }

    public String getButText() {
        return tvButText.getText().toString();
    }

    public void setTvButText(String butText) {
        this.tvButText.setText(butText);
    }
}
