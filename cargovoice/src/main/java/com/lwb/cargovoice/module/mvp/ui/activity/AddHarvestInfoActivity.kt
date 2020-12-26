package com.lwb.cargovoice.module.mvp.ui.activity

import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import android.view.View
import com.cargo.basecommon.base.BaseApplication
import com.cargo.basecommon.base.BaseFragmentActivity
import com.cargo.basecommon.bean.EnquiryAddGoalRequest
import com.cargo.basecommon.utils.AirToast
import com.lwb.cargovoice.R
import com.lwb.cargovoice.module.mvp.contract.AddHarvestInfoContract
import com.lwb.cargovoice.module.mvp.entity.response.AddHarvestInfoResponse
import com.lwb.cargovoice.module.mvp.entity.response.SelectRegionBean
import com.lwb.cargovoice.module.mvp.presenter.AddHarvestInfoPresenter
import com.lwb.cargovoice.utils.ClickToolsUtil.isFastDoubleClick
import com.lwb.cargovoice.view.BottomRegionDialog
import kotlinx.android.synthetic.main.cargovoice_activity_add_harvest_info.*

class AddHarvestInfoActivity : BaseFragmentActivity<AddHarvestInfoContract.View, AddHarvestInfoPresenter>(), AddHarvestInfoContract.View {
    private lateinit var mRequest: EnquiryAddGoalRequest

    private var bean = EnquiryAddGoalRequest.ConsigneeBean()

    override fun init() {
        mRequest = (application as BaseApplication).request
        val title: String? = intent.getStringExtra("title")

        if (title == "1") {
            bean = mRequest.consignor
            bean.category = 1
            et_company.visibility = View.GONE
            commonToolbar.setTvTitle(getString(R.string.shipments_information))
        } else if (title == "2") {
            bean = mRequest.consignee
            bean.category = 3
            commonToolbar.setTvTitle(getString(R.string.delivery_information))
        }

        showOldData()

        //公司名称
        et_company.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus && !TextUtils.isEmpty(et_company.text)) {
                bean.companyName = et_company.text.toString()
            }
        }

        //联系人
        et_linkman_name.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus && !TextUtils.isEmpty(et_linkman_name.text)) {
                bean.contact = et_linkman_name.text.toString()
            }
        }

        //联系人电话
        et_linkman_phone.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus && !TextUtils.isEmpty(et_linkman_phone.text)) {
//                if (!MatchUtil.matchTel(et_linkman_phone.text.toString())) {
//                    //手机号验证未通过
//                    AirToast.showToast("手机号码有误")
//                    return@OnFocusChangeListener
//                }
                bean.phone = et_linkman_phone.text.toString()
            }
        }

        et_location.setOnClickListener {
            if (isFastDoubleClick) return@setOnClickListener
            when (title) {
                "1" -> {
                    mPresenter.organization("1")
                }
                "2" -> {
                    mPresenter.organization("2")
                }
            }
        }

