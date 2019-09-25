package com.javarush.task.task07.task0706;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Улицы и дома
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
/*
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] dom = new int[15];
        int chet=0, nechet=0;
        for(int i=0; i<15; i++) {
            int count = Integer.parseInt(reader.readLine());
            if (i%2 == 0) chet+=count; else nechet+=count;
        }
        if(nechet>chet)
            System.out.println("В домах с нечетными номерами проживает больше жителей.");
        else
            System.out.println("В домах с четными номерами проживает больше жителей.");
*/
        //Integer[] tmpArray = {2, 4, 4, 4, 8, 8, 4, 12, 12, 14};
        //Integer[] tmpArray = {89,89,12,12,98,111,98,98,98,98,71};
        Integer[] tmpArray = {1,2,3,4,5};
        ArrayList<Integer> a = new ArrayList<Integer>();
        for(Integer i : tmpArray)
            a.add(i);
        System.out.println(a.toString());
        int num=0, count=1, max=0;
        for(Integer i: a) {
            if(i==num) {
                count++;
                if(count>max) max=count;
            } else {
                num=i;
                count=1;
            }
        }
        System.out.println(max);
    }
}

