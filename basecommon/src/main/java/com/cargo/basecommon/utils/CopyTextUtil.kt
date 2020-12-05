package com.cargo.basecommon.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

object CopyTextUtil {
    /**
     * 复制到剪贴板
     *
     * @param context
     * @param text 要复制的文字
     * @param hint 复制之后提示的文字
     */
    fun putTextIntoClip(context: Context, text: String?, hint: String? = "已复制") {
        val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        //创建ClipData对象
        val clipData = ClipData.newPlainText("HSFAppDemoClip", text)
        //添加ClipData对象到剪切板中
        clipboardManager.setPrimaryClip(clipData)
        AirToast.showToast(hint)
    }
}