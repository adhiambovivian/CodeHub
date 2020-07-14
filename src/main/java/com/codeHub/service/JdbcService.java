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
        createTable();
        createRecord();
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
//        String query="SELECT user FROM user_summary";
        try {
            Connection connection = connectDbCmd();
            //create statement object
            Statement statement = connection.createStatement();
            //execute query
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                System.out.println(rs.getString("first_name"));
            }
            Statement stmt=connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet=stmt.executeQuery(query);
            //get data for the 3rd row
            resultSet.absolute(3);
            System.out.println(resultSet.getString(1)+" "+resultSet.getString(2));
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
            int result = statement.executeUpdate(query);
            if(result!=0)
                System.out.println("Created table successfully");
            else
                System.out.println("Table exists");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createRecord() {

        String insertQuery="INSERT INTO employees (first_name, last_name, department, email) \n" +
                "VALUES (?,?,?,?)";
        try {
            Connection connection = connectDbCmd();
            //create statement object
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1,"Vitu");
            statement.setString(2,"Voila");
            statement.setString(3,"TECH");
            statement.setString(4,"voila@gmail.com");
            //execute query
            int result = statement.executeUpdate(insertQuery);
            if(result!=0)
                System.out.println("Created record successfully");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getDBMetadata(){
        try{
            String query="SELECT first_name, last_name, department, email FROM employees";

            Connection connection=connectDbCmd();
            PreparedStatement ps=connection.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            System.out.println("Total columns: "+rsm.getColumnCount());
            System.out.println("Column Name of 1st column: "+rsm.getColumnName(1));
            System.out.println("Column Type Name of 1st column: "+rsm.getColumnTypeName(1));
        }catch (SQLException e){
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
