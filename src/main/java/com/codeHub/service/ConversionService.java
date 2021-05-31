/* Copyright (C)2021  Vivian */
package com.codeHub.service;

public class ConversionService {
    public static void conversionCmd() {}

    public static void toDecimal() {
        int REDIX = 10; // redix 10 is for decimal no
        int a = 1;
        char c = Character.forDigit(a, REDIX);
        // from binary to decimal
        String binaryStr = "1010";
        int decimal = Integer.parseInt(binaryStr, 2);

        System.out.println("1 to decimal: " + c + " 1 to hexa: " + Character.forDigit(a, 16));

        // decToBinary
        System.out.println(Integer.toBinaryString(10));
        System.out.println(Integer.toBinaryString(31));

        // hexToDec
        System.out.println(Integer.parseInt("121", 16));
        System.out.println(Integer.parseInt("a", 16));

        // decToHex
        System.out.println(Integer.toHexString(289));
        System.out.println(Integer.toHexString(15));

        // octalToDec
        System.out.println(Integer.parseInt("121", 8));
        System.out.println(Integer.parseInt("10", 8));

        // DectoOctal
        System.out.println(Integer.toOctalString(81));
        System.out.println(Integer.toOctalString(19));
    }
}
