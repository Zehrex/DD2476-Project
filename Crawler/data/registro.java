2
https://raw.githubusercontent.com/portocreator/FAEv1.0/master/src/java/modelado/registro.java
package modelado;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import modelado.Operaciones;

public class registro {

    public static Connection getConnection(){
        //cambiar nombre de la bd
        String url="jdbc:mysql://localhost:3306/fae1";
        String userName="root";
        String password="root";
        
        Connection con = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, userName, password);
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return con;
    }
    
    public boolean registro(String user_tag, String user_password, String user_name, String user_appat, String user_apmat){
        boolean registro = false;
        Connection con = null;
        try{
            con = Operaciones.getConnection();

            //verificar nombre de las columnas
            String q = "insert into musuario (user_tag, user_password, user_name, user_appat, user_apmat, user_level)"
            + "values (?,?,?,?,?,3); ";

            PreparedStatement ps = con.prepareStatement(q);
            ps.setString(1, user_tag);
            ps.setString(2, user_password);
            ps.setString(3, user_name);
            ps.setString(4, user_appat);
            ps.setString(5, user_apmat);
            ps.executeUpdate();
          
            if(ps != null){
                ps.close();
                ps = null;
                registro = true;
            }
            con.close();
        } catch (Exception e)  { 
          System.out.println(e.getMessage());
          System.out.println(e.getStackTrace());
        }
        return registro;
    }
}