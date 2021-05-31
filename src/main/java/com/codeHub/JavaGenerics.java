/* Copyright (C)2021  Vivian */
package com.codeHub;

// import org.apache.commons.collections.ListUtils;

import java.util.*;

public class JavaGenerics {
    // create an enum
    public enum Numbers {
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE
    };

    public static void joinList() {
        List<String> listA = new ArrayList<String>();
        listA.add("A");
        List<String> listB = new ArrayList<String>();
        listB.add("B");

        listA.addAll(0, listB);
        System.out.println("ListA:: " + listA);
        System.out.println("ListB:: " + listB);
    }

    public static void joinListApache() {
        List<String> listA = new ArrayList<String>();
        listA.add("A");

        List<String> listB = new ArrayList<String>();
        listB.add("B");

        // List<String> listFinal = ListUtils.union(listA, listB);

        System.out.println("listA : " + listA);
        System.out.println("listB : " + listB);
        // System.out.println("listFinal : " + listFinal);

    }

    public static void arrayDequeExample() {
        Deque<Integer> deque = new ArrayDeque<Integer>(3);
        deque.add(20);
        deque.add(34);
        deque.add(56);
        deque.add(40);
        for (Integer number : deque) {
            System.out.println("Deque:" + number);
        }
        // print array length
        Object[] obj = deque.toArray();
        System.out.println("Array Size: " + obj.length);

        // clone
        // Deque<Integer> deque1=deque.clone();
    }

    public static void bitSetExample() {
        BitSet bitSet = new BitSet(8);
        BitSet bitSet1 = new BitSet(8);
        bitSet.set(0);
        bitSet.set(2);
        bitSet.set(5);
        bitSet1.set(9);

        System.out.println("BitSet: " + bitSet);
        System.out.println("BitSet2: " + bitSet1);
        // cardinality
        System.out.println("Cardinality bitset 1: " + bitSet.cardinality());
        System.out.println("Card bitset 2:" + bitSet1.cardinality());
    }

    public static void calendarExample() {
        Locale locale = Locale.UK;
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        Calendar calendar = Calendar.getInstance(timeZone, locale);

        Locale locale1 = Locale.GERMANY;
        TimeZone timeZone1 = TimeZone.getTimeZone("EST");
        Calendar calendar1 = Calendar.getInstance(timeZone1, locale1);
        String tzname1 = timeZone.getDisplayName();
        String tzname2 = timeZone1.getDisplayName();
        String name1 = locale.getDisplayName();
        String name2 = locale1.getDisplayName();
        System.out.println("GMT and Taiwan: " + tzname1 + " " + name1);
        System.out.println("EST and Germany: " + tzname2 + " " + name2);
        Locale[] array = new Locale[13];
        array = Locale.getAvailableLocales();
        System.out.println("First 13 installed locales: ");
        for (int i = 0; i < 13; i++) {
            System.out.println(
                    "Country "
                            + array[i].getISO3Country()
                            + " Language: "
                            + array[i].getISO3Language());
        }
        Calendar cal1 = new GregorianCalendar(2015, 8, 15);
        Calendar cal2 = new GregorianCalendar(2008, 1, 02);
        if (cal1.compareTo(cal2) == 1) {
            System.out.println("Equal");
        }
        System.out.println("Properties" + System.getProperties() + "Env: " + System.getenv());
        Calendar calendar2 = Calendar.getInstance();
        System.out.println("Time now:: " + calendar2.getTime());
        // add 20 days
        calendar2.add(Calendar.DATE, 20);
        System.out.println("Time after 20 days:: " + calendar2.getTime());
        // subtract 2 months
        calendar2.add(Calendar.MONTH, -2);
        System.out.println("Time before 2 months:: " + calendar2.getTime());
        calendar2.add(Calendar.YEAR, -5);
        Currency currency = Currency.getInstance(locale);
        System.out.println(
                "Currency:: " + currency.getCurrencyCode() + " symbol: " + currency.getSymbol());
        // create a date
        Date date = new Date(97, 1, 23);
        long diff = date.getTime();
        // print how many milliseconds have passed since January 1, 1970, 00:00:00 GMT
        System.out.println("If date is 23-01-1997, " + diff + " have passed.");
        Date date2 = new Date(15, 1, 21);

        // tests if date 2 is before date and print
        boolean before = date2.before(date);
        System.out.println("Date 2 is before date: " + before);
    }

    public static void collectionsEx() {
        List list = new ArrayList();
        list.add("A");
        list.add("B");
        System.out.println("Initial: " + list);
        Collections.shuffle(list);
        System.out.println("After shuffling: " + list);
        // create a thread-safe list
        List<String> synList = Collections.synchronizedList(list);
        System.out.println("Synchronized list: " + synList);

        Vector<String> vector = new Vector<String>();

        // populate the vector
        vector.add("1");
        vector.add("2");
        vector.add("5");
        // create a synchronized view
        Collection<String> c = Collections.synchronizedCollection(vector);
        System.out.println("Sunchronized view is :" + c);
        // create linked list object
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        linkedList.add(20);
        linkedList.add(-12);
        linkedList.add(8);
        // sort the list
        Collections.sort(list, null);
        System.out.println("List sorted in natural order: ");
        for (int i : linkedList) {
            System.out.println(i + " ");
        }
        Map map = Collections.singletonMap("key", "Value");
        System.out.println("Singleton map is: " + map);
        // create an array of string objs
        String init[] = {"One", "Two", "Three", "One", "Two", "Three"};

        // create one list
        List list1 = new ArrayList(Arrays.asList(init));

        System.out.println("List value before: " + list1);

        // create singleton list
        list1 = Collections.singletonList("TP");
        System.out.println("List value after: " + list1);
        HashSet<String> newset = new HashSet<String>();
        newset.add("Learning");
        newset.add("Easy");
        newset.add("Simply");
        Iterator iterator = newset.iterator();
        while (iterator.hasNext()) {
            System.out.println("Val " + iterator.next() + " ");
        }
        Hashtable htable = new Hashtable(3);

        // populate the table
        htable.put(1, "TP");
        htable.put(2, "IS");
        htable.put(3, "THE");
        htable.put(4, "BEST");
        htable.put(5, "TUTORIAL");

        System.out.println("Initial hash table value: " + htable);

        // remove element at key 3
        htable.remove(3);

        System.out.println("Hash table value after remove: " + htable);
    }

    public static void generics() {

        // create a new hashtable
        Dictionary d = new Hashtable();

        // add elements in the hashtable
        d.put("1", "Chocolate");
        d.put("2", "Cocoa");
        d.put("5", "Coffee");

        // return true if this dictionary maps no keys to value.
        boolean b = d.isEmpty();
        System.out.println("Dictionary is empty:" + b);
        EnumMap<Numbers, String> map = new EnumMap<Numbers, String>(Numbers.class);

        // assosiate values in map
        map.put(Numbers.ONE, "1");
        map.put(Numbers.TWO, "2");
        map.put(Numbers.THREE, "3");
        map.put(Numbers.FOUR, "4");

        // print the map
        System.out.println("Map: " + map);

        // remove value from Numbers.THREE
        map.remove(Numbers.THREE);

        // print the updated map
        System.out.println("Updated Map: " + map);
        // create an empty EnumSet
        EnumSet<Numbers> set = null;

        // print the set
        System.out.println(set);

        // create the set by getting all elements from Numbers
        set = EnumSet.allOf(Numbers.class);

        // print the updated set
        System.out.println("Updated set:" + set);
    }
}
