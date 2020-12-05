package com.lwb.cargovoice.utils

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import java.text.DecimalFormat

fun RecyclerView.Adapter<*>.isShowText(showText: String?, textView: TextView?) {
    if (!TextUtils.isEmpty(showText)) {
        textView?.visibility = View.VISIBLE
        textView?.text = showText
    } else {
        textView?.visibility = View.INVISIBLE
    }
}

/**
 * 千分位方法
 * @param text
 * @return
 */
fun Context.fmtMicrometer(text: String?): String? {
    var df: DecimalFormat? = null
    df = if (text?.indexOf(".")!! > 0) {
        when {
            text?.length!! - text?.indexOf(".") - 1 == 0 -> {
                DecimalFormat("###,##0.")
            }
            text.length - text.indexOf(".") - 1 == 1 -> {
                DecimalFormat("###,##0.0")
            }
            else -> {
                DecimalFormat("###,##0.00")
            }
        }
    } else {
        DecimalFormat("###,##0")
    }
    var number = 0.0
    number = try {
        text.toDouble()
    } catch (e: Exception) {
        0.0
    }
    return df.format(number)
}