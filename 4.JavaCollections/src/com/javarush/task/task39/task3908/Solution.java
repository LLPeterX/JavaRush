package com.javarush.task.task39.task3908;

import java.util.HashMap;
import java.util.Map;

/*
Возможен ли палиндром?
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isPalindromePermutation("ab"));

    }

    public static boolean isPalindromePermutation(String s) {
        if(s==null || s.length()==0) return false;
        char[] chars = s.toLowerCase().toCharArray();
        Map<Character,Integer> counts = new HashMap<>();
        for(Character c : chars) {
            if(counts.containsKey(c))
                counts.put(c,counts.get(c)+1);
            else
                counts.put(c,1);
        }
        // палиндром будет, если кол-во ВСЕХ символов четное или один символ нечетное кол-во раз
        int chet=0;
        int nechet=0;
        for(int num : counts.values()) {
            if(num%2==0) chet++; else nechet++;
        }
        return (chet == counts.size() || nechet==1);

    }
}
