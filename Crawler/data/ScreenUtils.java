18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/app/src/main/java/com/openjfx/database/app/utils/ScreenUtils.java
package com.openjfx.database.app.utils;

import javafx.geometry.Rectangle2D;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

/**
 * Screen relation operation tool class
 *
 * @author yangkui
 * @since 1.0
 */
public class ScreenUtils {
    /**
     * obtain primary screen bounds
     *
     * @return {@link Rectangle2D}
     */
    public static Rectangle2D getPrimaryBounds() {
        var screen = Screen.getPrimary();
        return screen.getVisualBounds();
    }


}
