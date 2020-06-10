package com.codeHub.service;


import com.codeHub.models.Blacklist;
import org.boon.core.Sys;
import org.boon.primitive.ByteBuf;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.PermissionCollection;
import java.util.*;
import java.util.zip.DeflaterOutputStream;

public class FileService{

    public static class CustomFilterWriter extends FilterWriter {
        CustomFilterWriter(Writer writer) {
            super(writer);
        }

        public void write(String val) throws IOException{
            super.write(val.toLowerCase());
        }
    }

    public static class CustomFilterReader extends FilterReader{
        CustomFilterReader(Reader filterReader){
            super(filterReader);
        }

        public int read() throws IOException{
            int data=super.read();
            if((char)data == ' ')
                return (int)'?';
            else
                return data;
        }
    }

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
//        grantFilePermission();
//        writerMethod();
//        readerMethod();
//        fileWriterReaderMethod();
//        fileWriterReaderBufferMethod();
//        bufferedConsoleReader();
//        charArrayReaderMethod();
//        charArrayWriterMethod();
//        printStreamMethod();
//        printWriterConsole();
//        printWriterFile();
//        outputStreamWriterMethod();
//        inputStreamReaderMethod();
//        pushbackInputStreamMethod();
//        pushbackReaderMethod();
//        stringWriterMethod();
//        stringReaderMethod();
//        pipedReaderWriterMethod();
//        filterReaderWriter();
//        fileMethods();
//        fdMethod();
//        processRandomAccessMethod();
//        scannerReader();
//        compressFileDeflater();
//        serialization();
//        deserialization();
//        copyData();
//        readDataFiles();
//        gatherBytes();
//        scatterBytes();
        combineFiles();

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

    public static void writerMethod(){
        try{
            long start=System.currentTimeMillis();
            Writer writer=new FileWriter(filePath+"test.txt");
            FileInputStream fileInputStream=new FileInputStream(filePath+"final.txt");
            int data=0;
            while((data=fileInputStream.read())!=-1){
                writer.write(data);
            }
            writer.close();
            fileInputStream.close();
            System.out.println("TT writer: "+(System.currentTimeMillis()-start));
        }catch (IOException e){
            System.out.println("sth went wrong "+e.getMessage());
        }
    }

    public static void readerMethod(){
        try{
            long start=System.currentTimeMillis();
            Writer writer=new FileWriter(filePath+"test.txt");
            Reader reader =new FileReader(filePath+"final.txt");
            int data=0;
            while((data=reader.read())!=-1){
                writer.write(data);
            }
            writer.close();
            reader.close();
            System.out.println("TT writer & reader: "+(System.currentTimeMillis()-start));
        }catch (IOException e){
            System.out.println("sth went wrong "+e.getMessage());
        }
    }

    public static void fileWriterReaderMethod(){
        try{
            long start=System.currentTimeMillis();
            FileWriter writer=new FileWriter(filePath+"test.txt");
            FileReader reader =new FileReader(filePath+"output.txt");
            int data=0;
            while((data=reader.read())!=-1){
                writer.write(data);
            }
            writer.close();
            reader.close();
            System.out.println("TT Filewriter & reader: "+(System.currentTimeMillis()-start));
        }catch (IOException e){
            System.out.println("sth went wrong "+e.getMessage());
        }
    }


    public static void fileWriterReaderBufferMethod(){
        try{
            long start=System.currentTimeMillis();
            FileWriter writer=new FileWriter(filePath+"test.txt");
            BufferedWriter bufferedWriter=new BufferedWriter(writer);

            FileReader reader =new FileReader(filePath+"output.txt");
            BufferedReader bufferedReader=new BufferedReader(reader);

            int data=0;
            while((data=bufferedReader.read())!=-1){
                bufferedWriter.write(data);
            }
            bufferedWriter.flush();
            bufferedWriter.close();
            bufferedReader.close();
            writer.close();
            reader.close();

            System.out.println("TT buffered Filewriter & reader: "+(System.currentTimeMillis()-start));
        }catch (IOException e){
            System.out.println("sth went wrong "+e.getMessage());
        }
    }

