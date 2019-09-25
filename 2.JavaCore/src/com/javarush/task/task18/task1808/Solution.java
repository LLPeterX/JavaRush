package com.javarush.task.task18.task1808;

/* 
Разделение файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String fileName1 = reader.readLine();
            String fileName2 = reader.readLine();
            String fileName3 = reader.readLine();
            reader.close();

            FileInputStream input = new FileInputStream(fileName1);
            FileOutputStream out2 = new FileOutputStream(fileName2);
            FileOutputStream out3 = new FileOutputStream(fileName3);
            int fileSize = input.available();
            int chunkSize = (fileSize % 2 == 0) ? fileSize / 2 : fileSize / 2 + 1;
            byte[] bytes = new byte[ chunkSize ];
            int count;
            count = input.read(bytes);
            out2.write(bytes, 0, count);
            count = input.read(bytes);
            out3.write(bytes, 0, count);

            out3.close();
            out2.close();
            input.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
