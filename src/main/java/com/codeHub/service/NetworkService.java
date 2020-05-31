package com.codeHub.service;

import org.boon.core.Sys;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

public class NetworkService extends Thread{

    public static void networkCommands(){
        clientMethod();
    }

    public  void run(){
        try {
            ServerSocket serverSocket = new ServerSocket(8097);
            Socket socket=serverSocket.accept(); //establish connection and waits for the client
            DataInputStream dataInputStream=new DataInputStream((socket.getInputStream()));

            DataOutputStream dataOutputStream=new DataOutputStream(socket.getOutputStream());

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String val="",val2="";
            while(!val.equals("stop")) {
                val = dataInputStream.readUTF();
                System.out.println("client says: "+val);

                val2=bufferedReader.readLine();
                dataOutputStream.writeUTF(val2);
                dataOutputStream.flush();

            }
            dataInputStream.close();
            serverSocket.close();
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void clientMethod(){

        try {
            Socket socket = new Socket("127.0.0.1", 8097);
            DataOutputStream dataOutputStream=new DataOutputStream(socket.getOutputStream());

            DataInputStream dataInputStream=new DataInputStream(socket.getInputStream());
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));

            String val="",val2="";
            while(!val.equals("stop")){
                val=bufferedReader.readLine();
                dataOutputStream.writeUTF(val);
                dataOutputStream.flush();
                val2=dataInputStream.readUTF();
                System.out.println("Server says: "+val2);
            }

            dataOutputStream.close();
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String getURLdetails(URL url) throws IOException{
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("authority: "+url.getAuthority()).append("host: "+url.getHost()).append("port: "+url.getPort()).append("def port: "+url.getDefaultPort()).
                append("path: "+url.getPath()).append("protocol: "+url.getProtocol()).append("user: "+url.getUserInfo()).append("file: "+url.getFile()).
                append("query: "+url.getQuery()).append("content: "+url.getContent());

        System.out.println(stringBuilder.toString());
        return stringBuilder.toString()
    }

}
