2
https://raw.githubusercontent.com/chengcheng1021/javaStudy/master/src/jdbc/Demo/JdbcDemo6.java
package jdbc.Demo;

import java.sql.*;

public class JdbcDemo6 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            //1、注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2、获取连接对象
            conn = DriverManager.getConnection("jdbc:mysql:///db1", "root", "");
            //3、定义 sql
            String sql = "select * from student";
            //4、获取执行 sql 对象
            stmt = conn.createStatement();
            //5、执行 sql
            rs = stmt.executeQuery(sql);
            //6.1、将游标向下移动一行
            rs.next();
            //6.2、获取数据
            int id = rs.getInt(1);
            String name = rs.getString("name");
            int sex = rs.getInt(3);

            System.out.println(id + "---" + name + "---" + sex);

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
    }
}
