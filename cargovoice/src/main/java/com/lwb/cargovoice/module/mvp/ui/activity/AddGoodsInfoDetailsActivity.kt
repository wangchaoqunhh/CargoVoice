package com.lwb.cargovoice.module.mvp.ui.activity

import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import android.view.View
import com.cargo.basecommon.base.*
import com.cargo.basecommon.bean.EnquiryAddGoalRequest
import com.cargo.basecommon.bean.GsonBean
import com.cargo.basecommon.utils.AirToast
import com.cargo.basecommon.utils.LocalJsonResolutionUtils.getGsonBeanByFileNameCode
import com.cargo.basecommon.utils.LocalJsonResolutionUtils.getJsonListBean
import com.lwb.cargovoice.R
import com.lwb.cargovoice.view.SelectDictionaryWindow
import kotlinx.android.synthetic.main.cargovoice_activity_add_goods_info_details.*

class AddGoodsInfoDetailsActivity : BaseFragmentActivity<IView<*>?, IPresenter?>() {
    private lateinit var mRequest: EnquiryAddGoalRequest

    private var bean = EnquiryAddGoalRequest.CommodityListBean()

    private var position = -1

    override fun init() {
        mRequest = (application as BaseApplication).request
        initListener()

        position = intent.getIntExtra("position", -1)
        //第一次进来也要显示 因为有默认的显示 是在类中写死的
        showOldData(position)
    }

    private fun showOldData(position: Int) {
        if (position != -1) {
            bean = mRequest.commodityList.get(position)
        } else {
            //货件类别
            bean.commodityCode = getJsonListBean(mContext, "commodityType(货件类别).json")?.get(0)?.code
            bean.commodityDesc = getGsonBeanByFileNameCode(mContext, "commodityType(货件类别).json", bean.commodityCode).nameCn
            //数量
            bean.packTypeCode = getJsonListBean(mContext, "packageType（包装类型）.json")?.get(0)?.code
            bean.packTypeDesc = getGsonBeanByFileNameCode(mContext, "packageType（包装类型）.json", bean.packTypeCode).nameCn
            //体积
            bean.volumeUnitCode = getJsonListBean(mContext, "volumeUnit（体积单位）.json")?.get(0)?.code
            bean.volumeUnitDesc = getGsonBeanByFileNameCode(mContext, "volumeUnit（体积单位）.json", bean.volumeUnitCode).nameCn
            //重量
            bean.weightUnitCode = getJsonListBean(mContext, "weightUnit（重量单位）.json")?.get(0)?.code
            bean.weightUnitDesc = getGsonBeanByFileNameCode(mContext, "weightUnit（重量单位）.json", bean.weightUnitCode).nameCn
        }
        val jsonCommodityType =
                getGsonBeanByFileNameCode(mContext, "commodityType(货件类别).json", bean.commodityCode)
        et_goods_classes.setText(jsonCommodityType.nameCn)

        et_goods_describe.setText(bean.goodsDesc)

        pmv_packQty.setNum(bean.packQty)
        tv_packQty_unit.text = getGsonBeanByFileNameCode(mContext,
                "packageType（包装类型）.json",
                bean.packTypeCode).nameCn

        pmv_volume.setNum(bean.volume)
        tv_volume_unit.text = getGsonBeanByFileNameCode(mContext,
                "volumeUnit（体积单位）.json",
                bean.volumeUnitCode).nameCn

        pmv_weight.setNum(bean.weight)
        tv_weight_unit.text = getGsonBeanByFileNameCode(mContext,
                "weightUnit（重量单位）.json",
                bean.weightUnitCode).nameCn

        et_mark.setText(bean.mark)

        if (!TextUtils.isEmpty(bean.imoClass)) {
            switch_is_danger.isChecked = true
            ev_imoClass.setText(getGsonBeanByFileNameCode(mContext,
                    "dangerousGoodsDegree（危险品级别）.json",
                    bean.imoClass).nameCn)
            ev_hs_code.setText(bean.hsCode)
        }
    }

