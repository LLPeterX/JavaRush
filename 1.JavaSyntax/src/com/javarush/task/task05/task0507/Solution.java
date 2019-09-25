package com.javarush.task.task05.task0507;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Среднее арифметическое
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        int count=0, sum=0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            int num = Integer.parseInt(reader.readLine());
            if(num==-1) break;
            sum+=num;
            count++;
        }
        System.out.println((double)sum/count);

    }
}

