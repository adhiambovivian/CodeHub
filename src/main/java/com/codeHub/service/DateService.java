package com.codeHub.service;

import org.boon.core.Sys;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class DateService {

    public static void dateCmds(){
        //localDateCmd();
        localTimeCmd();
        localDatetimeCmd();
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

    public static void localDatetimeCmd(){
        LocalDateTime now=LocalDateTime.now();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatDateTime=now.format(formatter);

        LocalDateTime pastTime=LocalDateTime.of(2019,11,23,12,34,56,200);
        String pastDateTime=pastTime.minusYears(102).format(formatter);
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("Day of week: "+now.get(ChronoField.DAY_OF_WEEK)).append(" Day of year: "+now.get(ChronoField.DAY_OF_YEAR)).append(" Day of month "+now.get(ChronoField.DAY_OF_MONTH)).
                append(" Hour of day "+now.get(ChronoField.HOUR_OF_DAY)).append(" Minute of day "+now.get(ChronoField.MINUTE_OF_DAY));
        System.out.println("Formatted current datetime: "+formatDateTime+" Current datetime details: "+stringBuilder.toString()+" past time: "+pastDateTime);

    }
}
