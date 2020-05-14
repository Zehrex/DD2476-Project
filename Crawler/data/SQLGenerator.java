18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/database/src/main/java/com/openjfx/database/SQLGenerator.java
package com.openjfx.database;

import com.openjfx.database.model.TableColumnMeta;

import java.util.List;
import java.util.concurrent.Future;

/**
 * sql生成接口
 *
 * @author yangkui
 * @since 1.0
 */
public interface SQLGenerator {
    /**
     * SELECT
     *
     * @param metas
     * @param tableName
     * @return
     */
    String select(List<TableColumnMeta> metas, String tableName);

    /**
     * UPDATE
     *
     * @param metas
     * @param tableName
     * @return
     */
    String update(List<TableColumnMeta> metas, String tableName);

    /**
     * INSERT
     *
     * @param metas
     * @param tableName
     * @return
     */
    String insert(List<TableColumnMeta> metas, String tableName);

    /**
     * DELETE
     *
     * @param metas
     * @param tableName
     * @return
     */
    String delete(List<TableColumnMeta> metas, String tableName);


    /**
     * 生成类型
     *
     * @author yangkui
     * @since 1.0
     */
    enum GeneratorType {
        /**
         * SELECT
         */
        SELECT,
        /**
         * INSERT
         */
        INSERT,
        /**
         * UPDATE
         */
        UPDATE,
        /**
         * DELETE
         */
        DELETE,
    }
}
