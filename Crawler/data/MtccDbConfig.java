11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-common/src/main/java/com/yf/mtcc/common/config/MtccDbConfig.java
package com.yf.mtcc.common.config;

import lombok.Data;

/**
 * @Author: Elvis on 2020/4/13
 * @Email: yfelvis@gmail.com
 * @Desc: Mtcc框架存储事务日志数据库连接配置
 */
@Data
public class MtccDbConfig {

    private String driverClassName = "com.mysql.jdbc.Driver";

    private String type = "com.zaxxer.hikari.HikariDataSource";

    private String url;

    private String username;

    private String password;

    private int minimumIdle;

    private int maximumPoolSize;

    private long idleTimeout;

    private long connectionTimeout;

    private String connectionTestQuery;

    private long maxLifetime;

}
