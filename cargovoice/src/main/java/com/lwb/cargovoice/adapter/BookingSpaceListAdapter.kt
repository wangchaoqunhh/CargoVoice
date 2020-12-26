package com.lwb.cargovoice.adapter

import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.widget.ImageView
import com.cargo.basecommon.constant.Constant
import com.cargo.basecommon.utils.AirToast
import com.cargo.basecommon.utils.CopyTextUtil
import com.cargo.basecommon.utils.LocalJsonResolutionUtils
import com.cargo.basecommon.utils.TimeUtils.stampToDateTime
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lwb.cargovoice.R
import com.lwb.cargovoice.module.mvp.entity.response.BookingSpaceListResponse
import com.lwb.cargovoice.utils.fmtMicrometer

class BookingSpaceListAdapter(layoutResId: Int, data: List<BookingSpaceListResponse?>?) : BaseQuickAdapter<BookingSpaceListResponse, BaseViewHolder>(layoutResId, data) {
    override fun convert(helper: BaseViewHolder, item: BookingSpaceListResponse) {

        item?.apply {
            //运输方式
            helper.setText(R.id.tv_plane_or_ship, LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "transportMode（运输方式）.json", transportModeCode).nameCn)
            //状态
            helper.setText(R.id.tv_state, LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "statusType.json", status).nameCn)

            //起运港
            helper.setText(R.id.tv_origin_name, portOfOriginName)
            helper.setText(R.id.tv_origin_code, portOfOriginCode)
            //目的港
            helper.setText(R.id.tv_terminus_name, portOfDestinationName)
            helper.setText(R.id.tv_terminus_code, portOfDestinationCode)

            val iv_plane_or_ship = helper.getView<ImageView>(R.id.iv_plane_or_ship)
            val iv_plane_or_ship2 = helper.getView<ImageView>(R.id.iv_plane_or_ship2)

            //预计起运
            helper.setText(R.id.tv_start_time, "ETD $departureDate")

            val containerMode = LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "containerMode（装箱方式）.json", containerModeCode)
            //装箱方式
            helper.setText(R.id.tv_container_way, if (Constant.isEnglist) containerMode.code else containerMode.nameCn)
            //商品商品重量
            helper.setText(R.id.tv_weight, mContext.fmtMicrometer(totalWeight) + totalWeightUnitCode)
            //商品体积
            helper.setText(R.id.tv_volume, mContext.fmtMicrometer(totalVolume) + totalVolumeUnitCode)

            //订舱单号
            if (TextUtils.isEmpty(ibookingNo)) {
                helper.setText(R.id.tv_order_number, mContext.getString(R.string.booking_order_number) + ":-")
            } else {
                helper.setText(R.id.tv_order_number, mContext.getString(R.string.booking_order_number) + ":$ibookingNo")
            }
            //订舱时间
            if (TextUtils.isEmpty(ibookingCreateTime)) {
                helper.setText(R.id.tv_time, "-")
            } else {
                try {
                    helper.setText(R.id.tv_time, stampToDateTime(ibookingCreateTime.toLong()))
                } catch (e: Exception) {
                }
            }
            //复制
            val copy = helper.getView<ImageView>(R.id.iv_copy)

            //海 运输方式
            if (transportModeCode == "海运") {
                iv_plane_or_ship.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.svg_ic_ship_white))
                iv_plane_or_ship2.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.svg_ic_ship))
            } else {
                iv_plane_or_ship.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.svg_ic_plane_white))
                iv_plane_or_ship2.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.svg_ic_plane))
            }

            helper.addOnClickListener(R.id.iv_copy)

            copy.setOnClickListener {
                if (TextUtils.isEmpty(ibookingNo)) {
                    AirToast.showToast(mContext.getString(R.string.no_booking_order_number))
                    return@setOnClickListener
                }
                CopyTextUtil.putTextIntoClip(mContext, ibookingNo)
            }

        }
    }
}