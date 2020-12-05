package com.cargo.login.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.cargo.login.R;
import com.cargo.login.module.mvp.entity.request.HolderBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


import java.util.List;

public class HolderListAdapter extends BaseQuickAdapter<HolderBean, BaseViewHolder> {

    public HolderListAdapter(int layoutResId, @Nullable List<HolderBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HolderBean item) {
        helper.setText(R.id.tv_client,item.getHolderName());

    }
}
