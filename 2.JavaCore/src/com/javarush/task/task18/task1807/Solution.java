package com.javarush.task.task18.task1807;

/* 
Подсчет запятых
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) {
        int count=0;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = reader.readLine();
            FileInputStream input = new FileInputStream(fileName);

            while (input.available() > 0) {
                int b = input.read();
                if (b == (byte) ',') count++;
            }
            input.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(count);

    }
}
