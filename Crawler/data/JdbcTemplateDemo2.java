2
https://raw.githubusercontent.com/chengcheng1021/javaStudy/master/src/jdbc/datasource/jdbctemplate/JdbcTemplateDemo2.java
package jdbc.datasource.jdbctemplate;

import jdbc.Domain.Emp;
import jdbc.datasource.utils.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class JdbcTemplateDemo2 {

    //Junit单元测试，可以让方法独立运行

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     *  1、修改1号数据的 sex = 9
     */
    @Test
    public void test1() {
        //2、定义sql
        String sql = "update student set sex = 9 where id = 2";
        //3、执行sql
        int count = template.update(sql);
        System.out.println(count);
    }

    /**
     *  2、添加一条记录
     */
    @Test
    public void test2() {
        //2、定义sql
        String sql = "insert into student value (?, ?, ?)";
        //3、执行sql
        int count = template.update(sql,null, "hahaha" ,1);
        System.out.println(count);
    }

    /**
     *  3、查询id=1，封装成map集合
     */
    @Test
    public void test3() {
        //2、定义sql
        String sql = "select * from student where id = ?";
        //3、执行sql
        Map<String, Object> map = template.queryForMap(sql, 2);
        System.out.println(map);
    }

    /**
     *  4、查询所有记录，将其封装成list
     */
    @Test
    public void test4() {
        //2、定义sql
        String sql = "select * from student";
        //3、执行sql
        List<Map<String, Object>> list = template.queryForList(sql);
        System.out.println(list);
    }

    /**
     *  5、查询所有记录，将其封装成emp对象的List集合
     */
    @Test
    public void test5() {
        //2、定义sql
        String sql = "select * from emp";
        //3、执行sql
        List<Emp> list = template.query(sql, new RowMapper<Emp>() {
            @Override
            public Emp mapRow(ResultSet rs, int i) throws SQLException {
                Emp emp = new Emp();
                int id = rs.getInt("id");
                String admin_login = rs.getString("admin_login");
                int last_login_time = rs.getInt("last_login_time");
                int status = rs.getInt("status");
                Date deleted_at = rs.getDate("deleted_at");
                String last_login_ip = rs.getString("last_login_ip");

                emp.setId(id);
                emp.setAdmin_login(admin_login);
                emp.setLast_login_time(last_login_time);
                emp.setStatus(status);
                emp.setDeleted_at(deleted_at);
                emp.setLast_login_ip(last_login_ip);

                return emp;
            }
        });

        for (Emp emp : list){
            System.out.println(emp);
        }
    }

    /**
     *  5、查询所有记录，将其封装成emp对象的List集合
     */
    @Test
    public void test5_2() {
        //2、定义sql
        String sql = "select * from emp";
        //3、执行sql
        List<Emp> list = template.query(sql, new BeanPropertyRowMapper<Emp>(Emp.class));
        for (Emp emp : list){
            System.out.println(emp);
        }
    }

    /**
     *  6、查询总记录数
     */
    @Test
    public void test6() {
        //2、定义sql
        String sql = "select count(id) from emp";
        //3、执行sql
        Long total = template.queryForObject(sql, Long.class);
        System.out.println(total);
    }
}
