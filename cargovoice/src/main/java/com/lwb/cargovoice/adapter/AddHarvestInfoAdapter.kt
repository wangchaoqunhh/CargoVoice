package com.lwb.cargovoice.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lwb.cargovoice.R
import com.lwb.cargovoice.module.mvp.entity.response.AddHarvestInfoResponse

class AddHarvestInfoAdapter(data: List<AddHarvestInfoResponse?>?) : BaseQuickAdapter<AddHarvestInfoResponse, BaseViewHolder>(R.layout.cargovoice_item_add_harvest_info, data) {
    override fun convert(helper: BaseViewHolder, item: AddHarvestInfoResponse) {
        //国家 省 市 区
        item.apply {
            helper.setText(R.id.tv_location, "$countryName $stateName $city")
            helper.setText(R.id.tv_location_describe, item.address1)
        }
    }
}