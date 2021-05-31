/* Copyright (C)2021  Vivian */
package com.codeHub.controller;

import com.codeHub.service.SparkService;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/v1/")
public class SparkController {
    @Autowired private SparkService sparkService;

    @RequestMapping(method = RequestMethod.POST, path = "wordcount")
    public Map<String, Long> count(@RequestParam(required = true) String words) {
        List<String> wordList = Arrays.asList(words.split("\\|"));
        return sparkService.wordCount(wordList);
    }
}
