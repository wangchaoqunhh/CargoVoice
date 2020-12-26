package com.lwb.cargovoice.module.mvp.ui.activity

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import com.cargo.basecommon.base.BaseFragmentActivity
import com.cargo.basecommon.constant.Constant
import com.cargo.basecommon.utils.AirToast
import com.cargo.basecommon.utils.CopyTextUtil
import com.cargo.basecommon.utils.ListUtils
import com.cargo.basecommon.utils.LocalJsonResolutionUtils
import com.lwb.cargovoice.R
import com.lwb.cargovoice.adapter.FreightDetailsBottomDialogListAdapter
import com.lwb.cargovoice.module.mvp.contract.FreightDetailsContract
import com.lwb.cargovoice.module.mvp.entity.response.FreightDetailsResponse
import com.lwb.cargovoice.module.mvp.presenter.FreightDetailsPresenter
import com.lwb.cargovoice.utils.fmtMicrometer
import com.lwb.cargovoice.view.BottomFreightDetailsBottomDialog
import kotlinx.android.synthetic.main.cargovoice_activity_freight_details.*


class FreightDetailsActivity : BaseFragmentActivity<FreightDetailsContract.View?, FreightDetailsPresenter?>(), FreightDetailsContract.View {
    private var mResponse: FreightDetailsResponse? = null

    override fun init() {
        val id = intent.getStringExtra("id")
        mPresenter?.freightDetails(id)

        initListener()
    }

    private fun initListener() {
        iv_share.setOnClickListener {
            CopyTextUtil.putTextIntoClip(mContext, mResponse?.forwardingShptNumber, getString(R.string.waybill_number_has_been_copied))
        }

        toolbar.setNavigationOnClickListener {
            finish()
        }

        iv_copy_code.setOnClickListener {
            if (TextUtils.isEmpty(mResponse?.forwardingShptNumber)) {
                AirToast.showToast(getString(R.string.no_waybill_number))
                return@setOnClickListener
            }
            CopyTextUtil.putTextIntoClip(mContext, mResponse?.forwardingShptNumber, getString(R.string.waybill_number_has_been_copied))
        }

        iv_bill_code_copy.setOnClickListener {
            if (TextUtils.isEmpty(mResponse?.wayBillNumber)) {
                AirToast.showToast(getString(R.string.no_bill_of_lading_number))
                return@setOnClickListener
            }
            CopyTextUtil.putTextIntoClip(mContext, mResponse?.wayBillNumber, getString(R.string.the_bill_of_lading_number_has_been_copied))
        }
    }

    override fun initPresenter() {
        mPresenter = FreightDetailsPresenter(mContext)
    }

