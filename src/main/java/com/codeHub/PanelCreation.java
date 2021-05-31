/* Copyright (C)2021  Vivian */
package com.codeHub;

import com.codeHub.models.Participant;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.simple.JSONObject;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

public class PanelCreation extends Thread {
    public synchronized void generateParticipantsJson(long length) {
        long start = System.currentTimeMillis();

        System.out.println("Thread " + Thread.currentThread().getName() + " panel size: " + length);
        try {
            LocalDateTime date = LocalDateTime.now();
            String fileName = "panel" + length + "_" + date.toString() + ".json";
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            List<Participant> participantList = new ArrayList<Participant>();
            BufferedWriter fileWriter =
                    new BufferedWriter(new FileWriter("/home/adhiambo/PANELS/" + fileName));
            for (int i = 0; i <= length; i++) {
                Faker faker = new Faker();
                String commId = generateCommId(7, "0124", "7");
                String city = faker.address().city();
                String last_name = faker.name().lastName();
                String first_name = faker.name().firstName();
                int ageInt = (new Random().nextInt((80 - 18) + 1) + 18);
                String age = Integer.toString(ageInt);
                Meta meta = new Meta(city, last_name, first_name, age);
                Participant participant = new Participant(commId, meta);
                participantList.add(participant);
                System.out.println("### " + Thread.currentThread().getName() + " Count:: " + i);
            }
            String json = gson.toJson(participantList);
            fileWriter.write(json);
            fileWriter.close();
            // launch thread to do this
            scanDirectory("/home/adhiambo/PANELS/");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println(
                    Thread.currentThread().getStackTrace()[1].getMethodName()
                            + " Duration:: "
                            + (System.currentTimeMillis() - start));
        }
    }

    /*
    If you want to generate other telco numbers, modify possibleComm, length,start
     */
    public String generateCommId(int length, String possibleComm, String start) {
        StringBuilder stringBuilder = new StringBuilder();
        while (length > 0) {
            stringBuilder.append(randomChar());
            length--;
        }
        String generatedString = stringBuilder.toString();
        Random random = new SecureRandom();
        String commId =
                start
                        + possibleComm.charAt(random.nextInt(possibleComm.length()))
                        + generatedString;
        return commId;
    }

    private char randomChar() {
        Random random = new SecureRandom();
        String numbers = "0123456789";
        return numbers.charAt(random.nextInt(numbers.length()));
    }

    /*
    scans directory for unprocessed files. If file is found, a new thread is created to create panel as the current thread contnues scanning.
     */
    public void scanDirectory(String path) {
        try {
            File directory = new File(path);
            for (File file : directory.listFiles()) {
                System.out.println(
                        "Found file "
                                + file.getName()
                                + " Current thread "
                                + Thread.currentThread().getName());
                if (file.getName().endsWith(".json")) {

                    Thread thread =
                            new Thread() {
                                public void run() {
                                    List<String> panels = new ArrayList<String>();
                                    String[] ids = {
                                        "11", "12", "10"
                                    }; // initial panel ids that are existing//Retrieve from db
                                    panels.addAll(Arrays.asList(ids));
                                    String generatedPanelId = "";
                                    //                            try{
                                    //
                                    // generatedPanelId=createPanel();
                                    //
                                    // if(!generatedPanelId.isEmpty())
                                    //
                                    // panels.add(generatedPanelId);
                                    //                            }catch (Exception e){
                                    //                                e.printStackTrace();
                                    //                            }
                                    Random random = new Random();
                                    System.out.println(
                                            "Processing file "
                                                    + file.getName()
                                                    + " Current thread "
                                                    + Thread.currentThread().getName());
                                    file.renameTo(
                                            new File(
                                                    "/home/adhiambo/PANELS/processed/"
                                                            + file.getName()));
                                    System.out.println("New file path:: " + file.getAbsolutePath());
                                    addParticipantsPanel(
                                            "/home/adhiambo/PANELS/processed/" + file.getName(),
                                            panels.get(random.nextInt(ids.length)));
                                }
                            };
                    thread.start();
                } else System.out.println("File not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String createPanel() {
        System.out.println("Creating panel..." + Thread.currentThread().getName());
        String panelId = "";
        final String url = "http://api.msurvey.co.ke:8083/v1/panel";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders header = new HttpHeaders();
        String auth = "bitnami" + ":" + "vivian2020";
        byte[] authentication = auth.getBytes();
        byte[] base64Authentication = Base64Utils.encode(authentication);
        String baseCredential = new String(base64Authentication);
        header.add(HttpHeaders.AUTHORIZATION, "Basic " + baseCredential);
        header.setContentType(MediaType.APPLICATION_JSON);

        JSONObject request = new JSONObject();
        request.put("country", "KE");
        request.put("name", "panel");
        HttpEntity<String> entity = new HttpEntity<String>(request.toString(), header);
        ResponseEntity<String> requestResponse =
                restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println("####" + requestResponse.getBody().toString());
        if (requestResponse.getStatusCode().is2xxSuccessful()) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode =
                        objectMapper.readValue(
                                requestResponse.getBody().toString(), JsonNode.class);
                JsonNode nestedObj = jsonNode.get("Data");
                JsonNode jsonNode2 = nestedObj.get("id");
                panelId = jsonNode2.asText();
                System.out.println(panelId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return panelId;
    }

    public String addParticipantsPanel(String filename, String panelId) {
        System.out.println("Panel id " + panelId + " file " + filename);
        final String url = "http://api.msurvey.co.ke:8083/v1/panel/{panelId}/participants/file";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders header = new HttpHeaders();
        String auth = "bitnami" + ":" + "vivian2020";
        byte[] authentication = auth.getBytes();
        byte[] base64Authentication = Base64Utils.encode(authentication);
        String baseCredential = new String(base64Authentication);
        header.add(HttpHeaders.AUTHORIZATION, "Basic " + baseCredential);
        File file = new File(filename);

        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("file", new FileSystemResource(file));
        header.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<>(map, header);
        ResponseEntity<String> requestResponse =
                restTemplate.exchange(url, HttpMethod.POST, entity, String.class, panelId);
        System.out.println(requestResponse);
        if (requestResponse.getStatusCode().is2xxSuccessful()) {
            System.out.println("created.. " + requestResponse.getBody());
            return "created";
        } else {
            System.out.println("Failed .." + requestResponse.getBody());
            return "failed";
        }
    }

    public void createParticipant(int noFiles) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(noFiles);

        for (int i = 0; i < noFiles; i++) {
            Runnable worker = new MyRunnable();
            executor.execute(worker);
        }
        executor.shutdown();
        // Wait until all threads are finish
        while (!executor.isTerminated()) {}

        System.out.println("\nFinished all threads");
    }

    public class MyRunnable implements Runnable {

        @Override
        public void run() {
            List<Integer> panelSize = new ArrayList<Integer>();
            Integer[] size = {20, 10, 50};
            panelSize.addAll(Arrays.asList(size));
            Random random = new Random();
            // possibilty of threads accessing one file
            generateParticipantsJson((panelSize.get(random.nextInt(size.length))));
        }
    }
}
