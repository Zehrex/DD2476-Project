18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/database/src/main/java/com/openjfx/database/base/AbstractDataBasePool.java
package com.openjfx.database.base;

import com.openjfx.database.*;
import com.openjfx.database.model.ConnectionParam;
import io.vertx.core.Future;
import io.vertx.sqlclient.Pool;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.SqlConnection;

/**
 * 封装数据库管理连接池
 *
 * @author yangkui
 * @since 1.0
 */
public class AbstractDataBasePool {
    /*********************************
     *          数据库语言             *
     *********************************/
    protected DDL ddl;
    protected DCL dcl;
    protected DML dml;
    protected DQL dql;

    protected SQLGenerator generator;

    protected Pool pool;
    /**
     * connection param
     */
    private ConnectionParam connectionParam;

    public DDL getDdl() {
        return ddl;
    }

    public DCL getDcl() {
        return dcl;
    }


    public DML getDml() {
        return dml;
    }


    public DQL getDql() {
        return dql;
    }

    public void close() {
        pool.close();
    }

    public SQLGenerator getGenerator() {
        return generator;
    }

    /**
     * get connection from database pool.
     * <p>This method will not be used in general,
     * but it needs to be called when some scenes only need to get the linked object to complete.
     * Such as database things, etc</p>
     *
     * @return connection
     */
    public Future<SqlConnection> getConnection() {
        return pool.getConnection();
    }

    public ConnectionParam getConnectionParam() {
        return connectionParam;
    }

    public void setConnectionParam(ConnectionParam connectionParam) {
        this.connectionParam = connectionParam;
    }
}
