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
import com.cargo.basecommon.utils.AirToast
import com.cargo.basecommon.utils.CopyTextUtil
import com.cargo.basecommon.utils.ListUtils
import com.cargo.basecommon.utils.TimeUtils.stampToDateTime
import com.lwb.cargovoice.R
import com.lwb.cargovoice.adapter.GoodsInfoAdapter3
import com.lwb.cargovoice.module.mvp.contract.BookingSpaceDetailsContract
import com.lwb.cargovoice.module.mvp.entity.response.BookingSpaceDetailsResponse
import com.lwb.cargovoice.module.mvp.presenter.BookingSpaceDetailsPresenter
import com.lwb.cargovoice.utils.fmtMicrometer
import com.lwb.cargovoice.view.BottomGoodsInfoDialog
import kotlinx.android.synthetic.main.cargovoice_activity_booking_space_details.*

class BookingSpaceDetailsActivity : BaseFragmentActivity<BookingSpaceDetailsContract.View?, BookingSpaceDetailsPresenter?>(), BookingSpaceDetailsContract.View {
    private var mResponse: BookingSpaceDetailsResponse? = null

    override fun init() {
        mPresenter?.bookingSpaceDetails(intent.getStringExtra("id"))

        toolbar.setNavigationOnClickListener {
            finish()
        }

        iv_copy.setOnClickListener {
            if (TextUtils.isEmpty(mResponse?.basicInfo?.ibookingNo)) {
                AirToast.showToast("暂无订舱单号")
                return@setOnClickListener
            }
            CopyTextUtil.putTextIntoClip(mContext, mResponse?.basicInfo?.ibookingNo)
        }

        tv_see_all.setOnClickListener {
            BottomGoodsInfoDialog
                    .setListAndType(mResponse?.commodityList)
                    .show(supportFragmentManager, "")
        }
    }

    override fun initPresenter() {
        mPresenter = BookingSpaceDetailsPresenter(mContext)
    }

    override fun setLayout(): Int {
        return R.layout.cargovoice_activity_booking_space_details
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onSuccess(response: BookingSpaceDetailsResponse?) {
        response?.let {
            this.mResponse = response
            //询价信息
            val basicInfo = response.basicInfo
            basicInfo.apply {
                tv_code.text = "订舱单号： " + if (TextUtils.isEmpty(ibookingNo)) "-" else ibookingNo
                tv_enquiry_time.text = if (TextUtils.isEmpty(ibookingCreateTime)) "-" else stampToDateTime(ibookingCreateTime.toLong())
                toolbar.title = statusDesc
//            tv_money.text = "￥" + quotedPrice
            }

            //地址信息
            var locationInfo = response.locationInfo
            locationInfo.apply {
                tv_start_name.text = "$portOfOriginName($portOfOriginCode)"
                tv_terminus.text = "$portOfDestinationName($portOfDestinationCode)"
                tv_time.text = "ETD $departureDate"
            }

            //业务类型
            var businessInfo = response.businessInfo
            businessInfo.apply {
                tv_plane_or_ship.text = transportModeDesc
                iv_plane_or_ship.setImageDrawable(ContextCompat.getDrawable(mContext, if (transportModeCode == "SEA") R.drawable.svg_ic_ship_white else R.drawable.svg_ic_plane_white))
            }

            //集装箱
            val containerList = response.containerList
            ll_container_content.visibility = if (ListUtils.isEmpty(containerList)) View.GONE else View.VISIBLE
            containerList.apply {
                for (i in 0 until containerList.size) {
                    val view = LayoutInflater.from(mContext).inflate(R.layout.cargovoice_item_details_container, null)
                    val bean = containerList.get(i);
                    val tvContainerMode = view.findViewById<TextView>(R.id.tv_container_mode)
                    val tvContainerNum = view.findViewById<TextView>(R.id.tv_container_num)
                    tvContainerMode.text = "箱型：" + bean.containerTypeDesc
                    tvContainerNum.text = "箱量：" + fmtMicrometer(bean.containerCount)
                    if (i == containerList.size - 1) {
                        view.findViewById<View>(R.id.view_line).visibility = View.GONE
                    }
                    ll_container_content.addView(view, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))
                }
            }

            //商品信息
            var commodityList = arrayListOf<BookingSpaceDetailsResponse.CommodityListBean>()
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
            rv_goods_info.adapter = GoodsInfoAdapter3(commodityList)
            //运输服务
            var transportService = response.transportService
            transportService.apply {
                sv_serve_rank.setLeft(serviceLevelDesc)
                sv_serve_clause.setLeft(incoTermDesc)
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

    override fun onError(result: String) {}

    companion object {
        fun launchActivity(context: Context, id: String?) {
            val intent = Intent(context, BookingSpaceDetailsActivity::class.java)
            intent.putExtra("id", id)
            context.startActivity(intent)
        }
    }
}