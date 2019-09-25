package com.javarush.task.task18.task1821;

/* 
Встречаемость символов
*/

import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) {
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<Character, Integer> map = new TreeMap<Character, Integer>();
        try {
            String fileName = args[0];
            BufferedInputStream fin = new BufferedInputStream(new FileInputStream(fileName));
            while (fin.available() > 0) {
                int b = fin.read();
                if (map.containsKey((char)b)) {
                    map.put((char)b, map.get((char)b) + 1);
                } else {
                    map.put((char)b, 1);
                }
            }
            fin.close();
            // sort map by value
            // output
            for(Map.Entry<Character, Integer> entry : map.entrySet())
                System.out.println(entry.getKey()+" "+entry.getValue());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("File name missing");
        }


    }
}
