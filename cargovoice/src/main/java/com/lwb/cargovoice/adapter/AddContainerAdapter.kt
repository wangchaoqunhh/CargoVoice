package com.lwb.cargovoice.adapter

import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.cargo.basecommon.base.BaseFragmentActivity
import com.cargo.basecommon.bean.EnquiryAddGoalRequest
import com.cargo.basecommon.utils.LocalJsonResolutionUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lwb.cargovoice.R
import com.lwb.cargovoice.utils.fmtMicrometer
import com.lwb.cargovoice.view.AddNumDialog
import com.lwb.cargovoice.view.SelectDictionaryWindow

/**
 *
 */
class AddContainerAdapter(private var settingContainerTypeNum: (() -> Unit?)? = null,
                          data: List<EnquiryAddGoalRequest.ContainerListBean?>?) :
        BaseQuickAdapter<EnquiryAddGoalRequest.ContainerListBean, BaseViewHolder>(R.layout.cargovoice_item_booking_space_add_container, data) {

    private var mDeleteClickListener: OnDeleteClickLister? = null

    override fun convert(helper: BaseViewHolder, item: EnquiryAddGoalRequest.ContainerListBean) {
        val llContent = helper.getView<LinearLayout>(R.id.ll_content)
        val ivDelete = helper.getView<ImageView>(R.id.iv_delete)
        val serial = helper.getView<TextView>(R.id.serial)
        val rlContainerType = helper.getView<RelativeLayout>(R.id.rl_container_type)
        val tvContainerType = helper.getView<TextView>(R.id.tv_container_type)
        val rlContainerNum = helper.getView<RelativeLayout>(R.id.rl_container_num)
        val tvContainerNum = helper.getView<TextView>(R.id.tv_container_num)
        val switchSoc = helper.getView<Switch>(R.id.switch_soc)

        val etContainerCode = helper.getView<EditText>(R.id.et_container_code)

        //序号 不用给后台
        val serialInt = helper.adapterPosition + 1
        serial.text = "#$serialInt"
        //箱型
        val containerJson = LocalJsonResolutionUtils.getGsonBeanByFileNameCode(mContext, "containerType（箱型）.json", item.containerTypeCode)
        tvContainerType.text = containerJson.nameCn
        //集装箱 个数
        tvContainerNum.text = mContext.fmtMicrometer(item.containerCount.toString()) + "箱"
        //设置swich选择否 必须用 if else
        if (recyclerView.isComputingLayout) {
            recyclerView.post {
                switchSoc.isChecked = item.isShipperOwned
            }
        } else {
            switchSoc.isChecked = item.isShipperOwned
        }
        //集装箱 编号
        if (item.isShipperOwned) {
            etContainerCode.visibility = View.VISIBLE
            etContainerCode.setText(item.containerNumber)
        } else {
            etContainerCode.visibility = View.GONE
        }

        rlContainerType.setOnClickListener {
            val window = SelectDictionaryWindow(mContext, "containerType（箱型）.json") {
                tvContainerType.text = it.nameCn
                item.containerTypeCode = it.code
                item.containerTypeDesc = it.nameCn
                settingContainerTypeNum?.invoke()
            }
            window.show()
        }

        rlContainerNum.setOnClickListener {
            val addNumDialog = AddNumDialog.newInstance()
            addNumDialog.setDecimals(false)
            addNumDialog.setBack {
                item.containerCount = it?.toInt()!!
                tvContainerNum.text = mContext.fmtMicrometer(item.containerCount.toString()) + "箱"
                settingContainerTypeNum?.invoke()
            }
            addNumDialog.show((mContext as BaseFragmentActivity<*, *>).supportFragmentManager, "")
        }

        switchSoc.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                item.isShipperOwned = true
                etContainerCode.visibility = View.VISIBLE
            } else {
                item.isShipperOwned = false
                etContainerCode.visibility = View.GONE
            }
            llContent.post {
                val params: ViewGroup.LayoutParams = ivDelete.getLayoutParams()
                params.height = llContent.measuredHeight
                ivDelete.requestLayout()
            }
        }

        etContainerCode.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus && !TextUtils.isEmpty(etContainerCode.text)) {
                item.containerNumber = etContainerCode.text.toString()
            }
        }

        llContent.post {
            val params: ViewGroup.LayoutParams = ivDelete.getLayoutParams()
            params.height = llContent.measuredHeight
            ivDelete.requestLayout()
        }

        ivDelete.setOnClickListener { view ->
            if (mDeleteClickListener != null && view.background != null) {
                mDeleteClickListener?.onDeleteClick(view, helper.adapterPosition)
                ivDelete.background = null
                settingContainerTypeNum?.invoke()
            }
        }
    }

    fun setOnDeleteClickListener(listener: OnDeleteClickLister) {
        this.mDeleteClickListener = listener
    }

    interface OnDeleteClickLister {
        fun onDeleteClick(view: View?, position: Int)
    }
}