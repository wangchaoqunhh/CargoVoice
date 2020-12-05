package com.lwb.cargovoice.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cargo.basecommon.base.BaseApplication;
import com.cargo.basecommon.bean.EnquiryAddGoalRequest;
import com.cargo.basecommon.utils.ListUtils;
import com.lwb.cargovoice.R;
import com.lwb.cargovoice.adapter.GoodsInfoAdapter2;

import java.util.ArrayList;
import java.util.List;

public class AddGoodsInfo extends LinearLayout {

    private Context mContext;
    private EnquiryAddGoalRequest mRequest;
    private GoodsInfoAdapter2 goodsInfoAdapter2;
    private List<EnquiryAddGoalRequest.CommodityListBean> list = new ArrayList();

    public LinearLayout llAddGoodsInfo;
    private LinearLayout llGoodsInfoContent;

    private TextView tv_see_all;

    public AddGoodsInfo(Context context) {
        super(context);
        init(context);
    }

    public AddGoodsInfo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AddGoodsInfo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public AddGoodsInfo(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    private void init(Context context) {
        mContext = context;
        mRequest = ((BaseApplication) ((Activity) mContext).getApplication()).getRequest();
        View view = LayoutInflater.from(mContext).inflate(R.layout.cargovoice_add_goods_info, null);
        llAddGoodsInfo = view.findViewById(R.id.ll_add_goods_info);
        llGoodsInfoContent = view.findViewById(R.id.ll_goods_info);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);

        goodsInfoAdapter2 = new GoodsInfoAdapter2(list);
        recyclerView.setAdapter(goodsInfoAdapter2);

        tv_see_all = view.findViewById(R.id.tv_see_all);

        addView(view, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        settingShow();
    }


    public void settingShow() {
        if (mRequest == null || ListUtils.isEmpty(mRequest.getCommodityList())) {
            llAddGoodsInfo.setVisibility(VISIBLE);
            llGoodsInfoContent.setVisibility(GONE);
        } else {
            llAddGoodsInfo.setVisibility(GONE);
            llGoodsInfoContent.setVisibility(VISIBLE);

            list.clear();
            for (int i = 0; i < mRequest.getCommodityList().size(); i++) {
                list.add(mRequest.getCommodityList().get(i));
                if (i == 1) {
                    break;
                }
            }
            goodsInfoAdapter2.notifyDataSetChanged();
        }
    }

    public AddGoodsInfo setSeeAllListener(OnClickListener onClickListener) {
        tv_see_all.setOnClickListener(onClickListener);
        return this;
    }
}
