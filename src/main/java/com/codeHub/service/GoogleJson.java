package com.codeHub.service;

import com.codeHub.BlackListView;
import com.codeHub.models.Person;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.*;

public class GoogleJson {
    public static void gsonParser(){
        //Gson gsonn=new GsonBuilder().setPrettyPrinting().create();
        GsonBuilder builder=new GsonBuilder();
        Gson gson=builder.create();
        String json = "{\"commId\":\"0728287279191\", \"comment\": xxx}";
        BlackListView blackListView = gson.fromJson(json,BlackListView.class);
    }
    public static void readerJson(){
        String json="{\"commId\":\"+2547298377\",\"comment\":\"DND\"}";
        JsonReader jsonReader=new JsonReader(new StringReader(json));
        try{
            while(jsonReader.hasNext()){
                JsonToken nextToken=jsonReader.peek();
                System.out.println(nextToken);
                if(JsonToken.BEGIN_OBJECT.equals(nextToken)){
                    jsonReader.beginObject();
                }else if(JsonToken.NAME.equals((nextToken))){
                    String name=jsonReader.nextName();
                    System.out.println(name);
                }else if(JsonToken.STRING.equals(nextToken)){
                    String value=jsonReader.nextString();
                    System.out.println(value);
                }else if(JsonToken.STRING.equals(nextToken)){
                    String val=jsonReader.nextString();
                    System.out.println(val);
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public static void parsingJson(){
        JsonParser parser=new JsonParser();
        String json="{ \"f1\":\"Hello\",\"f2\":{\"f3:\":\"World\"}}";
        JsonElement jsonTree=parser.parse(json);

        if(jsonTree.isJsonObject()){
            JsonObject jsonObject=jsonTree.getAsJsonObject();
            JsonElement f1=jsonObject.get("f1");
            JsonElement f2=jsonObject.get("f1");
            if(f2.isJsonObject()){
                JsonObject f2Obj=f2.getAsJsonObject();
                JsonElement f3=f2Obj.get("f3");
            }
        }
    }
    public static void writeJson(){
        JsonWriter jsonWriter;
        try{
            jsonWriter=new JsonWriter(new FileWriter("/home/adhiambo/projects/spring/gsontest.json"));
            jsonWriter.beginObject();//{
            jsonWriter.name("name").value("Vivian");
            jsonWriter.name("age").value(30);
            jsonWriter.name("messages");
            jsonWriter.beginArray();//[
            jsonWriter.value("msg 1");
            jsonWriter.value("msg 2");
            jsonWriter.endArray();//]
            jsonWriter.endObject();//}
            jsonWriter.close();
            System.out.println("done");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    //Reading json using object model
    public static void readJson(){
        BufferedReader reader=null;
        File file=null;
        try{
            file=new File("/home/adhiambo/projects/spring/person.json");
            reader =new BufferedReader(new FileReader(file));
            Gson gson=new GsonBuilder().create();
            Person[] people=gson.fromJson(reader,Person[].class);
            for(Person p:people) {
                System.out.println("Object mode: " + p.firstName);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }finally {
            System.out.println("File:: "+file.getAbsolutePath());
        }
    }
    public static void readStream(){
        try{
            InputStream inputStream = new FileInputStream("/home/adhiambo/projects/spring/person.json");
            JsonReader reader=new JsonReader(new InputStreamReader(inputStream,"UTF-8"));
            Gson gson=new GsonBuilder().create();
            //Read file in stream mode

            reader.beginArray();
            while(reader.hasNext()){
                //Read data into object model
                Person person=gson.fromJson(reader,Person.class);
                System.out.println("Person:: "+person.toString());
            }
            reader.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void writeFile(){
Person person=new Person("vivian","Adhiambo",22,"3737738");
Gson gson=new Gson();
String json=gson.toJson(person);
try{
    FileWriter  fileWriter=new FileWriter("blacklist.json");
    fileWriter.write(json);
    fileWriter.close();
}catch (IOException e){
    e.printStackTrace();
}
System.out.println(json);
    }
}
