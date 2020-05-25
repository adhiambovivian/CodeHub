package com.codeHub.service;

import org.boon.core.Sys;

import java.io.*;
import java.security.PermissionCollection;
import java.util.Calendar;
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
//        byteArrayInputputStreamFromFile();

//        dataOutputStreamWriter();
//        filterOutputStreamWriter();
//        filterInputStreamReader();
//        objectStreamClassAction();
//        consoleReader();
        grantFilePermission();


    }

    static String filePath="/Users/vivian/PERSONAL_PROJECTS/CodeHub/data/";



    /**
     * FileOutputStream and fileinputstream
     */
    public  static void readWriteFileInputOutputStream(){
        try{
            long start=System.currentTimeMillis();
            System.out.println(filePath);
            FileInputStream fileInputStream=new FileInputStream(filePath+"final.txt");
            FileOutputStream fileOutputStream=new FileOutputStream(filePath+"output.txt");

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
        }
    }

    public  static void readWriteFileInputOutputStreamBuffer(){
        try{
            long start=System.currentTimeMillis();
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
            byteArrayInputStream.close();
        }catch (Exception e){
            System.out.println("IO Error: "+e.getMessage());
        }
    }


    public static void dataOutputStreamWriter(){
        try {
            long start=System.currentTimeMillis();
            FileOutputStream fileOutputStream = new FileOutputStream(filePath + "test.txt");
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
            FileInputStream fileInputStream = new FileInputStream(filePath + "final.txt");

            int data = 0;
            while ((data = fileInputStream.read()) != -1) {
                dataOutputStream.write(data);

            }
            fileInputStream.close();
            fileOutputStream.close();
            dataOutputStream.close();
            System.out.println("dataoutput Finished time: "+(System.currentTimeMillis()-start));
        }catch (IOException e){
            System.out.println("sth went wrong. "+e.getMessage());
        }
    }

    public static void filterOutputStreamWriter(){
        try {
            long start=System.currentTimeMillis();
            File file = new File(filePath + "test.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            FilterOutputStream filterOutputStream=new FilterOutputStream(fileOutputStream);

            FileInputStream filenputStream=new FileInputStream(filePath+"final.txt");
            int data=0;
            while((data=filenputStream.read())!=-1){
                filterOutputStream.write(data);
            }
            filterOutputStream.flush();
            fileOutputStream.close();
            filenputStream.close();
            fileOutputStream.close();
            System.out.println("TT Filteroutputstream: "+(System.currentTimeMillis()-start));
        }catch (IOException e){
            System.out.println("sth went wrong "+e.getMessage());
        }
    }

    public static void filterInputStreamReader(){
        try{
            long start=System.currentTimeMillis();

            File file=new File(filePath+"final.txt");
            FileInputStream fileInputStream=new FileInputStream(file);
            FilterInputStream filterInputStream=new BufferedInputStream(fileInputStream);

            FileOutputStream fileOutputStream=new FileOutputStream(filePath+"test.txt");
             BufferedOutputStream bufferedOutputStream= new BufferedOutputStream(fileOutputStream);
            int data=0;
            while((data=filterInputStream.read())!=-1){
                bufferedOutputStream.write(data);
            }
            System.out.println("TT buffered FilterInputstream: "+(System.currentTimeMillis()-start));

            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            filterInputStream.close();
            fileInputStream.close();
            fileOutputStream.close();
        }catch (IOException e){
            System.out.println("sth went wrong "+e.getMessage());
        }
    }

    public static void objectStreamClassAction(){
        ObjectStreamClass objectStreamClass= ObjectStreamClass.lookup(Calendar.class);
        ObjectStreamField[] list=objectStreamClass.getFields();
        for(ObjectStreamField obj:list) {
            System.out.println("Name: " + obj.getName()+" Code: "+obj.getTypeCode());
        }
    }

    public static void consoleReader(){
        Console console= System.console(); //singleton instance of Console
        System.out.println("Enter your username: ");
        String name=console.readLine();
        System.out.println(name);

        //password

        System.out.println("Enter password: ");
        char[] passChar=console.readPassword();
        String password=String.valueOf(passChar);

        System.out.println(password);

    }

    public static void grantFilePermission(){
        String pathFile=filePath+"test.txt";
        FilePermission filepermission=new FilePermission(filePath+"-","read");
        PermissionCollection permissionCollection=filepermission.newPermissionCollection();
        permissionCollection.add(filepermission);

        FilePermission filePermission2=new FilePermission(pathFile,"Write");
        permissionCollection.add(filePermission2);

        if(permissionCollection.implies(new FilePermission(pathFile,"read,write"))){
            System.out.println("Read, write permission is granted for path: "+pathFile);
        }else {
            System.out.println("N read, write permission for path: "+pathFile);
        }
    }

}
