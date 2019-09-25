package com.javarush.task.task21.task2113;

public class Horse {
    private String name;
    private double speed;
    private double distance;

    public Horse() {}

    public Horse(String name, double speed, double distance) {
        this.name = name;
        this.speed=speed;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDistance() {
        return distance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void move() {
        //speed = speed * Math.random();
        distance += speed*Math.random();
    }

    public void print() {
        StringBuilder horsePath = new StringBuilder();
        for(int i=0; i<(int)distance; i++)
            horsePath.append(".");
        horsePath.append(this.name);
        System.out.println(horsePath.toString());
    }

}
