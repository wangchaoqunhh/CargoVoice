package com.lwb.cargovoice.view

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.TextView
import com.cargo.basecommon.utils.ListUtils
import com.lwb.cargovoice.R
import com.lwb.cargovoice.adapter.GoodsInfoAdapter
import com.lwb.cargovoice.module.mvp.entity.response.EnquiryDetailsResponse
import com.lwb.cargovoice.utils.ClickToolsUtil.isFastDoubleClick

@SuppressLint("ValidFragment")
object BottomGoodsInfoDialog : BottomSheetDialogFragment() {
    /**
     *
     */
    private val topOffset = 0
    private var behavior: BottomSheetBehavior<FrameLayout>? = null
    private var dialogTitle: TextView? = null
    private var rv: RecyclerView? = null
    private var list: List<*>? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return if (context == null) {
            super.onCreateDialog(savedInstanceState)
        } else {
            BottomSheetDialog(context!!, R.style.TransparentBottomSheetStyle)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mainView = inflater.inflate(R.layout.cargovoice_freight_details_bottom_dialog_view, null)
        dialogTitle = mainView.findViewById(R.id.tv_dialog_title)
        rv = mainView.findViewById(R.id.rv)
        initAdapter(list)
        return mainView
    }

    /**
     * @param
     */
    private fun initAdapter(list: List<*>?) {
        dialogTitle!!.text = "货件信息"
        if(!ListUtils.isEmpty(list)){
            val adapter = GoodsInfoAdapter((list as List<EnquiryDetailsResponse.CommodityListBean?>?))
            rv?.adapter = adapter
        }
    }

    fun setListAndType(list: List<*>?): BottomGoodsInfoDialog {
        this.list = list
        return this
    }

    override fun onStart() {
        super.onStart()
//        dialog.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
//        val dialog = dialog as BottomSheetDialog
//        val bottomSheet = dialog.delegate.findViewById<FrameLayout>(R.id.design_bottom_sheet)
//        if (bottomSheet != null) {
//            val layoutParams = bottomSheet.layoutParams as CoordinatorLayout.LayoutParams
//            behavior = BottomSheetBehavior.from(bottomSheet)
//            behavior?.setState(BottomSheetBehavior.STATE_EXPANDED)
//        }
    }

    private fun dp2px(context: Context, dipValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dipValue * scale + 0.5f).toString().toInt()
    }

    /**
     * @return height
     */
    private val height: Int
        private get() {
            var height = 1920
            if (context != null) {
                val wm = context!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager
                val point = Point()
                if (wm != null) {
                    wm.defaultDisplay.getSize(point)
                    height = point.y - topOffset
                }
            }
            return height
        }

    override fun show(manager: FragmentManager, tag: String) {
        if (isFastDoubleClick) return
        super.show(manager, tag)
    }
}