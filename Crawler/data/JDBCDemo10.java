2
https://raw.githubusercontent.com/chengcheng1021/javaStudy/master/src/jdbc/Demo/JDBCDemo10.java
package jdbc.Demo;

import jdbc.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *  事务操作
 */
public class JDBCDemo10 {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;

        try {
            //1、获取连接
            conn = JDBCUtils.getConnection();
            //开启事务
            conn.setAutoCommit(false);

            //2、定义sql
            //2.1 张三 - 500
            String sql1 = "update money set balance = balance - ? where id = ?";
            //2.2 李四 + 500
            String sql2 = "update money set balance = balance + ? where id = ?";
            //3、获取执行sql对象
            pstmt1 = conn.prepareStatement(sql1);
            pstmt2 = conn.prepareStatement(sql2);
            //4、设置参数
            pstmt1.setInt(1,500);
            pstmt1.setInt(2,1);

            pstmt2.setInt(1,500);
            pstmt2.setInt(2,2);

            pstmt1.executeUpdate();
            pstmt2.executeUpdate();

            //制造错误
            int i = 3/0;
            //提交事务
            conn.commit();
        } catch (Exception throwables) {
            try {
                if(conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt1, conn);
            JDBCUtils.close(pstmt2, null);
        }
    }
}
