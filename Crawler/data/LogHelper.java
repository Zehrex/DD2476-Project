1
https://raw.githubusercontent.com/wkp111/PageEventDemo/master/pageevent/src/main/java/com/wkp/pageevent/log/LogHelper.java
package com.wkp.pageevent.log;

import android.util.Log;

/**
 * Created by wkp111 on 2020/5/7.
 * 日志管理
 */
public final class LogHelper {
    /**
     * 默认不开启日志打印
     */
    private static boolean sLogEnable = false;

    private LogHelper() {
    }

    public static void enable(boolean enable) {
        sLogEnable = enable;
    }

    public static void i(String tag, String msg) {
        if (sLogEnable) {
            Log.i(tag, msg);
        }
    }
}