    override fun setLayout(): Int {
        return R.layout.cargovoice_activity_freight_details
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onSuccess(response: FreightDetailsResponse?) {
        this.mResponse = response
        response?.apply {
            //里程碑状态
            toolbar.title = if (TextUtils.isEmpty(lastestMilestoneDesc)) getString(R.string.no_milestones) else lastestMilestoneDesc
            tv_code.text = getString(R.string.waybill_number) + "：" + forwardingShptNumber
            tv_bill_code.text = getString(R.string.tracking_number) + "：" + wayBillNumber

            iv_plane_or_ship.setImageDrawable(if (transportModeCode == "SEA") ContextCompat.getDrawable(mContext, R.drawable.svg_ic_ship_white) else
                ContextCompat.getDrawable(mContext, R.drawable.svg_ic_plane_white))
            tv_plane_or_ship.text = LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "transportMode（运输方式）.json", transportModeCode).nameCn
            tv_server.text = LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "serviceLevel（服务级别）.json", serviceLevelCode).nameCn

            tv_origin.text = "$portOfOriginName($portOfOriginCode)"
            tv_origin_time.text = "ETD " + if (TextUtils.isEmpty(etd)) "-" else etd
            tv_terminus.text = "$portOfDestinationName($portOfDestinationCode)"
            tv_terminus_time.text = "ETA " + if (TextUtils.isEmpty(eta)) "-" else eta

            sv_sum.setLeft(fmtMicrometer(outerPacks) + LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "packageType（包装类型）.json", outerPacksPackageTypeCode).nameCn)
            sv_volume.setLeft(fmtMicrometer(totalVolume) + totalVolumeUnitCode)
            sv_weight.setLeft(fmtMicrometer(totalWeight) + totalWeightUnitCode)
            val containerMode = LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "containerMode（装箱方式）.json", containerModeCode)
            sv_container_mode.setLeft(if (Constant.isEnglist) containerMode.code else containerMode.nameCn)
            sv_release_mode.setLeft(releaseTypeDesc)
            sv_pay_mode.setLeft(hblawbChargesDisplayDesc)
            sv_lading_state.setLeft(shippedOnBoardDesc)

            showHied(ll_transport_info, tv_see_all1, transportLegList)
            rv_transport_info.adapter = FreightDetailsBottomDialogListAdapter(getTowList(transportLegList), 1)

            //日期信息
            ll_time_info.visibility = if (response?.localProcessing == null) View.GONE else View.VISIBLE
            response?.localProcessing?.apply {
                tv_time1.text = judgeText(estimatedPickup)
                tv_time2.text = judgeText(pickupRequiredBy)
                tv_time3.text = judgeText(pickupCartageAdvised)//
                tv_time4.text = judgeText(pickupCartageCompleted)
                tv_time5.text = judgeText(deliveryRequiredBy)
                tv_time6.text = judgeText(deliveryCartageAdvised)//
                tv_time7.text = judgeText(deliveryCartageCompleted)
                if ("LCL" == containerModeCode?.toUpperCase() || "LSE" == containerModeCode?.toUpperCase()) {
                    //散货
                    tv_time8.text = judgeText(lclAvailable)
                    tv_time9.text = judgeText(lclStorageCommences)
                } else {
                    //整箱
                    tv_time8.text = judgeText(fclAvailable)
                    tv_time9.text = judgeText(fclStorageCommences)
                }
            }

            showHied(ll_milestone, tv_see_all2, milestoneList)
            rv_milestone.adapter = FreightDetailsBottomDialogListAdapter(getTowList(milestoneList), 2)

            showHied(ll_goods, tv_see_all3, packingLineList)
            rv_goods.adapter = FreightDetailsBottomDialogListAdapter(getTowList(packingLineList), 3)

            showHied(ll_indent, tv_see_all4, orderList)
            rv_indent.adapter = FreightDetailsBottomDialogListAdapter(getTowList(orderList), 4)

            showHied(ll_container, tv_see_all5, containerList)
            rv_container.adapter = FreightDetailsBottomDialogListAdapter(getTowList(containerList), 5)

            showHied(ll_reference, tv_see_all6, additionalReferenceList)
            rv_reference.adapter = FreightDetailsBottomDialogListAdapter(getTowList(additionalReferenceList), 6)

            showHied(ll_remark, tv_see_all7, noteList)
            rv_remark.adapter = FreightDetailsBottomDialogListAdapter(getTowList(noteList), 7)

            tv_see_all1.setOnClickListener {
                BottomFreightDetailsBottomDialog()
                        .setListAndType(transportLegList, 1)
                        .show(supportFragmentManager, "")
            }
            tv_see_all2.setOnClickListener {
                BottomFreightDetailsBottomDialog()
                        .setListAndType(milestoneList, 2)
                        .show(supportFragmentManager, "")
            }
            tv_see_all3.setOnClickListener {
                BottomFreightDetailsBottomDialog()
                        .setListAndType(packingLineList, 3)
                        .show(supportFragmentManager, "")
            }
            tv_see_all4.setOnClickListener {
                BottomFreightDetailsBottomDialog()
                        .setListAndType(orderList, 4)
                        .show(supportFragmentManager, "")
            }
            tv_see_all5.setOnClickListener {
                BottomFreightDetailsBottomDialog()
                        .setListAndType(containerList, 5)
                        .show(supportFragmentManager, "")
            }
            tv_see_all6.setOnClickListener {
                BottomFreightDetailsBottomDialog()
                        .setListAndType(additionalReferenceList, 6)
                        .show(supportFragmentManager, "")
            }
            tv_see_all7.setOnClickListener {
                BottomFreightDetailsBottomDialog()
                        .setListAndType(noteList, 7)
                        .show(supportFragmentManager, "")
            }
        }
    }

    private fun showHied(viewGroup: ViewGroup, view: View, list: List<*>?) {
        if (ListUtils.isEmpty(list)) {
            viewGroup.visibility = View.GONE
        } else {
            viewGroup.visibility = View.VISIBLE
            if (!ListUtils.isEmpty(list) && list?.size!! > 2) {
                view.visibility = View.VISIBLE
            } else {
                view.visibility = View.GONE
            }
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

    //获取前两条 数据
    fun <T> getTowList(allList: List<T>): List<T> {
        val list = arrayListOf<T>()
        for (i in allList.indices) {
            list.add(allList[i])
            if (i == 1) {
                break
            }
        }
        return list
    }

    override fun onError(result: String) {}

    companion object {
        fun launchActivity(context: Context, id: String?) {
            val intent = Intent(context, FreightDetailsActivity::class.java)
            intent.putExtra("id", id)
            context.startActivity(intent)
        }
    }
}