    public static void bufferedConsoleReader(){
        try{
            InputStreamReader inputStreamReader=new InputStreamReader(System.in);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);

            System.out.println("Enter your username - ");
            String username=bufferedReader.readLine();

            System.out.println("IS this what you typed? "+username);

        }catch(IOException e){
            System.out.println("sth went wrong "+e.getMessage());
        }
    }

    public static void charArrayReaderMethod(){
        try {
            char[] array = {'a', 'q', 'w', 'e', 'r', 't', 'g', 'h', 'j', 'c', 'z'};
            CharArrayReader reader = new CharArrayReader(array);

            int data = 0;
            while ((data = reader.read()) != -1) {
                char ch=(char)data;
                System.out.println(ch+" : "+data);

            }
        }catch (IOException e){
            System.out.println("sth went wrong. "+e.getMessage());
        }
    }

    public static void charArrayWriterMethod(){
        try{
            CharArrayWriter writer=new CharArrayWriter();
            writer.write("Wow, such an amazing day. I ma exhilarated");

            FileWriter fn1 = new FileWriter(filePath + "test.txt");
            FileWriter fn2 = new FileWriter(filePath + "test copy.txt");
            FileWriter fn3 = new FileWriter(filePath + "test copy 2.txt");
            FileWriter fn4 = new FileWriter(filePath + "test copy 3.txt");
            FileWriter fn5 = new FileWriter(filePath + "test copy 4.txt");

            writer.writeTo(fn1);
            writer.writeTo(fn2);
            writer.writeTo(fn3);
            writer.writeTo(fn4);
            writer.writeTo(fn5);

            fn1.close();
            fn2.close();
            fn3.close();
            fn4.close();
            fn5.close();


        }catch (IOException e){
            System.out.println("sthe went wrong: "+e.getMessage());
        }
    }

    public static void printStreamMethod(){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath + "test.txt");
            PrintStream printStream=new PrintStream(fileOutputStream);

            printStream.println("wow wow wow, am so amazed and excited about life");
            printStream.printf("%s","Such a beautifule day outside...");
        }catch (FileNotFoundException e){
            System.out.println("sth went wrong: "+e.getMessage());
        }

    }

    public static void printWriterConsole(){

        try {
            PrintWriter printWriter = new PrintWriter(System.out);
            FileReader fileReader = new FileReader(filePath + "test.txt");
            int data=0;
            while((data=fileReader.read())!=-1){
                printWriter.write(data);
            }
            printWriter.flush();
            printWriter.close();
            fileReader.close();
        }catch (IOException e){
            System.out.println("sth went wrong: "+e.getMessage());
        }
    }

    public static void printWriterFile(){
        try {
            PrintWriter printWriter = new PrintWriter(new File(filePath + "template.txt"));
            printWriter.write("I love hiking. It is a very refreshing activity...");
            printWriter.flush();
            printWriter.close();

        }catch (FileNotFoundException e){
            System.out.println("sth went wrong. "+e.getMessage());
        }
    }

    public static void outputStreamWriterMethod() {
        try {
            OutputStream outputStream = new FileOutputStream(filePath + "template.txt");
            Writer writer=new OutputStreamWriter(outputStream);

            writer.write("Working remotely has been a blast... Such an invigorating experience");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void inputStreamReaderMethod() {
        try {
            long start=System.currentTimeMillis();
            OutputStream outputStream = new FileOutputStream(filePath + "template.txt");
            Writer writer=new OutputStreamWriter(outputStream);

            InputStream inputStream=new FileInputStream(filePath+"final.txt");
            Reader reader=new InputStreamReader(inputStream);

            int data=0;
            while((data=reader.read())!=-1) {
                writer.write((char)data);
            }
            writer.close();
            System.out.println("TT by streamReader & stream writer: "+(System.currentTimeMillis()-start));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void pushbackInputStreamMethod(){

        try {
            String value = "aldo$zyrie$ksh";

            byte[] valueByte = value.getBytes();

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(valueByte);
            PushbackInputStream pushbackInputStream = new PushbackInputStream(byteArrayInputStream);

            int data = 0;
            while ((data = pushbackInputStream.read()) != -1) {
                if(data=='$'){
                    int val=0;
                    if((val=pushbackInputStream.read())=='$'){
                        System.out.println("*************");

                    }else {
                        pushbackInputStream.unread(val);
                        System.out.println((char)data);
                    }
                }else{
                    System.out.println((char)data);
                }
            }
        }catch (IOException e){
            e.getStackTrace();
        }
    }
//todo
    public static void pushbackReaderMethod(){
        try {
            char arr[] = {'1', '-', '-', '2', '-', '3', '4', '-', '-', '-', '5', '6'};
            CharArrayReader reader = new CharArrayReader(arr);
            PushbackReader pushbackReader = new PushbackReader(reader);

            int data = 0;
            while ((data = pushbackReader.read()) != -1) {
                if(data == '-'){
                    int val;
                    if((val=pushbackReader.read())=='-'){
                        System.out.println("*************");
                    }else{
                        pushbackReader.unread(val);//push back single character
                        System.out.println((char)data);
                    }
                }else{
                    System.out.println((char)data);
                }
            }
        }catch ( IOException e){
            e.getMessage();
        }
    }

    public static void stringWriterMethod(){
        try {
            long start=System.currentTimeMillis();
            char[] arr = new char[512];
            StringWriter stringWriter = new StringWriter();
            FileInputStream fileInputStream = new FileInputStream(filePath + "final.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream,"UTF-8"));

            int data;
            while((data=bufferedReader.read(arr))!=-1){
                stringWriter.write(arr,0,data);
            }
            System.out.println(stringWriter.toString());
            stringWriter.close();
            fileInputStream.close();
            bufferedReader.close();
            System.out.println("TT string writer: "+(System.currentTimeMillis()-start));
        }catch (IOException e){
            e.getMessage();
        }

    }

    public static void stringReaderMethod(){
        try {
            String val = "Helllo hello, it's madaraka day....such an amazing day";
            StringReader stringReader = new StringReader(val);

            int data = 0;
            while ((data = stringReader.read()) != -1) {
                System.out.print((char) data);
            }
        }catch (IOException e){
            e.getMessage();
        }
    }

public static void pipedReaderWriterMethod(){
        try{
            final PipedReader pipedReader=new PipedReader();
            final PipedWriter pipedWriter=new PipedWriter(pipedReader);

            Thread readerThread=new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        int data=pipedReader.read();
                        while(data!=-1){
                            System.out.print((char)data);
                            data=pipedReader.read();
                        }
                        System.out.println("Reader: "+Thread.currentThread().getName());

                    }catch (IOException e){
                        e.getMessage();
                    }
                }
            });


            Thread writerThread =  new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        pipedWriter.write("Wah wah wah ... no daze\n".toCharArray());
                        System.out.println("Writer: "+Thread.currentThread().getName());
                    }catch (IOException e){
                        e.getMessage();
                    }
                }
            });


            readerThread.start();
            writerThread.start();
        }catch (IOException e){
            e.getMessage();
        }
}

