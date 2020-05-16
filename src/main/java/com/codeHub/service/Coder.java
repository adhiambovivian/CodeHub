package com.codeHub.service;

import java.sql.SQLSyntaxErrorException;

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

        StringBuffer sb=new StringBuffer("wow such a sunny");
        System.out.println(sb.append(" morning"));


    }
}
