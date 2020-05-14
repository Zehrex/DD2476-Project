18
https://raw.githubusercontent.com/GZYangKui/openjfx-database/master/mysql/src/main/java/com/openjfx/database/mysql/JSqlParserHelper.java
package com.openjfx.database.mysql;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.ShowStatement;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.update.Update;
import net.sf.jsqlparser.util.TablesNamesFinder;

import java.util.ArrayList;

/**
 * JSsqlParser 辅助工具类
 *
 * @author yangkui
 * @since 1.0
 */
public class JSqlParserHelper {
    /**
     * 查询一条sql语句中的所有表名
     *
     * @param sql 目标sql语句
     * @return 返回表明列表
     * @throws JSQLParserException sql解析异常
     */
    public static String transformSelectOrUpdate(final String sql, String scheme) throws JSQLParserException {
        var list = new ArrayList<String>();
        var statement = CCJSqlParserUtil.parse(sql);
        if (statement instanceof Select || statement instanceof Update) {
            var tablesNamesFinder = new TablesNamesFinder();
            var tableList = tablesNamesFinder.getTableList(statement);
            list.addAll(tableList);
        }
        var result = sql;
        for (var table : list) {
            //说明表名已经加了数据库名称
            if (table.contains(".")) {
                continue;
            }
            var tName = scheme + "." + table;
            result = sql.replaceAll(table, tName);
        }
        return result;
    }

    /**
     * @param sql    目标sql语句
     * @param scheme scheme
     * @return 返回转换后的sql语句
     * @throws JSQLParserException 转换异常
     */
    public static String transformShowSql(String sql, String scheme) throws JSQLParserException {
        var str = sql.toLowerCase();
        var statement = (ShowStatement) CCJSqlParserUtil.parse(sql);
        var name = statement.getName();
        var c = "tables".equals(name) && !str.contains("from");
        if (c) {
            var newName = name + " from " + scheme;
            statement.setName(newName);
            sql = statement.toString();
        }
        return sql;
    }

    /**
     * 转换函数
     *
     * @param str    目标字符串
     * @param scheme scheme
     * @return 返回转换后的字符串
     * @throws JSQLParserException 转换异常
     */
    public static String transform(String str, String scheme) throws JSQLParserException {
        var b = str.toLowerCase().startsWith("show");
        var sql = "";
        if (b) {
            sql = JSqlParserHelper.transformShowSql(str, scheme);
        } else {
            sql = JSqlParserHelper.transformSelectOrUpdate(str, scheme);
        }
        return sql;
    }
}
