/* Copyright (C)2021  Vivian */
package com.codeHub;

import com.codeHub.models.Participant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JSONConverter {
    public static void convert(String[] args) {
        Gson objGson = new GsonBuilder().setPrettyPrinting().create();

        List participantList =
                Stream.of(
                                new Participant("Mike", "harvey", 34, "001894536"),
                                new Participant("Nick", "young", 75, "005425676"),
                                new Participant("Jack", "slater", 21, "009654153"),
                                new Participant("gary", "hudson", 55, "00564536"),
                                new Participant("Mike", "harvey", 21, "003685417"),
                                new Participant("gary", "hudson", 25, "00452341"))
                        .collect(Collectors.toList());

        // Convert list to json
        System.out.println("1. Convert list of person objects to Json");
        String json = objGson.toJson(participantList);
        System.out.println(json);

        // Convert json back to list
        System.out.println("2. Convert JSON to list of person objects");
        Type listType = new TypeToken<List>() {}.getType();
        List readFromJson = objGson.fromJson(json, listType);
        readFromJson.forEach(System.out::println);
    }
}
