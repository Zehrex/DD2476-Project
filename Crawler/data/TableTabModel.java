18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/app/src/main/java/com/openjfx/database/app/model/impl/TableTabModel.java
package com.openjfx.database.app.model.impl;

import com.openjfx.database.app.model.BaseTabMode;

/**
 * tab数据
 *
 * @author yangkui
 * @since 1.0
 */
public class TableTabModel extends BaseTabMode {
    /**
     * 数据库名称
     */
    private final String database;
    /**
     * 表名
     */
    private final String tableName;
    /**
     * 连接名称
     */
    private final String serverName;

    public TableTabModel(String serverName, String uuid, String database, String tableName) {
        super(uuid + "_" + database + "_" + tableName);
        this.uuid = uuid;
        this.database = database;
        this.tableName = tableName;
        this.serverName = serverName;
    }

    public String getDatabase() {
        return database;
    }

    public String getTable() {
        return database + "." + tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public String getServerName() {
        return serverName;
    }
}
