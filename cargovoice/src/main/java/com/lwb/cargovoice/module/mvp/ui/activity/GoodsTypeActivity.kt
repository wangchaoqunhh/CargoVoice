package com.lwb.cargovoice.module.mvp.ui.activity

import android.app.Activity
import android.content.Intent
import android.view.View
import com.cargo.basecommon.base.BaseActivity
import com.cargo.basecommon.base.IPresenter
import com.cargo.basecommon.base.IView
import com.cargo.basecommon.bean.GsonBean
import com.cargo.basecommon.utils.LocalJsonResolutionUtils.getJsonListBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lwb.cargovoice.R
import kotlinx.android.synthetic.main.cargovoice_activity_goods_type.*

class GoodsTypeActivity : BaseActivity<IView<*>, IPresenter>() {

    override fun init() {

        val jsonListBean = getJsonListBean(mContext, "commodityType(货件类别).json")
        val adapter = Adapter(jsonListBean)
        recyclerview.adapter = adapter

        adapter.setOnItemClickListener { adapter1: BaseQuickAdapter<*, *>?, view: View?, position: Int ->
            val jsonBean = jsonListBean[position]
            var intent = Intent()
            intent.putExtra("jsonBean",jsonBean)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    override fun initPresenter() {

    }

    override fun setLayout(): Int = R.layout.cargovoice_activity_goods_type
    //cargovoice_item_goods_type.xml

    companion object {
        /**
         * @param
         */
        fun launchActivity(activity: Activity, requestCode: Int) {
            val intent = Intent(activity, GoodsTypeActivity::class.java)
            activity.startActivityForResult(intent, requestCode)
        }
    }

    class Adapter(data: List<GsonBean?>?) : BaseQuickAdapter<GsonBean, BaseViewHolder>(R.layout.cargovoice_item_goods_type, data) {
        override fun convert(helper: BaseViewHolder, item: GsonBean) {
            helper.setText(R.id.tv_item, item.nameCn)
        }
    }
}