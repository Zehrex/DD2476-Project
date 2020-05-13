2
https://raw.githubusercontent.com/portocreator/FAEv1.0/master/src/java/modelado/Operaciones.java
package modelado;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class Operaciones {
    
    public static Connection getConnection(){
        //cambiar nombre de la bd
        String url="jdbc:mysql://localhost:3306/fae1";
        String userName="root";
        String password="root";
        
        Connection con = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, userName, password);
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return con;
    }
    
    public int loguear(String usuario, String password){
        int nivel = 0;
        try{
            Connection con = Operaciones.getConnection();
            ResultSet rs;

            //verificar nombre de las columnas
            String q = "SELECT user_level FROM musuario where user_tag='"+usuario+"' and user_password='"+password+"'"; 
            PreparedStatement ps = con.prepareStatement(q);
 
            rs = ps.executeQuery();
            
            while(rs.next()){
                nivel = rs.getInt(1);
            }
            con.close();
        }catch(SQLException ed){
            System.out.println(ed.getMessage());
            System.out.println(Arrays.toString(ed.getStackTrace()));
        }
        return nivel;
    }
}