public static void filterReaderWriter(){
        try{
            FileWriter fileWriter=new FileWriter(filePath+"template.txt");
            CustomFilterWriter customFilterWriter=new CustomFilterWriter(fileWriter);

            customFilterWriter.write("ohhh nanannana ohhhh nannannan");
            FileReader fileReader=new FileReader(filePath+"test.txt");
            BufferedReader bufferedReader=new BufferedReader(fileReader);

            CustomFilterReader customFilterReader=new CustomFilterReader(bufferedReader);

            int data=0;
            while((data = customFilterReader.read())!= -1){
                System.out.print((char)data);
            }
            bufferedReader.close();
            fileWriter.close();
            fileReader.close();

        }catch (IOException e){
            e.getMessage();
        }
}

public static void fileMethods(){
        try{
            File file=new File(filePath+"tester.txt");
            if(file.createNewFile()){
                System.out.println("Created new file "+file.getName());
            }else{
                System.out.println("File with name "+file.getName()+" exists. Path Relative: "+file.getCanonicalPath()+" Absolute path: "+file.getAbsoluteFile());
            }

            File[] files=new File(filePath).listFiles();
            for(File file1:files){
                System.out.println("Name: "+file1.getName()+" path: "+file1.getPath()+"Disk space:  "+(file1.getFreeSpace())/1E9+" size: "+file1.length());
            }

        }catch (IOException e){
            System.out.println("sth went wrong: "+e.getMessage());
        }
}

