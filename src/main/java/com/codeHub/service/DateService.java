package com.codeHub.service;

import java.time.LocalDate;

public class DateService {

    public static void dateCmds(){
        localDateCmd();
    }

    public static void localDateCmd(){
        LocalDate localDate = LocalDate.now();
        LocalDate yesterday=localDate.minusDays(1);
        LocalDate tomorrow=localDate.plusDays(2);

        LocalDate date = LocalDate.of(2020,06,15);

        System.out.println("Today: "+localDate+" Tomorrow: "+tomorrow+" Yesterday: "+yesterday+" IsLeap year: "+date.isLeapYear());
    }
}
