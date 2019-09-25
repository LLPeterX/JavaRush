package com.javarush.task.task18.task1809;

/* 
Реверс файла
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //List<Byte> b = new ArrayList<Byte>;
        try {
            String sourceFile = reader.readLine();
            String destFile = reader.readLine();
            reader.close();

            FileInputStream input = new FileInputStream(sourceFile);
            FileOutputStream output = new FileOutputStream(destFile);
            byte[] content = new byte[ input.available() ];
            int count = input.read(content);
            //Collections.reverse(Arrays.asList(content));
            for(int i=content.length-1; i>=0; i--)
                 output.write(content[i]);
            output.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
