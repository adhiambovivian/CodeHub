package com.codeHub.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JdbcService {
    public static void jdbcCmd(){
        connectDbCmd("");
    }

    public static Connection connectDbCmd(String query){
        Connection connection=null;
        try {
            Class.forName("driver");
            //create connection object
            connection = DriverManager.getConnection("", "user", "pass");
            //create statement object
            Statement statement = connection.createStatement();
            //execute query
            ResultSet rs = statement.executeQuery(query);

//            while (rs.next()) {
//                //do sth
//            }
//            connection.close();

        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return connection;
    }

    public static void storeImage(){
        try {
            Connection connection = connectDbCmd("");
            PreparedStatement ps = connection.prepareStatement("INSERT INTO media_images (name, id) values(?,?)");
            ps.setString(1, "wow");
            FileInputStream fileInputStream = new FileInputStream("path");
            ps.setBinaryStream(2, fileInputStream);
            int status = ps.executeUpdate();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
