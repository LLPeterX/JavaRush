package com.javarush.task.task13.task1318;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.FileInputStream;

/* 
Чтение файла
*/

public class Solution {
    public static void main(String[] args) {
        // напишите тут ваш код
        FileInputStream inFile = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = reader.readLine();
            inFile = new FileInputStream(fileName);
            while(inFile.available()>0) {
                int c = inFile.read();
                System.out.print((char)c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if(inFile !=null) inFile.close();
            if(reader!=null) reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}