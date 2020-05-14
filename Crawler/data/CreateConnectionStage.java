18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/app/src/main/java/com/openjfx/database/app/stage/CreateConnectionStage.java
package com.openjfx.database.app.stage;

import com.openjfx.database.app.BaseStage;
import com.openjfx.database.app.annotation.Layout;
import com.openjfx.database.model.ConnectionParam;

/**
 * 创建连接界面
 *
 * @author yangkui
 * @since 1.0
 */
@Layout(layout = "create_connection_view.fxml", title = "新建连接", resizable = false)
public class CreateConnectionStage extends BaseStage<String> {
    public CreateConnectionStage() {
    }

    public CreateConnectionStage(String uuid) {
        super(uuid);
    }
}
