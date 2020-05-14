18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/app/src/main/java/com/openjfx/database/app/controller/SqlGenController.java
package com.openjfx.database.app.controller;

import com.openjfx.database.DQL;
import com.openjfx.database.SQLGenerator;
import com.openjfx.database.app.BaseController;
import com.openjfx.database.app.enums.NotificationType;
import com.openjfx.database.app.utils.DialogUtils;
import com.openjfx.database.app.utils.RobotUtils;
import com.openjfx.database.common.utils.StringUtils;
import com.openjfx.database.model.TableColumnMeta;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;


import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.ArrayList;
import java.util.List;

import static com.openjfx.database.app.DatabaseFX.DATABASE_SOURCE;
import static com.openjfx.database.app.config.Constants.*;


/**
 * SQL 生成控制器
 *
 * @author yangkui
 * @since 1.0
 */
public class SqlGenController extends BaseController<JsonObject> {

    @FXML
    private ToggleGroup toggleGroup;

    @FXML
    private TextArea sqlPrevious;

    private final List<TableColumnMeta> columnMetas = new ArrayList<>();

    private String uuid;

    private String tableName;

    @Override
    public void init() {
        uuid = data.getString(UUID);
        tableName = data.getString(TABLE_NAME);

        DQL dql = DATABASE_SOURCE.getDataBaseSource(uuid).getDql();
        Future<List<TableColumnMeta>> future = dql.showColumns(tableName);
        future.onSuccess(metas -> {
            columnMetas.addAll(metas);
            Platform.runLater(this::updateSqlPrevious);
        });
        future.onFailure(t -> DialogUtils.showErrorDialog(t, "获取表结构失败"));
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            updateSqlPrevious();
        });
    }

    private void updateSqlPrevious() {
        RadioButton toggle = (RadioButton) toggleGroup.getSelectedToggle();
        String text = toggle.getText();
        SQLGenerator.GeneratorType type = SQLGenerator.GeneratorType.valueOf(text);

        SQLGenerator generator = DATABASE_SOURCE.getDataBaseSource(uuid)
                .getGenerator();

        String t = tableName.split("\\.")[1];

        String genStr;
        switch (type) {
            case SELECT:
                genStr = generator.select(columnMetas, t);
                break;
            case DELETE:
                genStr = generator.delete(columnMetas, t);
                break;
            case INSERT:
                genStr = generator.insert(columnMetas, t);
                break;
            case UPDATE:
                genStr = generator.update(columnMetas, t);
                break;
            default:
                genStr = "";
        }
        sqlPrevious.setText(genStr);
    }

    @FXML
    public void copy(ActionEvent event) {
        var text = sqlPrevious.getText();
        if (StringUtils.isEmpty(text)) {
            DialogUtils.showNotification("sql不能为空", Pos.TOP_CENTER, NotificationType.INFORMATION);
            return;
        }
        RobotUtils.addStrClipboard(text);
    }

    @FXML
    void close(ActionEvent event) {
        stage.close();
    }
}
