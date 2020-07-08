package com.codeHub.service;

import java.util.*;

public class CollectionService {
    public static void collectionCmd(){
        linkedListCmd();
        arrayListCmd();
        vectorCmd();
        stackCmd();
        priorityQueueCmd();
        arrayDequeCmd();
        hashSetCmd();
        linkedHashsetCmd();
        treeSetCmd();
    }

    public static void arrayListCmd(){
        ArrayList<String> list=new ArrayList<String>();
        list.add("Apples");
        list.add("bananas");
        list.add("grapes");
        list.set(1,"watermelon");
        Collections.sort(list);

        //traverse
        System.out.println("ArrayList: ");
        Iterator<String> iterator=list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    public static void linkedListCmd(){
        System.out.println("LinkedList: ");
        LinkedList<String> list = new LinkedList<String>();
        list.add("LOL");
        list.add("WOW");
        list .add("huh");

        //traverse in reverse order
        ListIterator iterator=list.listIterator();
        while(iterator.hasPrevious()){
            System.out.println(iterator.previous());
        }
    }

    public static void vectorCmd(){
        Vector<String> vector = new Vector<String>();
        vector.add("Nile");
        vector.add("sabaki");
        vector.add("Nynando");
        System.out.println("Vector: ");
        for(int i=0; i<vector.size();i++) {
            System.out.println(vector.get(i));
        }

    }

    public static void stackCmd(){
        Stack<String> stack=new Stack<String>();
        stack.push("kales");
        stack.push("cauliflower");
        stack.push("cabbage");
        stack.push("brocolli");
        stack.push("cucumber");
        System.out.println(stack.pop());
        System.out.println("Stack: ");
        Iterator<String> iterator=stack.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }

    public static void priorityQueueCmd() {
        PriorityQueue<String> queue = new PriorityQueue<>();
        queue.add("winter");
        queue.add("summer");
        queue.add("spring");
        queue.add("Autumn");

        System.out.println("Head: " + queue.element());
        System.out.println("Head: " + queue.peek());

        //traverse using forEach
        queue.forEach(item->{ //lambda expression
            System.out.println(item);
        });
        queue.remove();//remove
        queue.poll();//remove
        Iterator iterator2 = queue.iterator();
        System.out.println("PriorityQueue: ");

        System.out.println("After removing 2 items: ");
        while (iterator2.hasNext()) {
            System.out.println(iterator2.next());
        }
    }

    public static void arrayDequeCmd(){
        Deque<String> deque = new ArrayDeque<>();
        deque.add("red");
        deque.add("yellow");
        deque.add("green");
        System.out.println("ArrayDeque: ");
        for(String str:deque){
            System.out.println(str);
        }
    }

    public static void hashSetCmd(){
        HashSet<String> hashSet=new HashSet<>();
        hashSet.add("mercury");
        hashSet.add("venus");
        hashSet.add("earth");
        System.out.println("hashSet: ");
        //traverse using forEachRemaining
        Iterator iterator=hashSet.iterator();
        iterator.forEachRemaining(item->{
            System.out.println(item);
        });
    }

    public static void linkedHashsetCmd(){
        LinkedHashSet<String> set=new LinkedHashSet<>();
        set.add("Jupiter");
        set.add("Uranus");
        set.add("Neptune");
        System.out.println("linkedHashset: ");

        Iterator iterator=set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    public static void treeSetCmd(){
        TreeSet<String> set = new TreeSet<>();
        set.add("cypress");
        set.add("eucalyptus");
        set.add("mugo");
        System.out.println("treeset: ");

        for(String str:set){
            System.out.println(str);
        }
    }

}
