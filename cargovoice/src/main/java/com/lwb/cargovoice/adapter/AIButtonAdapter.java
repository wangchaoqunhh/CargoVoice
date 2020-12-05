package com.lwb.cargovoice.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lwb.cargovoice.R;
import com.lwb.cargovoice.module.mvp.entity.response.AIResponse;
import com.lwb.cargovoice.module.mvp.entity.response.SelectRegionBean;

import java.util.List;

public class AIButtonAdapter extends BaseQuickAdapter<AIResponse.ButtonItem, BaseViewHolder> {

    public AIButtonAdapter(@Nullable List<AIResponse.ButtonItem> data) {
        super(R.layout.cargovoice_item_ai_button, data);
        mData = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, AIResponse.ButtonItem item) {
        helper.setText(R.id.tv_text, item.getTitle());
        ImageView icIcon = helper.getView(R.id.iv_icon);
        if ("空运".equals(item.getTitle())){
            icIcon.setVisibility(View.VISIBLE);
            icIcon.setImageDrawable(ContextCompat.getDrawable(mContext,R.drawable.svg_ic_airplane));
        }else if("海运".equals(item.getTitle())){
            icIcon.setVisibility(View.VISIBLE);
            icIcon.setImageDrawable(ContextCompat.getDrawable(mContext,R.drawable.svg_ic_cargo_ship__1));
        }else{
            icIcon.setVisibility(View.GONE);
        }
    }

}
