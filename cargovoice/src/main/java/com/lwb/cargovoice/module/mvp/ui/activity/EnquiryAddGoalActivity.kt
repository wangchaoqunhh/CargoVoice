package com.lwb.cargovoice.module.mvp.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.cargo.basecommon.base.BaseApplication
import com.cargo.basecommon.base.BaseFragmentActivity
import com.cargo.basecommon.bean.EnquiryAddGoalRequest
import com.cargo.basecommon.utils.AirToast
import com.cargo.basecommon.utils.ListUtils
import com.cargo.basecommon.utils.LocalJsonResolutionUtils
import com.lwb.cargovoice.R
import com.lwb.cargovoice.constant.Constant.Enquiry_BookingSpace
import com.lwb.cargovoice.module.mvp.contract.EnquiryAddGoalContract
import com.lwb.cargovoice.module.mvp.entity.response.AIResponse
import com.lwb.cargovoice.module.mvp.entity.response.EnquiryAddGoalResponse
import com.lwb.cargovoice.module.mvp.presenter.EnquiryAddGoalPresenter
import com.lwb.cargovoice.view.AddSuccessDialog
import com.lwb.cargovoice.view.BottomCarriageWayDialog
import com.lwb.cargovoice.view.BottomTimeDialog
import com.lwb.cargovoice.view.SelectDictionaryWindow
import kotlinx.android.synthetic.main.cargovoice_activity_enquiry_add_goal.*
import org.greenrobot.eventbus.EventBus

/***
 * 我的询价
 * 我的订舱
 * 添加
 */
@Route(path = "/cargovoice/EnquiryAddGoalActivity")
class EnquiryAddGoalActivity : BaseFragmentActivity<EnquiryAddGoalContract.View?, EnquiryAddGoalPresenter?>(), EnquiryAddGoalContract.View {
    //提交时候弹窗
    private var mAddSuccessDialog: AddSuccessDialog? = null

    private lateinit var mRequest: EnquiryAddGoalRequest

    //如果在列表进 enquiryBookingSpace会是0 而且有 运输方式、起运时间、起运港、目的港
    private var enquiryBookingSpace = 0

    //Ai 传来的的对象
    var order: AIResponse.Order? = null

    override fun init() {
        //如果==0 说明 是在列表页进的 需要显示 数据
        //如果 order！=null 说明AI来的 得显示数据
        if (enquiryBookingSpace == 0 || order != null) {
            initView()
        } else {
            settingTransportMode()
        }
        initListener()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        mContext = this
        //这个初始化必须在setContentView之前
        //如果在列表进 enquiryBookingSpace会是0 而且有 运输方式、起运时间、起运港、目的港
        enquiryBookingSpace = intent.getIntExtra("Enquiry_BookingSpace", 0)
        order = intent.getParcelableExtra("order")
        if (enquiryBookingSpace != 0) {
            //1询价 2订舱 用来判断请求接口时候
            Enquiry_BookingSpace = enquiryBookingSpace
            //从快速询价进来的 这时候 大对象还没有实例化 所以在这new，
            EnquiryAddGoalRequest.newInstance(application as BaseApplication)
            val transportMode = LocalJsonResolutionUtils.getJsonListBean(mContext, "transportMode（运输方式）.json")[0]
            (application as BaseApplication).request.businessInfo.transportModeCode = transportMode.code
            (application as BaseApplication).request.businessInfo.transportModeDesc = transportMode.nameCn
        }
        mRequest = (application as BaseApplication).request
        if (order != null) {
            AISetBean()
        }
        super.onCreate(savedInstanceState)
    }

    override fun setLayout(): Int = R.layout.cargovoice_activity_enquiry_add_goal

