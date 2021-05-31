/* Copyright (C)2021  Vivian */
package com.codeHub;

import java.io.*;
import java.util.Scanner;

import au.com.bytecode.opencsv.CSVReader;

public class ReadCSV {
    public static void scannerReader() {
        long start = System.currentTimeMillis();
        try {
            Scanner scanner =
                    new Scanner(new File("/home/adhiambo/projects/spring/Kenya_Audience.csv"));
            // set the delimeter used in file
            scanner.useDelimiter(",");
            // Get all tokens and store them in some data structure

            while (scanner.hasNext()) {
                System.out.println(scanner.next() + " |");
            }
            scanner.close();
            System.out.println("Time taken:: " + (System.currentTimeMillis() - start));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void splitReader() {
        long start = System.currentTimeMillis();
        BufferedReader reader = null;
        try {
            reader =
                    new BufferedReader(
                            new FileReader("/home/adhiambo/projects/spring/Kenya_Audience.csv"));
            // read the file line by line
            String line = "";
            while ((line = reader.readLine()) != null) {
                // get all tokens per line
                String[] tokens = line.split(",");
                for (String token : tokens) {
                    System.out.println("Token:: " + token);
                }
            }
            System.out.println("End:: " + (System.currentTimeMillis() - start));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void openCsvReader() {
        long start = System.currentTimeMillis();
        CSVReader reader = null;
        try {
            reader =
                    new CSVReader(
                            new FileReader("/home/adhiambo/projects/spring/Kenya_Audience.csv"),
                            ',');
            String[] nextLine;
            // Read one line at a time
            while ((nextLine = reader.readNext()) != null) {
                for (String token : nextLine) {
                    System.out.println("Token:: " + token);
                }
            }
            System.out.println("TT:: " + (System.currentTimeMillis() - start));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
