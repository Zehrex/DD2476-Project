2
https://raw.githubusercontent.com/Aivacom/JLYAudio-android/master/app/src/main/java/com/mediaroom/utils/LogUtil.java
package com.mediaroom.utils;

/**
 * 日志类
 *
 * @author Aslan chenhengfei@yy.com
 * @date 2020/1/3
 */
public final class LogUtil {

    public static void i(String tag, String log) {
//        Log.i(tag, log);
        tv.athena.klog.api.KLog.i(tag, log);
    }

    public static void w(String tag, String log) {
//        Log.w(tag, log);
        tv.athena.klog.api.KLog.w(tag, log);
    }

    public static void e(String tag, String log) {
//        Log.e(tag, log);
        tv.athena.klog.api.KLog.e(tag, log);
    }

    public static void d(String tag, String log) {
//        Log.d(tag, log);
        tv.athena.klog.api.KLog.d(tag, log);
    }

    public static void v(String tag, String log) {
//        Log.v(tag, log);
        tv.athena.klog.api.KLog.v(tag, log);
    }
}
