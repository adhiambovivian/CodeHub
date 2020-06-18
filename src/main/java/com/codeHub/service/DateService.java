package com.codeHub.service;

import org.boon.core.Sys;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class DateService {

    public static void dateCmds(){
        //localDateCmd();
        localTimeCmd();
    }

    public static void localDateCmd(){
        LocalDate localDate = LocalDate.now();
        LocalDate yesterday=localDate.minusDays(1);
        LocalDate tomorrow=localDate.plusDays(2);

        localDate.atTime(17,59);

        LocalDate date = LocalDate.of(2020,06,15);

        System.out.println("Today: "+localDate+" Tomorrow: "+tomorrow+" Yesterday: "+yesterday+" IsLeap year: "+date.isLeapYear());
    }

    public static void localTimeCmd(){
        LocalTime time=LocalTime.now();
        System.out.println("Current Time: "+time+ " Time later: "+LocalTime.of(10,45,22)+" Time before an hour: "+time.minusHours(1)+" Time after 30 min: "+time.plusMinutes(30));
        ZoneId kolkataZone = ZoneId.of("Asia/Kolkata");
        ZoneId tokyoZone = ZoneId.of("Asia/Tokyo");

        LocalTime timeKolkata = LocalTime.now(kolkataZone);
        LocalTime timeTokyo=LocalTime.now(tokyoZone);
        long hours = ChronoUnit.HOURS.between(timeTokyo,timeKolkata);
        long minutes =ChronoUnit.MINUTES.between(timeKolkata,timeTokyo);

        System.out.println("Time in Tokyo: "+timeTokyo+" Time in Kolkota: "+timeKolkata+" Hours btn: "+hours);
    }
}
