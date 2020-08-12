package com.codeHub.service;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SparkService {
    @Autowired
    private JavaSparkContext sc;

    public Map<String,Long> wordCount(List<String> wordList){
        JavaRDD<String> words = sc.parallelize(wordList);
        Map<String,Long> wordCounts = words.countByValue();
        return wordCounts;
    }
}
