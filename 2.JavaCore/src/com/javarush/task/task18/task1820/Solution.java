package com.javarush.task.task18.task1820;

/* 
Округление чисел
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String srcFilename = reader.readLine();
            String dstFilename = reader.readLine();
            BufferedReader fin = new BufferedReader(new FileReader(srcFilename));
            BufferedWriter fout = new BufferedWriter(new FileWriter(dstFilename));
            String[] strNumbers = fin.readLine().split(" ");
            String[] outNumbers = new String[ strNumbers.length ];
            for (int i = 0; i < strNumbers.length; i++)
                outNumbers[ i ] = String.valueOf((int) Math.round(Double.parseDouble(strNumbers[ i ])));
            fout.write(String.join(" ", outNumbers));
            fout.close();
            fin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
