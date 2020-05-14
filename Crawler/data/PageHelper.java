18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/mysql/src/main/java/com/openjfx/database/mysql/PageHelper.java
package com.openjfx.database.mysql;

/**
 * 分页查询辅助类
 *
 * @author yangkui
 * @since 1.0
 */
public class PageHelper {
    /**
     * 获取分页查询起始页面
     *
     * @param pageIndex 分页查询起始页
     * @param pageSize  分页查询尺寸
     * @return 返回起始页面
     */
    public static int getInitPage(int pageIndex, int pageSize) {
        return (pageIndex - 1) * pageSize;
    }
}