    //设置运输类型
    private fun settingTransportMode() {
        iv_transport_mode.setImageDrawable(if (mRequest.businessInfo.transportModeCode == "SEA") getDrawable(R.drawable.svg_ic_ship_white) else getDrawable(R.drawable.svg_ic_plane_white))
        tv_transport_mode.text = LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext,
                "transportMode（运输方式）.json",
                mRequest.businessInfo.transportModeCode).nameCn
    }

    private fun initView() {
        settingTransportMode()

        tv_departure_date.text = "ETD " + mRequest.locationInfo.departureDate
        tv_origin_code.text = mRequest.locationInfo.portOfOriginCode
        tv_origin_name.text = mRequest.locationInfo.portOfOriginName
        tv_destination_code.text = mRequest.locationInfo.portOfDestinationCode
        tv_destination_name.text = mRequest.locationInfo.portOfDestinationName

        //设置装箱方式
        if (!TextUtils.isEmpty(mRequest.businessInfo.containerModeCode)) {
            if (mRequest.businessInfo.containerModeCode == "LCL" || mRequest.businessInfo.containerModeCode == "LSE") {
                ll_bulk.isSelected = true
                ll_encasement.isSelected = false
            } else if (mRequest.businessInfo.containerModeCode == "FCL" || mRequest.businessInfo.containerModeCode == "ULD") {
                ll_bulk.isSelected = false
                ll_encasement.isSelected = true
                //存箱型号的
                val containerType = HashMap<String, String>()
                var containerCount = 0
                for (containerListBean in mRequest.containerList) {
                    if (!containerType.containsKey(containerListBean.containerTypeCode)) {
                        containerType.put(containerListBean.containerTypeCode, "")
                    }
                    containerCount += containerListBean.containerCount
                }

                add_container_view.setContainerTypeAndNum(containerType.size.toString(), containerCount.toString())
                add_container_view.visibility = View.VISIBLE
            }
        }

        //设置货件信息
        add_goods_info.settingShow()

        //服务级别
        val json = LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext,
                "serviceLevel（服务级别）.json",
                mRequest.transportService.serviceLevelDesc)
        et_service_level.setText(json.nameCn)

        //贸易条款
        et_inco_term.setText(mRequest.transportService.incoTermCode)

        //发货信息
        add_deliver_info.setCompany(mRequest.consignor?.companyName)
                .setLocation(mRequest.consignor?.countryName + " " + mRequest.consignor?.stateName + " " + mRequest.consignor?.city)
                .setDescribe(mRequest.consignor?.address1)
                .setPeople(mRequest.consignor?.contact)
                .setPhone(mRequest.consignor?.phone)
                .addData()

        //收货信息
        add_harvest_info.setCompany(mRequest.consignee?.companyName)
                .setLocation(mRequest.consignee?.countryName + " " + mRequest.consignee?.stateName + " " + mRequest.consignee?.city)
                .setDescribe(mRequest.consignee?.address1)
                .setPeople(mRequest.consignee?.contact)
                .setPhone(mRequest.consignee?.phone)
                .addData()
    }

    private fun initListener() {
        //切换 运输方式
        ll_transport_mode.setOnClickListener {
            BottomCarriageWayDialog.getInstant()
                    .setBack {
                        //1海 2空
                        var newTransportModeCode = if (it == "1") "SEA" else "AIR"
                        if (newTransportModeCode != mRequest.businessInfo.transportModeCode) {
                            mRequest.businessInfo.transportModeCode = newTransportModeCode
                            mRequest.businessInfo.transportModeDesc = LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "transportMode（运输方式）.json", newTransportModeCode).nameCn
                            settingTransportMode()
                            //清除集装箱 集合
                            mRequest.containerList.clear()
                            //让两个箱都 取消选中
                            ll_bulk.isSelected = false
                            ll_encasement.isSelected = false
                            //低下 添加集装箱信息 也取消掉 并隐藏
                            add_container_view.setContainerTypeAndNum("", "")
                            add_container_view.visibility = View.GONE
                        }
                    }.show(supportFragmentManager, "小弹窗")
        }

        //切换 起运时间
        ll_select_time.setOnClickListener {
            val dialog = BottomTimeDialog
                    .backSelectTime {
                        mRequest.locationInfo.departureDate = it?.year + "-" + it?.month + "-" + it?.day
                        tv_departure_date.text = "ETD " + mRequest.locationInfo.departureDate
                    }
            dialog.show(supportFragmentManager, "")
        }

        //切换起运 和目的港
        ll_origin.setOnClickListener {
            AddOriginActivity.launchActivity(this, 5000)
        }


        ll_bulk.setOnClickListener {
            ll_bulk.isSelected = true
            ll_encasement.isSelected = false
            var code = if (mRequest.businessInfo.transportModeCode == "SEA") {
                //海运 的散货code
                "LCL"
            } else {
                //空运 的散货code
                "LSE"
            }
            val json = LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "containerMode（装箱方式）.json", code)
            mRequest.businessInfo.containerModeCode = json.code
            mRequest.businessInfo.containerModeDesc = json.nameCn
            add_container_view.visibility = View.GONE
        }

        ll_encasement.setOnClickListener {
            if (TextUtils.isEmpty(mRequest.businessInfo.transportModeCode)) {
                AirToast.showToast(getString(R.string.please_select_shipping_method))
                return@setOnClickListener
            }
            ll_bulk.isSelected = false
            ll_encasement.isSelected = true
            var code = if (mRequest.businessInfo.transportModeCode == "SEA") {
                //海运 的集装箱code
                "FCL"
            } else {
                //空运 的集装箱code
                "ULD"
            }
            val json = LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "containerMode（装箱方式）.json", code)
            mRequest.businessInfo.containerModeCode = json.code
            mRequest.businessInfo.containerModeDesc = json.nameCn
            add_container_view.visibility = View.VISIBLE
        }

        //添加集装箱View
        add_container_view
                .setAddContainerListener {
                    AddContainerActivity.launchActivity(this, 1000)
                }
