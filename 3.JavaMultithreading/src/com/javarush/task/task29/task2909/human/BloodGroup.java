package com.javarush.task.task29.task2909.human;

public class BloodGroup {
    final private int code;

    private BloodGroup(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
    // Добавь в класс BloodGroup статические методы first(), second(), third() и fourth(), создающие и возвращающие объекты типа BloodGroup
    // с правильным кодом внутри (1, 2, 3 и 4 соответственно).
    public static BloodGroup first() {
        return new BloodGroup(1);
    }
    public static BloodGroup second() {
        return new BloodGroup(2);
    }
    public static BloodGroup third() {
        return new BloodGroup(3);
    }
    public static BloodGroup fourth() {
        return new BloodGroup(4);
    }

}
