18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/app/src/main/java/com/openjfx/database/app/stage/SplashStage.java
package com.openjfx.database.app.stage;

import com.openjfx.database.app.BaseStage;
import com.openjfx.database.app.annotation.Layout;
import javafx.stage.StageStyle;



/**
 * app启动屏
 *
 * @author yangkui
 * @since 1.0
 */
@Layout(layout = "splash_view.fxml", width = 600, height = 400, stageStyle = StageStyle.UNDECORATED,
        alwaysOnTop = true)
public class SplashStage extends BaseStage {
}
