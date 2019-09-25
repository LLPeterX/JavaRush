package com.javarush.task.task17.task1714;

/* 
Comparable
*/

public class Beach implements Comparable<Beach> {
    private String name;      //название
    private float distance;   //расстояние
    private int quality;    //качество

    public Beach(String name, float distance, int quality) {
        this.name = name;
        this.distance = distance;
        this.quality = quality;
    }

    synchronized public String getName() {
        return name;
    }

    synchronized public void setName(String name) {
        this.name = name;
    }

    synchronized public float getDistance() {
        return distance;
    }

    synchronized public void setDistance(float distance) {
        this.distance = distance;
    }

    synchronized public int getQuality() {
        return quality;
    }

    synchronized public void setQuality(int quality) {
        this.quality = quality;
    }

    public static void main(String[] args) {

    }


    @Override
    synchronized public int compareTo(Beach o) {
        //if(this.distance==o.getDistance() && this.quality==o.getQuality()) return 0;
        //return this.quality-this.distance - o.setQuality();getDistance() && this.quality > o.getQuality()) ? 1 : -1;
        return (int)((this.quality - this.distance) - (o.quality - o.distance));
    }
}
