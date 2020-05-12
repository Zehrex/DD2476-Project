2
https://raw.githubusercontent.com/chengcheng1021/javaStudy/master/src/jdbc/datasource/druid/DruidDemo2.java
package jdbc.datasource.druid;

import jdbc.datasource.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *  使用新的工具类
 */
public class DruidDemo2 {

    public static void main(String[] args) {
        /**
         *  完成添加操作，给 student 表添加一条记录
         */
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            //1、获取连接
            conn = JDBCUtils.getConnection();
            //2、定义sql
            String sql = "insert into student values(null, ?, ?)";
            //3、获取pstmt对象
            pstmt = conn.prepareStatement(sql);
            //4、给 ？赋值
            pstmt.setString(1, "ddddd");
            pstmt.setInt(2, 2);
            //5、执行sql
            int count = pstmt.executeUpdate();
            System.out.println(count);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt, conn);
        }
    }
}
