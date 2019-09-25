package com.javarush.task.task19.task1906;

/* 
Четные символы
*/

//import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String file1 = reader.readLine();
            String file2 = reader.readLine();
            reader.close();
            // red first file
            FileReader input = new FileReader(file1);
            FileWriter output = new FileWriter(file2);
            for (int i = 1; input.ready(); i++) {
                int b = input.read();
                if (i % 2 == 0)
                    output.write(b);
            }
            output.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
