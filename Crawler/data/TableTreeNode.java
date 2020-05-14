18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/app/src/main/java/com/openjfx/database/app/controls/impl/TableTreeNode.java
package com.openjfx.database.app.controls.impl;

import com.openjfx.database.app.DatabaseFX;
import com.openjfx.database.app.config.Constants;
import com.openjfx.database.app.controls.BaseTreeNode;
import com.openjfx.database.app.component.MainTabPane;
import com.openjfx.database.app.stage.DesignTableStage;
import com.openjfx.database.app.stage.SQLGenStage;
import com.openjfx.database.app.utils.DialogUtils;
import com.openjfx.database.common.VertexUtils;
import com.openjfx.database.model.ConnectionParam;
import io.vertx.core.json.JsonObject;
import javafx.application.Platform;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;


import static com.openjfx.database.app.config.Constants.*;
import static com.openjfx.database.app.utils.AssetUtils.getLocalImage;

/**
 * 数据库表节点
 *
 * @author yangkui
 * @since 1.0
 */
public class TableTreeNode extends BaseTreeNode<String> {

    private static final Image ICON_IMAGE = getLocalImage(
            20,
            20,
            "table_icon.png"
    );

    /**
     * 所属数据库
     */
    private final String database;

    public TableTreeNode(String database, String tableName, ConnectionParam param) {
        super(param, ICON_IMAGE);

        this.database = database;

        var params = new JsonObject();

        params.put(Constants.UUID, getUuid());
        params.put(TABLE_NAME, database + "." + tableName);

        setValue(tableName);

        var sqlMenu = new MenuItem("生成SQL");
        var design = new MenuItem("设计表");
        var delete = new MenuItem("删除");

        sqlMenu.setOnAction(e -> new SQLGenStage(params));
        design.setOnAction(e -> new DesignTableStage(params));
        delete.setOnAction(e -> {
            var result = DialogUtils.showAlertConfirm("确定要删除" + tableName + "表?");
            if (!result) {
                return;
            }
            var pool = DatabaseFX.DATABASE_SOURCE.getDataBaseSource(getUuid());
            var future = pool.getDdl().dropTable(database + "." + tableName);

            future.onSuccess(ar -> {
                var message = new JsonObject();
                var flag = getUuid() + "_" + database + "_" + tableName;
                message.put(ACTION, MainTabPane.EventBusAction.REMOVE);
                message.put(FLAG, flag);
                VertexUtils.eventBus().send(MainTabPane.EVENT_BUS_ADDRESS, message);
                Platform.runLater(() -> getParent().getChildren().remove(this));
            });

            future.onFailure(t -> DialogUtils.showErrorDialog(t, "删除表失败"));
        });
        addMenuItem(sqlMenu, design, delete);
    }

    public String getDatabase() {
        return database;
    }

    @Override
    public void init() { }
}