    private fun initListener() {
        et_goods_classes.setOnClickListener {
            GoodsTypeActivity.launchActivity(this, 1000)
        }

        et_goods_describe.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus && !TextUtils.isEmpty(et_goods_describe.text)) {
                bean.goodsDesc = et_goods_describe.text.toString()
            }
        }

        //数量
        pmv_packQty.setOnBackNum(false) {
            bean.packQty = it
        }
        //数量 单位
        rl_packQty_unit.setOnClickListener {
            val window = SelectDictionaryWindow(mContext, "packageType（包装类型）.json") {
                bean.packTypeCode = it.code
                bean.packTypeDesc = it.nameCn
                tv_packQty_unit.text = it.nameCn
            }
            window.show()
        }

        //体积
        pmv_volume.setOnBackNum(true) {
            bean.volume = it
        }
        rl_volume_unit.setOnClickListener {
            val window = SelectDictionaryWindow(mContext, "volumeUnit（体积单位）.json") {
                bean.volumeUnitCode = it.code
                bean.volumeUnitDesc = it.nameCn
                tv_volume_unit.text = it.nameCn
            }
            window.show()
        }
        //重量
        pmv_weight.setOnBackNum(true) {
            bean.weight = it
        }
        rl_weight_unit.setOnClickListener {
            val window = SelectDictionaryWindow(mContext, "weightUnit（重量单位）.json") {
                bean.weightUnitCode = it.code
                bean.weightUnitDesc = it.nameCn
                tv_weight_unit.text = it.nameCn
            }
            window.show()
        }

        et_mark.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus && !TextUtils.isEmpty(et_mark.text)) {
                bean.mark = et_mark.text.toString()
            }
        }

        switch_is_danger.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                ll_danger.visibility = View.VISIBLE
            } else {
                ll_danger.visibility = View.GONE
                bean.imoClass = null
                bean.hsCode = null
            }
        }

        ev_imoClass.setOnClickListener {
            val window = SelectDictionaryWindow(mContext, "dangerousGoodsDegree（危险品级别）.json") {
                bean.imoClass = it.code
                ev_imoClass.setText(it.nameCn)
            }
            window.show()
        }

        ev_hs_code.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus && !TextUtils.isEmpty(ev_hs_code.text)) {
                bean.hsCode = ev_hs_code.text.toString()
            }
        }

        but_save.rlBut.setOnClickListener {
            if (isEmptyCheck()) return@setOnClickListener
            if (position == -1) {
                mRequest.commodityList.add(bean)
            }
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    override fun initPresenter() {

    }

    override fun setLayout(): Int = R.layout.cargovoice_activity_add_goods_info_details

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                1000 -> {
                    val jsonBean = data?.getSerializableExtra("jsonBean") as GsonBean
                    bean.commodityCode = jsonBean.code
                    bean.commodityDesc = jsonBean.nameCn
                    et_goods_classes.setText(jsonBean.nameCn)
                }
            }
        }
    }

    /**
     * 必填项 检查 有 空 返回 true
     */
    private fun isEmptyCheck(): Boolean {
        if (TextUtils.isEmpty(bean.commodityCode)) {
            AirToast.showToast("请选择货件类别")
            return true
        }
        if (TextUtils.isEmpty(bean.packQty) || (bean.packQty).toInt() < 1) {
            AirToast.showToast("请输入数量")
            return true
        }
        if (TextUtils.isEmpty(bean.packTypeCode)) {
            AirToast.showToast("请选择数量单位")
            return true
        }
        if (TextUtils.isEmpty(bean.volume) || (bean.volume).toDouble() <= 0) {
            AirToast.showToast("请输入体积")
            return true
        }
        if (TextUtils.isEmpty(bean.volumeUnitCode)) {
            AirToast.showToast("请选择体积单位")
            return true
        }
        if (TextUtils.isEmpty(bean.weight) || (bean.weight).toDouble() <= 0) {
            AirToast.showToast("请输入重量")
            return true
        }
        if (TextUtils.isEmpty(bean.weightUnitCode)) {
            AirToast.showToast("请选择重量单位")
            return true
        }
        if (TextUtils.isEmpty(bean.mark)) {
            AirToast.showToast("请输入唛头")
            return true
        }
        if (switch_is_danger.isChecked && TextUtils.isEmpty(bean.imoClass)) {
            AirToast.showToast("请选择危险品等级")
            return true
        }
        if (switch_is_danger.isChecked && TextUtils.isEmpty(bean.hsCode)) {
            AirToast.showToast("请输入HS编码")
            return true
        }
        return false
    }

    companion object {
        /**
         * @param
         */
        fun launchActivity(activity: Activity, requestCode: Int, position: Int? = null) {
            val intent = Intent(activity, AddGoodsInfoDetailsActivity::class.java)
            intent.putExtra("position", position)
            activity.startActivityForResult(intent, requestCode)
        }
    }
}