package com.codeHub;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;


import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class JacksonJSONDemo {

    public static void jacksonObjMapper()

    {
        ObjectMapper objectMapper = new ObjectMapper();
        String blacklistJson = "{\"commId\":\"+254729880700\",\"countryId\":1,\"comment\":\"DND\"}";
        try {
            BlackListView blackListView = objectMapper.readValue(blacklistJson, BlackListView.class);
            System.out.println("Blacklist commId: " + blackListView.getCommId());
            System.out.println("Blacklist countryId: " + blackListView.getCountryId());
            System.out.println("Blacklist comment: " + blackListView.getComment());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void jacksonTree() {
        ObjectMapper objectMapper = new ObjectMapper();
        String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5," +
                "  \"owners\" : [\"John\", \"Jack\", \"Jill\"]," +
                "  \"nestedObject\" : { \"field\" : \"value\" } }";
        try {
            JsonNode jsonNode = objectMapper.readValue(carJson, JsonNode.class);

            JsonNode brandNode = jsonNode.get("brand");
            String brand = brandNode.asText();
            System.out.println(brand);

            JsonNode doorsNode = jsonNode.get("doors");
            int doors = doorsNode.asInt();
            System.out.println(doors);

            JsonNode arrayOwner = jsonNode.get("owners");
            JsonNode jsonNode1 = arrayOwner.get(0);
            String john = jsonNode1.asText();
            System.out.println(john);

            JsonNode nestedObj = jsonNode.get("nestedObject");
            JsonNode jsonNode2 = nestedObj.get("field");
            String field = jsonNode2.asText();
            System.out.println(field);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void jacksonReadJson_string() throws IOException {
        //Reading json as a string
        ObjectMapper objectMapper = new ObjectMapper();
        String blacklistJson = "{\"commId\":\"+254729880700\",\"countryId\":1,\"comment\":\"DND\"}";
        BlackListView bl = objectMapper.readValue(blacklistJson, BlackListView.class);
    }

    public static void jacksonReadJson_reader() throws IOException {
        //Reading json from a reader
        ObjectMapper objectMapper = new ObjectMapper();
        String blacklistJson = "{\"commId\":\"+254729880700\",\"countryId\":1,\"comment\":\"DND\"}";
        Reader reader = new StringReader(blacklistJson);
        BlackListView bl = objectMapper.readValue(reader, BlackListView.class);
    }

    public static void jacksonReadJson_file() throws IOException {
        //Reading json from a file
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("/home/adhiambo/Downloads/blacklist.json");
        BlackListView bl = objectMapper.readValue(file, BlackListView.class);
    }

    public static void jacksonReadJson_URL() throws IOException {
        //Reading json from a URL
        ObjectMapper objectMapper = new ObjectMapper();
        URL url = new URL("file:data/blacklist.json");
        BlackListView bl = objectMapper.readValue(url, BlackListView.class);
    }

    public static void jacksonReadJson_Inputstream() throws IOException {
        //Reading json from a inputStream
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = new FileInputStream("data/blacklist.json");
        BlackListView bl = objectMapper.readValue(inputStream, BlackListView.class);
    }

    public static void jacksonReadJson_byteArray() throws IOException {
        //Reading json from byteArray
        ObjectMapper objectMapper = new ObjectMapper();
        String blacklistJson = "{\"commId\":\"+254729880700\",\"countryId\":1,\"comment\":\"DND\"}";
        byte[] bytes = blacklistJson.getBytes("UTF-8");
        BlackListView bl = objectMapper.readValue(bytes, BlackListView.class);
    }

    public static void generateJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Blacklist blacklist = new Blacklist();
        blacklist.setCommId("+2547298811");
        blacklist.setComment("Gaming the system");
        objectMapper.writeValue(new FileOutputStream("data/output.json"), blacklist);
        String json = objectMapper.writeValueAsString(blacklist);
        System.out.println(json);
    }

    public static void jsonParser() throws IOException {
        BlackListView blackListView = new BlackListView();
        String blacklistJson = "{\"commId\":\"+254729880700\",\"countryId\":1,\"comment\":\"DND\"}";
        JsonFactory jsonFactory = new JsonFactory();
        //You can also pass a Reader, InputStream, URL, byte array or char array to the createParser() method
        JsonParser parser = jsonFactory.createParser(blacklistJson);
        while (!parser.isClosed()) {
            JsonToken jsonToken = parser.nextToken();
            System.out.println("jsonToken: " + jsonToken);
            if (JsonToken.FIELD_NAME.equals(jsonToken)) {
                String fieldName = parser.getCurrentName();
                System.out.println(fieldName);
                jsonToken = parser.nextToken();
                if ("commId".equals(fieldName)) {
                    blackListView.setCommId(parser.getValueAsString());
                } else if ("countryId".equals(fieldName)) {
                    blackListView.setCountryId(parser.getLongValue());
                } else if ("comment".equals(fieldName)) {
                    blackListView.setComment(parser.getValueAsString());
                }
            }
        }
        System.out.println("CommId: " + blackListView.getCommId());
        System.out.println("CountryId" + blackListView.getCountryId());
        System.out.println("comment: " + blackListView.getComment());
    }

    public static void jacksonGenerateJson() throws IOException {
        JsonFactory jsonFactory = new JsonFactory();
        JsonGenerator jsonGenerator = jsonFactory.createGenerator(new File("data/output.json"), JsonEncoding.UTF8);
        jsonGenerator.writeStartObject(); //writes {
        jsonGenerator.writeStringField("commId", "+254729880726");
        jsonGenerator.writeNumberField("countryId", 1);
        jsonGenerator.writeStringField("comment", "DND");
        jsonGenerator.writeEndObject(); //writes }
        jsonGenerator.close(); //closes any outputstream if used
    }

    public static void convertCSVJson() {
        File input = new File("/home/adhiambo/Kenya_Audience.csv");
        File output = new File("/home/adhiambo/zambia.json");
        CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).build();
        CsvMapper csvMapper = new CsvMapper();
        //Read Csv data
        try {
            List<Object> readAll = csvMapper.readerFor(Map.class).with(csvSchema).readValues(input).readAll();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(output, readAll);
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(readAll));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
