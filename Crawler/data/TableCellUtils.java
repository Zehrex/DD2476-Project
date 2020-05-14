18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/app/src/main/java/com/openjfx/database/app/utils/TableCellUtils.java
package com.openjfx.database.app.utils;

import com.openjfx.database.app.controls.TableDataCell;
import com.openjfx.database.app.controls.TableTextField;
import com.openjfx.database.model.TableColumnMeta;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;

import java.util.Objects;

/**
 * TableCell工具类
 *
 * @author yangkui
 * @since 1.0
 */
public class TableCellUtils {

    public static void updateItem(final TableDataCell cell, final TableTextField textField) {
        if (cell.isEmpty()) {
            cell.setText(null);
            cell.setGraphic(null);
            return;
        }
        if (cell.isEditing()) {
            if (textField != null) {
                textField.setText(getItemText(cell));
            }
            cell.setText(null);
            cell.setGraphic(textField);
        } else {
            cell.setText(getItemText(cell));
            cell.setGraphic(null);
        }
    }

    public static void startEdit(final TableDataCell cell, final TableTextField textField) {
        Objects.requireNonNull(textField);
        Objects.requireNonNull(cell);
        textField.setText(cell.getText());
        cell.setText(null);
        cell.setGraphic(textField);
        textField.selectAll();
    }

    /**
     * cancel table cell edit
     *
     * @param cell    target cell
     * @param graphic graphic
     */
    public static void cancelEdit(final TableDataCell cell, final Node graphic) {
        cell.setText(getItemText(cell));
        cell.setGraphic(graphic);
    }

    public static TableTextField createTextField(final TableDataCell cell, final TableColumnMeta meta) {
        final var textField = new TableTextField(cell.getText(), meta);

        textField.setActionEvent(event -> {
            cell.commitEdit(textField.getText());
            return null;
        });
        textField.setOnKeyRelease(t -> {
            if (t.getCode() == KeyCode.ESCAPE) {
                cell.cancelEdit();
                t.consume();
            }
        });
        return textField;
    }

    private static String getItemText(TableDataCell cell) {
        return Objects.isNull(cell.getItem()) ? "" : cell.getItem();
    }
}