//                .setContainerTypeAndNum("", "")

        //添加货件信息view
        add_goods_info.setSeeAllListener {
            AddGoodsInfoActivity.launchActivity(this, 2000)
        }.llAddGoodsInfo.setOnClickListener {
            AddGoodsInfoActivity.launchActivity(this, 2000)
        }
        //服务级别
        service_level.setOnClickListener {
            val window = SelectDictionaryWindow(mContext, "serviceLevel（服务级别）.json") {
                mRequest.transportService.serviceLevelCode = it.code
                mRequest.transportService.serviceLevelDesc = it.nameCn
                et_service_level.setText(it.nameCn)
            }
            window.show()
        }
        //贸易条款
        inco_term.setOnClickListener {
            val window = SelectDictionaryWindow(mContext, "incoTerm（贸易条款）.json") {
                mRequest.transportService.incoTermCode = it.code
                mRequest.transportService.incoTermDesc = it.nameCn
                et_inco_term.setText(it.code)
            }
            window.show()
        }

        //添加发货信息
        add_deliver_info.setPeopleInfoClickListener {
            AddHarvestInfoActivity.launchActivity(this, 3000, "1")
        }

        //添加收货信息
        add_harvest_info.setPeopleInfoClickListener {
            AddHarvestInfoActivity.launchActivity(this, 4000, "2")
        }

        commit.rlBut.setOnClickListener {
            //业务类型
            mRequest.businessInfo.containerModeCode
            mRequest.businessInfo.containerModeDesc
            mRequest.businessInfo.transportModeCode
            mRequest.businessInfo.transportModeDesc
            //时间地点
            mRequest.locationInfo.departureDate
            mRequest.locationInfo.portOfOriginCode
            mRequest.locationInfo.portOfOriginName
            mRequest.locationInfo.portOfDestinationCode
            mRequest.locationInfo.portOfDestinationName

            //商品信息 list
            val commodityBean = EnquiryAddGoalRequest.CommodityListBean()
//            商品类别
//            - 商品描述
//            - 商品数量/单位
//            - 商品体积/单位
//            - 商品重量/单位
//            - 唛头
//            - 危险品标识
//            - 危险品等级
//            - HS编码
            commodityBean.commodityCode
            commodityBean.commodityDesc
            commodityBean.packQty
            commodityBean.packTypeCode
            commodityBean.packTypeDesc
            commodityBean.volume
            commodityBean.volumeUnitCode
            commodityBean.volumeUnitDesc
            commodityBean.weight
            commodityBean.weightUnitCode
            commodityBean.weightUnitDesc
            commodityBean.mark
            commodityBean.id
            commodityBean.imoClass
            commodityBean.hsCode
//            mRequest.commodityList.add(commodityBean)

            //集装箱
            val containerListBean = EnquiryAddGoalRequest.ContainerListBean()
//            - 箱型
//            - 箱量
//            - SOC箱
//            - 集装箱号码
            containerListBean.containerTypeCode
            containerListBean.containerTypeDesc
            containerListBean.containerCount
            containerListBean.isShipperOwned
            containerListBean.containerNumber
//            mRequest.containerList.add(containerListBean)

            //运输服务
//            - 服务级别
//            - 贸易条款
            mRequest.transportService.serviceLevelCode
            mRequest.transportService.serviceLevelDesc
            mRequest.transportService.incoTermCode
            mRequest.transportService.incoTermDesc


            //关联方
//            - 发货人
//            - 收货人
//            - 联系人名称
//            - 联系电话
//            - 地址
//            - 国家
//            - 省市
//            - 详细地址
            mRequest.consignee.contact
            mRequest.consignee.phone
            mRequest.consignee.address1
            mRequest.consignee.countryCode
            mRequest.consignee.countryName
            mRequest.consignee.state
            mRequest.consignee.email

            if (isEmptyCheck()) return@setOnClickListener
            //如果选择的是散货 就把集装箱清空
            if (mRequest.businessInfo.containerModeCode == "LCL") {
                mRequest.containerList?.clear()
            }

            if (Enquiry_BookingSpace == 1) {
                //询价 添加
                mPresenter?.enquiryCreate(mRequest)
            } else if (Enquiry_BookingSpace == 2) {
                //订舱 添加
                mPresenter?.bookingSpaceCreate(mRequest)
            }
            //点提交按钮 出弹窗
            mAddSuccessDialog = AddSuccessDialog()
                    .setCountDownFinishListener {
                        //添加成功后会调用，
                        EventBus.getDefault().postSticky("1")
                        //清空提交数据
                        (application as BaseApplication).request = null
                        finish()
                    }
            mAddSuccessDialog?.show(supportFragmentManager, "")
        }
    }

    private fun isEmptyCheck(): Boolean {
        if (TextUtils.isEmpty(mRequest.businessInfo.transportModeCode)) {
            AirToast.showToast(getString(R.string.please_select_shipping_method))
            return true
        }
        if (TextUtils.isEmpty(mRequest.locationInfo.departureDate)) {
            AirToast.showToast(getString(R.string.please_select_the_departure_time))
            return true
        }
        if (TextUtils.isEmpty(mRequest.locationInfo.portOfOriginName)) {
            AirToast.showToast(getString(R.string.please_select_the_port_of_departure))
            return true
        }
        if (TextUtils.isEmpty(mRequest.businessInfo.containerModeCode)) {
            AirToast.showToast(getString(R.string.please_choose_a_container))
            return true
        }
        if ("FCL" == mRequest.businessInfo.containerModeCode && ListUtils.isEmpty(mRequest.containerList)) {
            AirToast.showToast(getString(R.string.please_add_container_information))
            return true
        }
        if (ListUtils.isEmpty(mRequest.commodityList)) {
            AirToast.showToast(getString(R.string.please_add_shipment_information))
            return true
        }
        if (TextUtils.isEmpty(mRequest.transportService.serviceLevelCode)) {
            AirToast.showToast(getString(R.string.please_select_service_level))
            return true
        }
        if (TextUtils.isEmpty(mRequest.transportService.incoTermCode)) {
            AirToast.showToast(getString(R.string.please_select_trade_terms))
            return true
        }
        if (TextUtils.isEmpty(mRequest.consignor?.contact)) {
            AirToast.showToast(getString(R.string.please_add_shipper_information))
            return true
        }
        if (TextUtils.isEmpty(mRequest.consignee?.contact)) {
            AirToast.showToast(getString(R.string.please_add_consignee_information))
            return true
        }
        return false
    }

    override fun initPresenter() {
        mPresenter = EnquiryAddGoalPresenter(mContext)
    }

    override fun onSuccess(response: EnquiryAddGoalResponse?) {
        mAddSuccessDialog?.commitSucceed()
    }

    override fun onError(result: String?) {
        mAddSuccessDialog?.commitFail()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                //集装箱 信息
                1000 -> {
                    //存箱型号的
                    val containerType = HashMap<String, String>()
                    var containerCount = 0
                    for (containerListBean in mRequest.containerList) {
                        if (!containerType.containsKey(containerListBean.containerTypeCode)) {
                            containerType.put(containerListBean.containerTypeCode, "")
                        }
                        containerCount += containerListBean.containerCount
                    }

                    add_container_view.setContainerTypeAndNum(containerType.size.toString(), containerCount.toString())
                }
                //货件信息
                2000 -> {
                    add_goods_info.settingShow()
                }
                //发货信息
                3000 -> {
                    add_deliver_info.setCompany(mRequest.consignor?.companyName)
                            .setLocation(mRequest.consignor?.countryName + " " + mRequest.consignor?.stateName + " " + mRequest.consignor?.city)
                            .setDescribe(mRequest.consignor?.address1)
                            .setPeople(mRequest.consignor?.contact)
                            .setPhone(mRequest.consignor?.phone)
                            .addData()
                }
                //收货信息
                4000 -> {
                    add_harvest_info.setCompany(mRequest.consignee?.companyName)
                            .setLocation(mRequest.consignee?.countryName + " " + mRequest.consignee?.stateName + " " + mRequest.consignee?.city)
                            .setDescribe(mRequest.consignee?.address1)
                            .setPeople(mRequest.consignee?.contact)
                            .setPhone(mRequest.consignee?.phone)
                            .addData()
                }
                //切换 起运港 和 目的港
                5000 -> {
                    tv_origin_code.text = mRequest.locationInfo.portOfOriginCode
                    tv_origin_name.text = mRequest.locationInfo.portOfOriginName
                    tv_destination_code.text = mRequest.locationInfo.portOfDestinationCode
                    tv_destination_name.text = mRequest.locationInfo.portOfDestinationName
                }
            }
        }
    }

    /**
     * 在AI页进来 set Bean
     */
    private fun AISetBean() {
        order?.apply {
            //商品信息 list
            val commodityBean = EnquiryAddGoalRequest.CommodityListBean()
//            商品类别
//            - 商品描述
//            - 商品数量/单位
//            - 商品体积/单位
//            - 商品重量/单位
//            - 唛头
//            - 危险品标识
//            - 危险品等级
//            - HS编码
            commodityBean.commodityCode = commodity_code
            commodityBean.commodityDesc = LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "commodityType(货件类别).json", commodity_code).nameCn

            commodityBean.packQty = commodity_package_quantity
            commodityBean.packTypeCode = commodity_package_unit_code
            commodityBean.packTypeDesc = LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "packageType（包装类型）.json", commodity_package_unit_code).nameCn

            commodityBean.volume = commodity_volume
            commodityBean.volumeUnitCode = commodity_volume_unit_code
            commodityBean.volumeUnitDesc = LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "volumeUnit（体积单位）.json", commodity_volume_unit_code).nameCn

            commodityBean.weight = commodity_gross_weight
            commodityBean.weightUnitCode = commodity_weight_unit_code
            commodityBean.weightUnitDesc = LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "weightUnit（重量单位）.json", commodity_weight_unit_code).nameCn

            commodityBean.mark = mark
            commodityBean.id
            commodityBean.imoClass
            commodityBean.goodsDesc = commodity_description
            commodityBean.hsCode = commodity_hs_code
            mRequest.commodityList.add(commodityBean)

            //TODO 收货人 发货人 AI实体和添加实体类，兑字段 可能有毛病
