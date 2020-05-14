18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/mysql/src/main/java/com/openjfx/database/mysql/impl/SQLGeneratorImpl.java
package com.openjfx.database.mysql.impl;

import com.openjfx.database.SQLGenerator;
import com.openjfx.database.common.utils.StringUtils;
import com.openjfx.database.model.TableColumnMeta;
import io.vertx.mysqlclient.MySQLPool;

import java.util.List;

/**
 * Mysql Generator impl
 *
 * @author yangkui
 * @since 1.0
 */
public class SQLGeneratorImpl implements SQLGenerator {

    private MySQLPool pool;

    public SQLGeneratorImpl(MySQLPool pool) {
        this.pool = pool;
    }

    @Override
    public String select(List<TableColumnMeta> metas, String tableName) {
        StringBuilder sb = new StringBuilder("SELECT ");
        for (int i = 0; i < metas.size(); i++) {
            TableColumnMeta meta = metas.get(i);
            sb.append(meta.getField());
            if (i != metas.size() - 1) {
                sb.append(",");
            } else {
                sb.append(" ");
            }
        }
        sb.append("FROM ").append(tableName).append(";");
        return sb.toString();
    }

    @Override
    public String update(List<TableColumnMeta> metas, String tableName) {
        StringBuilder sb = new StringBuilder("UPDATE ")
                .append(tableName)
                .append(" SET ");
        String pri = "";
        for (int i = 0; i < metas.size(); i++) {
            TableColumnMeta meta = metas.get(i);
            sb.append(meta.getField());
            if (i != metas.size() - 1) {
                sb.append("='',");
            } else {
                sb.append(" ");
            }
            String key = meta.getKey();
            if (StringUtils.nonEmpty(key) & "PRI".equals(key)) {
                pri = meta.getField();
            }
        }
        sb.append("WHERE ").append(pri).append("=''").append(";");
        return sb.toString();
    }

    @Override
    public String insert(List<TableColumnMeta> metas, String tableName) {
        StringBuilder sb = new StringBuilder("INSERT INTO ");
        sb.append(tableName).append(" (");
        StringBuilder sb1 = new StringBuilder();
        for (int i = 0; i < metas.size(); i++) {
            TableColumnMeta meta = metas.get(i);
            sb.append(meta.getField());
            sb1.append("' '");
            if (i != metas.size() - 1) {
                sb.append(",");
                sb1.append(",");
            } else {
                sb.append(") ");
                sb1.append(");");
            }
        }
        sb.append("VALUES (").append(sb1);

        return sb.toString();
    }

    @Override
    public String delete(List<TableColumnMeta> metas, String tableName) {
        return "DROP TABLE " + tableName + ";";
    }
}
