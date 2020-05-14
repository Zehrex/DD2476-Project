18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/database/src/main/java/com/openjfx/database/model/ConnectionParam.java
package com.openjfx.database.model;

import java.util.UUID;

import static com.openjfx.database.common.utils.StringUtils.isEmpty;

/**
 *
 *
 * 数据库连接参数
 *
 * @author yangkui
 * @since 1.0
 *
 */
public class ConnectionParam {
    /**
     * uuid 用户区别每个连接
     */
    private String uuid;
    /**
     * 连接名称
     */
    private String name;
    /**
     * 主机
     */
    private String host;
    /**
     * 端口
     */
    private int port;
    /**
     * 用户名
     */
    private String user;
    /**
     * 密码
     */
    private String password;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUuid() {
        if (isEmpty(uuid)){
            uuid = UUID.randomUUID().toString();
        }
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
