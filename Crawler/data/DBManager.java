1
https://raw.githubusercontent.com/Bekzatiitu/Final_Bekzat_Bekarys_ishs1901/master/Final%20Bekzat%20Bekarys%20ishs1901/src/kenn/shi/DBManager.java
package kenn.shi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBManager {
    private Connection connection;
    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/workfinding?useUnicode=true&serverTimezone=UTC","root", ""
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void adduser(You you){
        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO users (id, name, surname, age, city, desiredjobs) " +
                    "VALUES (NULL, ?, ?, ?, ?, ?)"
            );
            statement.setString(1, you.getName());
            statement.setString(2, you.getSurname());
            statement.setInt(3, you.getAge());
            statement.setString(4, you.getCity());
            statement.setString(5, you.getDesiredJobs());

            statement.executeUpdate();

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<You> getAllusers(){
        ArrayList<You> usersList = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                Integer age = resultSet.getInt("age");
                String city = resultSet.getString("city");
                String desiredjobs = resultSet.getString("desiredjobs");

                usersList.add(new You(id, name, surname, age, city, desiredjobs));
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return usersList;
    }
}
