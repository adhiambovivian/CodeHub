package com.codeHub;

import java.util.Comparator;

public class Person implements Comparable<Person> {
    public String firstName;
    public String lastName;
    public int age;
    public String contact;

    public Person(String firstName, String lastName,
                  int age, String contact) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.contact = contact;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "[" + firstName + " " + lastName +
                " " + age + " " + contact + "]";
    }

    public int compareTo(Person comparePerson) {
        int compareAge = comparePerson.age;
        //ascending
        return this.age - compareAge;
        //descending
        //return compareAge-this.age;
    }

    public static Comparator<Person> PersonCompare = new Comparator<Person>() {
        @Override
        public int compare(Person person1, Person person2) {
            String firstName1 = person1.firstName.toUpperCase();
            String firstName2 = person2.firstName.toUpperCase();
            //ascending
            return firstName1.compareTo(firstName2);
            //descending
            //return firstName2.compareTo(firstName1);

        }
    };

    }
