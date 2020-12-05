package com.lwb.cargovoice.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lwb.cargovoice.R;
import com.lwb.cargovoice.module.mvp.entity.response.SelectRegionBean;

import java.util.List;

public class SelectRegionAdapter extends BaseQuickAdapter<SelectRegionBean, BaseViewHolder> {
    public SelectRegionAdapter(@Nullable List<SelectRegionBean> data) {
        super(R.layout.cargovoice_item_select_region, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectRegionBean item) {
        helper.setText(R.id.tv_item, item.getName());

       TextView tag=  helper.getView(R.id.tag);
        int section = getSectionForPosition(helper.getAdapterPosition());
        //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if (helper.getAdapterPosition() == getPositionForSection(section)) {
            tag.setVisibility(View.VISIBLE);
            tag.setText(mData.get(helper.getAdapterPosition()).getLetters());
        } else {
            tag.setVisibility(View.GONE);
        }
    }

    /**
     * 根据ListView的当前位置获取分类的首字母的char ascii值
     */
    public int getSectionForPosition(int position) {
        return mData.get(position).getLetters().charAt(0);
    }


    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getItemCount(); i++) {
            String sortStr = mData.get(i).getLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }
}
