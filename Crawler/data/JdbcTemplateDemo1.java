2
https://raw.githubusercontent.com/chengcheng1021/javaStudy/master/src/jdbc/datasource/jdbctemplate/JdbcTemplateDemo1.java
package jdbc.datasource.jdbctemplate;

import jdbc.datasource.utils.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *  JdbcTemplate入门
 */
public class JdbcTemplateDemo1 {

    public static void main(String[] args) {
        //1、导入jar包
        //2、创建JDBCTemplate对象
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        //3、调用方法
        String sql = "update student set name = 'hahah' where id = ?";
        int count = template.update(sql, 3);

        System.out.println(count);
    }
}
