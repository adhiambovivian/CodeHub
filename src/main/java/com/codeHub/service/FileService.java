package com.codeHub.service;

import javax.xml.bind.ValidationException;
import java.io.*;
import java.util.Enumeration;
import java.util.Vector;

public class FileService {

    public static void fileCommands() {

//        readWriteFileInputOutputStream();
//        readWriteFileInputOutputStreamWithBufferWriter();
//        readWriteFileInputOutputStreamBuffer();
//        sequenceReader();
//        sequenceReaderVectors();
//        byteArrayOutputStream();
//        byteArrayOutputStreamFromFile();
        byteArrayInputputStreamFromFile();
    }

    static String filePath="/Users/vivian/PERSONAL_PROJECTS/CodeHub/data/";



    /**
     * FileOutputStream and fileinputstream
     */
    public  static void readWriteFileInputOutputStream(){
        try{
            long start=System.currentTimeMillis();
            String filePath="/Users/vivian/PERSONAL_PROJECTS/CodeHub/data/";
            System.out.println(filePath);
            FileInputStream fileInputStream=new FileInputStream(filePath+"final.txt");
            FileOutputStream fileOutputStream=new FileOutputStream(filePath+"ouput.txt");

            int data=0;
            while((data=fileInputStream.read())!=-1) {
                fileOutputStream.write(((char)data));
            }
            fileInputStream.close();
            fileOutputStream.close();
            System.out.println("TT without buffer: "+(System.currentTimeMillis()-start));
        }catch (IOException e){
            System.out.println("sth went wrong"+e.getMessage()+" "+e.getStackTrace());
        }finally {

        }
    }

    /**
     * Writing to file using buffered fileoutputstream
     */
    public  static void readWriteFileInputOutputStreamWithBufferWriter(){
        try{
            long start=System.currentTimeMillis();
            String filePath="/Users/vivian/PERSONAL_PROJECTS/CodeHub/data/";
            System.out.println(filePath);
            FileInputStream fileInputStream=new FileInputStream(filePath+"final.txt");
            FileOutputStream fileOutputStream=new FileOutputStream(filePath+"ouput.txt");
            BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(fileOutputStream);

            int data=0;
            while((data=fileInputStream.read())!=-1) {
                bufferedOutputStream.write(((char)data));
            }
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            fileInputStream.close();
            fileOutputStream.close();
            System.out.println("TT without buffer: "+(System.currentTimeMillis()-start));
        }catch (IOException e){
            System.out.println("sth went wrong"+e.getMessage()+" "+e.getStackTrace());
        }finally {

        }
    }

    public  static void readWriteFileInputOutputStreamBuffer(){
        try{
            long start=System.currentTimeMillis();
            String filePath="/Users/vivian/PERSONAL_PROJECTS/CodeHub/data/";
            System.out.println(filePath);
            FileInputStream fileInputStream=new FileInputStream(filePath+"final.txt");
            BufferedInputStream bufferedInputStream=new BufferedInputStream(fileInputStream);
            FileOutputStream fileOutputStream=new FileOutputStream(filePath+"ouput.txt");
            BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(fileOutputStream);

            int data=0;
            while((data=bufferedInputStream.read())!=-1) {
                bufferedOutputStream.write(((char)data));
            }
            bufferedInputStream.close();
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            fileInputStream.close();
            fileOutputStream.close();
            System.out.println("TT with buffer: "+(System.currentTimeMillis()-start));
        }catch (IOException e){
            System.out.println("sth went wrong"+e.getMessage()+" "+e.getStackTrace());
        }finally {

        }
    }

