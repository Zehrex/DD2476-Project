18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/mysql/src/main/java/com/openjfx/database/mysql/impl/DDLImpl.java
package com.openjfx.database.mysql.impl;

import com.openjfx.database.DDL;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.Row;

public class DDLImpl implements DDL {
    private MySQLPool client;

    public DDLImpl(MySQLPool client) {
        this.client = client;
    }

    @Override
    public Future<Void> dropDatabase(String database) {
        String sql = "DROP DATABASE "+database;
        Promise<Void> promise = Promise.promise();
        client.query(sql).onSuccess(rows->{
            promise.complete();
        }).onFailure(promise::fail);
        return promise.future();
    }

    @Override
    public Future<Void> dropTable(String table) {
        String sql = "DROP TABLE "+table;
        System.out.println(sql);
        Promise<Void> promise = Promise.promise();
        client.query(sql).onSuccess(rows->{
            promise.complete();
        }).onFailure(promise::fail);
        return promise.future();
    }

    @Override
    public Future<String> ddl(String tableName) {
        String sql = "SHOW CREATE TABLE "+tableName;
        Promise<String> promise = Promise.promise();
        client.query(sql).onSuccess(rows->{
            String ddl = "";
            for (Row row : rows) {
                ddl = (String) row.getValue(1);
            }
            promise.complete(ddl);
        }).onFailure(promise::fail);
        return promise.future();
    }
}
