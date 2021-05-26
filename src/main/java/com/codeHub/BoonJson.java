package com.codeHub;

import org.boon.json.JsonFactory;
import org.boon.json.ObjectMapper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoonJson {
public static void parseJson()throws IOException{
    String blacklists="{" +
            "  \"person\" : [" +
            "     { \"commId\" : \"+254729883736\", \"comment\" : \"DND\" }" +
            "    ,{ \"commId\" : \"+25473368282\", \"comment\" : \"Gaming\"}" +
            "    ,{ \"commId\" : \"+25479372929\",  \"comment\" : \"self\" }" +
            "  ]" +
            "}";
    ObjectMapper objectMapper= JsonFactory.create();
    PersonLists bl=objectMapper.readValue(blacklists, PersonLists.class);
    PersonLists bl2=objectMapper.fromJson(blacklists, PersonLists.class);

    //parsing json into maps
    Map blacklistMap=objectMapper.readValue(blacklists,Map.class);
    List<Map> blacklistList=(List<Map>)blacklistMap.get("person");
    for(Map map:blacklistList){
        String commId=(String) map.get("commId");
        String comment=(String)map.get("comment");
        System.out.println("CommId: "+commId);
        System.out.println("Comment: "+comment);

        //Can parse Json from: byte array,char array, File,Reade,InputStream, String
        PersonLists bl3=objectMapper.readValue(new FileInputStream("data/blacjlist.json"), PersonLists.class);
    }
}
public static void generateJson(){
    PersonLists bl=new PersonLists();
    bl.person =new Person[1];
    bl.person[0]=new Person("Never Again","+254729880726");
    ObjectMapper objectMapper=JsonFactory.create();
    String json=objectMapper.writeValueAsString(bl); //objectMapper.writeValue(new FileOutputStream("data/output.json"),bl); //Also File,Writer,outpuStream
    System.out.println(json);
}
public static void generateJsonFromMap()throws IOException{
    Map bl=new HashMap();
    bl.put("commID","+254328288292");
    bl.put("countryId",2);
    bl.put("comment","DND");
    List bls=new ArrayList();
    bls.add(bl);

    Map blParticipants=new HashMap();
    blParticipants.put("participants",bls);

    ObjectMapper objectMapper=JsonFactory.create();
    String json=objectMapper.writeValueAsString(blParticipants);
    objectMapper.writeValue(new FileOutputStream("data/xx.json"),bl);
    System.out.println(json);
}
public static void dateJson(){
    ObjectMapper objectMapper = JsonFactory.createUseJSONDates();
    ObjectMapper mapper = JsonFactory.create();
    int intVal = mapper.parser().parseInt("123");
    int[] ints = objectMapper.parser().parseIntArray("[123, 456, 789]");
    String jsonMap = "{ \"key1\" : \"val1\", \"key2\" : \"val2\" }";

    Map<String, Object> map =
            mapper.parser().parseMap(jsonMap);
}
}
