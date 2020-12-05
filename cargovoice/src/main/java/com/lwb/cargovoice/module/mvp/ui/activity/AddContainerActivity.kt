package com.lwb.cargovoice.module.mvp.ui.activity

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.View
import com.cargo.basecommon.base.*
import com.cargo.basecommon.bean.EnquiryAddGoalRequest
import com.cargo.basecommon.bean.GsonBean
import com.cargo.basecommon.utils.AirToast
import com.cargo.basecommon.utils.LocalJsonResolutionUtils
import com.lwb.cargovoice.R
import com.lwb.cargovoice.adapter.AddContainerAdapter
import kotlinx.android.synthetic.main.cargovoice_activity_add_container.*

class AddContainerActivity : BaseFragmentActivity<IView<*>?, IPresenter?>() {
    private lateinit var mRequest: EnquiryAddGoalRequest

    private lateinit var addContainerAdapter: AddContainerAdapter

    override fun init() {
        mRequest = (application as BaseApplication).request
        addContainerAdapter = AddContainerAdapter(::settingContainerTypeNum,
                mRequest.containerList)
        addContainerAdapter?.bindToRecyclerView(recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerview.addItemDecoration(itemDecoration)
        recyclerview.adapter = addContainerAdapter

        settingContainerTypeNum()

        fab_add.setOnClickListener {
            if (mRequest.containerList.size >= 20) {
                AirToast.showToast("您的箱数已添加到最大")
                return@setOnClickListener
            }
            val containerListBean = EnquiryAddGoalRequest.ContainerListBean()
            //所有箱型 集合
            val containerType = LocalJsonResolutionUtils.getJsonListBean(mContext, "containerType（箱型）.json")

            /**
             *获取空运 海运 的第一个对象
             * @param categoryCode SEA 海  AIR 空
             */
            fun getFun(categoryCode: String?): GsonBean? {
                for (gsonBean in containerType) {
                    if (gsonBean.categoryCode == categoryCode) {
                        return gsonBean
                    }
                }
                return GsonBean()
            }
            //海空运初始化 对象
            var sea: GsonBean? = getFun("SEA")
            //空运初始化 对象
            var air: GsonBean? =  getFun("AIR")

            containerListBean.containerTypeCode = if (mRequest.businessInfo.transportModeCode == "SEA") sea?.code else air?.code
            containerListBean.containerTypeDesc = if (mRequest.businessInfo.transportModeCode == "SEA") sea?.nameCn else air?.nameCn
            containerListBean.containerCount = 1
            mRequest.containerList.add(containerListBean)
            addContainerAdapter.notifyDataSetChanged()
            settingContainerTypeNum()
        }

        but_save.rlBut.setOnClickListener {
            if (isEmptyCheck()) return@setOnClickListener
            setResult(Activity.RESULT_OK)
            finish()
        }

        toolbar.setNavigationOnClickListener {
            finish()
        }

        addContainerAdapter.setOnDeleteClickListener(object : AddContainerAdapter.OnDeleteClickLister {
            override fun onDeleteClick(view: View?, position: Int) {
                mRequest.containerList.removeAt(position)
                addContainerAdapter.notifyDataSetChanged()
                recyclerview.closeMenu()
            }
        })
    }

    private var isIntercept: Boolean? = false

    /**
     * 设置 箱型总数 和 总箱数
     */
    private fun settingContainerTypeNum() {
        if (isIntercept!!) return
        Thread(Runnable {
            isIntercept = true
            val containerType = HashMap<String, String>()
            var containerCount = 0
            for (containerListBean in mRequest.containerList) {
                if (!containerType.containsKey(containerListBean.containerTypeCode) && !TextUtils.isEmpty(containerListBean.containerTypeCode)) {
                    containerType.put(containerListBean.containerTypeCode, "")
                }
                containerCount += containerListBean.containerCount
            }
            runOnUiThread {
                tv_container_type.text = "箱型总数:" + containerType.size
                tv_container_num.text = "总箱数:$containerCount"
                isIntercept = false
            }
        }).start()
    }

    private fun isEmptyCheck(): Boolean {
        mRequest.containerList?.forEach {
            if (TextUtils.isEmpty(it.containerTypeCode)) {
                AirToast.showToast("请选择箱型")
                return true
            }
            if (it.containerCount < 1) {
                AirToast.showToast("请输入箱量")
                return true
            }
            if (it.isShipperOwned && TextUtils.isEmpty(it.containerNumber)) {
                AirToast.showToast("请输入集装箱号码")
                return true
            }
        }
        return false
    }

    override fun initPresenter() {

    }

    override fun setLayout(): Int {
        return R.layout.cargovoice_activity_add_container
    }

    companion object {
        fun launchActivity(activity: Activity, requestCode: Int) {
            val intent = Intent(activity, AddContainerActivity::class.java)
            activity.startActivityForResult(intent, requestCode)
        }
    }
}