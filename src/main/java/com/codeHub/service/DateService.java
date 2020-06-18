package com.codeHub.service;

import org.boon.core.Sys;
import org.boon.primitive.Chr;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.ValueRange;

public class DateService {

    public static void dateCmds(){
        //localDateCmd();
//        localTimeCmd();
//        localDatetimeCmd();
//        monthDayCmd();
//        offsetTimeCmd();
//        offsetDateTimeCmd();
//        clockCmd();
        zonedDateTimeCmd();
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

    public static void monthDayCmd(){
        MonthDay month = MonthDay.now();
        LocalDate date = month.atYear(1995);
        ValueRange valMonth=month.range(ChronoField.MONTH_OF_YEAR);
        ValueRange valDay=month.range(ChronoField.DAY_OF_MONTH);

        System.out.println("   "+date+" Month of year "+month.get(ChronoField.MONTH_OF_YEAR)+" is 2021 valid "+month.isValidYear(2012)+" rangeMonth: "+valMonth+" rangeDay: "+valDay);

    }

    public static void offsetTimeCmd(){
        OffsetTime offsetTime=OffsetTime.now();

        StringBuilder st=new StringBuilder();
        st.append("Now: "+offsetTime+" Hr of day: "+offsetTime.get(ChronoField.HOUR_OF_DAY)).append(" Min of day: "+offsetTime.get(ChronoField.MINUTE_OF_DAY)).
                append(" Sec of day: "+offsetTime.get(ChronoField.SECOND_OF_DAY)).append(" Current hr: "+offsetTime.getHour()).append(" Offset minus 34 hrs: "+offsetTime.minusHours(34));

        System.out.println(st.toString());
    }

    public static void offsetDateTimeCmd(){
        OffsetDateTime off=OffsetDateTime.now();
        StringBuilder st=new StringBuilder();

        st.append("Date: "+off+" Day of month: "+off.getDayOfMonth()+" Day of year: "+off.getDayOfYear()+" Day of week: "+off.getDayOfWeek()).append(" Minus 100 months: "+off.minusMonths(100)).
                append(" To localDate: "+off.toLocalDate());
        System.out.println(st.toString());
    }

    public static void clockCmd(){
        Clock clock=Clock.systemDefaultZone();
        Duration duration =Duration.ofSeconds(3600);
        System.out.println("Current zone: "+clock.getZone()+" System utc: "+Clock.systemUTC().instant()+" +duration: "+Clock.offset(Clock.systemUTC(),duration).instant());
    }

    //todo not working
    public static void zonedDateTimeCmd(){
        ZonedDateTime zone = ZonedDateTime.parse("2020-06-08T08:20:10+03:30[Africa/Nairobi]");

        LocalDateTime date=LocalDateTime.of(2020,Month.JUNE,18,21,23);
        ZoneId lagos=ZoneId.of("Africa/Lagos");
        ZonedDateTime lagosZone=ZonedDateTime.of(date,lagos);

        ZoneId london=ZoneId.of("Europe/London");
        ZonedDateTime londonZone=lagosZone.withZoneSameInstant(london);

        ZonedDateTime currentZone=ZonedDateTime.now();
        System.out.println("Parse zone: "+zone+" lagos Time: "+lagosZone+" London: "+londonZone+" Current zone: "+currentZone+" minus 100 days: "+currentZone.minus(Period.ofDays(120)));

    }
}
