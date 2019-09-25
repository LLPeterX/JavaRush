package com.javarush.task.task07.task0708;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Самая длинная строка
*/

public class Solution {
    private static ArrayList<String> strings = new ArrayList<String>();

    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int maxlen = Integer.MIN_VALUE;
        for(int i=0; i<5; i++)
            strings.add(reader.readLine());
        //for(String s: strings)
          // maxlen = Math.max(s.length(), maxlen);
        for(int i=0; i<5; i++) {
            if(strings.get(i).length() > maxlen) maxlen = strings.get(i).length();
        }
        for(int i=0; i<5; i++) {
            if (strings.get(i).length() == maxlen)
                System.out.println(strings.get(i));
        }
    }
}
