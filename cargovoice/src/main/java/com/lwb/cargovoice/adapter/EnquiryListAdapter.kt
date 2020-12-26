package com.lwb.cargovoice.adapter

import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.cargo.basecommon.constant.Constant.isEnglist
import com.cargo.basecommon.utils.LocalJsonResolutionUtils
import com.cargo.basecommon.utils.TimeUtils.stampToDateTime
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lwb.cargovoice.R
import com.lwb.cargovoice.module.mvp.entity.response.EnquiryListResponse
import com.lwb.cargovoice.utils.fmtMicrometer

class EnquiryListAdapter(layoutResId: Int, data: List<EnquiryListResponse?>?) : BaseQuickAdapter<EnquiryListResponse, BaseViewHolder>(layoutResId, data) {
    override fun convert(helper: BaseViewHolder, item: EnquiryListResponse) {

        item?.apply {
            //询价单号
            val tvEnquiryNumber = helper.getView<TextView>(R.id.tv_enquiry_number)
            if (!TextUtils.isEmpty(inquiryNo)) {
                tvEnquiryNumber.text = mContext.getString(R.string.inquiry_number) + ":" + inquiryNo
            } else {
                tvEnquiryNumber.text = mContext.getString(R.string.inquiry_number) + "：-"
            }
            //询价时间
            if (!TextUtils.isEmpty(inquiryCreateTime)) {
                try {
                    helper.setText(R.id.tv_enquiry_time, stampToDateTime(inquiryCreateTime.toLong()))
                } catch (e: Exception) {
                }
            } else {
                helper.setText(R.id.tv_enquiry_time, "-")
            }
            //运输方式
            helper.setText(R.id.tv_plane_or_ship, LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "transportMode（运输方式）.json", transportModeCode).nameCn)
            //询价状态
            helper.setText(R.id.tv_enquiry_state, LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "statusType.json", status).nameCn)
            //起运港
            helper.setText(R.id.tv_origin_name, portOfOriginName)
            helper.setText(R.id.tv_origin_code, portOfOriginCode)
            //目的港
            helper.setText(R.id.tv_terminus_name, portOfDestinationName)
            helper.setText(R.id.tv_terminus_code, portOfDestinationCode)
            //预计起运
            helper.setText(R.id.tv_predict_time, "ETD $departureDate")

            val containerMode = LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "containerMode（装箱方式）.json", containerModeCode)
            //装箱方式
            helper.setText(R.id.tv_container_way, if (isEnglist) containerMode.code else containerMode.nameCn)
            //商品商品重量
            helper.setText(R.id.tv_weight, mContext.fmtMicrometer(totalWeight) + totalWeightUnitCode)
            //商品体积
            helper.setText(R.id.tv_volume, mContext.fmtMicrometer(totalVolume) + totalVolumeUnitCode)

            val ivPlaneOrShip = helper.getView<ImageView>(R.id.iv_plane_or_ship)
            val ivPlaneOrShip2 = helper.getView<ImageView>(R.id.iv_plane_or_ship2)
            val tvBooking = helper.getView<View>(R.id.tv_booking)
            //海 运输方式
            if (transportModeCode == "SEA") {
                ivPlaneOrShip.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.svg_ic_ship_white))
                ivPlaneOrShip2.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.svg_ic_ship))
            } else {
                ivPlaneOrShip.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.svg_ic_plane_white))
                ivPlaneOrShip2.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.svg_ic_plane))
            }

            if (status == "102") {
                tvBooking.visibility = View.VISIBLE
            } else {
                tvBooking.visibility = View.GONE
            }
        }

        helper.addOnClickListener(R.id.tv_booking)
    }
}