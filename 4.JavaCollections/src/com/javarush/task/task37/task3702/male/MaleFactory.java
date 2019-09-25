package com.javarush.task.task37.task3702.male;

import com.javarush.task.task37.task3702.AbstractFactory;
import com.javarush.task.task37.task3702.Human;

public class MaleFactory implements AbstractFactory {

    public Human getPerson(int age) {
        if (age <= 12) {
            // вернуть объект KidBoy
            return new KidBoy();
//            KidBoy obj = (KidBoy)c.newInstance();
//            return (T)obj;
        } else if (age <= 19) {
            return new TeenBoy();
        } else
            return new Man();
    }
}
