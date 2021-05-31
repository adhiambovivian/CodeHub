/* Copyright (C)2021  Vivian */
package com.codeHub.configs;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

public class JedisEx {
    // address of your redis server
    private static final String redisHost = "localhost";
    private static final Integer redisPort = 6379;

    // the jedis connection pool..
    private static JedisPool pool = null;

    public JedisEx() {
        // configure our pool connection
        pool = new JedisPool(redisHost, redisPort);
    }

    public void addSets() {
        // let us first add some data in our redis server using Redis SET.
        String key = "members";
        String member1 = "Sedarius";
        String member2 = "Richard";
        String member3 = "Joe";

        // get a jedis connection jedis connection pool
        Jedis jedis = pool.getResource();
        try {
            // save to redis
            jedis.sadd(key, member1, member2, member3);

            // after saving the data, lets retrieve them to be sure that it has really added in
            // redis
            Set members = jedis.smembers(key);
            for (Object member : members) {
                System.out.println(member.toString());
            }
        } catch (JedisException e) {
            // if something wrong happen, return it back to the pool
            if (null != jedis) {
                pool.returnBrokenResource(jedis);
                jedis = null;
            }
        } finally {
            /// it's important to return the Jedis instance to the pool once you've finished using
            // it
            if (null != jedis) pool.returnResource(jedis);
        }
    }

    public void addHash() {
        // add some values in Redis HASH
        String key = "javapointers";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Java Pointers");
        map.put("domain", "www.javapointers.com");
        map.put("description", "Learn how to program in Java");

        Jedis jedis = pool.getResource();
        try {
            // save to redis
            jedis.hmset(key, map);

            // after saving the data, lets retrieve them to be sure that it has really added in
            // redis
            Map<String, String> retrieveMap = jedis.hgetAll(key);
            for (String keyMap : retrieveMap.keySet()) {
                System.out.println(keyMap + " " + retrieveMap.get(keyMap));
            }

        } catch (JedisException e) {
            // if something wrong happen, return it back to the pool
            if (null != jedis) {
                pool.returnBrokenResource(jedis);
                jedis = null;
            }
        } finally {
            /// it's important to return the Jedis instance to the pool once you've finished using
            // it
            if (null != jedis) pool.returnResource(jedis);
        }
    }

    //
    // }{
    //        "key_data": "pendingsurvey_+254733930492_None",
    //        "value_data": {
    //        "survey":
    // "cdjango.db.models.base\nmodel_unpickle\np1\n((S'engine'\np2\nS'SurveyProgress'\np3\nt(lcdjango.db.models.base\nsimple_class_factory\np4\ntRp5\n(dp6\nS'finished_at'\np7\nNsS'respondent_id'\np8\nL3883377L\nsS'attempted_at'\np9\nNsS'optout_at'\np10\nNs$
    //        }

    public void setHash() {
        // add some values in Redis HASH
        String key = "";
        Map<String, String> map = new HashMap<>();

        BufferedReader reader = null;
        File file = null;
        try {
            file = new File("/home/adhiambo/projects/spring/data.json");
            FileReader fileReader = new FileReader(file);

            JSONParser jsonParser = new JSONParser();
            JSONArray jsonObject = (JSONArray) jsonParser.parse(fileReader);
            JSONArray redisList = (JSONArray) jsonObject;
            Iterator<JSONObject> iterator = redisList.iterator();
            while (iterator.hasNext()) {
                JSONObject redis = (JSONObject) iterator.next();
                // System.out.println(redis.get("key_data"));
                map.put("survey", (String) redis.get("value_data"));
                key = (String) redis.get("key_data");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            System.out.println("File:: " + file.getAbsolutePath());
        }
        Jedis jedis = pool.getResource();
        try {
            // save to redis
            jedis.hmset(key, map);

            // after saving the data, lets retrieve them to be sure that it has really added in
            // redis
            Map<String, String> retrieveMap = jedis.hgetAll(key);
            for (String keyMap : retrieveMap.keySet()) {
                System.out.println("$$$$$$$$$$ " + keyMap + " " + retrieveMap.get(keyMap));
            }

        } catch (JedisException e) {
            // if something wrong happen, return it back to the pool
            if (null != jedis) {
                pool.returnBrokenResource(jedis);
                jedis = null;
            }
        } finally {
            /// it's important to return the Jedis instance to the pool once you've finished using
            // it
            if (null != jedis) pool.returnResource(jedis);
        }
    }
}
