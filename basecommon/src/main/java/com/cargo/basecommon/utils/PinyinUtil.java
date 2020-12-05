package com.cargo.basecommon.utils;

import net.sourceforge.pinyin4j.PinyinHelper;



/**
 * 汉子转拼音 共通类
 * (c)2018 AIR Times Inc. All rights reserved.
 *
 * @version 1.0
 * @date 2018/5/29 10:16
 */
public class PinyinUtil {

    /**
     * 得到中文首字母
     *
     * @param str
     * @return
     */
    public static String getPinYinFristHeadChar(String str) {

        String convert = "";
        if(str.length()>=1){
            char word = str.charAt(0);
            if ("长".equals(String.valueOf(word))){
                return "c";
            }
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert;
    }

}
