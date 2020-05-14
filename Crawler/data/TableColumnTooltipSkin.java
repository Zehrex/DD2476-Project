18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/app/src/main/java/com/openjfx/database/app/skin/TableColumnTooltipSkin.java
package com.openjfx.database.app.skin;

import com.openjfx.database.app.controls.TableDataColumn;
import com.openjfx.database.common.utils.StringUtils;
import com.openjfx.database.model.TableColumnMeta;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.layout.GridPane;

import java.util.Objects;

public class TableColumnTooltipSkin implements Skin<TableDataColumn.TableColumnTooltip> {
    private final TableDataColumn.TableColumnTooltip tooltip;
    private final TableColumnMeta columnMeta;

    public TableColumnTooltipSkin(TableDataColumn.TableColumnTooltip tooltip) {
        this.tooltip = tooltip;
        columnMeta = tooltip.getTableColumnMetaObjectProperty();
    }

    @Override
    public TableDataColumn.TableColumnTooltip getSkinnable() {
        return tooltip;
    }

    @Override
    public Node getNode() {
        var gridPane = new GridPane();
        gridPane.getStyleClass().add("grid-pane");
        gridPane.getStylesheets().add("css/table_column_tooltip.css");

        var field = columnMeta.getField();
        var type = columnMeta.getType();
        var key = StringUtils.isEmpty(columnMeta.getKey()) ? "FALSE" : "TRUE";
        var defValue = columnMeta.getDefault();
        var comment = columnMeta.getComment();
        var isNull = columnMeta.getNull();
        var extra = columnMeta.getExtra();
        var collation = columnMeta.getCollation();

        gridPane.addRow(0, new Label("Field:"), new Label(field));
        gridPane.addRow(1, new Label("Type:"), new Label(type));
        gridPane.addRow(2, new Label("Key"), new Label(key));
        gridPane.addRow(3, new Label("Default"), new Label(defValue));
        gridPane.addRow(4, new Label("Comment"), new Label(comment));
        gridPane.addRow(5, new Label("Null"), new Label(isNull));
        gridPane.addRow(7, new Label("Extra"), new Label(extra));
        gridPane.addRow(8, new Label("Collation"), new Label(collation));

        return gridPane;
    }

    @Override
    public void dispose() {

    }
}
