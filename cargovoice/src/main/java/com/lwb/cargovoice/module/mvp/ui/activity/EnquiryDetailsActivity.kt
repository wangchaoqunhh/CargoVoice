package com.lwb.cargovoice.module.mvp.ui.activity

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.cargo.basecommon.base.BaseFragmentActivity
import com.cargo.basecommon.constant.Constant.isEnglist
import com.cargo.basecommon.utils.AirToast
import com.cargo.basecommon.utils.CopyTextUtil
import com.cargo.basecommon.utils.ListUtils
import com.cargo.basecommon.utils.LocalJsonResolutionUtils
import com.cargo.basecommon.utils.TimeUtils.stampToDateTime
import com.lwb.cargovoice.R
import com.lwb.cargovoice.adapter.GoodsInfoAdapter
import com.lwb.cargovoice.module.mvp.contract.EnquiryDetailsContract
import com.lwb.cargovoice.module.mvp.entity.response.EnquiryDetailsResponse
import com.lwb.cargovoice.module.mvp.entity.response.OneTouchResponse
import com.lwb.cargovoice.module.mvp.presenter.EnquiryDetailsPresenter
import com.lwb.cargovoice.utils.fmtMicrometer
import com.lwb.cargovoice.view.AddSuccessDialog
import com.lwb.cargovoice.view.BottomGoodsInfoDialog
import com.lwb.cargovoice.view.StripView
import kotlinx.android.synthetic.main.cargovoice_activity_enquiry_details.*

class EnquiryDetailsActivity : BaseFragmentActivity<EnquiryDetailsContract.View?, EnquiryDetailsPresenter?>(), EnquiryDetailsContract.View {
    //提交时候弹窗
    private var mAddSuccessDialog: AddSuccessDialog? = null

    private var mResponse: EnquiryDetailsResponse? = null

    private var id: String? = null

    override fun init() {
        id = intent.getStringExtra("id")
        mPresenter?.enquiryDetails(id)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        bottom_but.rlBut.setOnClickListener {
            //TODO 后期得改 status
            when (mResponse?.basicInfo?.status) {//status
                "101" -> {//询价中 取消询价 （已废弃）

                }
                "102" -> {//已报价 一键订舱 102
                    mPresenter?.oneTouch(id)
                    //点提交按钮 出弹窗
                    mAddSuccessDialog = AddSuccessDialog()
                            .setCountDownFinishListener {
                                mPresenter?.enquiryDetails(id)
                            }
                    mAddSuccessDialog?.show(supportFragmentManager, "")
                }
                else -> {

                }
            }
        }

        iv_copy.setOnClickListener {
            if (TextUtils.isEmpty(mResponse?.basicInfo?.inquiryNo)) {
                AirToast.showToast("暂无询价单号")
                return@setOnClickListener
            }
            CopyTextUtil.putTextIntoClip(mContext, mResponse?.basicInfo?.inquiryNo)
        }

        tv_see_all.setOnClickListener {
            BottomGoodsInfoDialog
                    .setListAndType(mResponse?.commodityList)
                    .show(supportFragmentManager, "")
        }
    }

    override fun initPresenter() {
        mPresenter = EnquiryDetailsPresenter(mContext)
    }

