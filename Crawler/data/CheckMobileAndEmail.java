1
https://raw.githubusercontent.com/niufuwei/block_chian/master/Stock/app/src/main/java/com/hjq/demo/helper/CheckMobileAndEmail.java
package com.hjq.demo.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 账号输入检测手机号或者邮箱正确性工具
 */
public class CheckMobileAndEmail {
    /**
     * 　　* 验证邮箱地址是否正确
     * <p>
     * 　　* @param email
     * <p>
     * 　　* @return
     * <p>
     */
    public static boolean checkEmail(String email) {

        boolean flag = false;

        try {

            String check = "^（[a-z0-9A-Z]+[-|\\.]?）+[a-z0-9A-Z]@（[a-z0-9A-Z]+（-[a-z0-9A-Z]+）？\\.）+[a-zA-Z]{2,}$";

            Pattern regex = Pattern.compile(check);

            Matcher matcher = regex.matcher(email);

            flag = matcher.matches();

        } catch (Exception e) {

            flag = false;

        }

        return flag;

    }
    /**
     * 　　* 验证手机号码
     * <p>
     * 　　* @param mobiles
     * <p>
     * 　　* @return  [0-9]{5,9}
     * <p>
     */
    public static boolean isMobileNO(String mobiles) {

        boolean flag = false;

        try {

            Pattern p = Pattern.compile("^[1]([3-9])[0-9]{9}");

            Matcher m = p.matcher(mobiles);

            flag = m.matches();

        } catch (Exception e) {

            flag = false;

        }

        return flag;

    }
    public static boolean isNum(String number) {
        boolean flag = false;
        try {

            Pattern p = Pattern.compile("^[0-9]{5}$");

            Matcher m = p.matcher(number);

            flag = m.matches();

        } catch (Exception e) {

            flag = false;

        }
        return flag;
    }
}