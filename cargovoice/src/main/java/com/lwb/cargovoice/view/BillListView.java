package com.lwb.cargovoice.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cargo.basecommon.bean.GsonBean;
import com.cargo.basecommon.utils.LocalJsonResolutionUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lwb.cargovoice.R;
import com.lwb.cargovoice.module.mvp.entity.response.BillListResponse;

import java.util.ArrayList;
import java.util.List;

import static com.cargo.basecommon.constant.Constant.isEnglist;
import static com.lwb.cargovoice.utils.ExpandingFunKt.fmtMicrometer;

public class BillListView extends LinearLayout {
    private Context mContext;
    private List<BillListResponse.ShipmentListBean> list;
    private Adapter adapter;

    public BillListView(Context context) {
        super(context);
        init(context);
    }

    public BillListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BillListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public BillListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.cargovoice_recyclerview, null);
        list = new ArrayList();
        adapter = new Adapter(list);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);
        addView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
    }

    public void setList(List<BillListResponse.ShipmentListBean> list) {
        this.list.clear();
        this.list.addAll(list);
        adapter.notifyDataSetChanged();
    }

    public static class Adapter extends BaseQuickAdapter<BillListResponse.ShipmentListBean, BaseViewHolder> {

        public Adapter(@Nullable List<BillListResponse.ShipmentListBean> data) {
            super(R.layout.cargovoice_item_bill_list_view, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, BillListResponse.ShipmentListBean item) {
            ImageView ivPlaneOrShip = helper.getView(R.id.iv_plane_or_ship);
            TextView tvPlaneOrShip = helper.getView(R.id.tv_plane_or_ship);
            ImageView iv_arrows = helper.getView(R.id.iv_arrows);
            TextView tv_origin = helper.getView(R.id.tv_origin);
            TextView tv_terminus = helper.getView(R.id.tv_terminus);

            TextView tv_goods_describe = helper.getView(R.id.tv_goods_describe);
            TextView tv_container_way = helper.getView(R.id.tv_container_way);
            TextView tv_weight = helper.getView(R.id.tv_weight);
            TextView tv_volume = helper.getView(R.id.tv_volume);

            //出发港 和 到达港
            tv_origin.setText(item.getPortOfOriginName());
            tv_terminus.setText(item.getPortOfDestinationName());

            tv_goods_describe.setText(mContext.getString(R.string.product_description) + "：" + item.getGoodsDesc());
            GsonBean containerMode = LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "containerMode（装箱方式）.json", item.getContainerModeCode());
            tv_container_way.setText(isEnglist ? containerMode.getCode() : containerMode.getNameCn());
            tv_weight.setText(fmtMicrometer(mContext, item.getTotalWeight()) + item.getTotalWeightUnitCode());
            tv_volume.setText(fmtMicrometer(mContext, item.getTotalVolume()) + item.getTotalVolumeUnitCode());

            if (item.getTransportModeCode() == "SEA") {
                ivPlaneOrShip.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.svg_ic_ship_white));
            } else {
                ivPlaneOrShip.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.svg_ic_plane_white));
            }

            tvPlaneOrShip.setText(LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "transportMode（运输方式）.json", item.getTransportModeCode()).getNameCn());

            //底下内容收缩 控件
            UMExpandLayout expand_view = helper.getView(R.id.expand_view);
//            expand_view.initExpand(false);

            //逆时针旋转 打开用 改变箭头
            Animation rotateAnimation = AnimationUtils.loadAnimation(mContext, R.anim.cargovoice_rotate_anim_open);
            rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    iv_arrows.clearAnimation();
                    iv_arrows.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.svg_ic_chevron_down));
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            //顺时针旋转 关闭用 改变箭头
            Animation rotateAnimAnticlockwise = AnimationUtils.loadAnimation(mContext, R.anim.cargovoice_rotate_anim_close);
            rotateAnimAnticlockwise.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    iv_arrows.clearAnimation();
                    iv_arrows.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.svg_ic_chevron_top));
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            helper.getView(R.id.ll_click).setOnClickListener(v -> {
                if (expand_view.isExpand()) {
                    expand_view.collapse();
                    iv_arrows.startAnimation(rotateAnimation);
                } else {
                    expand_view.expand();
                    iv_arrows.startAnimation(rotateAnimAnticlockwise);
                }
            });
        }
    }

}
