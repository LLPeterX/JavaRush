package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    // Этот метод должен для переданного множества строк возвращать множество идентификаторов.
    // Идентификатор для каждой отдельной строки нужно получить, используя shortener
    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
       Set<Long> set = new HashSet<>();
       for(String s: strings) {
           set.add(shortener.getId(s));
       }
       return set;
    }

    public static  Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> set = new HashSet();
        for(Long key : keys) {
            set.add(shortener.getString(key));
        }
        return set;
    }

    // Метод будет тестировать работу переданной стратегии на определенном количестве элементов elementsNumber.
    // Реализация метода должна:
    //6.2.3.1. Выводить имя класса стратегии. Имя не должно включать имя пакета.
    //6.2.3.2. Генерировать тестовое множество строк, используя Helper и заданное количество элементов elementsNumber.
    //6.2.3.3. Создавать объект типа Shortener, используя переданную стратегию.
    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());
        // сгененировать тестовое м-во строк из elementsNumber
        Set<String> testSet = new HashSet();
        for(long i=0; i<elementsNumber; i++) {
            testSet.add(Helper.generateRandomString());
        }
        //System.out.println(" >>> testSet:"+testSet.size());
        Shortener shortener = new Shortener(strategy);
        Date d0, d1;
        long duration;

        // теперь замерить время для getIds()
        Set<Long> testLongSet = new HashSet<>();
        d0 = new Date();
        for(String s: testSet) {
            testLongSet.add(shortener.getId(s));
        }
        //System.out.println(" >>> testIds:"+testLongSet.size());
        d1 = new Date();
        duration = d1.getTime()-d0.getTime();
        Helper.printMessage(String.valueOf(duration));

        // замеряем время для getStrings()
        d0 = new Date();
        Set<String> resultSet = new HashSet<>();
        for(Long key : testLongSet)
             resultSet.add(strategy.getValue(key));
        d1 = new Date();
        duration = d1.getTime()-d0.getTime();
        Helper.printMessage(String.valueOf(duration));
        if(testSet.size() == resultSet.size()) {
            Helper.printMessage("Тест пройден.");
        } else {
            Helper.printMessage("Тест не пройден.");
            Helper.printMessage(String.valueOf("Orig: "+testSet.size())+ " Res:"+resultSet.size());

        }

    }

    public static void main(String[] args) {
        testStrategy(new HashMapStorageStrategy(),10000);
        testStrategy(new OurHashMapStorageStrategy(),10000);
        testStrategy(new FileStorageStrategy(),100);
        testStrategy(new OurHashBiMapStorageStrategy(),10000);
        testStrategy(new HashBiMapStorageStrategy(),10000);
        testStrategy(new DualHashBidiMapStorageStrategy(), 10000);
    }
}
