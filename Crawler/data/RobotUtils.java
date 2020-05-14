18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/app/src/main/java/com/openjfx/database/app/utils/RobotUtils.java
package com.openjfx.database.app.utils;

import com.openjfx.database.app.enums.NotificationType;
import javafx.geometry.Pos;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

/**
 * Robot相关工具类
 *
 * @author yangkui
 * @since 1.0
 */
public class RobotUtils {
    /**
     * 添加字符串到剪切板
     *
     * @param target 目标字符串
     */
    public static void addStrClipboard(final String target) {
        var clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        var tText = new StringSelection(target);
        clipboard.setContents(tText, null);
        DialogUtils.showNotification("复制成功", Pos.TOP_CENTER, NotificationType.INFORMATION);
    }
}
