package com.javarush.task.task39.task3906;

public class LightBulb implements Switchable {
    private boolean on = false;

    public boolean isOn() {
        return on;
    }

    void turnOff() {
        System.out.println("The light is off!");
        on = false;
    }

    void turnOn() {
        System.out.println("The light is on!");
        on = true;
    }

    @Override
    public void press() {
        if(isOn())
            turnOff();
        else
            turnOn();
    }
}
