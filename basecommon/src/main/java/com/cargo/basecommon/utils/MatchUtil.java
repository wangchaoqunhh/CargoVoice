package com.cargo.basecommon.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Desc ${PARAMS}
 * @Author Hexl
 * @Date 2018/7/13
 */
public class MatchUtil {
    private static final String TAG = "MatchUtil";

    /**
     * 邮箱正则表达式判断输入书是否正确
     */
    public static boolean matchEmail(String newpwd) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
        Matcher matcher = pattern.matcher(newpwd);
        return matcher.matches();
    }

    /**
     * QQ正则表达式判断输入书是否正确
     */
    public static boolean matchQQ(String newpwd) {
        Pattern pattern = Pattern.compile("^[1-9][0-9]{4,9}$");
        Matcher matcher = pattern.matcher(newpwd);
        return matcher.matches();
    }

    /**
     * 微信正则表达式判断输入书是否正确
     */
    public static boolean matchWechat(String newpwd) {
        Matcher matcherWechat = Pattern.compile("^[a-zA-Z]{1}[-_a-zA-Z0-9]{5,19}+$").matcher(newpwd);
        Matcher matcherQQ = Pattern.compile("^[1-9][0-9]{4,9}$").matcher(newpwd);
        Matcher matcherTel = Pattern.compile("^[1][34578]\\d{9}$").matcher(newpwd);
        return matcherWechat.matches() || matcherQQ.matches() || matcherTel.matches();
    }

    /**
     * 微信或QQ正则表达式判断输入书是否正确
     */
    public static boolean matchWechatOrQQ(String newpwd) {
        Matcher matcherWechat = Pattern.compile("^[a-zA-Z]{1}[-_a-zA-Z0-9]{5,19}+$").matcher(newpwd);
        Matcher matcherQQ = Pattern.compile("^[1-9][0-9]{4,9}$").matcher(newpwd);
        Matcher matcherTel = Pattern.compile("^[1][34578]\\d{9}$").matcher(newpwd);
        return matcherWechat.matches() || matcherQQ.matches() || matcherTel.matches();
    }

    /**
     * 手机号正则表达式判断输入书是否正确
     */
    public static boolean matchTel(String newpwd) {
        Pattern pattern = Pattern.compile("^[1][345678]\\d{9}$");
        Matcher matcher = pattern.matcher(newpwd);
        return matcher.matches();
    }

    /**
     * 网址正则表达式判断输入书是否正确
     */
    public static boolean matchWebsite(String newpwd) {
        Pattern pattern = Pattern.compile("((http|ftp|https):\\/\\/)?[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?");
        Matcher matcher = pattern.matcher(newpwd);
        return matcher.matches();
    }

    /**
     * 金额正则表达式判断输入是否正确
     */
    public static boolean matchMoney(String newpwd) {
        Pattern pattern = Pattern.compile("(^[1-9](\\d+)?(\\.\\d{1,2})?$)|(^(0){1}$)|(^\\d\\.\\d{1,2}?$)");
        Matcher matcher = pattern.matcher(newpwd);
        return matcher.matches();
    }

    /**
     * 固定电话正则表达式判断输入是否正确
     */
    public static boolean matchFixedPhone(String newpwd) {
        Pattern pattern = Pattern.compile("^0\\d{2,3}-\\d{7,8}$");
        Matcher matcher = pattern.matcher(newpwd);
        return matcher.matches();
    }

    /**
     * 传真电话正则表达式判断输入是否正确
     */
    public static boolean matchFaxNumber(String newpwd) {
        Pattern pattern = Pattern.compile("^0\\d{2,3}-\\d{7,8}$");
        Matcher matcher = pattern.matcher(newpwd);
        return matcher.matches();
    }


    /**
     * 固定电话或手机正则表达式判断输入是否正确
     */
    public static boolean matchAllPhone(String newpwd) {
        Pattern pattern = Pattern.compile("^((0\\d{2,3}-\\d{7,8})|(1[345678]\\d{9}))$");
        Matcher matcher = pattern.matcher(newpwd);
        return matcher.matches();
    }

    /**
     * 身份证正则表达式判断输入是否正确
     */
    public static boolean matchIDCard(String newPwd) {
        Pattern pattern = Pattern.compile("^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$");
        Matcher matcher = pattern.matcher(newPwd);
        return matcher.matches();
    }

    public static boolean matchPassword(String pwd){

        Pattern pattern = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,18}$");
        Matcher matcher = pattern.matcher(pwd);
        return matcher.matches();
    }

    /**
     * 是否是数字
     *
     * @param str 输入的字符
     * @return true 是 false 不是
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    /**
     * 是否是一个日期
     *
     * @param strDate 需判断的文字
     * @return true 是,false 不是
     */
    public static boolean isDate(String strDate) {
        Pattern pattern = Pattern
                .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?" +
                        "((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?" +
                        "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|" +
                        "(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|" +
                        "(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);
        return m.matches();
    }


}
