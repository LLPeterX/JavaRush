package com.javarush.task.task13.task1319;

import java.io.*;

/* 
Писатель в файл с консоли
*/

public class Solution {
    public static void main(String[] args) {
        // напишите тут ваш код
        BufferedReader reader;
        BufferedWriter writer;
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = reader.readLine();
            FileWriter outStream = new FileWriter(fileName);
            writer = new BufferedWriter(outStream);
            while(true) {
                String str = reader.readLine();
                writer.write(str+"\n"); // without \r !!!
                if(str.equals("exit")) break;
            }
            writer.close();
            outStream.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
