package com.codeHub.service;

import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

@Component
public class URLConnectAppService {
    public static void getUsers() {
        try {
            URL url = new URL("http://api.sth.co.ke:8083/v1/users");
            HttpURLConnection connection=(HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept","application/json");
            if(connection.getResponseCode()!=200){
                throw new RuntimeException("Failed: "+connection.getResponseMessage()+" status: "+connection.getResponseCode());
            }
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            HashMap <String,Object> output=new HashMap<String,Object>();
            String response=bufferedReader.readLine();
            while(response!=null){
                System.out.println(output);
            }
            connection.disconnect();
        } catch (MalformedURLException e) {
e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void createUser() {
        try {
            URL url = new URL("http://api.sth.co.ke:8083/v1/surveys/1/panel");
            HttpURLConnection connection=(HttpURLConnection)url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/json");
            String payload="{\"panelId\":2,\"target\":2}";
            OutputStream outputStream=connection.getOutputStream();
            outputStream.write(payload.getBytes());
            outputStream.flush();

            if(connection.getResponseCode()!=HttpURLConnection.HTTP_ACCEPTED){
                throw new RuntimeException("Failed: "+connection.getResponseMessage()+" Status: "+connection.getResponseCode());
            }
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String output=bufferedReader.readLine();
            while(output!=null){
                System.out.println(output);
            }
            connection.disconnect();
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
