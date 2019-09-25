package com.javarush.task.task19.task1909;

/* 
Замена знаков
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String fname1 = reader.readLine();
            String fname2 = reader.readLine();
            reader.close();
            BufferedReader inp = new BufferedReader(new FileReader(fname1));
            BufferedWriter outp = new BufferedWriter(new FileWriter(fname2));
            while(inp.ready()) {
                int b = inp.read();
                outp.write(b=='.' ? '!' : b);
            }
            outp.close();
            inp.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
