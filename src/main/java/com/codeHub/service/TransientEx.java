package com.codeHub.service;

import java.io.*;

public class TransientEx {
    public static void serializeBlacklist() {
        Person person = new Person("Texted the word stop", "SELF", 1, "+254729880726");
        try {
            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("BlacklistSerialized"));
            o.writeObject(person);
            o.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //reading object
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("BlacklistSerialized"));
            Person bl = (Person) in.readObject();
            System.out.println(bl.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
