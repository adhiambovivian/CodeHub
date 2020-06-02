package com.codeHub.service;

import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class CoderService {
    public static void minor(){

        Class c= CoderService.class;
        System.out.println(c.getClassLoader());
/**
 * Operator shift
 */
        System.out.println(11>>3);//11/2^3=11/8=1
        System.out.println(20>>2);//20/4=5

//Left Shift operator
        System.out.println(15<<4);//15*2^4=15/16=240
/**
 * Strings
 */
        char[] ch = {'v','i','v','i','a','n'};
        String s = new String (ch);
        String st="vivian";
        System.out.println("ch\t"+s+"\tstring\t"+st+"\tInterned string: "+st.intern()
        );

//equality
        String s1="Vivianne";
        String s2="Vivianne";
        String s3=new String ("vivianne");

        System.out.println(s1==s2);//true (refer to the same instance)
        System.out.println(s1==s3);//false (s3 refers to instance created in nonpool)
        System.out.println(s1.substring(0,4));

        String st1=s3.intern();

/*
StringBuffer
 */
/*

        StringBuffer sb=new StringBuffer("wow");
        System.out.println(sb.append(""));

        sb.insert(1,"wow ");
        sb.replace(1,3,"Nice");
        sb.delete(1,3);
        sb.reverse();

        System.out.println(sb);
        compareStringConcatPerformance();
        stringTokenizing();
 */
//        patternMatching();
//        toConsole();

        CoderService coderService=new CoderService();
        coderService.forNameMethod();

    }

    public static void toConsole(){
        try {
            int i = System.in.read();//returns ASCII code of first character
            System.out.println(i);
            System.out.println((char) i);
        }catch (IOException e){
            System.err.println(e.getStackTrace());
        }
    }

    public static String concatWithString(){
        String st="vivian";
        for(int i=0;i<10000;i++){
            st=st+"Adhiambo9";
        }
        return st;
    }

    public static String concatWithStringBuffer(){
        StringBuffer sb=new StringBuffer("vivian");
        for(int i=0;i<10000;i++){
            sb.append("Adhiambo6");
        }
        return sb.toString();
    }

    public static String concatWithStringBuilder(){
        StringBuilder sb=new StringBuilder("vivian");
        for(int i=0;i<10000;i++){
            sb.append("Adhiambo7");
        }
        return sb.toString();
    }

    public static void compareStringConcatPerformance(){
        long startTime=System.currentTimeMillis();
        concatWithString();
        System.out.println("TT by concat with String: "+(System.currentTimeMillis()-startTime));

        startTime=System.currentTimeMillis();
        concatWithStringBuffer();
        System.out.println("TT by concat with StringBuffer: "+(System.currentTimeMillis()-startTime));

        startTime=System.currentTimeMillis();
        concatWithStringBuilder();
        System.out.println("TT by concat with StringBuilder: "+(System.currentTimeMillis()-startTime));

    }

    public static void stringTokenizing(){
        StringTokenizer st=new StringTokenizer("my name is ule yule, I can dance"," ");
        System.out.println("Next token: "+st.nextToken(","));

    }

    public static void stringBytes(){
        String s1="ABCDEFG01234569";
        byte[] by=s1.getBytes();
        for(int i=0;i<by.length;i++)
            System.out.println(by[i]);
    }

    public static void patternMatching(){
        System.out.println(Pattern.matches("[amn]?","a"));//true (a or m comes one time
        System.out.println(Pattern.matches("[amn]?","aaazzss"));//false (a comes one time
        System.out.println(Pattern.matches("[amn]?","am"));//false (a or m or n must come one time

        System.out.println(Pattern.matches("[amn]+","a"));//true (a or m or n once or more
        System.out.println(Pattern.matches("[amn]+","aaa"));//true (a or m comes one time
        System.out.println(Pattern.matches("[amn]+","aazzta"));//false (z and t are not matching

        System.out.println(Pattern.matches("[amn]*","ammmmnna"));//true (a or m or n may come zero or more times

        System.out.println(Pattern.matches("\\d", "abc"));//false (non-digit)
        System.out.println(Pattern.matches("\\d", "1"));//true (digit an comes onces)
        System.out.println(Pattern.matches("\\d", "4443"));//false (digit but comes more than once))
        System.out.println(Pattern.matches("\\d", "123"));//false (single digit only)

        System.out.println(Pattern.matches("\\D", "123"));//false (digits)
        System.out.println(Pattern.matches("\\D*", "mak"));//true (non digit and may come 0 or more times)

        System.out.println(Pattern.matches("[a-zA-Z0-9]{6}", "arun32"));//true
        System.out.println(Pattern.matches("[a-zA-Z0-9]{6}", "kkvarun32"));//false (more than 6 char)

        System.out.println(Pattern.matches("[789]{1}[0-9]{9}", "9953038949"));//true
        System.out.println(Pattern.matches("[789][0-9]{9}", "6953038949"));//false (starts from 6)
        System.out.println(Pattern.matches("[789]{1}\\d{9}", "3853038949"));//false (starts from 3)


    }

    public  void forNameMethod(){
        try {
            Class classVal = Class.forName("CoderService");
            System.out.println("Class details: " + classVal.toString());
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }


}
