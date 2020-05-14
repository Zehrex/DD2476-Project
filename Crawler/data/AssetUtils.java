18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/app/src/main/java/com/openjfx/database/app/utils/AssetUtils.java
package com.openjfx.database.app.utils;

import javafx.scene.image.Image;
import javafx.scene.text.Font;

import java.io.InputStream;
import java.util.Objects;

/**
 * Resource operation utils collection
 *
 * @author yangkui
 * @since 1.0
 */
public class AssetUtils {
    /**
     * Picture root path
     */
    private static final String IMAGE_PATH = "assets/images/";
    /**
     * Font root path
     */
    private static final String FONT_PATH = "fonts/";
    /**
     * Font default size
     */
    private static final double DEFAULT_FONT_SIZE = 12D;
    /**
     * Font list
     */
    private static final String[] FONTS = new String[]{
            FONT_PATH + "SourceHanSerifCN-Light.otf"
    };

    /**
     * load image resource
     *
     * @param width    width of picture
     * @param height   height of picture
     * @param filename name of picture
     * @return return {@link Image} instance
     */
    public static Image getLocalImage(double width, double height, String filename) {
        var path = IMAGE_PATH + filename;
        var in = ClassLoader.getSystemResourceAsStream(path);
        return new Image(Objects.requireNonNull(in), width, height, false, true);
    }

    /**
     * load all font of application
     */
    public static void loadAllFont() {
        for (String font : FONTS) {
            var in = ClassLoader.getSystemResourceAsStream(font);
            Font.loadFont(in, DEFAULT_FONT_SIZE);
        }
    }
}
