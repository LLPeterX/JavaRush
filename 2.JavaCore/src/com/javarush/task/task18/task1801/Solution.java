package com.javarush.task.task18.task1801;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Максимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        int maxByte=0;
        FileInputStream input = new FileInputStream(fileName);
        while(input.available()>0) {
            int b = input.read();
            if(b> maxByte) maxByte=b;
        }
        input.close();
        System.out.println(maxByte);
    }
}
