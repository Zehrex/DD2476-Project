2
https://raw.githubusercontent.com/Aivacom/JLYLiveChat-android/master/app/src/main/java/com/mediaroom/facade/FeedBackManager.java
package com.mediaroom.facade;

import android.app.Application;
import android.text.TextUtils;

import com.mediaroom.utils.Constant;

import java.util.Objects;

import tv.athena.core.axis.Axis;
import tv.athena.crash.api.ICrashService;
import tv.athena.feedback.api.FeedbackData;
import tv.athena.feedback.api.IFeedbackService;
import tv.athena.klog.api.ILogService;
import tv.athena.klog.api.LogLevel;
import tv.athena.util.FP;
import tv.athena.util.ProcessorUtils;
import tv.athena.util.RuntimeInfo;


public class FeedBackManager {

    private final static String mLogReportAppId = Constant.FEEDBACK_CRASHLOGID;
    private final static String mLogFileTag = Constant.FEEDBACK_CRASHLOGID;
    private final static String mCrashReportAppId = Constant.FEEDBACK_CRASHLOGID;
    private final static long mkLogMaxSize = 100 * 1024 * 1024; // 100M
    private static FeedBackManager instance;

    public static FeedBackManager getInstance() {
        if (instance == null) {
            instance = new FeedBackManager();
        }
        return instance;
    }

    private FeedBackManager() {
    }

    public void init(Application application) {
        try {
            RuntimeInfo.INSTANCE.appContext(application)
                    /*** 设置包名* */
                    .packageName(application.getPackageName())
                    /*** 保存进程名* */
                    .processName(ProcessorUtils.INSTANCE.getMyProcessName())
                    /** 设置是否是debug 模式* */
                    .isDebuggable(true)
                    /*** 保存当前进程是否是主进程* */
                    .isMainProcess(FP.eq(RuntimeInfo.sPackageName, RuntimeInfo.sProcessName));

            if (TextUtils.equals(application.getPackageName(), ProcessorUtils.INSTANCE.getMyProcessName())) {
                /*** 初始化 KLog  现在是默认配置* */
                Objects.requireNonNull(Axis.Companion.getService(ILogService.class)
                        .config())
                        /*** 设置存储日志内存上限*/
                        .logCacheMaxSiz(mkLogMaxSize)
                        /*** 单个log文件最大值*/
                        .singleLogMaxSize(4 * 1024 * 1024)
                        /*** 设置日志写入级别*/
                        .logLevel(LogLevel.INSTANCE.getLEVEL_VERBOSE())
                        /*** 设置进程标识* */
                        .processTag(RuntimeInfo.sProcessName).apply();

                //通过Axis初始化log服务
                //init the log service
                Axis.Companion.getService(ILogService.class).config().processTag(mLogFileTag).logPath(Constant.logFilePath).logCacheMaxSiz(mkLogMaxSize);
                //通过Axis初始化crash服务
                //init the crash service
                Axis.Companion.getService(ICrashService.class).start(mCrashReportAppId, "");
                //崩溃捕捉上报
                //Crash capture escalation
                Axis.Companion.getService(ICrashService.class).config().setGUid(Constant.uid).setAppId(mCrashReportAppId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void feedBackLog(String msg, FeedbackData.FeedbackStatusListener feedbackStatusListener) {
        try {
            FeedbackData feedbackData = new FeedbackData.Builder(mLogReportAppId, Long.valueOf(Constant.uid), msg).
                    setFeedbackStatusListener(feedbackStatusListener).
                    create();
            Axis.Companion.getService(IFeedbackService.class).
                    sendNewLogUploadFeedback(feedbackData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
