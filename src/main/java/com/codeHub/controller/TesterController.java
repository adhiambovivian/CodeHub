/* Copyright (C)2021  Vivian */
package com.codeHub.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController("v1/")
public class TesterController {

    @RequestMapping(method = RequestMethod.GET, path = "test")
    public HashMap<String, Long> testerMethod() {
        HashMap<String, Long> response = new HashMap<>();
        response.put("lol", 100L);
        return response;
    }
}
