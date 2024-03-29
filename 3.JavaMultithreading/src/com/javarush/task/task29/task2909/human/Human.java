package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Human implements Alive {
    private static int nextId = 0;
    private int id;
    protected int age;
    protected String name;

    //protected int[] size;
    private Size size;


//    public static final int FIRST = 1;
//    public static final int SECOND = 2;
//    public static final int THIRD = 3;
//    public static final int FOURTH = 4;
    //private int bloodGroup;
    private BloodGroup bloodGroup;
    private List<Human> children = new ArrayList<>();
    // ------------------------- methods -------------------------------
    public void setBloodGroup(BloodGroup group) {
        this.bloodGroup = group;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

public Human(String name, int age) {
    this.id = nextId;
    this.name = name;
    this.age = age;
    nextId++;
}

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Human> getChildren() {
        //return children;
        return Collections.unmodifiableList(children);
    }
    public void addChild(Human h) {
        children.add(h);
    }
    public void removeChild(Human h) {
        children.remove(h);
    }


    public void live() {
    }

    public String getPosition() {
        return "Человек";
    }
    
    public int getId() {
        return id;
    }


    public void printSize() {
        System.out.println("Рост: " + size.height + " Вес: " + size.weight);
    }

    public void printData() {
        System.out.println(getPosition()+": "+name);
    }

    // public class Size
    public class Size {
        public int height;
        public int weight;
    }
}