package com.javarush.task.task39.task3902;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Биты были биты
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Please enter a number: ");

        long l = Long.parseLong(reader.readLine());
        String result = isWeightEven(l) ? "even" : "odd";
        System.out.println("The entered number has " + result + "ones");

    }

    // определить, четное ли кол-во "1" в побитовой записи числа (напр. 888 = 1101111000 - 6 - true
    public static boolean isWeightEven(long number) {
        // используем только побитовые операции
        // последовательно делим на 2 сдвигом вправо
        int count=0;
        while(number>0) {
            if((number & 1L)==1)
                count++;
            number >>=1;
        }
        return count%2==0;
    }
}
