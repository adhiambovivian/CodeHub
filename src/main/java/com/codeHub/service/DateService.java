package com.codeHub.service;

import org.boon.core.Sys;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.ValueRange;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateService {

    public static void dateCmds(){
//        localDateCmd();
//        localTimeCmd();
//        localDatetimeCmd();
//        monthDayCmd();
//        offsetTimeCmd();
//        offsetDateTimeCmd();
//        clockCmd();
//        zonedDateTimeCmd();
//        zonedIdCmd();
//        zoneOffsetCmd();
//        yearCmd();
//        yearMonthCmd();
//        periodCmd();
//        durationCmd();
//        instantCmd();
//        dayOfWeekCmd();
//        monthCmd();
//        utilDateCmd();
//        sqlDateCmd();
//        calendarCmd();
//        timezoneCmd();
        dateformatCmd();
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


    public static void zonedIdCmd(){
        ZoneId zoneId = ZoneId.of("Asia/Kolkata");
        ZoneId zoneId1 = ZoneId.of("Asia/Tokyo");

        LocalTime localTime = LocalTime.now(zoneId);
        LocalTime localTime1 = LocalTime.now(zoneId1);

        System.out.println(localTime);
        System.out.println(localTime1);

        System.out.println(zoneId.getDisplayName(TextStyle.FULL, Locale.ROOT)+"   "+localTime.isBefore(localTime1));

    }

    public static void zoneOffsetCmd(){
        ZoneOffset zoneOffset = ZoneOffset.UTC;
        Temporal temporal = zoneOffset.adjustInto(ZonedDateTime.now());

        System.out.println(temporal+" Gap in time: "+ZoneOffset.ofHours(6)+" Zone supported: "+ZoneOffset.UTC.isSupported(ChronoField.OFFSET_SECONDS));
    }

    public static void yearCmd(){
        Year year = Year.now();
        LocalDate localDate = year.atDay(200);
        System.out.println("Current Year: "+year+" and day 200: "+localDate+" Year length: "+year.length()+" Comparison: "+year.minusYears(1023));
    }

    public static void yearMonthCmd(){
        YearMonth yearMonth = YearMonth.now();
        String formattedYearMonth = yearMonth.format(DateTimeFormatter.ofPattern("MM yyyy"));
        System.out.println("Current YearMonth: "+yearMonth+" current month: "+yearMonth.get(ChronoField.MONTH_OF_YEAR)+
                " Comparison: "+yearMonth.minus(Period.ofYears(1837))+" Formatted date: "+formattedYearMonth);

    }

    public static void periodCmd(){
        Period period = Period.ofDays(23);
        Temporal temporal = period.addTo(LocalDateTime.now());
        System.out.println(temporal);

        Period period2 = period.minus(Period.ofYears(322));
        System.out.println(period2);

    }

    public static void durationCmd(){
        Duration duration = Duration.between(LocalTime.NOON, LocalTime.MAX);
        System.out.println(duration.get(ChronoUnit.SECONDS));

        System.out.println("Duration: "+duration.plus(duration).getSeconds());
    }

    public static void instantCmd(){
        Instant instant = Instant.parse("2018-08-03T10:37:30.00Z");
        System.out.println("Some future date: "+instant.plus(Duration.ofDays(8928))+" is Years supported: "+instant.isSupported(ChronoUnit.YEARS)+" current time: "+Instant.now());

    }

    public static void dayOfWeekCmd(){
        LocalDate localDate = LocalDate.of(2020,Month.JUNE,22);
        DayOfWeek dayOfWeek = DayOfWeek.from((localDate));
        System.out.println(dayOfWeek.get(ChronoField.DAY_OF_WEEK)+" Day::"+dayOfWeek.name()+" Future day: "+dayOfWeek.plus(56));
    }

    public static void monthCmd(){
        Month month = Month.valueOf("January".toUpperCase());
        Month month1 = Month.from(LocalDateTime.now());

        System.out.println("Month: "+month1+" Days: "+month1.length(true)+" Name: "+month1.name());
    }

    public static void utilDateCmd(){
        java.util.Date date=new java.util.Date();
        long millis=System.currentTimeMillis();
        java.util.Date date1=new java.util.Date(millis);
        System.out.println("Date 0: "+date+" Date 1: "+date1);
    }

    public static void sqlDateCmd(){
        long millis=System.currentTimeMillis();

        java.sql.Date date=new java.sql.Date(millis);
        String dateStr= "2020-06-23";
        java.sql.Date date1=java.sql.Date.valueOf(dateStr);
        System.out.println("Date 0: "+date+" Date 1: "+date1);
    }
    public static void calendarCmd(){
        Calendar calendar = Calendar.getInstance();
        System.out.print("Calendar type: "+calendar.getCalendarType()+" Current date: "+calendar.getTime()+" Current year: "+calendar.get(Calendar.YEAR)+" Current Day: "+calendar.get(Calendar.DATE));
        System.out.print("\tMax no of days in a week: "+calendar.getMaximum(Calendar.DAY_OF_WEEK)+" Max weeks in a yr: "+calendar.getMaximum(Calendar.WEEK_OF_YEAR)+" Time zone: "+calendar.getTimeZone());


        calendar.add(Calendar.DATE,-12);
        calendar.getTime();
        System.out.println("12 days ago: "+calendar.getTime());

        calendar.add(Calendar.MONTH,5);
        System.out.println("5 months later: "+calendar.getTime());

        calendar.add(Calendar.YEAR,-5);
        System.out.println("5 years ago: "+calendar.getTime());


    }

    public static void timezoneCmd(){
        String[] timezones= TimeZone.getAvailableIDs();
        for(int i=0;i<timezones.length;i++){
            TimeZone timeZone=TimeZone.getTimeZone(timezones[i]);
            System.out.println(timezones[i]+" Offset: "+timeZone.getOffset(Calendar.ZONE_OFFSET)+" Observes daylight: "+timeZone.observesDaylightTime());
        }
    }

    public static void dateformatCmd(){
        Date currentDate=new Date();
        System.out.println("Date format using getInstance(): "+DateFormat.getInstance().format(currentDate));
        System.out.println("Date format using getDateInstance(): "+DateFormat.getDateInstance().format(currentDate));
        System.out.println("Date format using getTimeInstance(): "+DateFormat.getTimeInstance().format(currentDate));
        System.out.println("Date format using getDateTimeInstance(): "+DateFormat.getDateTimeInstance().format(currentDate));
        System.out.println("Date format using getTimeInstance(DateFormat.SHORT): "+DateFormat.getTimeInstance(DateFormat.SHORT).format(currentDate));
        System.out.println("Date format using getTimeInstance(DateFormat.MEDIUM): "+DateFormat.getTimeInstance(DateFormat.MEDIUM).format(currentDate));
        System.out.println("Date format using getTimeInstance(DateFormat.LONG): "+DateFormat.getTimeInstance(DateFormat.LONG).format(currentDate));
        System.out.println("Date format using getDateTimeInstance(DateFormat.LONG,DateFormat.SHORT): "+DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.SHORT).format(currentDate));

        try {
            Date someDate = DateFormat.getDateInstance().parse("Jun 30, 2034");
            System.out.println("Some date: "+someDate);
        }catch (ParseException e){
            e.printStackTrace();
        }

    }

}
