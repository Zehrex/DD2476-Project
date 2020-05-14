18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/mysql/src/main/java/com/openjfx/database/mysql/impl/DQLImpl.java
package com.openjfx.database.mysql.impl;

import com.openjfx.database.DQL;
import com.openjfx.database.DataConvert;
import com.openjfx.database.model.TableColumnMeta;
import com.openjfx.database.mysql.PageHelper;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.Tuple;

import java.util.*;

import static com.openjfx.database.common.config.StringConstants.NULL;

public class DQLImpl implements DQL {

    private MySQLPool client;

    private final DataConvert dataConvert = new SimpleMysqlDataConvert();

    public DQLImpl(MySQLPool client) {
        this.client = client;
    }

    @Override
    public Future<List<String>> showDatabase() {
        String sql = "SHOW DATABASES";
        Promise<List<String>> promise = Promise.promise();
        client.query(sql).onSuccess(r -> {
            List<String> schemes = new ArrayList<>();
            r.forEach(row -> {
                String scheme = row.getString(0);
                schemes.add(scheme);
            });
            promise.complete(schemes);
        }).onFailure(promise::fail);

        return promise.future();
    }

    @Override
    public Future<List<String>> showTables(String scheme) {
        String sql = "SHOW TABLES FROM " + scheme + "";
        Promise<List<String>> promise = Promise.promise();
        client.query(sql).onSuccess(r -> {
            List<String> schemes = new ArrayList<>();
            r.forEach(row -> {
                String table = row.getString(0);
                schemes.add(table);
            });
            promise.complete(schemes);
        }).onFailure(promise::fail);
        return promise.future();
    }

    @Override
    public Future<List<TableColumnMeta>> showColumns(String table) {
        String sql = "SHOW FULL COLUMNS FROM " + table;

        Promise<List<TableColumnMeta>> promise = Promise.promise();

        client.query(sql)
                .onSuccess(rows -> {
                    List<TableColumnMeta> metas = new ArrayList<>();
                    for (Row row : rows) {
                        TableColumnMeta meta = new TableColumnMeta();
                        meta.setField(row.getString("Field"));
                        meta.setType(row.getString("Type"));
                        meta.setCollation(row.getString("Collation"));
                        meta.setNull(row.getString("Null"));
                        meta.setKey(row.getString("Key"));
                        meta.setDefault(row.getString("Default"));
                        meta.setExtra(row.getString("Extra"));
                        meta.setPrivileges(row.getString("Privileges"));
                        meta.setComment(row.getString("Comment"));
                        metas.add(meta);
                    }
                    promise.complete(metas);
                }).onFailure(promise::fail);

        return promise.future();
    }

    @Override
    public Future<List<String[]>> query(String table, int pageIndex, int pageSize) {
        String sql = "SELECT * FROM " + table + " LIMIT ?,?";
        int a = PageHelper.getInitPage(pageIndex, pageSize);
        Tuple tuple = Tuple.of(a, pageSize);
        Promise<List<String[]>> promise = Promise.promise();
        client.preparedQuery(sql, tuple).onSuccess(rows -> {
            var list = dataConvert.toConvert(rows);
            promise.complete(list);
        }).onFailure(promise::fail);
        return promise.future();
    }

    @Override
    public Future<Long> count(String tableName) {
        String sql = "SELECT COUNT(*) FROM " + tableName;
        Promise<Long> promise = Promise.promise();
        client.query(sql).onSuccess(rows -> {
            Long number = 0L;
            for (Row row : rows) {
                number = row.getLong(0);
            }
            promise.complete(number);
        }).onFailure(promise::fail);
        return promise.future();
    }

    @Override
    public Future<Void> heartBeatQuery() {
        var sql = "SELECT 1";
        var promise = Promise.<Void>promise();
        var future = client.query(sql);
        future.onSuccess(ar -> promise.complete());
        future.onFailure(promise::fail);
        return promise.future();
    }

    @Override
    public Future<Map<List<String>, List<String[]>>> executeSql(String sql) {
        var future = client.query(sql);
        var promise = Promise.<Map<List<String>, List<String[]>>>promise();
        future.onSuccess(rows -> {
            var columns = rows.columnsNames();
            var dd = dataConvert.toConvert(rows);
            var map = new HashMap<List<String>, List<String[]>>();
            map.put(columns, dd);
            promise.complete(map);
        });
        future.onFailure(promise::fail);
        return promise.future();
    }
}
