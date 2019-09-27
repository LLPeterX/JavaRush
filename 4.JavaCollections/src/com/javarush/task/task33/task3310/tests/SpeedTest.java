// код украден с https://github.com/tyomakr/JavaRushHomeWork/blob/master/src/com/javarush/test/level33/lesson15/big01/tests/SpeedTest.java
package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {

    // должен возвращать время в миллисекундах необходимое для получения идентификаторов для всех строк из strings.
    // Идентификаторы должны быть записаны в ids
    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
       Date d0 = new Date();
       for(String str : strings) {
           ids.add(shortener.getId(str));
       }
       Date d1 = new Date();
       return d1.getTime() - d0.getTime();
    }

    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        Date d0 = new Date();
        for(Long key : ids) {
            strings.add(shortener.getString(key));
        }
        Date d1 = new Date();
        return d1.getTime() - d0.getTime();
    }

    @Test
    public void testHashMapStorage() {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10000; i++)
            origStrings.add(Helper.generateRandomString());

        Set<Long> ids1 = new HashSet<>();
        Set<Long> ids2 = new HashSet<>();
        long time1 = getTimeToGetIds(shortener1, origStrings, ids1);
        long time2 = getTimeToGetIds(shortener2, origStrings, ids2);
        Assert.assertTrue(time1 > time2);

        long time3 = getTimeToGetStrings(shortener1, ids1, new HashSet<String>());
        long time4 = getTimeToGetStrings(shortener2, ids2, new HashSet<String>());
        Assert.assertEquals(time3, time4, 5);
    }
}

