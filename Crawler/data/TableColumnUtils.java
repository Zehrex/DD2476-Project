18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/app/src/main/java/com/openjfx/database/app/utils/TableColumnUtils.java
package com.openjfx.database.app.utils;

import com.openjfx.database.app.controls.TableDataCell;
import com.openjfx.database.app.controls.TableDataColumn;
import com.openjfx.database.model.TableColumnMeta;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * TableColumn utils
 *
 * @author yangkui
 * @since 1.0
 */
public class TableColumnUtils {
    /**
     * create table data column
     *
     * @param metas database table column meta data
     * @return return {@link TableDataColumn} list
     */
    public static List<TableDataColumn> createTableDataColumn(List<TableColumnMeta> metas) {
        var columns = new ArrayList<TableDataColumn>();
        for (int i = 0; i < metas.size(); i++) {
            var meta = metas.get(i);
            var column = new TableDataColumn(meta);
            final var columnIndex = i;
            column.setCellValueFactory(cellDataFeatures -> {
                var values = cellDataFeatures.getValue();
                if (columnIndex >= values.size()) {
                    return new SimpleStringProperty("");
                } else {
                    return cellDataFeatures.getValue().get(columnIndex);
                }
            });
            column.setCellFactory(TableDataCell.forTableColumn());
            columns.add(column);
        }
        return columns;
    }
}
