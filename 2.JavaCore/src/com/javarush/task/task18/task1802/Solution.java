package com.javarush.task.task18.task1802;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Минимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        int min = Integer.MAX_VALUE;
        FileInputStream input = new FileInputStream(fileName);
        while(input.available()>0) {
            int b = input.read();
            if(b<min) min=b;
        }
        input.close();
        System.out.println(min);
    }
}
