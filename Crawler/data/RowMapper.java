2
https://raw.githubusercontent.com/sciuridae564/PcrTool/tick/src/main/java/cn/sciuridae/DB/sqLite/RowMapper.java
package cn.sciuridae.DB.sqLite;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
    T mapRow(ResultSet rs, int index) throws SQLException;
}