package com.javarush.task.task18.task1823;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* 
Нити и байты
*/

public class Solution {
    volatile public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                String filename = reader.readLine();
                if (filename.equals("exit")) break;
                new ReadThread(filename).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static class ReadThread extends Thread {
        private String filename;
        public ReadThread(String fileName) {
            this.filename = fileName;
            //implement constructor body
        }
        // implement file reading here - реализуйте чтение из файла тут
        @Override
        public void run() {
            byte[] counts = new byte[255];
            try {
                FileInputStream fin = new FileInputStream(filename);
                while(fin.available()>0) {
                    counts[fin.read()]++;
                }
                fin.close();
                //Arrays.sort(counts);
                // ищем макс.искомый байт
                int max=0, b=0;
                for(int i=0; i<counts.length; i++) {
                    if(counts[i]>max) {
                        max=counts[i];
                        b=i;
                    }
                }
                synchronized (resultMap) {
                    resultMap.put(this.filename,b);
                }
                //System.out.println(Thread.currentThread().getName()+" = "+b+" = "+max);
            } catch (IOException e) {

            }

        }
    }
}
