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
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.cargo.basecommon.utils.ListUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

import com.lwb.cargovoice.R
import com.lwb.cargovoice.R2.id.design_bottom_sheet
import com.lwb.cargovoice.utils.ClickToolsUtil

import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("ValidFragment")
object BottomTimeDialog : BottomSheetDialogFragment() {
    /**
     * 顶部向下偏移量
     */
    var topOffset = 0
    var behavior: BottomSheetBehavior<FrameLayout>? = null

    //大列表 和 adapter
    private val data = arrayListOf<TimeBean>()
    private val adapter = MonthAdapter(data)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return if (context == null) {
            super.onCreateDialog(savedInstanceState)
        } else BottomSheetDialog(context!!, R.style.TransparentBottomSheetStyleDialog)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mainView = inflater.inflate(R.layout.cargovoice_bottom_time_dialog_layout, container, false)
        val rlTime = mainView?.findViewById<RecyclerView>(R.id.rl_time)

        if (ListUtils.isEmpty(data)) {
            //当前 年 月
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            for (y in year..year + 3) {
                if (y == year) {
                    for (m in month + 1..12) {
                        data.add(TimeBean(y.toString(), m.toString()))
                    }
                } else {
                    for (m in 1..12) {
                        data.add(TimeBean(y.toString(), m.toString()))
                    }
                }
            }
        }
        rlTime?.adapter = adapter
        return mainView
    }

    override fun onStart() {
        super.onStart()
        // 设置软键盘不自动弹出
        dialog!!.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        val dialog = dialog as BottomSheetDialog?
        val bottomSheet = dialog!!.delegate.findViewById<FrameLayout>(design_bottom_sheet)
        if (bottomSheet != null) {
            val layoutParams = bottomSheet.layoutParams as CoordinatorLayout.LayoutParams
            topOffset = dp2px(context, 100f)
            layoutParams.height = getHeight()
            layoutParams.leftMargin = dp2px(context, 8f)
            layoutParams.rightMargin = dp2px(context, 8f)
            behavior = BottomSheetBehavior.from(bottomSheet)
            // 初始为展开状态
            behavior?.setState(BottomSheetBehavior.STATE_EXPANDED)
        }
    }

    private var backTime: ((TimeBean?) -> Unit)? = null

    fun backSelectTime(backTime: (TimeBean?) -> Unit): BottomTimeDialog {
        this.backTime = backTime
        return this
    }

    fun dp2px(context: Context?, dipValue: Float): Int {
        val scale = context!!.resources.displayMetrics.density
        return (dipValue * scale + 0.5f).toInt()
    }// 使用Point已经减去了状态栏高度

    /**
     * 获取屏幕高度
     *
     * @return height
     */
    private fun getHeight(): Int {
        var height = 1920
        if (context != null) {
            val wm = context!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val point = Point()
            if (wm != null) {
                // 使用Point已经减去了状态栏高度
                wm.defaultDisplay.getSize(point)
                height = point.y - topOffset
            }
        }
        return height
    }

    class TimeBean {
        var year: String? = null
        var month: String? = null
        var day: String? = null
        var week: String? = null
        var isSelect: Boolean? = false
        var dayList: List<TimeBean>? = null
        var itemAdapter: DayAdapter? = null

        //创建空对象时用
        constructor() {

        }

        //创建最外头大集合时用
        constructor(year: String?, month: String?) {
            this.year = year
            this.month = month

            this.dayList = arrayListOf()
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.YEAR, year!!.toInt())
            calendar.set(Calendar.MONTH, month!!.toInt() - 1)
            calendar.set(Calendar.DATE, 1)
            //这个月的第一天是周几 , 1 到 7 对应 日 到 六
            val week = calendar.get(Calendar.DAY_OF_WEEK)
            //包左不包右
            for (i in 1 until week) {
                (dayList as ArrayList<TimeBean>)?.add(TimeBean())
            }

            var maxDayNum = 0
            when (month) {
                "1", "3", "5", "7", "8", "10", "12" -> maxDayNum = 31
                "4", "6", "9", "11" -> maxDayNum = 30
                "2" -> {
                    maxDayNum = if (year?.toInt() % 4 == 0 && year?.toInt() % 100 != 0 || year?.toInt() % 400 == 0) {
                        29
                    } else {
                        28
                    }
                }
            }

            for (i in 1..maxDayNum) {
                (dayList as ArrayList<TimeBean>)?.add(TimeBean(year, month, i.toString()))
            }

            this.itemAdapter = DayAdapter(dayList)
        }

        //创建小集合时用
        constructor(year: String?, month: String?, day: String?) {
            this.year = year
            this.month = month
            this.day = day

            val f = SimpleDateFormat("yyyy-MM-dd")
            val weekDays = arrayOf("周日", "周一", "周二", "周三", "周四", "周五", "周六")
            val cal = Calendar.getInstance() // 获得一个日历

            val date = f.parse("$year-$month-$day")
            cal.time = date
            var w = cal[Calendar.DAY_OF_WEEK] - 1 // 指示一个周中的某天。
            if (w < 0) w = 0
            week = weekDays[w]
        }
    }

    class MonthAdapter(data: List<TimeBean>?) : BaseQuickAdapter<TimeBean, BaseViewHolder>(R.layout.cargovoice_item_time, data) {
        //选中的时间
        private var selectDay: TimeBean? = null
        private var selectAdapter: DayAdapter? = null

        //当前年
        private val currentYear = Calendar.getInstance().get(Calendar.YEAR)

        override fun convert(helper: BaseViewHolder, item: TimeBean) {
            if (currentYear == item.year?.toInt()) {
                helper.setText(R.id.tv_month, item.month + "月")
            } else {
                helper.setText(R.id.tv_month, item.year + "年" + item.month + "月")
            }
            val rvDay = helper.getView<RecyclerView>(R.id.rv_day)
            rvDay.layoutManager = GridLayoutManager(mContext, 7)
            rvDay.isNestedScrollingEnabled = false
            rvDay.adapter = item.itemAdapter

            item.itemAdapter?.setOnItemClickListener { _, _, position ->
                if (!TextUtils.isEmpty(item.dayList?.get(position)?.day)) {
                    //原来选择的 时间 给去掉
                    selectDay?.isSelect = false
                    selectAdapter?.notifyDataSetChanged()
                    //新选的时间
                    selectDay = item.dayList?.get(position)
                    selectDay?.isSelect = true
                    selectAdapter = item.itemAdapter
                    selectAdapter?.notifyDataSetChanged()
                    if (backTime != null) {
                        backTime?.invoke(selectDay)
                        dismiss()
                    }
                }
            }
        }
    }

    private val mCalendar = Calendar.getInstance()

    class DayAdapter(data: List<TimeBean?>?) : BaseQuickAdapter<TimeBean?, BaseViewHolder>(R.layout.cargovoice_item_time_calendar, data) {
        override fun convert(helper: BaseViewHolder, item: TimeBean?) {
            val itemWorkCalendarDay = helper.getView<TextView>(R.id.item_calendar_day)
            val itemWorkCalendarParent =
                    helper.getView<RelativeLayout>(R.id.item_work_calendar_parent)

            helper.setText(R.id.item_calendar_day, item?.day)
            //是否隐藏这个item
            if (TextUtils.isEmpty(item?.day)) {
                itemWorkCalendarParent.visibility = View.GONE
            } else {
                itemWorkCalendarParent.visibility = View.VISIBLE
            }
            if (mCalendar.get(Calendar.YEAR) == item?.year?.toInt()
                    && mCalendar.get(Calendar.MONTH) + 1 == item?.month?.toInt()
                    && mCalendar.get(Calendar.DATE) >= item?.day?.toInt()!!) {
                itemWorkCalendarDay.setTextColor(ContextCompat.getColor(mContext, R.color.c127))
                helper.itemView.isClickable = false
            } else if (helper.adapterPosition == 0 || helper.adapterPosition % 7 == 0 || (helper.adapterPosition + 1) % 7 == 0) {
                //周日周六c125 其余c121
                itemWorkCalendarDay.setTextColor(ContextCompat.getColor(mContext, R.color.c201))
                helper.itemView.isClickable = true
            } else {
                itemWorkCalendarDay.setTextColor(ContextCompat.getColor(mContext, R.color.c101))
                helper.itemView.isClickable = true
            }

            itemWorkCalendarParent.isSelected = item?.isSelect!!
        }
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if (ClickToolsUtil.isFastDoubleClick) return
        super.show(manager, tag)
    }

}


