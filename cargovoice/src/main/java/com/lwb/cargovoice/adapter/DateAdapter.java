package com.lwb.cargovoice.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lwb.cargovoice.R;
import com.lwb.cargovoice.module.mvp.entity.response.AIResponse;
import com.lwb.cargovoice.module.mvp.entity.response.MyDataBean;

import java.util.List;

public class DateAdapter extends BaseQuickAdapter<MyDataBean, BaseViewHolder> {
    private List<MyDataBean> mData;

    public DateAdapter(@Nullable List<MyDataBean> data) {
        super(R.layout.cargoce_item_data, data);
        mData = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, MyDataBean item) {
        TextView textView = helper.getView(R.id.tv_item_data);
        textView.setText(item.getData());
        if (item.isWhrite()){
            textView.setTextColor(ContextCompat.getColor(mContext,R.color.c101));
        }else{
            textView.setTextColor(ContextCompat.getColor(mContext,R.color.c100_60));
        }
    }


}
