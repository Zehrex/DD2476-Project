18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/mysql/src/main/java/com/openjfx/database/mysql/impl/MysqlPoolImpl.java
package com.openjfx.database.mysql.impl;

import com.openjfx.database.base.AbstractDataBasePool;
import io.vertx.mysqlclient.MySQLPool;

/**
 * 封装MysqlPool类
 *
 * @author yangkui
 * @since 1.0
 */
public class MysqlPoolImpl extends AbstractDataBasePool {

    private MysqlPoolImpl(MySQLPool pool) {
        this.pool = pool;
        dql = new DQLImpl(pool);
        ddl = new DDLImpl(pool);
        dml = new DMLImpl(pool);
        generator = new SQLGeneratorImpl(pool);
    }


    public static AbstractDataBasePool create(MySQLPool pool) {
        return new MysqlPoolImpl(pool);
    }
}
