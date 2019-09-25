package com.javarush.task.task37.task3702;

import com.javarush.task.task37.task3702.female.FemaleFactory;
import com.javarush.task.task37.task3702.male.MaleFactory;

public class FactoryProducer {

    //  В корне задачи создай класс FactoryProducer, в котором создай публичный статический энум HumanFactoryType со значениями MALE, FEMALE.
    public static enum HumanFactoryType {
        MALE,
        FEMALE
    }

    // В FactoryProducer создай публичный статический метод getFactory с аргументом HumanFactoryType.
    //Этот метод должен возвращать одну из фабрик: для ключа MALE - MaleFactory, для FEMALE - FemaleFactory.
    public static AbstractFactory getFactory(HumanFactoryType type) {
        switch (type) {
            case MALE:
                return new MaleFactory();
            case FEMALE:
                return new FemaleFactory();
        }
        return null;
    }
}
