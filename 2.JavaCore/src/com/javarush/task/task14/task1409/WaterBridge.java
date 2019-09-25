package com.javarush.task.task14.task1409;

public class WaterBridge implements Bridge {
    @Override
    public int getCarsCount() {
        return (int)Math.random()*5+1;
    }
}