public  static void fdMethod(){
        FileDescriptor fileDescriptor=null;
        byte[] bytes={34,55,23,98,27,74,43};
        try{
            FileOutputStream fileOutputStream=new FileOutputStream(filePath+"test.txt");
            FileInputStream fileInputStream=new FileInputStream(filePath+"final.txt");

            fileDescriptor=fileOutputStream.getFD();
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
            fileDescriptor.sync();//confirms data to be written to the disk
            int data=0;
            while((data=fileInputStream.read())!=-1){
                System.out.print((char)data);
            }
        }catch (IOException e){
            e.getMessage();
        }
}

public static void writeRandomAccess(String path, String data,int position) throws IOException{
    RandomAccessFile randomAccessFile=new RandomAccessFile(path,"rw");
    randomAccessFile.seek(position);
    randomAccessFile.write(data.getBytes());
    randomAccessFile.close();
}

public static byte[] readRandomAccess(String path, int position, int size) throws IOException{
        RandomAccessFile randomAccessFile=new RandomAccessFile(path,"r");
        randomAccessFile.seek(position);
        byte[] bytes =new byte[size];
        randomAccessFile.read(bytes);
        randomAccessFile.close();

        return bytes;
}

public static void processRandomAccessMethod() {
    try {
        System.out.println(new String(readRandomAccess(filePath + "test.txt", 0, 5)));
        writeRandomAccess(filePath + "template.txt", "la ls ld lf lg lh lj lk", 10);
    } catch (IOException e) {
        e.getMessage();
    }
}

public static void  scannerReader(){
        Scanner scanner =new Scanner(System.in);
        scanner.useDelimiter("\n");
        System.out.println("Enter your username: ");
        String username=scanner.nextLine();
        System.out.println("username: "+username);
        scanner.close();
}
//todo: not working
public static void compressFileDeflater(){
        try{
            FileInputStream fileInputStream=new FileInputStream(filePath+"tests.txt");
            FileOutputStream fileOutputStream=new FileOutputStream(filePath+"compressed.txt");
            DeflaterOutputStream deflaterOutputStream=new DeflaterOutputStream(fileOutputStream);

            int data=0;
            while((data=fileInputStream.read())!=-1){
                deflaterOutputStream.write((byte)data);
            }
            deflaterOutputStream.flush();

            fileInputStream.close();
            fileOutputStream.close();
        }catch (IOException e){
            e.getMessage();
        }
}

public static void serialization(){
    Blacklist blacklist=new Blacklist();
    blacklist.setAccountId(2);
    blacklist.setBlockedBy(3);
    blacklist.setCommId("+25363773736");
    blacklist.setComment("You have been added to dnd. we will not contact you again");
    Date date=new Date();
    blacklist.setCreateDate(date);

    try {
        FileOutputStream fileOutputStream = new FileOutputStream(filePath + "objects.txt");
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(blacklist);
        objectOutputStream.flush();
        objectOutputStream.close();

    }catch (IOException e){
        e.getMessage();
    }
}

public static void deserialization(){
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath + "objects.txt"));
            Blacklist blacklist=(Blacklist)objectInputStream.readObject();
            System.out.println(blacklist.toString());
        }catch (IOException e){
            e.getMessage();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
}

