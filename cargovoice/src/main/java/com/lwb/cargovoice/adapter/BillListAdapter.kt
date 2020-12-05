package com.lwb.cargovoice.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lwb.cargovoice.R
import com.lwb.cargovoice.module.mvp.entity.response.BillListResponse
import com.lwb.cargovoice.utils.fmtMicrometer
import com.lwb.cargovoice.view.BillListView

class BillListAdapter(layoutResId: Int, data: List<BillListResponse?>?) : BaseQuickAdapter<BillListResponse?, BaseViewHolder?>(layoutResId, data) {
    override fun convert(helper: BaseViewHolder?, item: BillListResponse?) {
        item?.apply {
            helper?.setText(R.id.tv_number, "账单编号：$number")
                    ?.setText(R.id.tv_transactionDate, transactionDate)
                    ?.setText(R.id.tv_osTotal, mContext.fmtMicrometer(osTotal))
                    ?.setText(R.id.tv_outstandingAmount, mContext.fmtMicrometer(outstandingAmount))
                    ?.setText(R.id.tv_jobKey, "运单号：$jobKey")
            val blv = helper?.getView<BillListView>(R.id.blv)
            blv?.setList(item?.shipmentList)
        }
    }
}