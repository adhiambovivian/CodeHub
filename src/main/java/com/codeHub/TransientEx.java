package com.codeHub;

import java.io.*;

public class TransientEx {
    public static void serializeBlacklist() {
        Blacklist blacklist = new Blacklist("Texted the word stop", "SELF", 1, "+254729880726");
        try {
            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("BlacklistSerialized"));
            o.writeObject(blacklist);
            o.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //reading object
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("BlacklistSerialized"));
            Blacklist bl = (Blacklist) in.readObject();
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
