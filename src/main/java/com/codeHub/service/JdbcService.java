package com.codeHub.service;

import org.boon.core.Sys;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class JdbcService {
    static String propertyPath="/Users/vivian/PERSONAL_PROJECTS/CodeHub/src/main/resources/application.properties";
    static String filePath="/Users/vivian/PERSONAL_PROJECTS/CodeHub/data/";

    public static void jdbcCmd()
    {
        String query="\n" +
            "CREATE TABLE IF NOT EXISTS employee (\n" +
            "first_name varchar(25),\n" +
            "last_name  varchar(25),\n" +
            "department varchar(15),\n" +
            "email  varchar(50),\n" +
            "id INT AUTO_INCREMENT PRIMARY KEY\n"+
            ")";
        createTable(query);
        createRecord();
        getEmpList();
        getRsDBMetadata();
        getDBMetadata();
        storeImage();
        storeFile();
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

        String query="SELECT first_name, last_name, department, email FROM employee";
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
            resultSet.absolute(1);
            System.out.println(resultSet.getString(1)+" "+resultSet.getString(2));
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTable(String query) {
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

        String insertQuery="INSERT INTO employee (first_name, last_name, department, email) VALUES (?,?,?,?)";
        try {
            Connection connection = connectDbCmd();
            //create statement object
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1,"Vitu");
            statement.setString(2,"Voila");
            statement.setString(3,"TECH");
            statement.setString(4,"voila@gmail.com");
            //execute query
            int result = statement.executeUpdate();
            if(result!=0)
                System.out.println("Created record successfully");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getRsDBMetadata(){
        try{
            String query="SELECT first_name, last_name, department, email FROM employee";

            Connection connection=connectDbCmd();
            PreparedStatement ps=connection.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            ResultSetMetaData rsm=rs.getMetaData();
            System.out.println("Total columns: "+rsm.getColumnCount());
            System.out.println("Column Name of 1st column: "+rsm.getColumnName(1));
            System.out.println("Column Type Name of 1st column: "+rsm.getColumnTypeName(1));
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void getDBMetadata(){
        try{
            String query="SELECT first_name, last_name, department, email FROM employee";

            Connection connection=connectDbCmd();
            DatabaseMetaData dbmd=connection.getMetaData();

            System.out.println("Driver Name: "+dbmd.getDriverName());
            System.out.println("Driver Version: "+dbmd.getDriverVersion());
            System.out.println("UserName: "+dbmd.getUserName());
            System.out.println("Database Product Name: "+dbmd.getDatabaseProductName());
            System.out.println("Database Product Version: "+dbmd.getDatabaseProductVersion());
            //print tables/views
            String table[]={"TABLE"};
            String view[]={"VIEW"};
            ResultSet rs=dbmd.getTables(null,null,null,table);
            while (rs.next()){
                System.out.println(rs.getString(1));
            }
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void storeImage(){
        try {
            String createTableQ="CREATE TABLE IF NOT EXISTS profile_image \n" +
                    "   (\n" +
                    "id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                    "profile_photo MEDIUMBLOB NOT NULL,\n" +
                    "employee_id INT UNIQUE ,\n" +
                    "FOREIGN KEY(employee_id)REFERENCES employee(id)\n" +
                    " \n" +
                    "   )";
            createTable(createTableQ);

            Connection connection = connectDbCmd();

            Statement st=connection.createStatement();
            st.executeUpdate(createTableQ);

            PreparedStatement ps = connection.prepareStatement("INSERT INTO profile_image (profile_photo, employee_id) values(?,?)");
            FileInputStream fileInputStream = new FileInputStream(filePath+"profile.png");
            System.out.println("Size of data: "+fileInputStream.available());
            ps.setBinaryStream(1, fileInputStream,fileInputStream.available());
            ps.setInt(2, 1);

            int status = ps.executeUpdate();
            if(status>0) {
                System.out.println("Successfully add profile pic");

                //retrieve file
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT profile_photo,employee_id FROM profile_image");
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    Blob blob = rs.getBlob(1);
                    byte data[] = blob.getBytes(1, (int) blob.length());
                    FileOutputStream fileOutputStream = new FileOutputStream(filePath + "pic_output.png");
                    fileOutputStream.write(data);
                    fileOutputStream.close();
                    fileInputStream.close();
                }
            }
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void storeFile(){
        try {
            String createTableQ="CREATE TABLE IF NOT EXISTS employee_file \n" +
                    "   (\n" +
                    "id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                    "file_name TEXT NOT NULL,\n" +
                    "employee_id INT UNIQUE,\n" +
                    "FOREIGN KEY(employee_id)REFERENCES employee(id)\n" +
                    " )";
            createTable(createTableQ);

            Connection connection = connectDbCmd();

            Statement st=connection.createStatement();
            st.executeUpdate(createTableQ);

            PreparedStatement ps = connection.prepareStatement("INSERT INTO employee_file (file_name, employee_id) values(?,?)");
            File file = new File(filePath+"playbook.pdf");
            FileReader fileReader=new FileReader(file);

            ps.setCharacterStream(1, fileReader,(int)file.length());
            ps.setInt(2, 1);

            int status = ps.executeUpdate();
            if(status>0) {
                System.out.println("Successfully add doc");

                //retrieve file
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT file_name,employee_id FROM employee_file");
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    Clob blob = rs.getClob(1);
                    Reader reader = blob.getCharacterStream();
                    FileWriter fileWriter = new FileWriter(filePath + "doc_output.pdf");
                    int data = 0;
                    while ((data = reader.read()) != -1)
                        fileWriter.write((char) data);
                    fileWriter.close();
                    reader.close();
                }
            }
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }



}
