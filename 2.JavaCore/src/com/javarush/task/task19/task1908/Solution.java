package com.javarush.task.task19.task1908;

/* 
Выделяем числа
*/

//import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String fname1 = reader.readLine();
            String fname2 = reader.readLine();
            BufferedReader input = new BufferedReader(new FileReader(fname1));
            BufferedWriter output = new BufferedWriter(new FileWriter(fname2));
            reader.close();
            String str;
            while ((str = input.readLine()) != null) {
                String[] s = str.split(" ");
                String sOut = "";
                for (int i = 0; i < s.length; i++) {
                    try {
                        int x = Integer.parseInt(s[ i ]);
                        sOut = sOut + x + " ";
                    } catch (Exception e) {

                    }
                }
                output.write(sOut.trim());
                output.newLine();
            }
            output.close();
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
