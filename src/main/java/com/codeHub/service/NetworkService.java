/* Copyright (C)2021  Vivian */
package com.codeHub.service;

import java.io.*;
import java.net.*;

public class NetworkService extends Thread {

    public static void networkCommands() {
        //         getDocUrlDetails();
        //         readWebpage();
        //         readHttpWebpage();
        //         getWebpageHeaders();
        //         getWebpageInformation();
        //         getPageAddressDetails();
        //         sendDatagramPacket();
        //         receiveDatagramPacket();
    }

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(8097);
            Socket socket = serverSocket.accept(); // establish connection and waits for the client
            DataInputStream dataInputStream = new DataInputStream((socket.getInputStream()));

            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String val = "", val2 = "";
            while (!val.equals("stop")) {
                val = dataInputStream.readUTF();
                System.out.println("client says: " + val);

                val2 = bufferedReader.readLine();
                dataOutputStream.writeUTF(val2);
                dataOutputStream.flush();
            }
            dataInputStream.close();
            serverSocket.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clientMethod() {

        try {
            Socket socket = new Socket("127.0.0.1", 8097);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            String val = "", val2 = "";
            while (!val.equals("stop")) {
                val = bufferedReader.readLine();
                dataOutputStream.writeUTF(val);
                dataOutputStream.flush();
                val2 = dataInputStream.readUTF();
                System.out.println("Server says: " + val2);
            }

            dataOutputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getURLdetails(URL url) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("authority: " + url.getAuthority())
                .append(" host: " + url.getHost())
                .append(" port: " + url.getPort())
                .append(" def port: " + url.getDefaultPort())
                .append(" path: " + url.getPath())
                .append(" protocol: " + url.getProtocol())
                .append(" user: " + url.getUserInfo())
                .append(" file: " + url.getFile())
                .append(" query: " + url.getQuery())
                .append(" content: " + url.getContent());
        return stringBuilder.toString();
    }

    public static void getDocUrlDetails() {
        try {
            URL url =
                    new URL(
                            "https://docs.google.com/document/d/1gNOG_3A-JQZA7iIgI0oOunuu5kW0O1wU7Ga5Eu5C2xA/edit#");
            String detail = getURLdetails(url);
            System.out.println(detail);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readWebpage() {
        try {
            URL url = new URL("https://www.javatpoint.com/URLConnection-class");
            URLConnection connection = url.openConnection();
            InputStream stream = connection.getInputStream();

            int data = 0;
            while ((data = stream.read()) != -1) {
                System.out.print((char) data);
            }
            stream.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public static void readHttpWebpage() {
        try {
            URL url = new URL("https://www.javatpoint.com/URLConnection-class");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream stream = connection.getInputStream();

            int data = 0;
            while ((data = stream.read()) != -1) {
                System.out.print((char) data);
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public static void getWebpageHeaders() {
        try {
            URL url = new URL("https://google.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            for (int key = 1; key < 10; key++) {
                System.out.println(
                        connection.getHeaderFieldKey(key) + " = " + connection.getHeaderField(key));
            }
            connection.disconnect();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public static void getWebpageInformation() {
        try {
            URL url = new URL("https://google.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder
                    .append("method: " + connection.getRequestMethod())
                    .append(" code:" + connection.getResponseCode())
                    .append(" permission: " + connection.getPermission())
                    .append(" msg: " + connection.getResponseMessage())
                    .append(" encoding: " + connection.getContentEncoding());

            System.out.println(stringBuilder);
            connection.disconnect();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public static void getPageAddressDetails() {
        try {
            InetAddress ip = InetAddress.getByName("www.ajua.com");
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder
                    .append("hostname: " + ip.getHostName())
                    .append(" hostAdd: " + ip.getHostAddress())
                    .append(" canonical Addr: " + ip.getCanonicalHostName());
            System.out.println(stringBuilder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendDatagramPacket() {
        try {
            DatagramSocket datagramSocket = new DatagramSocket();
            String val = "Welcome to today's session... hope y'al have fun";
            InetAddress ip = InetAddress.getByName("127.0.0.1");
            DatagramPacket datagramPacket =
                    new DatagramPacket(val.getBytes(), val.length(), ip, 8099);

            datagramSocket.send(datagramPacket);
            datagramSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void receiveDatagramPacket() {
        try {
            Thread.sleep(1000);

            DatagramSocket datagramSocket = new DatagramSocket(8099);
            byte[] buf = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(buf, 1024);
            datagramSocket.receive(datagramPacket);

            String val = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
            System.out.println(val);
            datagramSocket.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
