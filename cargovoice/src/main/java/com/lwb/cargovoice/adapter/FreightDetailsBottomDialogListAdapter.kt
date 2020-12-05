package com.lwb.cargovoice.adapter

import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.cargo.basecommon.utils.LocalJsonResolutionUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lwb.cargovoice.R
import com.lwb.cargovoice.module.mvp.entity.response.FreightDetailsResponse
import com.lwb.cargovoice.utils.fmtMicrometer

class FreightDetailsBottomDialogListAdapter(data: List<Any?>?,
                                            /**
                                             * 1 运输信息
                                             * 2 里程碑
                                             * 3 货件/物品
                                             * 4 订单
                                             * 5 集装箱
                                             * 6 参考号
                                             * 7 备注
                                             */
                                            private val mType: Int) : BaseQuickAdapter<Any, BaseViewHolder>(R.layout.cargovoice_item_freight_details_bottom_dialog_list, data) {
    override fun convert(helper: BaseViewHolder, item: Any) {
        var parent: View? = null
        when (mType) {
            1 -> {
                parent = helper.getView(R.id.parent_1)
                setting1(parent, item)
            }
            2 -> {
                parent = helper.getView(R.id.parent_2)
                setting2(parent, item, helper)
            }
            3 -> {
                parent = helper.getView(R.id.parent_3)
                setting3(parent, item)
            }
            4 -> {
                parent = helper.getView(R.id.parent_4)
                setting4(parent, item)
            }
            5 -> {
                parent = helper.getView(R.id.parent_5)
                setting5(parent, item)
            }
            6 -> {
                parent = helper.getView(R.id.parent_6)
                setting6(parent, item)
            }
            7 -> {
                parent = helper.getView(R.id.parent_7)
                setting7(parent, item)
            }
        }
        if (parent != null) {
            parent.visibility = View.VISIBLE
        }
    }

    //* 1 运输信息
    private fun setting1(parent: View, item: Any) {
        val bean = item as FreightDetailsResponse.TransportLegListBean
        val ivPlaneOrShip = parent?.findViewById<ImageView>(R.id.iv_plane_or_ship)
        val tvPlaneOrShip = parent?.findViewById<TextView>(R.id.tv_plane_or_ship)
        val tvShipsName = parent?.findViewById<TextView>(R.id.tv_ships_name)//船名
        val tvVoyage = parent?.findViewById<TextView>(R.id.tv_voyage)        //航次
        val tvDepart = parent?.findViewById<TextView>(R.id.tv_depart)
        val tvArrive = parent?.findViewById<TextView>(R.id.tv_arrive)
        val tvType = parent?.findViewById<TextView>(R.id.tv_type)
        val tvAccept = parent?.findViewById<TextView>(R.id.tv_accept)

        bean?.apply {
            tvShipsName.text = judgeText(vesselName)  //船名 +
            tvVoyage.text = judgeText(voyageFlightNo)   //航次
            tvDepart.text = judgeText(actualDeparture)
            tvArrive.text = judgeText(actualArrival)
            tvType.text = "类型：" + judgeText(legType)
            tvAccept.text = "承运人：" + judgeText(carrierName)

            if (transportMode.toUpperCase() == "SEA") ivPlaneOrShip.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.svg_ic_ship_white)) else
                ivPlaneOrShip.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.svg_ic_plane_white))
            tvPlaneOrShip.text = transportModeDesc

        }
    }

    //* 2 里程碑
    private fun setting2(parent: View, item: Any, helper: BaseViewHolder) {
        val bean = item as FreightDetailsResponse.MilestoneListBean
        val tvArriveHarbor = parent?.findViewById<TextView>(R.id.tv_arrive_harbor)
        val tvPredictTime = parent?.findViewById<TextView>(R.id.tv_predict_time)
        val tvPracticalTime = parent?.findViewById<TextView>(R.id.tv_practical_time)
        val viewLineTop = parent?.findViewById<View>(R.id.view_line_top)
        val viewLineDown = parent?.findViewById<View>(R.id.view_line_down)

        bean?.apply {
            tvArriveHarbor.text = judgeText(description)
            tvPredictTime.text = "预计日期:" + judgeText(estimatedDate)
            if (!TextUtils.isEmpty(actualDate)) {
                tvPracticalTime.text = "实际日期:$actualDate"
                tvPracticalTime.visibility = View.VISIBLE
            } else {
                tvPracticalTime.visibility = View.GONE
            }
        }

        val position = helper.adapterPosition
        if (position == 0) {
            viewLineTop.visibility = View.INVISIBLE
        } else {
            viewLineTop.visibility = View.VISIBLE
        }
        if (position == data.size - 1) {
            viewLineDown.visibility = View.INVISIBLE
        } else {
            viewLineDown.visibility = View.VISIBLE
        }
    }

    //* 3 货件/物品
    private fun setting3(parent: View, item: Any) {
        val bean = item as FreightDetailsResponse.PackingLineListBean
        val tv_container_code = parent?.findViewById<TextView>(R.id.tv_container_code)
        val tv_goods_describe = parent?.findViewById<TextView>(R.id.tv_goods_describe)
        val tv_packaging_number = parent?.findViewById<TextView>(R.id.tv_packaging_number)
        val tv_tv_packaging_type = parent?.findViewById<TextView>(R.id.tv_tv_packaging_type)
        bean?.apply {
            tv_container_code.text = "集装箱号码：" + judgeText(containerNumber)
            tv_goods_describe.text = judgeText(goodsDesc)
            tv_packaging_number.text = "包装数量：" + mContext.fmtMicrometer(packQty)
            tv_tv_packaging_type.text = "包装类型：" + LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "packageType（包装类型）.json", packTypeCode).nameCn
        }
    }

    //* 4 订单
    private fun setting4(parent: View, item: Any) {
        val bean = item as FreightDetailsResponse.OrderListBean
        val tv_order_number = parent?.findViewById<TextView>(R.id.tv_order_number)
        val tv_date = parent?.findViewById<TextView>(R.id.tv_date)
        val tv_order_stats = parent?.findViewById<TextView>(R.id.tv_order_stats)
        bean?.apply {
            tv_order_number.text = judgeText(orderNumber)
            tv_date.text = judgeText(orderDateTime)
            tv_order_stats.text = judgeText(statusDesc)
        }
    }

    //* 5 集装箱
    private fun setting5(parent: View, item: Any) {
        val bean = item as FreightDetailsResponse.ContainerListBean
        val tv_container_type = parent?.findViewById<TextView>(R.id.tv_container_type)
        val tv_tv_container_num = parent?.findViewById<TextView>(R.id.tv_tv_container_num)
        val tv_time1 = parent?.findViewById<TextView>(R.id.tv_time1)
        val tv_time2 = parent?.findViewById<TextView>(R.id.tv_time1)

        bean?.apply {
            tv_container_type.text = "箱型：" + judgeText(containerTypeCode)
            tv_tv_container_num.text = judgeText(containerNumber)
            tv_time1.text = "预计重箱提货日期：" + judgeText(departureEstimatedPickup)
            tv_time2.text = "实际重柜提柜日期：" + judgeText(arrivalCartageComplete)
        }
    }

    //* 6 参考号
    private fun setting6(parent: View, item: Any) {
        val bean = item as FreightDetailsResponse.AdditionalReferenceListBean
        val tv_type3 = parent?.findViewById<TextView>(R.id.tv_type3)
        val tv_code3 = parent?.findViewById<TextView>(R.id.tv_code3)
        val tv_code4 = parent?.findViewById<TextView>(R.id.tv_code4)
        val ll_explain = parent?.findViewById<LinearLayout>(R.id.ll_explain)
        bean?.apply {
            tv_type3.text = judgeText(typeCode)
            tv_code3.text = judgeText(referenceNumber)
            tv_code4.text = judgeText(typeDesc)
            ll_explain.visibility = if (!TextUtils.isEmpty(typeDesc)) View.VISIBLE else View.GONE
        }
    }

    //* 7 备注
    private fun setting7(parent: View, item: Any) {
        val bean = item as FreightDetailsResponse.NoteListBean
        val tv_remark = parent?.findViewById<TextView>(R.id.tv_remark)
        val tv_remark_content = parent?.findViewById<TextView>(R.id.tv_remark_content)

        bean?.apply {
            tv_remark.text = judgeText(description)
            tv_remark_content.text = judgeText(noteText)
        }
    }

    //判断text是否空 空返回 - 否则返回 原值
    private fun judgeText(text: String?): String {
        return if (TextUtils.isEmpty(text)) {
            "-"
        } else {
            text!!
        }
    }
}