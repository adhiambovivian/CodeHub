package com.codeHub.service;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JdbcService {
    static String propertyPath="/Users/vivian/PERSONAL_PROJECTS/CodeHub/src/main/resources/application.properties";

    public static void jdbcCmd()
    {
        getEmpList();
    }

    public static Connection connectDbCmd(){
        Connection connection=null;
        String dbUsername=null;
        String dbPassword=null;
        String dbName=null;
        try {
            Properties properties = new Properties();
            FileReader reader = new FileReader(propertyPath);
            properties.load(reader);
            dbUsername=properties.getProperty("mysql.datasource.username");
            dbPassword=properties.getProperty("mysql.datasource.password");
            dbName=properties.getProperty("mysql.datasource.dbname");
            System.out.println(dbName+dbUsername+dbPassword);
        }catch (IOException e){
            e.printStackTrace();
        }
        try {
           // Class.forName("com.mysql.cj.jdbc.Driver"); //optional
            //create connection object
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName, dbUsername, dbPassword);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }

    public static void getEmpList() {

        String query="SELECT first_name, last_name, department, email FROM employees";
        try {
            Connection connection = connectDbCmd();
            //create statement object
            Statement statement = connection.createStatement();
            //execute query
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                System.out.println(rs.getString("first_name"));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTable() {
        String query="\n" +
                "CREATE TABLE IF NOT EXISTS employees (\n" +
                "first_name varchar(25),\n" +
                "last_name  varchar(25),\n" +
                "department varchar(15),\n" +
                "email  varchar(50)\n" +
                ")";
        try {
            Connection connection = connectDbCmd();
            //create statement object
            Statement statement = connection.createStatement();
            //execute query
            ResultSet rs = statement.executeQuery(query);
            ResultSet insertRs=statement.executeQuery(query);

            while (insertRs.next()) {
                //do sth
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createRecord() {

        String insertQuery="INSERT INTO employees (first_name, last_name, department, email) \n" +
                "VALUES ('Lorenz', 'Vanthillo', 'IT', 'lvthillo@mail.com')";
        try {
            Connection connection = connectDbCmd();
            //create statement object
            Statement statement = connection.createStatement();
            //execute query
            ResultSet insertRs=statement.executeQuery(insertQuery);

            while (insertRs.next()) {
                //do sth
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static void storeImage(){
        try {
            Connection connection = connectDbCmd();
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
