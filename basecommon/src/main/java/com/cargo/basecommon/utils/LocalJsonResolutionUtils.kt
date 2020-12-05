package com.cargo.basecommon.utils

import android.content.Context
import com.cargo.basecommon.bean.GsonBean
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

object LocalJsonResolutionUtils {
    /**
     * 得到json文件中的内容
     *
     * @param context
     * @param fileName
     * @return
     */
    fun getJson(context: Context, fileName: String?): String {
        val stringBuilder = StringBuilder()
        //获得assets资源管理器
        val assetManager = context.assets
        //使用IO流读取json文件内容
        try {
            val bufferedReader = BufferedReader(InputStreamReader(
                    assetManager.open(fileName!!), "utf-8"))
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return stringBuilder.toString()
    }

    @JvmStatic
    fun getJsonListBean(context: Context, fileName: String?): List<GsonBean> {
        val json = getJson(context, fileName)
        val gson = Gson()
        return gson.fromJson(json, object : TypeToken<List<GsonBean?>?>() {}.type)
    }

    /**
     *  通过文件名字 和 code 返回这个bean
     */
    @JvmStatic
    fun getGsonBeanByFileNameCode(context: Context, fileName: String?, code: String?): GsonBean {
        val jsonListBean = getJsonListBean(context, fileName)
        for (gsonBean in jsonListBean) {
            if (code == gsonBean.code) {
                return gsonBean
            }
        }
        return GsonBean()
    }

    /**
     * 通过文件名字 和 中文名 返回这个bean
     *
     * @param fileName 文件名
     * @param name     中文名字
     */
    @JvmStatic
    fun getGsonBeanByFileName(context: Context, fileName: String?, name: String?): GsonBean {
        val jsonListBean = getJsonListBean(context, fileName)
        for (gsonBean in jsonListBean) {
            if (name == gsonBean.nameCn) {
                return gsonBean
            }
        }
        return GsonBean()
    }

    /**
     * 获取所有港口的集合
     * 港口信息由于数据量较多，为了避免JSON格式文件过大，
     * 采用数组方式保存（记录的字段的顺序依次为：
     * 港口的代码、港口的名称、港口所属国家名称、港口类型[2-海港、3-空港、4-既是海港也是空港]）。
     */
    fun getPortList(context: Context): List<List<Any>> {
        val json = getJson(context, "port.json")
        val gson = Gson()
        return gson.fromJson(json, object : TypeToken<List<List<Any?>?>?>() {}.type)
    }

    /**
     * 将字符串转换为 对象
     *
     * @param json
     * @param type
     * @return
     */
    fun <T> JsonToObject(json: String?, type: Class<T>?): T {
        val gson = Gson()
        return gson.fromJson(json, type)
    }
}