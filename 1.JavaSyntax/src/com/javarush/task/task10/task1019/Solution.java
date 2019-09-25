package com.javarush.task.task10.task1019;

import java.io.*;
import java.util.*;

/* 
Функциональности маловато!
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        while(true) {
          String strId = reader.readLine();
          if(strId==null || strId.isEmpty()) {
            break;
          }
          int id = Integer.parseInt(strId);
          String name = reader.readLine();
          map.put(name,id);
        }
        for(Map.Entry<String, Integer> entry: map.entrySet()) {
            System.out.println(entry.getValue() + " " + entry.getKey());
        }


    }
}
