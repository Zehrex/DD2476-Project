18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/database/src/main/java/com/openjfx/database/base/AbstractDatabaseSource.java
package com.openjfx.database.base;

import com.openjfx.database.model.ConnectionParam;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据库操作实体类
 *
 * @author yangkui
 * @since 1.0
 */
public abstract class AbstractDatabaseSource {
    /**
     * 数据库连接池缓存Map
     */
    protected ConcurrentHashMap<String, AbstractDataBasePool> pools = new ConcurrentHashMap<>();
    /**
     * 心跳id
     */
    protected Long timerId;

    /**
     * 根据UUID 获取数据库连接池
     *
     * @param uuid uuid
     * @return 返回数据库连接池
     */
    public AbstractDataBasePool getDataBaseSource(String uuid) {
        Objects.requireNonNull(uuid);
        return pools.get(uuid);
    }

    /**
     * 新建数据库连接池
     *
     * @param params 连接参数
     * @return 返回pool
     */
    public abstract AbstractDataBasePool createPool(ConnectionParam params);

    /**
     * 关闭某一个连接池
     *
     * @param uuid uuid
     */
    public abstract void close(String uuid);

    /**
     * 关闭资源
     */
    public abstract void closeAll();

    /**
     * 防止长时间没有交互与数据库服务器失去响应
     */
    public abstract void heartBeat();
}