    public static void sequenceReader(){
        try {
            long start=System.currentTimeMillis();
            FileInputStream fn1 = new FileInputStream(filePath + "final.txt");
            FileInputStream fn2 = new FileInputStream(filePath + "ouput.txt");

            FileOutputStream fileOutputStream=new FileOutputStream(filePath+"merge.txt");
            SequenceInputStream sequenceInputStream=new SequenceInputStream(fn1,fn2);
            int data=0;
            while((data=sequenceInputStream.read())!=-1){
                fileOutputStream.write(data);
            }
            sequenceInputStream.close();
            fileOutputStream.close();
            fn1.close();
            fn2.close();
            System.out.println("Finitooooo. TT: "+(System.currentTimeMillis()-start));
        }catch (IOException e){
            System.out.println("IO Error: "+e.getMessage());
        }
    }

    public static void sequenceReaderVectors(){
        try {
            long start=System.currentTimeMillis();

            FileInputStream fn1 = new FileInputStream(filePath + "test.txt");
            FileInputStream fn2 = new FileInputStream(filePath + "test copy.txt");
            FileInputStream fn3 = new FileInputStream(filePath + "test copy 2.txt");
            FileInputStream fn4 = new FileInputStream(filePath + "test copy 3.txt");
            FileInputStream fn5 = new FileInputStream(filePath + "test copy 4.txt");

            FileOutputStream fileOutputStream=new FileOutputStream(filePath+"merge.txt");

            //create vector object to all the stream
            Vector vector=new Vector();
            vector.add(fn1);
            vector.add(fn2);
            vector.add(fn3);
            vector.add(fn4);
            vector.add(fn5);

            //create enumeration object by calling the elements method
            Enumeration elements= vector.elements();

            SequenceInputStream sequenceInputStream=new SequenceInputStream(elements);
            int data=0;
            while((data=sequenceInputStream.read())!=-1){
                fileOutputStream.write(data);
            }
            sequenceInputStream.close();
            fileOutputStream.close();
            fn1.close();
            fn2.close();
            fn3.close();
            fn4.close();
            fn5.close();
            System.out.println("Finitooooo. TT by vectors: "+(System.currentTimeMillis()-start));
        }catch (IOException e){
            System.out.println("IO Error: "+e.getMessage());
        }
    }

    public static void byteArrayOutputStream(){
        try {
            long start=System.currentTimeMillis();
            FileOutputStream fn1 = new FileOutputStream(filePath + "template1.txt");
            FileOutputStream fn2 = new FileOutputStream(filePath + "template2.txt");

            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            byteArrayOutputStream.write("Hello is this what you are looking for?".getBytes());
            byteArrayOutputStream.writeTo(fn1);
            byteArrayOutputStream.writeTo(fn2);

            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            fn1.close();
            fn2.close();
            System.out.println("Finitooooo. TT: "+(System.currentTimeMillis()-start));
        }catch (IOException e){
            System.out.println("IO Error: "+e.getMessage());
        }
    }

    public static void byteArrayOutputStreamFromFile(){
        try {
            long start=System.currentTimeMillis();
            FileOutputStream fn1 = new FileOutputStream(filePath + "template1.txt");
            FileOutputStream fn2 = new FileOutputStream(filePath + "template2.txt");

            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            FileInputStream fileInputStream=new FileInputStream(filePath+"final.txt");
            BufferedInputStream bufferedInputStream=new BufferedInputStream(fileInputStream);
            int data=0;
            while((data=bufferedInputStream.read())!=-1){
                byteArrayOutputStream.write(data);
             }
            byteArrayOutputStream.writeTo(fn1);
            byteArrayOutputStream.writeTo(fn2);
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            fn1.close();
            fn2.close();
            bufferedInputStream.close();
            System.out.println("Finitooooo. TT: "+(System.currentTimeMillis()-start));
        }catch (IOException e){
            System.out.println("IO Error: "+e.getMessage());
        }
    }



    public static void byteArrayInputputStreamFromFile(){
        try {
            byte[] buffer={32,45,23,56,78,98,35,36,37,38};

            ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(buffer);
            int data=0;
            while((data=byteArrayInputStream.read())!=-1){
                char ch=(char)data;
                System.out.println("ASCII value of char is: "+data+" the special char is "+ch);

            }
        }catch (Exception e){
            System.out.println("IO Error: "+e.getMessage());
        }
    }

}
