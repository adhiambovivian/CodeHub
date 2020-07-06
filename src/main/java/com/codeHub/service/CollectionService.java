package com.codeHub.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

public class CollectionService {
    public static void collectionCmd(){
        linkedListCmd();
        arrayListCmd();
    }

    public static void arrayListCmd(){
        ArrayList<String> list=new ArrayList<String>();
        list.add("Apples");
        list.add("bananas");
        list.add("grapes");

        //traverse
        Iterator iterator=list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    public static void linkedListCmd(){
        LinkedList<String> list = new LinkedList<String>();
        list.add("LOL");
        list.add("WOW");
        list .add("huh");

        //traverse
        Iterator iterator=list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    public static void vectorCmd(){
        Vector<String> vector = new Vector<String>();
        vector.add("Nile");
        vector.add("sabaki");
        vector.add("Nynando");

        Iterator iterator=vector.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }



}
