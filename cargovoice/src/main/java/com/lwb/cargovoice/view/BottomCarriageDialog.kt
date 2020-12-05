package com.lwb.cargovoice.view

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.BottomSheetDialogFragment
import android.support.design.widget.CoordinatorLayout
import android.support.v4.app.FragmentManager
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import com.cargo.basecommon.base.BaseApplication
import com.cargo.basecommon.bean.EnquiryAddGoalRequest
import com.cargo.basecommon.utils.TimeUtils
import com.lwb.cargovoice.R
import com.lwb.cargovoice.R2.id.design_bottom_sheet
import com.lwb.cargovoice.utils.ClickToolsUtil

@SuppressLint("ValidFragment")
class BottomCarriageDialog : BottomSheetDialogFragment() {

    var topOffset = 0
    var behavior: BottomSheetBehavior<FrameLayout>? = null
    var tvETDTime: EditText? = null
    var tvOrigin: EditText? = null
    var tvGoal: EditText? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return if (context == null) {
            super.onCreateDialog(savedInstanceState)
        } else BottomSheetDialog(context!!, R.style.TransparentBottomSheetStyleDialog)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mainView = inflater.inflate(R.layout.cargovoice_bottom_carriage_dialog, null)
        val llAirborne = mainView.findViewById<LinearLayout>(R.id.ll_airborne)
        val llAquage = mainView.findViewById<LinearLayout>(R.id.ll_aquage)
        tvOrigin = mainView.findViewById<EditText>(R.id.tv_origin)
        tvGoal = mainView.findViewById<EditText>(R.id.tv_goal)
        tvETDTime = mainView.findViewById(R.id.tv_time)

        //空运
        llAirborne.setOnClickListener {
            llAirborne.isSelected = true
            llAquage.isSelected = false
            onBack?.onBackMode("2")
        }

        //海运
        llAquage.setOnClickListener {
            llAirborne.isSelected = false
            llAquage.isSelected = true
            onBack?.onBackMode("1")
        }

        tvOrigin?.setOnClickListener {
            onBack?.onOrigin()
        }

        tvETDTime?.setOnClickListener {
            onBack?.onTime()
        }

        val request: EnquiryAddGoalRequest? = (activity?.application as BaseApplication).request
        if (request != null) {
            if (request.businessInfo.transportModeCode == "SEA") {
                llAirborne.isSelected = false
                llAquage.isSelected = true
            } else if (request.businessInfo.transportModeCode == "AIR") {
                llAirborne.isSelected = true
                llAquage.isSelected = false
            }

            //起运港 目的港
            if (request.locationInfo.portOfOriginName != null) {
                tvOrigin?.setText(request.locationInfo.portOfOriginName + "(" + request.locationInfo.portOfOriginCode + ")")
            }
            if (request.locationInfo.portOfDestinationName != null) {
                tvGoal?.setText(request.locationInfo.portOfDestinationName + "(" + request.locationInfo.portOfDestinationCode + ")")
            }

            if (!TextUtils.isEmpty(request.locationInfo.departureDate)) {
                val departureDate = request.locationInfo.departureDate.split("-")
                tvETDTime?.setText(departureDate[0] + "年" + departureDate[1] + "月" + departureDate[2] + "日 " + TimeUtils.getWeekByTMD(request.locationInfo.departureDate))
            }
        }
        return mainView
    }

    fun setOrigin(text: String?) {
        tvOrigin?.setText(text)
    }

    fun setGoal(text: String?) {
        tvGoal?.setText(text)
    }

    fun setBack(onBack: Back): BottomCarriageDialog {
        this.onBack = onBack
        return this
    }

    var onBack: Back? = null

    interface Back {
        // 1 海 2空
        fun onBackMode(haiOrKong: String?)
        fun onOrigin()
        fun onTime()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        val dialog = dialog as BottomSheetDialog?
        val bottomSheet = dialog?.delegate?.findViewById<FrameLayout>(design_bottom_sheet)
        if (bottomSheet != null) {
            val layoutParams = bottomSheet.layoutParams as CoordinatorLayout.LayoutParams
            layoutParams.leftMargin = dp2px(context, 8f)
            layoutParams.rightMargin = dp2px(context, 8f)
        }
    }


    fun dp2px(context: Context?, dipValue: Float): Int {
        val scale = context!!.resources.displayMetrics.density
        return (dipValue * scale + 0.5f).toInt()
    }

    /**
     *
     * @return height
     */
    private fun getHeight(): Int {
        var height = 1920
        if (context != null) {
            val wm = context!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val point = Point()
            if (wm != null) {
                // ʹ��Point�Ѿ���ȥ��״̬���߶�
                wm.defaultDisplay.getSize(point)
                height = point.y - topOffset
            }
        }
        return height
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if (ClickToolsUtil.isFastDoubleClick) return
        super.show(manager, tag)
    }

}