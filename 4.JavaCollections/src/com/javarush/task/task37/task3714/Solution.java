package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Древний Рим
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        // {I, V, X, L, C, D, M}
        Map<Character,Integer> rim = new HashMap<>();
        rim.put('I',1);
        rim.put('V',5);
        rim.put('X',10);
        rim.put('L',50);
        rim.put('C',100);
        rim.put('D',500);
        rim.put('M',1000);
        int last=0, result = 0; // последнее число и результат
        s=s.toUpperCase();
        for(int i=s.length()-1; i>=0; i-- ) {
            Character c = s.charAt(i);
            int num = rim.get(c);
            if (num < last) // проверяем условие типа IV = 4
                result -= num;
            else
                result += num;
            last = num;
        }
        return result;
    }
}
