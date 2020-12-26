package com.lwb.cargovoice.module.mvp.ui.activity

import android.app.Activity
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.cargo.basecommon.base.BaseFragmentActivity
import com.cargo.basecommon.view.PagingView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lwb.cargovoice.R
import com.lwb.cargovoice.adapter.SelectRegionAdapter
import com.lwb.cargovoice.module.mvp.contract.SelectRegionContract
import com.lwb.cargovoice.module.mvp.entity.response.SelectRegionBean
import com.lwb.cargovoice.module.mvp.presenter.SelectRegionPresenter
import com.lwb.cargovoice.utils.ClickToolsUtil
import com.lwb.cargovoice.utils.PinyinComparator
import com.lwb.cargovoice.utils.PinyinUtils
import com.lwb.cargovoice.utils.SideBar
import kotlinx.android.synthetic.main.cargovoice_activity_select_region.*
import java.util.*


class SelectRegionActivity : BaseFragmentActivity<SelectRegionContract.View?, SelectRegionPresenter?>(), SelectRegionContract.View {
    private val titleList = arrayListOf<SelectRegionBean>()
    private val titleAdapter = TitleAdapter(titleList)

    private val allList = arrayListOf<SelectRegionBean>()
    private val allAdapter = SelectRegionAdapter(allList)

    private var mPagingView: PagingView<SelectRegionBean>? = null

    /**
     *  1 国家
     *  2 省
     *  3 城市
     */
    private var type = "1"

    //国家code
    private var countryCode: String? = null

    //省code
    private var provinceCode: String? = null

    private var mRecyclerView: RecyclerView? = null
    private var sideBar: SideBar? = null
    private var dialog: TextView? = null
    var manager: LinearLayoutManager? = null

    /**
     * 根据拼音来排列RecyclerView里面的数据类
     */
    private var pinyinComparator: PinyinComparator? = null

    override fun init() {
        val title = SelectRegionBean()
        title.name = getString(R.string.please_choose)
        titleList.add(title)
        rv_title.adapter = titleAdapter

        mPagingView = findViewById(R.id.paging_view)
        mRecyclerView = mPagingView?.recyclerview
        mPagingView
                ?.setAdapter(allAdapter)
                ?.setOnRequest { mPresenter?.location(mPagingView, countryCode, provinceCode, type) }
                ?.setEnableLoadMore(false)
                ?.setEnableRefresh(false)
        mPagingView?.startRequest()

        titleAdapter.setOnItemClickListener { adapter, view, position ->
            val num = titleList.size
            for (i in position until num - 1) {
                titleList.removeAt(titleList.size - 1)
            }
            type = (position + 1).toString()
            titleAdapter.notifyDataSetChanged()
//            onRequest()
            mPagingView?.newRequest()
        }

        allAdapter.setOnItemClickListener { adapter, view, position ->
            if (ClickToolsUtil.isFastDoubleClick) return@setOnItemClickListener
            var item = allList[position]
            titleList.add(item)
            titleAdapter?.notifyDataSetChanged()
            when (type) {
                "1" -> {
                    type = "2"
                    countryCode = item.code
//                    onRequest()
                    mPagingView?.newRequest()
                }
                "2" -> {
                    type = "3"
                    provinceCode = item.code
                    mPagingView?.newRequest()
//                    onRequest()
                }
                "3" -> {
                    val intent = Intent()
                    intent.putExtra("result", titleList)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }
            }
        }

    }

    private fun initViews() {
        pinyinComparator = PinyinComparator()
        sideBar = findViewById<View>(R.id.sideBar) as SideBar
        dialog = findViewById<View>(R.id.dialog) as TextView
        sideBar?.setTextView(dialog)

        //设置右侧SideBar触摸监听
        sideBar?.setOnTouchingLetterChangedListener { s ->
            //该字母首次出现的位置
            val position: Int = allAdapter.getPositionForSection(s[0].toInt())
            if (position != -1) {
                manager!!.scrollToPositionWithOffset(position, 0)
            }
        }

        // 根据a-z进行排序源数据
        Collections.sort(allList, pinyinComparator)
        //RecyclerView社置manager
        manager = LinearLayoutManager(this)
        manager?.orientation = LinearLayoutManager.VERTICAL
        mRecyclerView?.layoutManager = manager
        //item点击事件
        /*adapter.setOnItemClickListener(new SortAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, ((SortModel)adapter.getItem(position)).getName(),Toast.LENGTH_SHORT).show();
            }
        });*/
    }


    private fun onRequest() {

    }

    override fun initPresenter() {
        mPresenter = SelectRegionPresenter(mContext)
    }

    override fun setLayout(): Int {
        return R.layout.cargovoice_activity_select_region
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onSuccess(response: List<SelectRegionBean>?, type: String?) {
//        allList.clear()
//        if (!ListUtils.isEmpty(response)) {
//            allList.addAll(response!!)
//        }
//        allAdapter?.notifyDataSetChanged()
        mPagingView?.onSuccess(response)
        when (type) {
            "1" -> {

            }
            "2" -> {

            }
            "3" -> {
            }
        }
        response?.forEach {
            //汉字转换成拼音
            //汉字转换成拼音
            val pinyin: String = PinyinUtils.getPingYin(it.name)
            val sortString = pinyin.substring(0, 1).toUpperCase()

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches(Regex("[A-Z]"))) {
                it.setLetters(sortString.toUpperCase())
            } else {
                it.setLetters("#")
            }
        }
        initViews()
    }

    override fun onSuccess(response: SelectRegionBean) {

    }

    override fun onError(result: String) {}

    class TitleAdapter(var title: List<SelectRegionBean>) : BaseQuickAdapter<SelectRegionBean, BaseViewHolder>(R.layout.layout_breadcrumbs_tab, title) {
        override fun convert(helper: BaseViewHolder, item: SelectRegionBean) {
            val tvContent = helper.getView<TextView>(R.id.tv_content)
            helper.setText(R.id.tv_content, item.name)
            val ivArrow = helper.getView<ImageView>(R.id.iv_arrow)

            if (helper.adapterPosition == data.size - 1) {
                ivArrow.visibility = View.GONE
                tvContent.setTextColor(ContextCompat.getColor(mContext, R.color.c005))
            } else {
                ivArrow.visibility = View.VISIBLE
                tvContent.setTextColor(ContextCompat.getColor(mContext, R.color.c101))
            }
        }
    }

    companion object {
        fun launchActivity(activity: Activity, requestCode: Int) {
            val intent = Intent(activity, SelectRegionActivity::class.java)
            activity.startActivityForResult(intent, requestCode)
        }
    }
}