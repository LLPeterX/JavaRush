package com.javarush.task.task33.task3310.strategy;

public interface StorageStrategy {
    public boolean containsKey(Long key); // должен вернуть true, если хранилище содержит переданный ключ

    public boolean containsValue(String value); // - должен вернуть true, если хранилище содержит переданное значение.

    public void put(Long key, String value); // добавить в хранилище пару ключ-значение

    public Long getKey(String value); // вернуть ключ для переданного значения

    public String getValue(Long key); // вернуть значение для переданного ключа
}
