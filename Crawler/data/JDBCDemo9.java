2
https://raw.githubusercontent.com/chengcheng1021/javaStudy/master/src/jdbc/Demo/JDBCDemo9.java
package jdbc.Demo;

import jdbc.util.JDBCUtils;

import java.sql.*;
import java.util.Scanner;

/**
 *  练习：
 *      需求：
 *          1、通过键盘录入用户名和密码
 *          2、判断用户是否登录成功
 */
public class JDBCDemo9 {

    public static void main(String[] args) {
        //1、键盘录入
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = sc.nextLine();
        System.out.println("请输入密码：");
        String password = sc.nextLine();
        //2、调用方法
        boolean flag = new JDBCDemo9().login2(username, password);
        if(flag){
            System.out.println("登录成功！");
        } else {
            System.out.println("用户名或密码错误！");
        }
    }


    /**
     *  登录方法
     */
    public boolean login(String username, String password){

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            //1、获取连接
            conn = JDBCUtils.getConnection();
            //2、定义sql
            String sql = "select * from user where username = '" + username + "' and password = '" + password + "'";
            //获取执行sql的对象
            stmt = conn.createStatement();
            //4、执行查询
            rs = stmt.executeQuery(sql);
            //5、判断
//            if(rs.next()){
//                return true;
//            } else {
//                return false;
//            }
            System.out.println(sql);

            return rs.next();//如果有下一行，则返回true

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(rs, stmt, conn);
        }

        return false;
    }

    /**
     *  登录方法 - 优化
     */
    public boolean login2(String username, String password){

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            //1、获取连接
            conn = JDBCUtils.getConnection();
            //2、定义sql
            String sql = "select * from user where username = ? and password = ?";
            //获取执行sql的对象
            pstmt = conn.prepareStatement(sql);
            //给？赋值
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            //4、执行查询
            rs = pstmt.executeQuery();
            //5、判断
//            if(rs.next()){
//                return true;
//            } else {
//                return false;
//            }
            System.out.println(sql);

            return rs.next();//如果有下一行，则返回true

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(rs, pstmt, conn);
        }

        return false;
    }
}