public static void copyData(){
        try {
            long start=System.currentTimeMillis();
            FileInputStream fileInputStream = new FileInputStream(filePath + "final.txt");
            ReadableByteChannel source = fileInputStream.getChannel();

            FileOutputStream fileOutputStream = new FileOutputStream(filePath + "test.txt");
            WritableByteChannel destination = fileOutputStream.getChannel();
            ByteBuffer buffer = ByteBuffer.allocateDirect(20 * 1024);
            while (source.read(buffer) != -1) {
                //buffer is to be used to drained
                buffer.flip();
                //ensure buffer is fully drained
                while (buffer.hasRemaining()) {
                    destination.write(buffer);
                }
                buffer.clear(); //now the buffer is empty, ready for filling
            }
            source.close();
            destination.close();
            System.out.println("TT: "+(System.currentTimeMillis()-start));
        }
            catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void readDataFiles(){
        try{
            long start = System.currentTimeMillis();
            Path path = Paths.get(filePath+"final.txt");
            InputStream inputStream = Files.newInputStream(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            int data=0;
            while((data=bufferedReader.read())!=-1)
            System.out.print((char)data);
            bufferedReader.close();
            inputStream.close();

            System.out.println("TT: "+(System.currentTimeMillis()-start));
        }catch (IOException e){
            e.printStackTrace();
           }
        }

        //todo: unable to open file
    public static void gatherBytes() {

        try {
            //1st buffer is used for holding a random number
            ByteBuffer buffer = ByteBuffer.allocate(8);
            //2nd buffer is used for holding data that's to be written
            ByteBuffer buffer1 = ByteBuffer.allocate(400);
            buffer.asIntBuffer().put(420);
            buffer1.asCharBuffer().put("Wow Wow weeu weeeeu asgsgsgajauwuuwnsn shhshsuuwuwu shbxbuss");

            FileChannel fileChannel = new FileOutputStream(filePath + "test.txt").getChannel();

            GatheringByteChannel gatherer = fileChannel;
            //write data to file
            gatherer.write(new ByteBuffer[]{buffer, buffer1});

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void scatterBytes(){
        try{
            ByteBuffer buffer = ByteBuffer.allocate(8);
            ByteBuffer buffer1 = ByteBuffer.allocate(400);

            FileChannel fileChannel = new FileInputStream(filePath + "test.txt").getChannel();

            ScatteringByteChannel scatter =fileChannel;
            scatter.read(new ByteBuffer[]{buffer,buffer1});

            //read the two buffers separately
            buffer.rewind();
            buffer1.rewind();

            int bufferOne=buffer.asIntBuffer().get();
            String bufferTwo=buffer1.asCharBuffer().toString();
            //verify content

            System.out.println(bufferOne);
            System.out.println(bufferTwo);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //todo: open file
    public static void combineFiles() {
        try {
            String[] filePaths = new String[]{filePath + "test.txt", filePath + "test copy 2.txt", filePath + "test copy 3.txt", filePath + "test copy 4.txt"};
            FileOutputStream outputStream = new FileOutputStream(new File(filePath + "output.txt"));
            WritableByteChannel targetChannel = outputStream.getChannel();

            for (int i = 0; i < filePaths.length; i++) {
                FileInputStream inputStream = new FileInputStream(filePaths[i]);
                FileChannel fileChannel = inputStream.getChannel();

                fileChannel.transferTo(0, fileChannel.size(), targetChannel);
                fileChannel.close();
                inputStream.close();
            }

            targetChannel.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void serverSelectors(){
        try {
            Selector selector = Selector.open();
            System.out.println("Selector is open for making connection: "+selector.isOpen());

            //get the server socket channel and register using selector
            ServerSocketChannel server = ServerSocketChannel.open();
            InetSocketAddress address=new InetSocketAddress("localhost",8090);
            server.bind(address);
            server.configureBlocking(false);
            int ops = server.validOps();

            SelectionKey selectionKey = server.register(selector,ops,null);

            for(;;){
                System.out.println("Waiting for connection...");
                int noOfKeys =selector.select();
                System.out.println("No of keys selected: "+noOfKeys);
                Set selectedKeys  = selector.selectedKeys();
                Iterator iterator = selectedKeys.iterator();

                while(iterator.hasNext()){
                    SelectionKey key = (SelectionKey)iterator.next();
                    if(key.isAcceptable()){
                        //the new client connection is accepted
                        SocketChannel client = server.accept();
                        client.configureBlocking(false);
                        //new connection is added to the selector
                        client.register(selector,SelectionKey.OP_READ);
                        System.out.println("The new connection is accepted from the client: "+client);
                    }else if (key.isReadable()){
                        //data is read from the client
                        SocketChannel client = (SocketChannel)key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(256);
                        client.read(buffer);
                        String output = new String(buffer.array()).trim();
                        System.out.println("Msg from client: "+output);
                        if(output.equals("Bye bye")){
                            client.close();
                            System.out.println("Close session");
                        }
                    }
                    iterator.remove();
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