//        //公司所在地址
//        bean.city
//        //公司所在国家代号
//        bean.countryCode
//        //公司所在国家名称
//        bean.countryName
//        //公司Email
//        bean.email
//        //公司邮编
//        bean.postcode
//        //公司所在省份/州
//        bean.state

        //公司地址
        et_detail_location.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus && !TextUtils.isEmpty(et_detail_location.text)) {
                bean.address1 = et_detail_location.text.toString()
            }
        }

        et_postcode.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus && !TextUtils.isEmpty(et_postcode.text)) {
                bean.postcode = et_postcode.text.toString()
            }
        }

        but_save.rlBut.setOnClickListener {
            if (isEmptyCheck()) return@setOnClickListener
            when (title) {
                "1" -> mRequest.consignor = bean
                "2" -> mRequest.consignee = bean
            }
            setResult(Activity.RESULT_OK)
            finish()
        }

    }

    private fun showOldData() {
        if (bean.companyName != null) {
            et_company.setText(bean.companyName)
        }
        if (bean.contact != null) {
            et_linkman_name.setText(bean.contact)
        }

        if (bean.phone != null) {
            et_linkman_phone.setText(bean.phone)
        }
        if (bean.countryName != null && bean.stateName != null && bean.city != null) {
            et_location.setText(bean.countryName + " " + bean.stateName + " " + bean.city)

        }
        if (bean.address1 != null) {
            et_detail_location.setText(bean.address1)
        }
        if (bean.postcode != null) {
            et_postcode.setText(bean.postcode)
        }
    }

    private fun isEmptyCheck(): Boolean {
        if (et_company.visibility != View.GONE && TextUtils.isEmpty(bean.companyName)) {
            AirToast.showToast(getString(R.string.please_enter_a_name))
            return true
        }
        if (TextUtils.isEmpty(bean.contact)) {
            AirToast.showToast(getString(R.string.please_enter_a_contact))
            return true
        }
        if (TextUtils.isEmpty(bean.phone)) {
            AirToast.showToast(getString(R.string.please_enter_the_correct_contact_number))
            return true
        }
        if (TextUtils.isEmpty(bean.countryCode)) {
            AirToast.showToast(getString(R.string.please_enter_country_province_city))
            return true
        }
        if (TextUtils.isEmpty(bean.address1)) {
            AirToast.showToast(getString(R.string.please_enter_the_detailed_address))
            return true
        }
        return false
    }


    override fun initPresenter() {
        mPresenter = AddHarvestInfoPresenter(mContext)
    }

    override fun setLayout(): Int {
        return R.layout.cargovoice_activity_add_harvest_info
    }

    override fun onSuccess(response: AddHarvestInfoResponse?) {

    }

    override fun organization(response: List<AddHarvestInfoResponse>?) {
        BottomRegionDialog()
                .setList(response)
                .setBackSelectData(object : BottomRegionDialog.BackSelectData {
                    override fun onBackSelectData(backBean: AddHarvestInfoResponse?) {
                        backBean?.apply {
                            //公司地址
                            bean.address1 = address1
                            //公司所在城市
                            bean.city = city
                            //公司所在国家代号
                            bean.countryCode = countryCode
                            //公司所在国家名称
                            bean.countryName = countryName
                            //公司Email
                            bean.email = email
                            //公司邮编
                            bean.postcode = postCode
                            //公司所在省份/州
                            bean.state = state
                            bean.stateName = stateName

                            //发货信息
                            if (bean.category == 3) {
                                bean.companyName = companyName
                                bean.contact = contact
                                //公司名称
                                et_company.setText(companyName)
                                //联系人名称
                                et_linkman_name.setText(contact)
                            }

                            et_location.setText(bean.countryName + " " + bean.stateName + " " + bean.city)
                            et_detail_location.setText(address1)
                            et_postcode.setText(postCode)
                        }
                    }

                    override fun onAddLocation() {
                        SelectRegionActivity.launchActivity(this@AddHarvestInfoActivity, 1000)
                    }

                })
                .show(supportFragmentManager, "")
    }

    override fun onError(result: String?) {
        AirToast.showToast(result)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                1000 -> {
                    val locationList = data?.getSerializableExtra("result") as ArrayList<SelectRegionBean>
                    //公司地址
                    bean.address1
                    //公司所在城市
                    bean.city = locationList[3].name
                    //公司所在国家代号
                    bean.countryCode = locationList[1].code
                    //公司所在国家名称
                    bean.countryName = locationList[1].name
                    //公司Email
                    bean.email
                    //公司邮编
                    bean.postcode
                    //公司所在省份/州
                    bean.state = locationList[2].code
                    bean.stateName = locationList[2].name

                    et_location.setText(bean.countryName + " " + bean.stateName + " " + bean.city)
//                    et_detail_location.setText(address1)
//                    et_postcode.setText(fax)
                }
            }
        }
    }

    companion object {
        /**
         * @param 1发 2收
         */
        fun launchActivity(activity: Activity, requestCode: Int, title: String) {
            val intent = Intent(activity, AddHarvestInfoActivity::class.java)
            intent.putExtra("title", title)
            activity.startActivityForResult(intent, requestCode)
        }
    }
}