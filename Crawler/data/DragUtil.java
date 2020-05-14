151
https://raw.githubusercontent.com/fordes123/Subtitles-View/master/src/main/java/org/fordes/subview/util/DragUtil.java
package org.fordes.subview.util;

import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * 工具类
 * @author Light
 */
public class DragUtil {
     public static void addDragListener(Stage stage, Node root) {
        new DragListener(stage).enableDrag(root);
    }
}