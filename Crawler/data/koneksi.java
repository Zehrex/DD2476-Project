2
https://raw.githubusercontent.com/heridho118140173/TUBES-PBO-MASTER/master/TUBES-PBO-master/Bengkel1/src/koneksi.java
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.SQLException;
/**
 *
 * @author Heridho
 */
public class koneksi {
    private static Connection con;
    public static Connection getkoneksi() throws SQLException{
     try{
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        con = DriverManager.getConnection("jdbc:mysql://localhost/db_bengkel","root","");       
    }catch (Exception e){
         System.out.println("Koneksi Gagal" + e.getMessage());
    }
    return con;
    }
}
