package com.lwb.cargovoice.adapter

import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.widget.ImageView
import com.cargo.basecommon.utils.AirToast
import com.cargo.basecommon.utils.CopyTextUtil
import com.cargo.basecommon.utils.LocalJsonResolutionUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lwb.cargovoice.R
import com.lwb.cargovoice.module.mvp.entity.response.FreightListResponse
import com.lwb.cargovoice.utils.fmtMicrometer

/**
 *
 */
class FreightListAdapter(layout: Int, data: List<FreightListResponse?>?) : BaseQuickAdapter<FreightListResponse, BaseViewHolder>(layout, data) {
    override fun convert(helper: BaseViewHolder, item: FreightListResponse) {
        val ivPlaneOrShip = helper.getView<ImageView>(R.id.iv_plane_or_ship)
        val ivPlaneOrShip2 = helper.getView<ImageView>(R.id.iv_plane_or_ship2)

        item.apply {
            helper.setText(R.id.tv_state, lastestMilestoneDesc)
            helper.setText(R.id.tv_origin_name, portOfOriginName)
            helper.setText(R.id.tv_origin_code, portOfOriginCode)
            helper.setText(R.id.tv_terminus_name, portOfDestinationName)
            helper.setText(R.id.tv_terminus_code, portOfDestinationCode)
            helper.setText(R.id.tv_start_time, "ETD " + if (TextUtils.isEmpty(etd)) "-" else etd)

            helper.setText(R.id.tv_container_way, LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "containerMode（装箱方式）.json", containerModeCode).nameCn)
            helper.setText(R.id.tv_weight, mContext.fmtMicrometer(totalWeight) + totalWeightUnitCode)
            helper.setText(R.id.tv_volume, mContext.fmtMicrometer(totalVolume) + totalVolumeUnitCode)

            helper.setText(R.id.tv_goods_describe, "商品描述：" + if (TextUtils.isEmpty(goodsDesc)) "-" else goodsDesc)
            helper.setText(R.id.tv_order_number, "运单号：" + if (!TextUtils.isEmpty(forwardingShptNumber)) forwardingShptNumber else "-")
            ivPlaneOrShip.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.svg_ic_plane_white))

            if (transportModeCode == "SEA") ivPlaneOrShip.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.svg_ic_ship_white)) else
                ivPlaneOrShip.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.svg_ic_plane_white))
            if (transportModeCode == "SEA") ivPlaneOrShip2.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.svg_ic_ship)) else
                ivPlaneOrShip2.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.svg_ic_plane))
            helper.setText(R.id.tv_plane_or_ship, transportModeDesc)
            //复制运单号
            helper.getView<ImageView>(R.id.iv_copy).setOnClickListener {
                if (TextUtils.isEmpty(forwardingShptNumber)) {
                    AirToast.showToast("暂无运单号")
                    return@setOnClickListener
                }
                CopyTextUtil.putTextIntoClip(mContext, forwardingShptNumber)
            }
        }

        helper.addOnClickListener(R.id.iv_copy)

    }
}