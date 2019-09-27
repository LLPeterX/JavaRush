package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.StorageStrategy;

public class Shortener {
    private Long lastId = 0L; // последнее значение id
    private StorageStrategy storageStrategy;

    // возвращает id заданной строки
    public synchronized Long getId(String string) {
        if(storageStrategy.containsValue(string)) {
            return storageStrategy.getKey(string);
        } else {
            lastId++;
            storageStrategy.put(lastId,string);
            return lastId;
        }
    }

    // возвращает строку по id
    public synchronized String getString(Long id) {
        //if(storageStrategy.containsKey(id)) {
            return storageStrategy.getValue(id);
        //} else {
//            return null;
  //      }
    }

    // конструктор
    public Shortener(StorageStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }
}
