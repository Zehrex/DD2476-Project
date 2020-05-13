2
https://raw.githubusercontent.com/lixiangwudi/service/master/src/main/java/com/example/lx/utils/StringUtils.java
package com.example.lx.utils;
/**
 * @Author lixiang
 * @Date 2020/5/10 14:30
 * @Version 1.0
 */

import java.util.Random;

public class StringUtils {
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
