/* Copyright (C)2021  Vivian */
package com.codeHub.service.jsonService;

// import jdk.nashorn.internal.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReadTxtJsonSimpleJSON {
    public static void txtToJson() {
        try {
            JSONParser parser = new JSONParser();

            Object obj = parser.parse(new FileReader("/home/adhiambo/kenya.txt"));
            JSONObject jsonObject = (JSONObject) obj;
            String name = (String) jsonObject.get("Name");
            String author = (String) jsonObject.get("Author");
            JSONArray companyList = (JSONArray) jsonObject.get("Company List");

            System.out.println("Name: " + name);
            System.out.println("Author: " + author);
            System.out.println("\nCompany List: " + companyList);
            Iterator<String> iterator = companyList.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static void writeJson() {
        JSONObject obj = new JSONObject();
        obj.put("name", "Vivian");
        obj.put("age", new Integer(34));
        JSONArray list = new JSONArray();
        list.add("msg 1");
        list.add("msg 2");
        list.add("msg 3");

        obj.put("messages", list);
        try {
            FileWriter file = new FileWriter("/home/adhiambo/simple.json");
            file.write(obj.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(obj);
    }

    public static void readJson() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("firstName", "Vivian");
            jsonObject.put("lastName", "Adhiambo");
            jsonObject.put("age", 22);
            Map map = new LinkedHashMap(4);
            map.put("Street Address", "45 2nd Street");
            map.put("City", "New York");
            map.put("State", "NY");
            map.put("Postal Code", 383737);

            jsonObject.put("address", map);
            JSONArray jsonArray = new JSONArray();
            map = new LinkedHashMap(2);
            map.put("type", "home");
            map.put("number", "227 555-26252");
            jsonArray.add(map);
            map = new LinkedHashMap(2);
            map.put("type", "fax");
            map.put("number", "212-3636-2827");
            jsonArray.add(map);
            jsonObject.put("phone numbers", jsonArray);

            // write to file
            PrintWriter printWriter = new PrintWriter("simple.json");
            printWriter.write(jsonObject.toJSONString());
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
