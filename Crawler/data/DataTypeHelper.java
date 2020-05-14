18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/database/src/main/java/com/openjfx/database/DataTypeHelper.java
package com.openjfx.database;

/**
 * database data type help class
 *
 * @author yangkui
 * @since 1.0
 */
public class DataTypeHelper {
    private static final String[] NUMBER = new String[]{
            "TINYINT", "SMALLINT", "MEDIUMINT", "INT", "BIGINT"
    };
    private static final String[] DATETIME = new String[]{
            "DATETIME", "TIMESTAMP", "DATE", "TIME", "YEAR"
    };

    public static boolean number(final String type) {
        return isFixType(NUMBER, type);
    }

    public static boolean dateTime(final String type) {
        return isFixType(DATETIME, type);
    }

    private static boolean isFixType(final String[] ss, final String type) {
        for (String s : ss) {
            if (type.toUpperCase().startsWith(s)) {
                return true;
            }
        }
        return false;
    }
}
