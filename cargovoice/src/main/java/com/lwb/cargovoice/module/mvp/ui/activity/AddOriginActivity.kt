package com.lwb.cargovoice.module.mvp.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.RelativeLayout
import com.cargo.basecommon.base.BaseActivity
import com.cargo.basecommon.base.BaseApplication
import com.cargo.basecommon.base.IPresenter
import com.cargo.basecommon.base.IView
import com.cargo.basecommon.bean.EnquiryAddGoalRequest
import com.cargo.basecommon.utils.AirToast
import com.cargo.basecommon.utils.LocalJsonResolutionUtils.getPortList
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lwb.cargovoice.R
import kotlinx.android.synthetic.main.cargovoice_activity_add_origin.*

class AddOriginActivity : BaseActivity<IView<*>, IPresenter>() {

//    private var originText: String? = null
//    private var destinationText: String? = null

    private val list = arrayListOf<List<Any>>()
    private val mAdapter = Adapter(list)

    private lateinit var mRequest: EnquiryAddGoalRequest

    override fun init() {
        mRequest = (application as BaseApplication).request
        initData()
        initListener()
    }

    private fun initListener() {

        mAdapter.setOnItemClickListener { adapter, view, position ->
            //每点击item时判断 是否有模糊查寻，如果有应该去掉
            if (!TextUtils.isEmpty(mAdapter?.containsText)) {
                mAdapter?.containsText = null
                mAdapter?.notifyDataSetChanged()
            }

            val item = list.get(position)
            if (rl_origin.isSelected) {
                mRequest.locationInfo.portOfOriginCode = item[0] as String
                mRequest.locationInfo.portOfOriginName = item[1] as String
                et_origin.setText("从「" + mRequest.locationInfo.portOfOriginName + "」" + getString(R.string.start_shipment))

                rl_origin.isSelected = false
                rl_destination.isSelected = true
                iv_origin_delete.visibility = View.GONE
                iv_destination_delete.visibility = View.GONE

                et_origin.visibility = View.GONE
                ll_origin.visibility = View.VISIBLE
                tv_origin.text = "从「" + mRequest.locationInfo.portOfOriginName + "」" + getString(R.string.start_shipment)

            } else if (rl_destination.isSelected) {
                if (TextUtils.isEmpty(et_origin.text) || TextUtils.isEmpty(mRequest.locationInfo.portOfOriginName)) {
                    AirToast.showToast(getString(R.string.please_select_the_port_of_departure))
                    return@setOnItemClickListener
                }

                mRequest.locationInfo.portOfDestinationCode = item[0] as String
                mRequest.locationInfo.portOfDestinationName = item[1] as String
                et_destination.setText(mRequest.locationInfo.portOfDestinationName)

                rl_origin.isSelected = false
                rl_destination.isSelected = true
                iv_origin_delete.visibility = View.GONE
                iv_destination_delete.visibility = View.VISIBLE

                if (intent.getIntExtra("requestCode", 0) == 0) {
                    //TODO (已废弃不可能进这里来)
                    //正常列表页选择的 需要去 新增大页
                    EnquiryAddGoalActivity.launchActivity(mContext)
                    finish()
                } else {
                    //新增大页更改 得退回去
                    setResult(Activity.RESULT_OK)
                    finish()
                }
            }
        }

        iv_origin_delete.setOnClickListener {
            rl_origin.isSelected = true
            rl_destination.isSelected = false
            iv_origin_delete.visibility = View.GONE
            iv_destination_delete.visibility = View.GONE
            et_origin.text = null
        }

        iv_destination_delete.setOnClickListener {
            et_destination.text = null
        }

        iv_origin_delete2.setOnClickListener {
            rl_origin.isSelected = true
            rl_destination.isSelected = false
            iv_origin_delete.visibility = View.GONE
            iv_destination_delete.visibility = View.GONE
            et_origin.text = null

            ll_origin.visibility = View.GONE
            et_origin.visibility = View.VISIBLE
        }
    }

    private fun initData() {
        rl_origin.isSelected = true
        rl_destination.isSelected = false
        iv_origin_delete.visibility = View.GONE
        iv_destination_delete.visibility = View.GONE

        //获取所有 港口 是个 大集合List<List<Object>>
        val transportMode = if (mRequest.businessInfo.transportModeCode == "SEA") {
            "2"
        } else {
            "3"
        }
        var portList = getPortList(this)
        for (mutableList in portList) {
            if ((mutableList[3] == transportMode || mutableList[3] == 4)) {
                list.add(mutableList)
            }
        }
        recyclerview.adapter = mAdapter

        et_origin.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                // 此处为得到焦点时的处理内容
                rl_origin.isSelected = true
                rl_destination.isSelected = false
                iv_origin_delete.visibility = View.VISIBLE
                iv_destination_delete.visibility = View.GONE
            } else {
                // 此处为失去焦点时的处理内容

            }
        }

        et_destination.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                // 此处为得到焦点时的处理内容
                rl_origin.isSelected = false
                rl_destination.isSelected = true
                iv_origin_delete.visibility = View.GONE
                iv_destination_delete.visibility = View.VISIBLE
                //TODO 跳页
            } else {
                // 此处为失去焦点时的处理内容

            }
        }

        et_origin.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null && !s?.toString()!!.contains(getString(R.string.start_shipment))) {
                    mAdapter?.containsText = s.toString()
                    mAdapter.notifyDataSetChanged()
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
        })

        et_destination.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null && !s?.toString()!!.contains(getString(R.string.start_shipment))) {
                    mAdapter?.containsText = s.toString()
                    mAdapter.notifyDataSetChanged()
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
        })
    }

    override fun initPresenter() {

    }

    override fun setLayout(): Int = R.layout.cargovoice_activity_add_origin

    class Adapter(data: List<List<Any>>) : BaseQuickAdapter<List<Any>, BaseViewHolder>(R.layout.cargovoice_item_add_origin, data) {
        var containsText: String? = null
        override fun convert(helper: BaseViewHolder, item: List<Any>) {
            val rlDestination = helper.getView<RelativeLayout>(R.id.rl_destination)
            helper.setText(R.id.tv_dress_name, item[1] as String)
            helper.setText(R.id.tv_dress_code, item[2] as String)
            if (containsText != null) {
                //转换成小写在比较
                if (containsText?.toLowerCase()!! in (item[1] as String).toLowerCase()) {
                    rlDestination.visibility = View.VISIBLE
                } else {
                    rlDestination.visibility = View.GONE
                }
            } else {
                rlDestination.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        /**
         * @param transportMode 运输方式 1海 2空
         */
        fun launchActivity(context: Context) {
            val intent = Intent(context, AddOriginActivity::class.java)
            context.startActivity(intent)
        }

        fun launchActivity(activity: Activity, requestCode: Int) {
            val intent = Intent(activity, AddOriginActivity::class.java)
            intent.putExtra("requestCode", requestCode)
            activity.startActivityForResult(intent, requestCode)
        }
    }
}