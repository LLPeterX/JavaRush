package com.javarush.task.task27.task2712.kitchen;

import java.util.Arrays;

public enum Dish {
    //Fish, Steak, Soup, Juice, Water;
    Fish(25), Steak(30), Soup(15), Juice(5), Water(3);

    private int duration;

    Dish(int duration) {
        this.duration= duration;
    }

    public static String allDishesToString() {
        return Arrays.toString(Dish.values()).replaceAll("\\[|\\]","");
    }
    // продолжительность создания блюда

    public int getDuration() {
        return duration;
    }
}
