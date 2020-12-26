package com.lwb.cargovoice.adapter

import android.text.TextUtils
import com.cargo.basecommon.utils.LocalJsonResolutionUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lwb.cargovoice.R
import com.lwb.cargovoice.module.mvp.entity.response.BookingSpaceDetailsResponse
import com.lwb.cargovoice.utils.fmtMicrometer

/**
 * 订舱详情用  货件信息
 */
class GoodsInfoAdapter3(data: List<BookingSpaceDetailsResponse.CommodityListBean>?) :
        BaseQuickAdapter<BookingSpaceDetailsResponse.CommodityListBean, BaseViewHolder>(R.layout.cargovoice_item_details_goods_type1, data) {
    override fun convert(helper: BaseViewHolder, item: BookingSpaceDetailsResponse.CommodityListBean) {
        item.apply {
            helper.setText(R.id.tv_goods_type1, LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "commodityType(货件类别).json", commodityCode).nameCn)
            helper.setText(R.id.tv_goods_describe1, if (TextUtils.isEmpty(goodsDesc)) "-" else goodsDesc)

            //拼接三连
            var joint = mContext.getString(R.string.quantity) + "：" + mContext.fmtMicrometer(packQty) + LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "packageType（包装类型）.json", packTypeCode).nameCn +
                    "  " + mContext.getString(R.string.volume) + "：" + mContext.fmtMicrometer(volume) + LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "volumeUnit（体积单位）.json", volumeUnitCode).nameCn +
                    "  " + mContext.getString(R.string.weight) + "：" + mContext.fmtMicrometer(weight) + LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "weightUnit（重量单位）.json", weightUnitCode).nameCn
            helper.setText(R.id.tv_num1, joint)
//            helper.setText(R.id.tv_volume1, "体积：$volume$volumeUnitDesc")
//            helper.setText(R.id.tv_weight1, "重量：$weight$weightUnitDesc")

            helper.setText(R.id.tv_location1, if (TextUtils.isEmpty(mark)) "-" else mark)
            if (TextUtils.isEmpty(imoClass)) {
                helper.setText(R.id.tv_1, mContext.getString(R.string.dangerous_goods) + "：-")
            } else {
                helper.setText(R.id.tv_1, mContext.getString(R.string.dangerous_goods) + "：$imoClass")
            }
            if (TextUtils.isEmpty(hsCode)) {
                helper.setText(R.id.tv_code1, mContext.getString(R.string.hs_code) + "：-")
            } else {
                helper.setText(R.id.tv_code1, mContext.getString(R.string.hs_code) + "：$hsCode")
            }
        }
    }
}