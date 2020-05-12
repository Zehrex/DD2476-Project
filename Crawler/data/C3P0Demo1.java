2
https://raw.githubusercontent.com/chengcheng1021/javaStudy/master/src/jdbc/datasource/c3p0/C3P0Demo1.java
package jdbc.datasource.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class C3P0Demo1 {

    public static void main(String[] args) throws SQLException {
        //1、创建数据库连接池
        DataSource ds = new ComboPooledDataSource();
        //2、获取连接对象
        Connection conn = ds.getConnection();

        //3、打印
        System.out.println(conn);
    }


}


