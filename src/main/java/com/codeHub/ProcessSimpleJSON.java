/* Copyright (C)2021  Vivian */
package com.codeHub;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ProcessSimpleJSON {
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void saveBlacklist() {
        try {
            // read the json file
            FileReader fileReader = new FileReader(getFileName());

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);

            String country = (String) jsonObject.get("country");

            JSONObject structure = (JSONObject) jsonObject.get("participant");
            String comment = (String) structure.get("comment");
            String comm_id = (String) structure.get("comm_id");
            System.out.println("comment: " + comment + " phone: " + comm_id);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
