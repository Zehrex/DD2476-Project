18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/database/src/main/java/com/openjfx/database/model/TableColumnMeta.java
package com.openjfx.database.model;

/**
 * TableColumn meta
 *
 * @author yangkui
 * @since 1.0
 */
public class TableColumnMeta {
    /**
     * 字段值
     */
    private String Field;
    /**
     * 数据类型
     */
    private String Type;
    /**
     * 字符编码
     */
    private String Collation;
    /**
     * 是否空
     */
    private String Null;
    /**
     * 是否key值
     */
    private String Key;
    /**
     * 默认值
     */
    private String Default;
    /**
     * 外部数据
     */
    private String Extra;
    /**
     * 权限
     */
    private String Privileges;
    /**
     * 备注信息
     */
    private String Comment;

    public String getField() {
        return Field;
    }

    public void setField(String field) {
        Field = field;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getCollation() {
        return Collation;
    }

    public void setCollation(String collation) {
        Collation = collation;
    }

    public String getNull() {
        return Null;
    }

    public void setNull(String aNull) {
        Null = aNull;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getDefault() {
        return Default;
    }

    public void setDefault(String aDefault) {
        Default = aDefault;
    }

    public String getExtra() {
        return Extra;
    }

    public void setExtra(String extra) {
        Extra = extra;
    }

    public String getPrivileges() {
        return Privileges;
    }

    public void setPrivileges(String privileges) {
        Privileges = privileges;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }
}
