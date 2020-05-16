package com.codeHub.service;

import org.boon.Str;
import org.boon.core.Sys;

import java.sql.SQLSyntaxErrorException;
import java.util.StringTokenizer;

public class Coder{
    public static void minor(){

        Class c=Coder.class;
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

//StringBuffer

        StringBuffer sb=new StringBuffer("wow");
        System.out.println(sb.append(""));

    //insert()
//        sb.insert(1,"wow ");
//        sb.replace(1,3,"Nice");
//        sb.delete(1,3);
        sb.reverse();

        System.out.println(sb);
        Coder.compareStringConcatPerformance();
        stringTokenizing();
        stringBytes();

        String schinese="ni3好你好";
                //"hao3,  你好,  感feeling,  愛,  子hao, shànghǎi,  ni3好";
        System.out.println("No xters in chinese word: "+schinese+" "+schinese.length());



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
}
