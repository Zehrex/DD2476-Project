18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/mysql/src/main/java/com/openjfx/database/mysql/MysqlHelper.java
package com.openjfx.database.mysql;

import com.openjfx.database.common.utils.StringUtils;
import com.openjfx.database.model.ConnectionParam;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;


/**
 * mysql数据库操作一些辅助方法
 *
 * @author yangkui
 * @since 1.0
 */
public class MysqlHelper {

    /**
     * 创建数据库连接池
     *
     * @param param 参数
     * @return 返回数据库连接池
     */
    public static MySQLPool createPool(ConnectionParam param) {
        return createPool(param, 10);
    }

    /**
     * 创建数据库连接池
     *
     * @param param    连接参数
     * @param initSize 初始化尺寸
     * @return 返回连接池
     */
    public static MySQLPool createPool(ConnectionParam param, int initSize) {
        return createPool(param, initSize, null);
    }

    /**
     * 创建数据库连接池
     *
     * @param param    连接参数
     * @param initSize 初始化连接池尺寸
     * @param database 初始化数据库
     * @return 返回连接池
     */
    public static MySQLPool createPool(ConnectionParam param, int initSize, String database) {
        var options = new MySQLConnectOptions()
                .setPort(param.getPort())
                .setHost(param.getHost())
                .setUser(param.getUser())
                .setPassword(param.getPassword())
                .setTcpKeepAlive(true)
                //设置链接超时为5s
                .setConnectTimeout(5000)
                .setIdleTimeout(5)
                .setSslHandshakeTimeout(5);

        if (StringUtils.nonEmpty(database)) {
            options.setDatabase(database);
        }

        var poolOptions = new PoolOptions();
        poolOptions.setMaxSize(initSize);
        return MySQLPool.pool(options, poolOptions);
    }

    /**
     * 测试连接
     *
     * @param param 连接参数
     * @return 异步返回测试结果
     */
    public static Future<Boolean> testConnection(ConnectionParam param) {
        var client = createPool(param);
        var testSql = "SELECT 1";
        var promise = Promise.<Boolean>promise();
        client.query(testSql).setHandler(ar -> {
            if (ar.succeeded()) {
                promise.complete(true);
            } else {
                promise.fail(ar.cause());
            }
            //关闭连接
            client.close();
        });
        return promise.future();
    }

}
