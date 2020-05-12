2
https://raw.githubusercontent.com/chengcheng1021/javaStudy/master/src/jdbc/Demo/JdbcDemo8.java
package jdbc.Demo;

import jdbc.Domain.Emp;
import jdbc.util.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  定义一个方法，查询emp表的数据将其封装为对象，然后封装集合，返回
 */
public class JdbcDemo8 {

    public static void main(String[] args) {
        List<Emp> list = new JdbcDemo8().findAll2();
        System.out.println(list);
        System.out.println(list.size());
    }

    /**
     * 查询多有emp对象
     * @return
     */
    public List<Emp> findAll(){
        ResultSet rs = null;
        Connection conn = null;
        Statement stmt = null;
        ArrayList<Emp> list = null;

        try {
            //1、注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2、获取连接
            conn = DriverManager.getConnection("jdbc:mysql:///db1", "root", "");
            //3、定义sql
            String sql = "select * from emp";
            //4、获取执行sql的对象
            stmt = conn.createStatement();
            //5、执行sql
            rs = stmt.executeQuery(sql);

            Emp emp = null;
            list = new ArrayList<>();
            //6、遍历结果集，封装对象，装载集合
            while (rs.next()){
                //获取数据
                int id = rs.getInt("id");
                String admin_login = rs.getString("admin_login");
                int last_login_time = rs.getInt("last_login_time");
                int status = rs.getInt("status");
                Date deleted_at = rs.getDate("deleted_at");
                String last_login_ip = rs.getString("last_login_ip");

                //创建emp对象
                emp = new Emp();
                emp.setId(id);
                emp.setAdmin_login(admin_login);
                emp.setLast_login_time(last_login_time);
                emp.setStatus(status);
                emp.setDeleted_at(deleted_at);
                emp.setLast_login_ip(last_login_ip);

                //装载集合
                list.add(emp);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        return list;
    }

    /**
     * 查询多有emp对象
     * @return
     */
    public List<Emp> findAll2(){
        ResultSet rs = null;
        Connection conn = null;
        Statement stmt = null;
        ArrayList<Emp> list = null;

        try {
//            //1、注册驱动
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            //2、获取连接
//            conn = DriverManager.getConnection("jdbc:mysql:///db1", "root", "");

            conn = JDBCUtils.getConnection();
            //3、定义sql
            String sql = "select * from emp";
            //4、获取执行sql的对象
            stmt = conn.createStatement();
            //5、执行sql
            rs = stmt.executeQuery(sql);

            Emp emp = null;
            list = new ArrayList<>();
            //6、遍历结果集，封装对象，装载集合
            while (rs.next()){
                //获取数据
                int id = rs.getInt("id");
                String admin_login = rs.getString("admin_login");
                int last_login_time = rs.getInt("last_login_time");
                int status = rs.getInt("status");
                Date deleted_at = rs.getDate("deleted_at");
                String last_login_ip = rs.getString("last_login_ip");

                //创建emp对象
                emp = new Emp();
                emp.setId(id);
                emp.setAdmin_login(admin_login);
                emp.setLast_login_time(last_login_time);
                emp.setStatus(status);
                emp.setDeleted_at(deleted_at);
                emp.setLast_login_ip(last_login_ip);

                //装载集合
                list.add(emp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
//            if(rs != null){
//                try {
//                    rs.close();
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//            }
//
//            if(stmt != null){
//                try {
//                    stmt.close();
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//            }
//
//            if(conn != null){
//                try {
//                    conn.close();
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//            }

            JDBCUtils.close(rs, stmt, conn);
        }

        return list;
    }
}