//            - 收货人 category = 3
//            - 联系人名称
//            - 联系电话
//            - 地址
//            - 国家
//            - 省市
//            - 详细地址
            mRequest.consignee.category = 3
            mRequest.consignee.contact = delivery_contact
            mRequest.consignee.phone = delivery_phone
            mRequest.consignee.address1 = delivery_addressline1
            mRequest.consignee.countryCode = delivery_country_code
            mRequest.consignee.countryName = delivery_country
            mRequest.consignee.state = delivery_state_code
            mRequest.consignee.stateName = delivery_state
            mRequest.consignee.postcode = delivery_postcode
            mRequest.consignee.city = delivery_city
            mRequest.consignee.companyName = consignee_organization_name
            mRequest.consignee.email

//            -发货人 category = 1
//            - 联系人名称
//            - 联系电话
//            - 地址
//            - 国家
//            - 省市
//            - 详细地址
            mRequest.consignor.category = 1
            mRequest.consignor.contact = pickup_contact
            mRequest.consignor.phone = pickup_phone
            mRequest.consignor.address1 = pickup_addressline1
            mRequest.consignor.countryCode = pickup_country_code
            mRequest.consignor.countryName = pickup_country
            mRequest.consignor.state = pickup_state_code
            mRequest.consignor.stateName = pickup_state
            mRequest.consignor.postcode = pickup_postcode
            mRequest.consignor.city = pickup_city
            mRequest.consignor.companyName = consignor_organization_name
            mRequest.consignor.email


            //业务类型
            mRequest.businessInfo.containerModeCode = container_mode
            mRequest.businessInfo.containerModeDesc = LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "containerMode（装箱方式）.json", container_mode).nameCn
            mRequest.businessInfo.transportModeCode = transport
            mRequest.businessInfo.transportModeDesc = LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "transportMode（运输方式）.json", transport).nameCn


            //集装箱
            val containerListBean = EnquiryAddGoalRequest.ContainerListBean()