    override fun setLayout(): Int {
        return R.layout.cargovoice_activity_enquiry_details
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onSuccess(response: EnquiryDetailsResponse?) {
        response?.let {
            this.mResponse = response
            //询价信息
            val basicInfo = response.basicInfo
            basicInfo.apply {
                tv_code.text = getString(R.string.inquiry_number) + "：" + if (TextUtils.isEmpty(inquiryNo)) "-" else inquiryNo
                tv_enquiry_time.text = if (TextUtils.isEmpty(inquiryCreateTime)) "-" else stampToDateTime(inquiryCreateTime.toLong())
                toolbar.title = if (TextUtils.isEmpty(status)) "-" else LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "statusType.json", status).nameCn
                if (status == "102") {
                    tv_money.text = "￥$quotedPrice"
                }

                //TODO 后期得改 status
                when (status) {//status
                    "101" -> {//询价中 取消询价
                        bottom_but.visibility = View.GONE
                        tv_money.visibility = View.GONE
                    }
                    "102" -> {//已报价 一键订舱 //102
                        bottom_but.visibility = View.VISIBLE
                        bottom_but.setTvButText(getString(R.string.one_click_booking))
                        tv_money.visibility = View.VISIBLE
                    }
                    else -> {
                        bottom_but.visibility = View.GONE
                        tv_money.visibility = View.GONE
                    }
                }
            }

            //地址信息
            var locationInfo = response.locationInfo
            locationInfo.apply {
                tv_start_name.text = "$portOfOriginName($portOfOriginCode)"
                tv_terminus.text = "$portOfDestinationName($portOfDestinationCode)"
                tv_time.text = "EDT $departureDate"
            }

            //业务类型
            var businessInfo = response.businessInfo
            businessInfo.apply {
                tv_plane_or_ship.text = LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "transportMode（运输方式）.json", transportModeCode).nameCn
                iv_plane_or_ship.setImageDrawable(ContextCompat.getDrawable(mContext, if (transportModeCode == "SEA") R.drawable.svg_ic_ship_white else R.drawable.svg_ic_plane_white))
            }

            //集装箱
            val containerList = response.containerList
            ll_container_content.visibility = if (ListUtils.isEmpty(containerList)) View.GONE else View.VISIBLE
            ll_container_content.removeAllViews()
            containerList?.apply {
                for (i in 0 until containerList.size) {
                    val view = LayoutInflater.from(mContext).inflate(R.layout.cargovoice_item_details_container, null)
                    val bean = containerList.get(i);
                    val tvContainerMode = view.findViewById<TextView>(R.id.tv_container_mode)
                    val tvContainerNum = view.findViewById<TextView>(R.id.tv_container_num)
                    val containerType = LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "containerType（箱型）.json", bean.containerTypeCode)
                    tvContainerMode.text = getString(R.string.box) + ":" + if (isEnglist) containerType.code else containerType.nameCn
                    tvContainerNum.text = getString(R.string.container_volume) + ":" + fmtMicrometer(bean.containerCount)
                    if (i == containerList.size - 1) {
                        view.findViewById<View>(R.id.view_line).visibility = View.GONE
                    }
                    ll_container_content.addView(view, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))
                }
            }

            //商品信息
            var commodityList = arrayListOf<EnquiryDetailsResponse.CommodityListBean>()
            if (!ListUtils.isEmpty(response.commodityList)) {
                for (i in 0 until response.commodityList.size) {
                    commodityList.add(response.commodityList[i])
                    if (i == 1) {
                        break
                    }
                }
            }
            ll_goods_info.visibility = if (ListUtils.isEmpty(commodityList)) View.GONE else View.VISIBLE
            showHied(ll_goods_info, tv_see_all, response.commodityList)
            rv_goods_info.adapter = GoodsInfoAdapter(commodityList)
            //运输服务
            var transportService = response.transportService
            transportService.apply {
                sv_serve_rank.setLeft(LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "serviceLevel（服务级别）.json", serviceLevelCode).nameCn)
                sv_serve_clause.setLeft(LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "incoTerm（贸易条款）.json", incoTermCode).nameCn)
            }
            //关联人 （收货人）
            var organizationList = response.organizationList
            if (!ListUtils.isEmpty(organizationList)) {
                for (bean in organizationList) {
                    if (bean.category == 3) {
                        sv_consignee.setLeft(bean.companyName)
                        sv_linkman.setLeft(bean.contact)
                        sv_phone.setLeft(bean.phone)
                        tv_take_location.text = bean.countryName + " " + bean.stateName + " " + bean.city
                        tv_take_details.text = bean.address1
                        break
                    }
                }
            }

            //费用信息
            var jobCosting = response.jobCosting
            ll_job_costing_parent.visibility = if (jobCosting == null || TextUtils.isEmpty(jobCosting.currencyCode)) View.GONE else View.VISIBLE
            ll_job_costing_content.removeAllViews()
            jobCosting?.apply {
                //费用总数
                tv_sum_money.text = currencyCode + fmtMicrometer(totalWIP)
                chargeLineList?.forEach {
                    val sv = StripView(mContext)
                    sv.setName(it.chargeCodeDesc)
                    sv.setRight(it.sellOSCurrencyCode + fmtMicrometer(it.costLocalAmount))
                    ll_job_costing_content.addView(sv, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))
                }
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

    override fun oneTouch(response: OneTouchResponse?) {
        mAddSuccessDialog?.commitSucceed()
    }

    override fun onError(result: String) {
        mAddSuccessDialog?.commitFail()
    }

    companion object {
        @JvmStatic
        fun launchActivity(context: Context, id: String?) {
            val intent = Intent(context, EnquiryDetailsActivity::class.java)
            intent.putExtra("id", id)
            context.startActivity(intent)
        }
    }
}