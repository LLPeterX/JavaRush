package com.javarush.task.task39.task3904;

import java.util.HashMap;

/* 
Лестница
*/
public class Solution {
    private static int n = 70;
    static HashMap<Integer, Long> cache = new HashMap<Integer,Long>();
    static {
        cache.put(0, 1L);
        cache.put(1, 1L);
        cache.put(2, 2L);
        cache.put(3, 4L);
    }

    public static void main(String[] args) {
        System.out.println("The number of possible ascents for " + n + " steps is: " + numberOfPossibleAscents(n));
    }

    public static long numberOfPossibleAscents(int n) {
        if(n<0) return 0;
        if(n==0) return 1;

        if (cache.containsKey(n)) {
            return cache.get(n);
        } else {
            long value = ((numberOfPossibleAscents(n - 3) + numberOfPossibleAscents(n - 2))  + numberOfPossibleAscents(n - 1));
            cache.put(n, value);
            return value;
        }
    }
}

