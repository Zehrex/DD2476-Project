18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/app/src/main/java/com/openjfx/database/app/controller/DesignTableController.java
package com.openjfx.database.app.controller;

import com.fasterxml.jackson.databind.deser.impl.PropertyValue;
import com.openjfx.database.DDL;
import com.openjfx.database.DQL;
import com.openjfx.database.app.BaseController;
import com.openjfx.database.app.utils.DialogUtils;
import com.openjfx.database.model.TableColumnMeta;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

import static com.openjfx.database.app.DatabaseFX.DATABASE_SOURCE;
import static com.openjfx.database.app.config.Constants.*;

/**
 * 设计表控制器
 *
 * @author yangkui
 * @since 1.0
 */
public class DesignTableController extends BaseController<JsonObject> {
    @FXML
    private TableView<TableColumnMeta> designTable;

    @FXML
    private TextArea ddlTextArea;

    @FXML
    private TextField commentField;

    private String uuid = null;

    private String tableName = null;

    private ObservableList<TableColumnMeta> items = FXCollections.observableArrayList();

    @Override
    public void init() {
        uuid = data.getString(UUID);
        tableName = data.getString(TABLE_NAME);
        DQL dql = DATABASE_SOURCE.getDataBaseSource(uuid).getDql();
        stage.setTitle(tableName);

        Future<List<TableColumnMeta>> future = dql.showColumns(tableName);

        future.onSuccess(items::addAll);

        future.onFailure(t -> DialogUtils.showErrorDialog(t, "获取表结构失败"));

        designTable.getColumns().forEach(column -> {
            String userData = (String) column.getUserData();
            column.setCellValueFactory(new PropertyValueFactory(userData));
        });
        designTable.getSelectionModel()
                .selectedItemProperty().addListener((observable, oldValue, newValue) -> updateAttr(newValue));
        DDL ddl = DATABASE_SOURCE.getDataBaseSource(uuid).getDdl();
        Future<String> future1 = ddl.ddl(tableName);
        future1.onSuccess(ddlTextArea::setText);
        future.onFailure(t -> DialogUtils.showErrorDialog(t, "获取DDL失败"));
        designTable.setSortPolicy(it -> null);
        designTable.setItems(items);
    }

    private void updateAttr(TableColumnMeta meta) {
        commentField.setText(meta.getComment());
    }
}
