package com.codeHub.service;

import java.sql.*;
import java.util.Properties;

public class JdbcService {
    public static void jdbcCmd(){
        connectDbCmd("");
    }

    public static void connectDbCmd(String query){
        try {
            //create connection object
            Connection connection = DriverManager.getConnection("", "user", "pass");
            //create statement object
            Statement statement = connection.createStatement();
            //execute query
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                //do sth
            }
            connection.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
