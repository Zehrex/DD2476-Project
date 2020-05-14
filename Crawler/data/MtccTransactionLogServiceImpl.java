11
https://raw.githubusercontent.com/yfelvis/mtcc/master/mtcc-core/src/main/java/com/yf/mtcc/core/service/impl/MtccTransactionLogServiceImpl.java
package com.yf.mtcc.core.service.impl;

import com.google.common.collect.Maps;
import com.yf.mtcc.common.config.MtccConfig;
import com.yf.mtcc.common.config.MtccDbConfig;
import com.yf.mtcc.common.domain.MtccParticipant;
import com.yf.mtcc.common.domain.MtccTransaction;
import com.yf.mtcc.common.exception.MtccException;
import com.yf.mtcc.common.serializer.KryoSerializer;
import com.yf.mtcc.common.serializer.ObjectSerializer;
import com.yf.mtcc.core.service.MtccTransactionLogDaoService;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * @Author: Elvis on 2020/4/13
 * @Email: yfelvis@gmail.com
 * @Desc: TODO
 */
@Service
@Slf4j
public class MtccTransactionLogServiceImpl implements MtccTransactionLogDaoService {

    private DataSource dataSource;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 序列化器
     */
    private ObjectSerializer serializer;

    @Override
    public int save(MtccTransaction transaction) {
        String sql = "insert into " + tableName + "(trans_id,phase,role,retried_times,version,target_class,target_method,"
                + "confirm_method,cancel_method,create_time,update_time,invocation)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            List<MtccParticipant> participantList = transaction.getParticipantList();
            byte[] serialize = serializer.serialize(participantList);
            return executeUpdate(sql, transaction.getTransactionId(), transaction.getPhase(), transaction.getRole(),
                    transaction.getRetryTimes(), transaction.getVersion(), transaction.getTargetClass(), transaction.getTargetMethod(),
                    transaction.getConfirmMethod(), transaction.getCancelMethod(), transaction.getCreateTime(), transaction.getUpdateTime(), serialize);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("序列化参与者列表失败....", e);
        }
        return 0;
    }

    @Override
    public int updateTransactionPhase(String transactionId, int phase) {
        String updateSql = "update " + tableName + " set phase = ? , update_time = ? where trans_id = ? ";
        return executeUpdate(updateSql, phase, new Date(), transactionId);

    }

    @Override
    public int updateTransactionParticipant(String transactionId, List<MtccParticipant> participantList) {
        String updateSql = "update " + tableName + " set invocation = ?, update_time = ? where trans_id = ?";
        try {
            byte[] invocation = serializer.serialize(participantList);
            return executeUpdate(updateSql, invocation, new Date(), transactionId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("序列化参与者列表失败....", e);
        }
        return 0;
    }

    @Override
    public MtccTransaction selectByTransId(String transactionId) {
        String sql = "select * from " + tableName + " where trans_id = ?";
        List<Map<String, Object>> maps = executeQuery(sql, transactionId);
        if (CollectionUtils.isNotEmpty(maps)) {
            return maps.stream()
                    .filter(Objects::nonNull)
                    .map(this::buildByResultMap)
                    .findFirst().orElse(null);
        }
        return null;
    }

    @Override
    public int deleteByTransId(String transactionId) {
        String sql = "delete from " + tableName + " where trans_id = ? and phase != 0";
        return executeUpdate(sql, transactionId);
    }

    @Override
    public List<MtccTransaction> selectAllByDelay(Date delayDate) {
        String selectSql = "select * from " + tableName + " where update_time < ? ";
        List<Map<String, Object>> list = executeQuery(selectSql, delayDate);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.stream().filter(Objects::nonNull)
                    .map(this::buildByResultMap)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public int updateTransactionRetryTimes(String transactionId, Integer version, Integer retryTimes) {
        try {
            int currentVersion = version + 1;
            String sql = "update " + tableName + " set retried_times = ? , version = ? ,update_time = ? where trans_id = ? and version = ? ";
            return executeUpdate(sql, retryTimes, currentVersion, new Date(), transactionId, version);
        } catch (MtccException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private MtccTransaction buildByResultMap(final Map<String, Object> map) {
        MtccTransaction mtccTransaction = new MtccTransaction();
        mtccTransaction.setTransactionId((String) map.get("trans_id"));
        mtccTransaction.setPhase((Integer) map.get("phase"));
        mtccTransaction.setRole((Integer) map.get("role"));
        mtccTransaction.setRetryTimes((Integer) map.get("retried_times"));
        mtccTransaction.setVersion((Integer) map.get("version"));
        mtccTransaction.setCreateTime((Date) map.get("create_time"));
        mtccTransaction.setUpdateTime((Date) map.get("update_time"));
        byte[] bytes = (byte[]) map.get("invocation");
        try {
            final List<MtccParticipant> mtccParticipantList = serializer.deSerialize(bytes, CopyOnWriteArrayList.class);
            mtccTransaction.setParticipantList(mtccParticipantList);
        } catch (MtccException e) {
            e.printStackTrace();
        }
        return mtccTransaction;
    }

    private List<Map<String, Object>> executeQuery(final String sql, final Object... params) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Map<String, Object>> list = null;
        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }
            rs = ps.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            list = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> rowData = Maps.newHashMap();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                list.add(rowData);
            }
        } catch (SQLException e) {
            log.error("query mtcc log table error: {}", e);
        } finally {
            close(connection, ps, rs);
        }
        return list;
    }


    @Override
    public void init(MtccConfig mtccConfig) {
//创建数据库连接池
        HikariDataSource hikariDataSource = new HikariDataSource();
        MtccDbConfig mtccDbConfig = mtccConfig.getDbConfig();
        hikariDataSource.setJdbcUrl(mtccDbConfig.getUrl());
        hikariDataSource.setDriverClassName(mtccDbConfig.getDriverClassName());
        hikariDataSource.setUsername(mtccDbConfig.getUsername());
        hikariDataSource.setPassword(mtccDbConfig.getPassword());
        hikariDataSource.setMinimumIdle(mtccDbConfig.getMinimumIdle());
        hikariDataSource.setMaximumPoolSize(mtccDbConfig.getMaximumPoolSize());
        hikariDataSource.setIdleTimeout(mtccDbConfig.getIdleTimeout());
        hikariDataSource.setConnectionTimeout(mtccDbConfig.getConnectionTimeout());
        hikariDataSource.setConnectionTestQuery(mtccDbConfig.getConnectionTestQuery());
        hikariDataSource.setMaxLifetime(mtccDbConfig.getMaxLifetime());
        dataSource = hikariDataSource;

        tableName = "mtcc_" + mtccConfig.getServiceName() + "_log";
        createTable(tableName);
        log.info("创建表成功");
        serializer = new KryoSerializer();

    }

    private void createTable(String tableName) {
        String buildTableSQL = buildMysql(tableName);
        executeUpdate(buildTableSQL);
    }


    private String buildMysql(final String tableName) {
        return "CREATE TABLE IF NOT EXISTS `" +
                tableName +
                "` (" +
                "  `trans_id` varchar(64) NOT NULL," +
                "  `phase` tinyint NOT NULL," +
                "  `role` tinyint NOT NULL," +
                "  `retried_times` tinyint NOT NULL," +
                "  `version` tinyint NOT NULL," +
                "  `target_class` varchar(256) ," +
                "  `target_method` varchar(128) ," +
                "  `confirm_method` varchar(128) ," +
                "  `cancel_method` varchar(128) ," +
                "  `invocation` longblob," +
                "  `create_time` datetime NOT NULL," +
                "  `update_time` datetime NOT NULL," +
                "  PRIMARY KEY (`trans_id`))";
    }

    private int executeUpdate(final String sql, final Object... params) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = dataSource.getConnection();
            //开启事务
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }
            int influence = ps.executeUpdate();
            connection.commit();
            return influence;
        } catch (SQLException e) {
            log.error("create mtcc log table error: {}", e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return 0;
        } finally {
            close(connection, ps, null);
        }

    }


    private void close(final Connection con, final PreparedStatement ps, final ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        try {
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }
}
