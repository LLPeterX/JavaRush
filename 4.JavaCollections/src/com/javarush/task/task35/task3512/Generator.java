package com.javarush.task.task35.task3512;

public class Generator<T> {
    Class<T> clazz;
    // конструктор
    public Generator (Class<T> c) {
        this.clazz = c;
    }
     T newInstance() throws IllegalAccessException, InstantiationException {
        //return new T(); // так нельзя! надо использовать Class<T>.newInstance()

         return (T)clazz.newInstance();
    }
}
