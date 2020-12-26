package com.lwb.cargovoice.module.mvp.ui.activity

import android.os.Bundle
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.cargo.basecommon.utils.SPUtils
import com.cargo.basecommon.utils.SwitchLanguageUtil
import com.lwb.cargovoice.R
import com.lwb.cargovoice.view.AddSuccessDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

@Route(path = "/cargovoice/MainActivity")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val list = arrayListOf<BillListResponse.ShipmentListBean>()
//        list.add(BillListResponse.ShipmentListBean())
//        list.add(BillListResponse.ShipmentListBean())
//        blv.setList(list)

        but3.setOnClickListener {
            ARouter.getInstance()
                    .build("/login/MainActivity")
                    .navigation()
        }

        but_login.setOnClickListener {
            if (!TextUtils.isEmpty(SPUtils.get(this, "token", "") as String)) {
                ARouter.getInstance()
                        .build("/login/MainActivity")
                        .navigation()
            } else {
                ARouter.getInstance()
                        .build("/login/LoginActivity")
                        .navigation()
            }


        }

        but_list_xunjia.setOnClickListener {
//            EnquiryListActivity.launchActivity(this)
            val d = AddSuccessDialog()
                    .setCountDownFinishListener {
                        finish()
                    }
            d.show(supportFragmentManager, "")
            Timer().schedule(object : TimerTask() {
                override fun run() {
                    runOnUiThread {
                        d.commitFail()
                    }
                }
            }, 3000)
        }

        but_list_ding.setOnClickListener {
//            BookingSpaceListActivity.launchActivity(this)
//            val d = AddSuccessDialog()
//                    .setCountDownFinishListener {
//                        finish()
//                    }
//            d.show(supportFragmentManager, "")
//            Timer().schedule(object : TimerTask() {
//                override fun run() {
//                    Log.e("isMainThread", isMainThread().toString())
//                    runOnUiThread {
//                        d.commitSucceed()
//                    }
//                }
//
//            }, 3000)

            SwitchLanguageUtil.switchLanguage(this, Locale.US)

        }


    }

    fun isMainThread(): Boolean {
        return Looper.getMainLooper() === Looper.myLooper()
    }
}