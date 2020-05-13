2
https://raw.githubusercontent.com/Aivacom/JLYLiveChat-android/master/app/src/main/java/com/mediaroom/utils/CrashHandler.java
package com.mediaroom.utils;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;
import android.text.format.Time;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.Properties;

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    /**
     * Debug L tag
     */
    public static final String TAG = "CrashHandler";
    /**
     * 是否开启日志输出,在Debug状态下开启,
     * 在Release状态下关闭以提示程序性能
     */
    public static final boolean DEBUG = true;
    /**
     * 系统默认的UncaughtException处理类
     */
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    /**
     * CrashHandler实例
     */
    private static CrashHandler INSTANCE;
    /**
     * 程序的Context对象
     */
    private Context mContext;
    /**
     * 使用Properties来保存设备的信息和错误堆栈信息
     */
    private Properties mDeviceCrashInfo = new Properties();
    private static final String VERSION_NAME = "versionName";
    private static final String VERSION_CODE = "versionCode";
    private static final String STACK_TRACE = "STACK_TRACE";
    /**
     * 错误报告文件的扩展名
     */
    private static final String CRASH_REPORTER_EXTENSION = ".cr";

    private static Object syncRoot = new Object();
    private String deviceInfo = "";

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        // 防止多线程访问安全，这里使用了双重锁
        if (INSTANCE == null) {
            synchronized (syncRoot) {
                if (INSTANCE == null) {
                    INSTANCE = new CrashHandler();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 初始化
     * @param ctx
     */
    public void init(Context ctx) {
        mContext = ctx;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);

        deviceInfo = collectCrashDeviceInfo(mContext);
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            }
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(10);
        }
    }

    /**
     * 自定义错误处理,收集错误信息
     * 发送错误报告等操作均在此完成.
     * 开发者可以根据自己的情况来自定义异常处理逻辑
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return true;
        }

        StackTraceElement[] stackTraceElements = ex.getStackTrace();
        StringBuffer stringBuffer = new StringBuffer();
        for (StackTraceElement s : stackTraceElements) {
            stringBuffer.append(s + "\n");
        }

        final String msg = ex.getLocalizedMessage() + "\n" + stringBuffer.toString();

        new Thread() {
            @Override
            public void run() {
                Looper.prepare();

//                if (DEBUG) {
                    Toast toast = Toast.makeText(mContext, "程序异常，即将退出",
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

//                    sendMessage("出错信息：\n" + msg + "\r\n\n设备信息：" + deviceInfo);

                    //保存错误报告文件
                    LogToFile.w("xxxEL", msg+"\n设备信息：" + deviceInfo);
//                }
                Looper.loop();
            }
        }.start();

        return true;
    }

    /**
     * 保存错误信息到文件中
     * save crash info to file
     *
     * @param ex
     * @return
     */
    private String saveCrashInfoToFile(Throwable ex) {
        Writer info = new StringWriter();
        PrintWriter printWriter = new PrintWriter(info);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        String result = info.toString();
        printWriter.close();
        mDeviceCrashInfo.put("EXEPTION", ex.getLocalizedMessage());
        mDeviceCrashInfo.put(STACK_TRACE, result);
        try {
            Time t = new Time("GMT+8");
            t.setToNow(); // 取得系统时间
            int date = t.year * 10000 + t.month * 100 + t.monthDay;
            int time = t.hour * 10000 + t.minute * 100 + t.second;
            String fileName = "crash-" + date + "-" + time + CRASH_REPORTER_EXTENSION;
            FileOutputStream trace = mContext.openFileOutput(fileName,
                    Context.MODE_PRIVATE);
            mDeviceCrashInfo.store(trace, "");
            trace.flush();
            trace.close();
            return fileName;
        } catch (Exception e) {
            Log.e("TAG","an error occured while writing report file..." + e);
        }
        return null;
    }

    /**
     * 收集程序崩溃的设备信息
     * collect crash device info
     *
     * @param ctx
     */
    private String collectCrashDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(),
                    PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                mDeviceCrashInfo.put(VERSION_NAME,
                        pi.versionName == null ? "not set" : pi.versionName);
                mDeviceCrashInfo.put(VERSION_CODE, "" + pi.versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("TAG","Error while collect package info" + e);
        }

        //使用反射来收集设备信息.在Build类中包含各种设备信息
        Field[] fields = Build.class.getDeclaredFields();
        StringBuffer sb = new StringBuffer();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                mDeviceCrashInfo.put(field.getName(), "" + field.get(null));
//                if (DEBUG) {
                    Log.e("TAG",field.getName() + " : " + field.get(null));
                    sb.append(field.getName() + " ： " + field.get(null) + "\r\n");
//                }
            } catch (Exception e) {
                Log.e("TAG","Error while collect crash info" + e);
            }
        }
        return sb.toString();
    }

}