//            - 箱型
//            - 箱量
//            - SOC箱
//            - 集装箱号码
            containerListBean.containerTypeCode = container_type
            containerListBean.containerTypeDesc = LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "containerType（箱型）.json", container_type).nameCn

            try {
                containerListBean.containerCount = container_quantity.toInt()
            } catch (e: Exception) {
                containerListBean.containerCount = 0
            }
            containerListBean.isShipperOwned
            containerListBean.containerNumber
            mRequest.containerList.add(containerListBean)

            //时间地点
            mRequest.locationInfo.departureDate = etd
            mRequest.locationInfo.portOfOriginCode = port_of_departure_code
            mRequest.locationInfo.portOfOriginName = port_of_departure
            mRequest.locationInfo.portOfDestinationCode = port_of_destination_code
            mRequest.locationInfo.portOfDestinationName = port_of_destination

            //运输服务
//            - 服务级别
//            - 贸易条款
            mRequest.transportService.serviceLevelCode = service_level_code
            mRequest.transportService.serviceLevelDesc = LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "serviceLevel（服务级别）.json", service_level_code).nameCn
            mRequest.transportService.incoTermCode = incoterms
            mRequest.transportService.incoTermDesc = LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "incoTerm（贸易条款）.json", incoterms).nameCn
        }
    }

    companion object {
        @JvmStatic
        fun launchActivity(context: Context) {
            val intent = Intent(context, EnquiryAddGoalActivity::class.java)
            context.startActivity(intent)
        }

        /**
         * @param Enquiry_BookingSpace 1询价 2订舱
         * @param order                AI的对象
         */
        @JvmStatic
        fun launchActivity(context: Context, Enquiry_BookingSpace: Int, order: AIResponse.Order) {
            val intent = Intent(context, EnquiryAddGoalActivity::class.java)
            intent.putExtra("Enquiry_BookingSpace", Enquiry_BookingSpace)
            intent.putExtra("order", order)
            context.startActivity(intent)
        }
    }
}