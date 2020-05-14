18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/app/src/main/java/com/openjfx/database/app/controls/impl/SchemeTreeNode.java
package com.openjfx.database.app.controls.impl;


import com.openjfx.database.DQL;
import com.openjfx.database.app.controls.BaseTreeNode;
import com.openjfx.database.app.component.MainTabPane;
import com.openjfx.database.app.config.Constants;
import com.openjfx.database.app.stage.SQLEditStage;
import com.openjfx.database.app.utils.DialogUtils;
import com.openjfx.database.common.VertexUtils;
import com.openjfx.database.model.ConnectionParam;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import javafx.application.Platform;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;

import java.util.List;
import java.util.stream.Collectors;

import static com.openjfx.database.app.DatabaseFX.DATABASE_SOURCE;
import static com.openjfx.database.app.config.Constants.ACTION;
import static com.openjfx.database.app.config.Constants.FLAG;
import static com.openjfx.database.app.utils.AssetUtils.getLocalImage;

/**
 * scheme tree node
 *
 * @author yangkui
 * @since 1.0
 */
public class SchemeTreeNode extends BaseTreeNode<String> {

    private static final Image ICON_IMAGE = getLocalImage(20, 20, "db_icon.png");

    private final String scheme;

    private final MenuItem flush = new MenuItem("刷新");

    private final MenuItem open = new MenuItem("打开数据库");

    private final MenuItem close = new MenuItem("关闭数据库");

    public SchemeTreeNode(String scheme, ConnectionParam param) {
        super(param, ICON_IMAGE);
        this.scheme = scheme;

        setValue(scheme);


        var deleteMenu = new MenuItem("删除数据库");
        var sqlEditor = new MenuItem("SQL编辑器");


        addMenuItem(open, sqlEditor, deleteMenu);

        flush.setOnAction(e -> flush());

        deleteMenu.setOnAction(event -> {
            var result = DialogUtils.showAlertConfirm("你确定要删除" + scheme + "?");
            if (result) {
                var dml = DATABASE_SOURCE.getDataBaseSource(getUuid()).getDdl();
                var future = dml.dropDatabase(scheme);
                future.onSuccess(r -> {
                    closeOpenTab();
                    //delete current node from parent node
                    getParent().getChildren().remove(this);
                    //remove cached database pool
                    DATABASE_SOURCE.close(getUuid());
                });
                future.onFailure(t -> DialogUtils.showErrorDialog(t, "删除schema失败"));
            }
        });

        //close scheme->close relative tab
        close.setOnAction(e -> {
            setExpanded(false);
            getChildren().clear();
            closeOpenTab();
            removeMenu(flush);
            removeMenu(close);
        });

        //open database scheme
        open.setOnAction(event -> init());

        //open sql editor
        sqlEditor.setOnAction(e -> {
            var json = new JsonObject();
            json.put(Constants.UUID, param.getUuid());
            json.put(Constants.SCHEME, getValue());
            new SQLEditStage(json);
        });
    }

    /**
     * by event bus notify {@link MainTabPane} close current scheme relative tab
     */
    private void closeOpenTab() {
        var message = new JsonObject();
        message.put(ACTION, MainTabPane.EventBusAction.REMOVE_MANY);
        message.put(FLAG, getUuid() + "_" + scheme);
        VertexUtils.send(MainTabPane.EVENT_BUS_ADDRESS, message);
    }

    @Override
    public void init() {
        if (getChildren().size() > 0 || isLoading()) {
            return;
        }
        setLoading(true);
        DQL dcl = DATABASE_SOURCE.getDataBaseSource(getUuid()).getDql();
        Future<List<String>> future = dcl.showTables(getValue());
        future.onSuccess(tables ->
        {
            var tas = tables.stream().map(s -> new TableTreeNode(getValue(), s, param.get())).collect(Collectors.toList());
            Platform.runLater(() -> {
                getChildren().addAll(tas);
                if (tas.size() > 0) {
                    setExpanded(true);
                }
                addMenuItem(0, close);
                addMenuItem(flush);
                removeMenu(open);
            });
            setLoading(false);
        });
        future.onFailure(t -> initFailed(t, "获取scheme失败"));
    }
}
