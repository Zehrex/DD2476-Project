2
https://raw.githubusercontent.com/Aivacom/JLYAudio-android/master/app/src/main/java/com/mediaroom/utils/FileUtil.java
package com.mediaroom.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import java.io.File;

/**
 * File Utils Class
 *
 * ZH：
 * 文件工具类
 *
 * @author Aslan chenhengfei@yy.com
 * @date 2020/1/3
 */
public class FileUtil {
    private static final String LOG = "log";

    private static String log;

    public static String getLog(Context context) {
        if (!TextUtils.isEmpty(log)) {
            return log;
        }

        log = getFilesDir(context, LOG).getAbsolutePath();
        return log;
    }

    /**
     * Determine whether the sd card exists
     *
     * @return Returns true if it exists, otherwise does not exist
     *
     * ZH：
     * 判断sd卡是否存在
     *
     * @return 如果存在就返回true，反之不存在
     */
    public static boolean isSdCardExist() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * is missing permissions
     *
     * @return If true lack of permissions, otherwise it does not lack
     *
     * ZH：
     * 是否缺少权限
     *
     * @return true缺少权限，反之不缺少
     */
    public static Boolean isLackPermission(Context context) {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && (ContextCompat
                .checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat
                .checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED);
    }

    /**
     * Get local file path, preferentially use SD card
     *
     * ZH：
     * 获取本地文件路径,优先采用SD卡
     */
    private static File getFilesDir(Context context, String tag) {
        // if (isLackPermission(context) || !isSdCardExist()) {
        //     return context.getFilesDir();
        // } else {
        //     File file = context.getExternalFilesDir(tag);
        //     if (file == null) {
        //         return context.getFilesDir();
        //     } else {
        //         return file;
        //     }
        // }
        return context.getExternalFilesDir(tag);
    }

    /**
     * Delete files in bulk
     *
     * @param file           folder
     * @param isDeleteDirect  whether delete the folder
     *
     * ZH：
     * 批量删除文件
     *
     * @param file           文件夹
     * @param isDeleteDirect 是否需要删除文件夹
     */
    private void deleteFile(File file, boolean isDeleteDirect) {
        if (file == null) {
            return;
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                deleteFile(f, isDeleteDirect);
            }

            if (isDeleteDirect) {
                file.delete();
            }
        } else if (file.exists()) {
            file.delete();
        }
    }
}
