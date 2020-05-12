2
https://raw.githubusercontent.com/chengcheng1021/javaStudy/master/src/jdbc/datasource/c3p0/C3P0Demo2.java
package jdbc.datasource.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class C3P0Demo2 {

    public static void main(String[] args) throws SQLException {
        //1、获取DataSource，使用默认配置
        ComboPooledDataSource ds = new ComboPooledDataSource();
        //2、获取连接

        for(int i = 1; i <= 11; i++){
            Connection conn = ds.getConnection();
            System.out.println(i + ":" +conn);

            if(i == 5){
                conn.close();//归还连接到连接池
            }
        }

        testNamedConfig();
    }

    public static void testNamedConfig() throws SQLException {
        //1、获取DataSource，使用默认配置
        ComboPooledDataSource ds = new ComboPooledDataSource("otherc3p0");
        //2、获取连接

        for(int i = 1; i <= 10; i++){
            Connection conn = ds.getConnection();
            System.out.println(i + ":" +conn);

            if(i == 5){
                conn.close();//归还连接到连接池
            }
        }
    }
}
