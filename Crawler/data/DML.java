18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/database/src/main/java/com/openjfx/database/DML.java
package com.openjfx.database;

import com.openjfx.database.model.TableColumnMeta;
import io.vertx.core.Future;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 数据库操作语言接口
 *
 * @author yangkui
 * @since 1.0
 */
public interface DML {
    /**
     * 更新值(存在唯一标识)
     *
     * @param fields    字段值
     * @param tableName 表名
     * @param key       key值
     * @param keyValue  keyValue
     * @return 返回更新结果
     */
    Future<Integer> update(Map<String, String> fields, String tableName, String key, String keyValue);

    /**
     * 批量更新
     *
     * @param tableName 表名
     * @param items     待更新数据
     * @param metas     列信息
     * @return 返回更新结果
     */
    Future<Integer> batchUpdate(List<Map<String, Object[]>> items, String tableName, List<TableColumnMeta> metas);

    /**
     * 新增数据
     *
     * @param metas     table meta
     * @param columns   列值
     * @param tableName 表名
     * @return 返回新增结果
     */
    Future<Long> insert(List<TableColumnMeta> metas, Object[] columns, String tableName);

    /**
     * 批量插入
     *
     * @param tableName 表名
     * @param rows      列值
     * @param metas     表字段信息
     * @return 返回插入结果
     */
    Future<Object> batchInsert(List<TableColumnMeta> metas, List<Object[]> rows, String tableName);

    /**
     * 获取自增字段
     *
     * @param metas 字段列表
     * @return 返回自增字段
     */
    Optional<TableColumnMeta> getAutoIncreaseField(List<TableColumnMeta> metas);

    /**
     * 批量删除
     *
     * @param keyMeta   key字段
     * @param keyValues key值列表
     * @param tableName 表名
     * @return 返回受影响行数
     */
    Future<Integer> batchDelete(TableColumnMeta keyMeta, Object[] keyValues, String tableName);

    /**
     * 执行给定的sql更新语句
     *
     * @param sql sql语句
     * @return 返回受影响行数
     */
    Future<Integer> executeSqlUpdate(String sql);
}
