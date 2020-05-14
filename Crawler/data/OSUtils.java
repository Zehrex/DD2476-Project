18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/common/src/main/java/com/openjfx/database/common/utils/OSUtils.java
package com.openjfx.database.common.utils;

/**
 * 系统相关操作工具类
 *
 * @author yangkui
 * @since 1.0
 */
public class OSUtils {
    /**
     * 获取操作系统名称
     *
     * @return 返回操作系统名称
     */
    public static String getOsName() {
        return System.getProperty("os.name");
    }

    /**
     * 获取用户主目录
     * @return 返回主目录路径
     */
    public static String getUserHome(){
        return System.getProperty("user.home");
    }
}
