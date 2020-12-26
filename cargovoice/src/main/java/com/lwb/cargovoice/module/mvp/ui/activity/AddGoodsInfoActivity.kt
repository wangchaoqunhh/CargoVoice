package com.lwb.cargovoice.module.mvp.ui.activity

import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import com.cargo.basecommon.base.BaseActivity
import com.cargo.basecommon.base.BaseApplication
import com.cargo.basecommon.base.IPresenter
import com.cargo.basecommon.base.IView
import com.cargo.basecommon.bean.EnquiryAddGoalRequest
import com.lwb.cargovoice.R
import com.lwb.cargovoice.adapter.GoodsInfoAdapter2
import kotlinx.android.synthetic.main.cargovoice_activity_add_goods_info.*

class AddGoodsInfoActivity : BaseActivity<IView<*>?, IPresenter?>() {
    private lateinit var mRequest: EnquiryAddGoalRequest

    private lateinit var goodsInfoAdapter2: GoodsInfoAdapter2

    override fun init() {
        mRequest = (application as BaseApplication).request
        goodsInfoAdapter2 = GoodsInfoAdapter2(mRequest.commodityList)
        recyclerview.adapter = goodsInfoAdapter2
        settingTypeAndNum()

        toolbar.setNavigationOnClickListener {
            finish()
        }

        fab_add.setOnClickListener {
            AddGoodsInfoDetailsActivity.launchActivity(this, 1000)
        }

        but_save.rlBut.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }

        goodsInfoAdapter2.setOnItemClickListener { adapter, view, position ->
            AddGoodsInfoDetailsActivity.launchActivity(this, 1000, position)
        }
    }

    override fun initPresenter() {

    }

    override fun setLayout(): Int {
        return R.layout.cargovoice_activity_add_goods_info
        //cargovoice_item_add_goods_info.xml
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                1000 -> {
                    this.goodsInfoAdapter2.notifyDataSetChanged()
                    settingTypeAndNum()
                }
            }
        }
    }

    /***
     * 设置 上面的类别 和 总数
     */
    fun settingTypeAndNum() {
        val goodsTypeMap = hashMapOf<String, String>()
        var num = 0
        for (bean in mRequest.commodityList) {
            if (!goodsTypeMap.containsKey(bean.commodityCode) && !TextUtils.isEmpty(bean.commodityCode)) {
                goodsTypeMap.put(bean.commodityCode, "")
            }
            if (!TextUtils.isEmpty(bean.packQty)) {
                num += (bean.packQty).toInt()
            }
        }
        tv_goods_classes.text = getString(R.string.category) + "：" + goodsTypeMap.size
        tv_sum.text = getString(R.string.total_num) + "：" + num
    }

    companion object {
        /**
         * @param
         */
        fun launchActivity(activity: Activity, requestCode: Int) {
            val intent = Intent(activity, AddGoodsInfoActivity::class.java)
            activity.startActivityForResult(intent, requestCode)
        }
    }
}