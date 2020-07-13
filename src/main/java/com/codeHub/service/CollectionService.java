package com.codeHub.service;

import java.io.*;
import java.util.*;

public class CollectionService {
    static String filePath="/Users/vivian/PERSONAL_PROJECTS/CodeHub/data/";
    enum days{SUNDAY,MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY}


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
        traverseMap();
        traverseMapCmd();
        linkedhashmapCmd();
        sortedMapCmd();
        hashtableCmd();
        enumSetCmd();
        enumMapCmd();
        collectionsCmd();
    }

    public static void arrayListCmd(){
        ArrayList<String> list=new ArrayList<String>();
        list.add("Apples");
        list.add("bananas");
        list.add("grapes");
        list.set(1,"watermelon");

        ArrayList<String> list2 =  new ArrayList<>();
        list2.add("Oranges");
        list2.add("peach");
        list2.add("Apples");
        //retains matching(includin case) elements only
        //list.retainAll(list2);
        list.addAll(list2);

        Collections.sort(list);

        //traverse
        System.out.println("ArrayList: ");
        Iterator<String> iterator=list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        try {
            //serialization
            FileOutputStream fileOutputStream = new FileOutputStream(filePath + "test.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);
            fileOutputStream.close();
            objectOutputStream.close();

            //Deserialization
            FileInputStream fileInputStream = new FileInputStream(filePath+"test.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            ArrayList list3 = (ArrayList)objectInputStream.readObject();
            System.out.println(list3);

        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static void linkedListCmd(){
        System.out.println("LinkedList: ");
        LinkedList<String> list = new LinkedList<String>();
        list.add("LOL");
        list.add("WOW");
        list .add("huh");
        list.addFirst("nah");
        list.addLast("ok");
        list.removeLastOccurrence("ok");

        //traverse in reverse order
        ListIterator iterator=list.listIterator();
        while(iterator.hasPrevious()){
            System.out.println(iterator.previous());
        }
        //traverse in reverse
        Iterator iterator2=list.descendingIterator();
        System.out.println("Reverse LinkedList: ");

        while(iterator2.hasNext()){
            System.out.println(iterator2.next());
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
        deque.offer("white");
        deque.offer("off-white");
        deque.offerFirst("blue");
        deque.offerLast("black");
        deque.pollLast();
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
        hashSet.add("pluto");

        hashSet.removeIf(str->str.contains("pluto"));

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
        set.add("Jupiter");
        set.add("neptune");
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
        set .add("cypress");

        System.out.println("treeset: Highest val: "+set.pollFirst()+" Lowest val: "+set.pollLast()+
                " reverse set: "+set.descendingSet());

        for(String str:set){
            System.out.println(str);
        }
    }
    //old
    public static void traverseMap(){
        Map map=new HashMap();
        map.put(1,"circle");
        map.put(3,"square");
        map.put(2,"pentagon");
        map.put(4,"circle");

        //traverse map
        Set set=map.entrySet();//convert to set
        Iterator iterator=set.iterator();
        while (iterator.hasNext()){
            //convert to Map.Entry to get key and val separately
            Map.Entry entry=(Map.Entry)iterator.next();
            System.out.println("Key: "+entry.getKey()+" Val: "+entry.getValue());
        }
    }

    //new
    public static void traverseMapCmd(){
        Map<Integer,String> map=new HashMap<Integer, String>();
        map.put(1,"circle");
        map.put(3,"square");
        map.put(2,"pentagon");
        map.put(4,"circle");
        map.put(3,"rectangle");

        HashMap<Integer,String> hashMap=new HashMap<>();
        hashMap.putIfAbsent(8,"octagon");
        hashMap.putIfAbsent(5,"oval");
        map.putAll(hashMap);

        //traverse map
        for(Map.Entry data:map.entrySet()){
            System.out.println("Key: "+data.getKey()+" Val: "+data.getValue());
        }
        map.replace(5,"quadragon");
        map.replaceAll((k,v)->"shape");
        //returns a Set view of the mappings contained in this map
        map.entrySet()
        //returns sequential Stream with this collection as its source
        .stream()
        //sorted according to the provided Comparator
        //.sorted(Map.Entry.comparingByKey())
        //to sort in descending order by key
        .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
        //performs an action for each element of this stream
        .forEach(System.out::println);

    }

    public static void linkedhashmapCmd(){
        LinkedHashMap<Integer, String> map=new LinkedHashMap<Integer, String>();
        map.put(10,"glass");
        map.put(11,"fork");
        map.put(22,"spoon");

        System.out.println(map.entrySet());
    }
    public static void treeMapCmd(){
        TreeMap<Integer,String> map=new TreeMap<>();
        map.put(90,"apple");
        map.put(23,"lenovo");
        map.put(45,"sumsung");
        map.put(2,"google");

        System.out.println("Descending: "+map.descendingMap());
        //Returns key-value pairs whose keys are less than or equal to the specified key.
        System.out.println("headMap: "+map.headMap(20,true));
        //Returns key-value pairs whose keys are greater than or equal to the specified key.
        System.out.println("tailMap: "+map.tailMap(40,true));
        //Returns key-value pairs exists in between the specified key.
        System.out.println("subMap: "+map.subMap(20, false, 30, true));

    }

    public static void sortedMapCmd(){
        SortedMap<Integer,String> map=new TreeMap<>();
        map.put(100,"vodka");
        map.put(198,"wine");
        map.put(30,"whiskey");
        map.put(10,"beer");

        //Returns key-value pairs whose keys are less than the specified key.
        System.out.println("HeadMap: "+map.headMap(30));
        //Returns key-value pairs whose keys are greater than or equal to the specified key.
        System.out.println("tailMap: "+map.tailMap(100));
        //Returns key-value pairs exists in between the specified key.
        System.out.println("subMap: "+map.subMap(1,30));

    }

    public static void hashtableCmd(){
        Hashtable<Integer,String> map=new Hashtable<>();
        map.put(10,"1000");
        map.put(2,"2000");
        map.put(30,"3000");
        map.putIfAbsent(104,"10400");


        System.out.println(map.getOrDefault(105, "Not Found"));
        for(Map.Entry data:map.entrySet()){
            System.out.println("Key: "+data.getKey()+" Val: "+data.getValue());
        }
    }

    private static void enumSetCmd(){
        Set<days> set=EnumSet.of(days.FRIDAY,days.MONDAY);
        //traverse
        Iterator<days> iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        Set<days> set2=EnumSet.allOf(days.class);
        System.out.println("All days: "+set2);
        Set<days> set3=EnumSet.noneOf(days.class);
        System.out.println("None of the days: "+set3);
    }

    private static void enumMapCmd(){
        EnumMap<days,String> map=new EnumMap<days, String>(days.class);

        map.put(days.MONDAY,"3");
        map.put(days.FRIDAY,"6");
        map.put(days.SATURDAY,"7");

        for(Map.Entry data:map.entrySet()){
            System.out.println("Key: "+data.getKey()+" Val: "+data.getValue());
        }
    }

    private static void collectionsCmd(){
        List<String> list=new ArrayList<>();
        list.add(1,"circle");
        list.add(3,"square");
        list.add(2,"pentagon");
        list.add(4,"circle");
        System.out.println("Max: "+Collections.max(list));
        Collections.sort(list);
        System.out.println("search: "+Collections.binarySearch(list,"oval"));
        System.out.println("reverse: "+Collections.reverseOrder());
        Collections.sort(list,Collections.reverseOrder());

        //sort user-defined objs
        ArrayList<Student> students=new ArrayList<Student>();
        students.add(new Student(101,"Lucy",23));
        students.add(new Student(106,"Mary",27));
        students.add(new Student(105,"John",21));
        Collections.sort(students);

        for(Student student:students){
            System.out.println(student);
        }
    }

    public static void compareObjs(){
        ArrayList<Student> students=new ArrayList<Student>();
        students.add(new Student(101,"Lucy",23));
        students.add(new Student(106,"Mary",27));
        students.add(new Student(105,"John",21));

        //using java 8
        Comparator<Student> studentComparator=Comparator.comparing(Student::getName);
        Collections.sort(students,studentComparator);
        System.out.println("Sorting by Name");
        for(Student st: students){
            System.out.println(st.idNo+" "+st.name+" "+st.age);
        }
        //sort but nulls first
        Comparator<Student> cm1=Comparator.comparing(Student::getName,Comparator.nullsFirst(String::compareTo));


    }

    private static class Student implements Comparable<Student>{
        int idNo;
        String name;
        int age;

        public Student(int idNo, String name, int age) {
            this.idNo = idNo;
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(Student o) {
            if(age==o.age)
            return 0;
            else if(age>o.age)//switch if sort by reverse eg age<0.age, return 1
                return 1;
            else
                return -1;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "idNo=" + idNo +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

}
