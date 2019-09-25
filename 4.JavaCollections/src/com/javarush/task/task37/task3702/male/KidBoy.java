package com.javarush.task.task37.task3702.male;

import com.javarush.task.task37.task3702.Human;

public class KidBoy implements Human {
    // В каждом классе KidBoy, TeenBoy создай свою публичную константу MAX_AGE с соответствующими значениями: 12, 19.
    final static public int MAX_AGE = 12;

    @Override
    public String toString() {
        return "KidBoy{}";
    }
}
