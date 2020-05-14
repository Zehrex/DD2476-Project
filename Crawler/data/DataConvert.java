18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/database/src/main/java/com/openjfx/database/DataConvert.java
package com.openjfx.database;

import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;

import java.util.List;

/**
 * 数据库数据转换接口
 *
 * @author yangkui
 * @since 1.0
 */
public interface DataConvert {
    /**
     * 转换目标数据为字符串
     *
     * @param rows 原始数据
     * @return 字符串集合
     */
    List<String[]> toConvert(RowSet<Row> rows);
}
