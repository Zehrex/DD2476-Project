18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/app/src/main/java/com/openjfx/database/app/model/TableDataChangeMode.java
package com.openjfx.database.app.model;

/**
 * 数据表变动记录
 *
 * @author yangkui
 * @since 1.0
 */
public class TableDataChangeMode {
    /**
     * 变动列系数
     */
    private int columnIndex = -1;
    /**
     * 变动行系数
     */
    private int rowIndex = -1;
    /**
     * 变动前的数据
     */
    private Object originalData;

    /**
     * 变动后的数据
     */
    private Object changeData;

    private String id;

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public Object getOriginalData() {
        return originalData;
    }

    public void setOriginalData(Object originalData) {
        this.originalData = originalData;
    }

    public Object getChangeData() {
        return changeData;
    }

    public void setChangeData(Object changeData) {
        this.changeData = changeData;
    }

    public String getId() {
        return rowIndex+"_"+columnIndex;
    }
}
