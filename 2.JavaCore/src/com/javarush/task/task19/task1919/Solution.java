package com.javarush.task.task19.task1919;

/* 
Считаем зарплаты
*/

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) {
        if(args.length==0) return;
        String filename = args[0];
        Map<String,Double> salary = new TreeMap<String,Double>();
        try {
            BufferedReader input = new BufferedReader(new FileReader(filename));
            String str;
            while ((str = input.readLine()) != null) {
                String[] s = str.split(" ");
                double v = Double.parseDouble(s[ 1 ]);
                if (salary.containsKey(s[ 0 ])) {
                    salary.put(s[ 0 ], salary.get(s[ 0 ]) + v);
                } else
                    salary.put(s[ 0 ], v);
            }
            input.close();
            // sort map
            // вывод
            for (Map.Entry<String, Double> entry : salary.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: "+ args[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
