package com.codeHub.service;

import java.io.*;

public class FileService {

    public static void fileCommands() {

        readWriteFileInputOutputStream();
        readWriteFileInputOutputStreamWithBufferWriter();
        readWriteFileInputOutputStreamBuffer();
    }



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
}
