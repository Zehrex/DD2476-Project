2
https://raw.githubusercontent.com/sciuridae564/PcrTool/tick/src/main/java/cn/sciuridae/DB/sqLite/ResultSetExtractor.java
package cn.sciuridae.DB.sqLite;

import java.sql.ResultSet;

public interface ResultSetExtractor<T> {

    T extractData(ResultSet rs);

}