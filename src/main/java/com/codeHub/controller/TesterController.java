/* Copyright (C)2021  Vivian */
package com.codeHub.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController("/v2/")
public class TesterController {

    @RequestMapping(
            method = RequestMethod.GET,
            path = "tester",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, Long> testerMethod() {
        HashMap<String, Long> response = new HashMap<>();
        response.put("lol", 100L);
        return response;
    }
}
