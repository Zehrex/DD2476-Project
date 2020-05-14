18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/app/src/main/java/com/openjfx/database/app/controls/TableDataCell.java
package com.openjfx.database.app.controls;

import com.openjfx.database.app.model.TableDataChangeMode;
import com.openjfx.database.app.utils.TableCellUtils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.openjfx.database.common.config.StringConstants.NULL;


/**
 * table cell
 *
 * @author yangkui
 * @since 1.0
 */
public class TableDataCell extends TableCell<ObservableList<StringProperty>, String> {

    //    private TextField textField;
    private TableTextField textField;

    /**
     * 空值样式
     */
    private final String NULL_STYLE = "null-style";
    /**
     * 值改变样式
     */
    private final String CHANGE_STYLE = "change-style";

    {
        //禁止换行,防止文字显示过长
        setWrapText(false);
    }


    private final ObjectProperty<StringConverter<String>> converter = new SimpleObjectProperty<>();

    public TableDataCell(StringConverter<String> converter) {
        this.setConverter(converter);
    }

    public StringConverter<String> getConverter() {
        return converter.get();
    }

    public ObjectProperty<StringConverter<String>> converterProperty() {
        return converter;
    }

    public void setConverter(StringConverter<String> converter) {
        this.converter.set(converter);
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty
                || Objects.isNull(item)
                || Objects.isNull(getTableView())
                || Objects.isNull(getTableRow())) {
            return;
        }
        TableCellUtils.updateItem(this, textField);

        if (item.equals(NULL)) {
            addClass(NULL_STYLE);
        } else {
            removeStyle(NULL_STYLE);
        }
        var dataView = (TableDataView) getTableView();

        int rowIndex = getTableRow().getIndex();
        int colIndex = getTableView().getColumns().indexOf(getTableColumn());

        var optional = dataView.getChangeModel(rowIndex, colIndex);

        if (optional.isPresent()) {
            addClass(CHANGE_STYLE);
        } else {
            removeStyle(CHANGE_STYLE);
        }
    }

    @Override
    public void startEdit() {

        if (!isEditable()
                || !getTableView().isEditable()
                || !getTableColumn().isEditable()) {
            return;
        }

        super.startEdit();

        if (isEditing()) {
            var column = (TableDataColumn) getTableColumn();
            if (textField == null) {
                textField = TableCellUtils.createTextField(this, column.getMeta());
            }
            TableCellUtils.startEdit(this, textField);
        }
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        TableCellUtils.cancelEdit(this, null);
    }

    @Override
    public void commitEdit(String newValue) {
        String oldValue = getItem();
        var colIndex = getTableView().getEditingCell().getColumn();
        var rowIndex = getTableRow().getIndex();

        //值变化
        if (!oldValue.equals(newValue)) {
            var dataView = (TableDataView) getTableView();

            var optional = dataView.getChangeModel(rowIndex, colIndex);

            if (optional.isEmpty()) {
                var model = new TableDataChangeMode();
                model.setRowIndex(rowIndex);
                model.setColumnIndex(colIndex);
                model.setOriginalData(oldValue);
                model.setChangeData(newValue);
                dataView.addChangeMode(model);
                addClass(CHANGE_STYLE);
            } else {
                var model = optional.get();
                if (model.getOriginalData().equals(newValue)) {
                    dataView.removeChange(model);
                    removeStyle(CHANGE_STYLE);
                } else {
                    //更新值
                    model.setChangeData(newValue);
                }
            }
        }
        //更新值
        updateItem(newValue, false);
        super.commitEdit(newValue);
        //聚焦当前tableCell
        getTableView().requestFocus();
        getTableView().getSelectionModel().select(rowIndex, getTableColumn());

    }

    public static Callback<TableColumn<ObservableList<StringProperty>, String>, TableCell<ObservableList<StringProperty>, String>> forTableColumn() {
        return list -> new TableDataCell(new DefaultStringConverter());
    }

    /**
     * 增加样式
     *
     * @param className 样式名称
     */
    private void addClass(String className) {
        boolean a = this.getStyleClass().contains(className);
        //如果之前没有添加样式->添加
        if (!a) {
            this.getStyleClass().add(className);
        }
    }

    /**
     * 移出样式名
     *
     * @param className 样式名
     */
    private void removeStyle(String className) {
        ObservableList<String> list = getStyleClass();
        List<Integer> dd = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String cla = list.get(i);
            if (cla.equals(className)) {
                dd.add(i);
            }
        }
        //移出样式
        for (Integer integer : dd) {
            list.remove(integer.intValue());
        }
    }
}
