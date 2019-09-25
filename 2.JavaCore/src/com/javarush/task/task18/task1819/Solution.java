package com.javarush.task.task18.task1819;

/* 
Объединение файлов
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String fname1 = reader.readLine();
            String fname2 = reader.readLine();
            // читаем первый файлв массив
            FileInputStream fin1 = new FileInputStream(fname1);
            byte[] arr1 = new byte[ fin1.available() ];
            fin1.read(arr1);
            fin1.close();
            FileOutputStream fout = new FileOutputStream(fname1);
            FileInputStream fin2 = new FileInputStream(fname2);
            // начала пишем содержимое второго файла
            while (fin2.available() > 0)
                fout.write(fin2.read());
            // дописываем
            fout.write(arr1);
            fin2.close();
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
