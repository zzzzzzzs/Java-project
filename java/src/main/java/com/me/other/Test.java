package com.me.other;

import java.util.*;

class Person {
    public String name;
    public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class Test {

    public static void main(String[] args) {
        PriorityQueue<Person> queue = new PriorityQueue<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o2.age - o1.age;
            }
        });
        queue.add(new Person("a", 1));
        queue.add(new Person("b", 2));
        queue.add(new Person("c", 3));
        queue.add(new Person("d", 4));



        while (!queue.isEmpty()) {
            System.out.println(queue.poll().name);
        }
    }
}
