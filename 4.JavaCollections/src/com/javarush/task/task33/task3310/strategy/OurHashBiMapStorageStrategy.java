package com.javarush.task.task33.task3310.strategy;

import java.util.HashMap;

public class OurHashBiMapStorageStrategy implements StorageStrategy {
    // ---- вот это ниже (копи-пасте с HashMapStorageStrategy) нельзя оставлять - валидатор ругается
    //private static final int DEFAULT_INITIAL_CAPACITY = 16;
    //private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    //private Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];
    //private int size;
    //private int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    //private float loadFactor = DEFAULT_LOAD_FACTOR;

    private HashMap<Long, String> k2v = new HashMap<Long, String>();
    private HashMap<String, Long> v2k = new HashMap<String, Long>();

    // ----- local methods -----------


// ------------- переопределение методов из интерфейса --------------
    @Override
    public boolean containsKey(Long key) {
        return k2v.containsKey(key);
    }

    @Override
    public boolean containsValue(String value) {
        return v2k.containsKey(value);
    }

    @Override
    public void put(Long key, String value) {
        k2v.put(key,value);
        v2k.put(value,key);

    }

    @Override
    public Long getKey(String value) {
        return v2k.get(value);
    }

    @Override
    public String getValue(Long key) {
        return k2v.get(key);
    }
}
