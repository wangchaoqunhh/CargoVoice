package com.lwb.cargovoice.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lwb.cargovoice.R;

/**
 * 添加收获信息View
 */
public class AddHarvestInfoView extends LinearLayout {
    private Context mContext;
    public RelativeLayout rlAddHarvestPeopleInfo;
    private LinearLayout ll_harvest_info;
    private TextView tv_title;
    private TextView tv_company;
    private TextView tv_location;
    private TextView tv_describe;
    private TextView tv_linkman;

    public AddHarvestInfoView(Context context) {
        super(context);
        init(context);

    }

    public AddHarvestInfoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
        initTypeArray(context, attrs);
    }

    public AddHarvestInfoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        initTypeArray(context, attrs);
    }

    public AddHarvestInfoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
        initTypeArray(context, attrs);
    }

    private void initTypeArray(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.AddHarvestInfoView);
        String title = array.getString(R.styleable.AddHarvestInfoView_affiliated_title);
        tv_title.setText(title);
    }

    private void init(Context context) {
        mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.cargovoice_add_harvest_info, null);
        rlAddHarvestPeopleInfo = view.findViewById(R.id.rl_add_harvest_people_info);
        tv_title = view.findViewById(R.id.tv_title);
        ll_harvest_info = view.findViewById(R.id.ll_harvest_info);

        tv_company = view.findViewById(R.id.tv_company);
        tv_location = view.findViewById(R.id.tv_location);
        tv_describe = view.findViewById(R.id.tv_describe);
        //人名 电话
        tv_linkman = view.findViewById(R.id.tv_linkman);

        addView(view, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        settingShow();
    }

    private String company;
    private String location;
    private String describe;
    private String people;
    private String phone;

    public void addData() {
        tv_company.setText(company);
        tv_location.setText(location);
        tv_describe.setText(describe);
        tv_linkman.setText(people + " " + phone);
        settingShow();
    }

    private void settingShow() {
        //用联系人判断
        if (TextUtils.isEmpty(people)) {
            rlAddHarvestPeopleInfo.setVisibility(VISIBLE);
            ll_harvest_info.setVisibility(GONE);
        } else {
            tv_company.setVisibility(TextUtils.isEmpty(company) ? GONE : VISIBLE);
            rlAddHarvestPeopleInfo.setVisibility(GONE);
            ll_harvest_info.setVisibility(VISIBLE);
        }
    }

    public AddHarvestInfoView setCompany(String company) {
        this.company = company;
        return this;
    }

    public AddHarvestInfoView setLocation(String location) {
        this.location = location;
        return this;
    }

    public AddHarvestInfoView setDescribe(String describe) {
        this.describe = describe;
        return this;
    }

    public AddHarvestInfoView setPeople(String people) {
        this.people = people;
        return this;
    }

    public AddHarvestInfoView setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPeopleInfoClickListener(@Nullable OnClickListener l) {
        rlAddHarvestPeopleInfo.setOnClickListener(l);
        ll_harvest_info.setOnClickListener(l);
    }
}
