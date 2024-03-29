package com.javarush.task.task28.task2811;

/* 
ReentrantReadWriteLock

Класс ReadWriteMap должен предоставлять корректный доступ к объекту Map из многих потоков, но в его реализации были допущены ошибки. Найди их и исправь.

P.S. Для корректной работы Map в многопоточной среде, желательно пользоваться стандартной реализацией, например ConcurrentHashMap из пакета java.util.concurrent. Но иногда требуется работа с другим типом мапы, например LinkedHashMap и тогда предложенное решение будет как никогда кстати.



*/

import java.util.LinkedHashMap;

public class Solution {
    public static void main(String[] args) {
        ReadWriteMap<Integer, String> linkedSafeMap = new ReadWriteMap<>(new LinkedHashMap<>());
    }
}
