package com.lwb.cargovoice.utils

/**
 * Created by zhou on 17/3/20.
 */
object ClickToolsUtil {
    //��ֹ�������
    /** �ж��Ƿ��ǿ��ٵ��  */
    private var lastClickTime: Long = 0
    val isFastDoubleClick: Boolean
        get() {
            val time = System.currentTimeMillis()
            val timeD = time - lastClickTime
            if (timeD < 500) {
                return true
            }
            lastClickTime = time
            return false
        }
}