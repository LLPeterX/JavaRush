package com.javarush.task.task18.task1818;

/* 
Два в одном
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String dstName = reader.readLine();
            String srcName = reader.readLine();
            String addname = reader.readLine();
            FileOutputStream fout = new FileOutputStream(dstName);
            FileInputStream fin1 = new FileInputStream(srcName);
            FileInputStream fin2 = new FileInputStream(addname);
            // пишем в fout содержимое fin1
            //int count;
            while (fin1.available() > 0)
                fout.write(fin1.read());
            while (fin2.available() > 0)
                fout.write(fin2.read());
            fin2.close();
            fin1.close();
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
