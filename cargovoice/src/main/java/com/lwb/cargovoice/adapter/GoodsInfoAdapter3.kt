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
            var joint = "数量：" + mContext.fmtMicrometer(packQty) + packTypeDesc +
                    " 体积：" + mContext.fmtMicrometer(volume) + volumeUnitDesc +
                    " 重量：" + mContext.fmtMicrometer(weight) + weightUnitDesc
            helper.setText(R.id.tv_num1, joint)
//            helper.setText(R.id.tv_volume1, "体积：$volume$volumeUnitDesc")
//            helper.setText(R.id.tv_weight1, "重量：$weight$weightUnitDesc")

            helper.setText(R.id.tv_location1, if (TextUtils.isEmpty(mark)) "-" else mark)
            if (TextUtils.isEmpty(imoClass)) {
                helper.setText(R.id.tv_1, "危险品：-")
            } else {
                helper.setText(R.id.tv_1, "危险品：$imoClass")
            }
            if (TextUtils.isEmpty(hsCode)) {
                helper.setText(R.id.tv_code1, "HS编码：-")
            } else {
                helper.setText(R.id.tv_code1, "HS编码：$hsCode")
            }
        }
    }
}