18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/app/src/main/java/com/openjfx/database/app/enums/MenuItemOrder.java
package com.openjfx.database.app.enums;

/**
 * 菜单指令集
 *
 * @author yangkui
 * @since 1.0
 */
public enum MenuItemOrder {
    /**
     * 打开文件
     */
    OPEN("open"),
    /**
     * 刷新连接列表
     */
    FLUSH("flush"),
    /**
     * 导入数据库
     */
    IMPORT("import"),
    /**
     * 导出数据库
     */
    EXPORT("EXPORT"),
    /**
     * 退出程序
     */
    EXIT("exit"),
    /**
     * 打开SQL编辑器
     */
    EDITOR("editor"),
    /**
     * 新建连接
     */
    CONNECTION("connection"),
    /**
     * 首选项
     */
    SETTING("setting"),
    /**
     * 关于我们
     */
    ABOUT("about");

    /**
     * 具体指令值
     */
    private String order;

    MenuItemOrder(String order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return order;
    }
}
