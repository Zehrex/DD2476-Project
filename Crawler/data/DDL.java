18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/database/src/main/java/com/openjfx/database/DDL.java
package com.openjfx.database;


import io.vertx.core.Future;

/**
 *
 * 数据定义语言接口
 *
 *
 * @author yangkui
 * @since 1.0
 *
 */
public interface DDL {
    /**
     * 删除数据库
     * @param database 数据库名
     * @return 返回删除结果
     */
    Future<Void> dropDatabase(String database);

    /**
     * 获取某一张表的ddl
     * @param tableName 表名
     * @return 返回目标表的ddl
     */
    Future<String> ddl(String tableName);

    /**
     * 删除table
     * @param table 表名
     * @return 返回删除结果
     */
    Future<Void> dropTable(String table);
}
