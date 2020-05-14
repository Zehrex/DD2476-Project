18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/mysql/src/main/java/com/openjfx/database/mysql/impl/SimpleMysqlDataConvert.java
package com.openjfx.database.mysql.impl;

import com.openjfx.database.DataConvert;
import com.openjfx.database.common.utils.StringUtils;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.openjfx.database.common.config.StringConstants.NULL;

/**
 * mysql数据转换器
 *
 * @author yangkui
 * @since 1.0
 */
public class SimpleMysqlDataConvert implements DataConvert {
    /**
     * 默认时间转换格式
     */
    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @Override
    public List<String[]> toConvert(RowSet<Row> rows) {
        var list = new ArrayList<String[]>();
        for (Row row : rows) {
            var size = row.size();
            var array = new String[size];
            for (var i = 0; i < size; i++) {
                var val = row.getValue(i);
                if (Objects.isNull(val)) {
                    array[i] = NULL;
                } else if (val instanceof LocalDateTime) {
                    array[i] = StringUtils.localDateTimeToStr((LocalDateTime) val, DEFAULT_DATE_PATTERN);
                } else {
                    array[i] = val.toString();
                }
            }
            list.add(array);
        }
        return list;
    }


}
