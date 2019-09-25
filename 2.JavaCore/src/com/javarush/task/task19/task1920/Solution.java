package com.javarush.task.task19.task1920;

/* 
Самый богатый
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
            // find max
            Double max = 0.0;
            for (Map.Entry<String, Double> entry : salary.entrySet()) {
                if(entry.getValue() > max) {
                    max = entry.getValue();
                }
            }
            //System.out.println("MAX:"+max);

            List<String> maxPerson = new ArrayList<String>(); // перечень персон, у кот. з/п = max
            for (Map.Entry<String, Double> entry : salary.entrySet()) {
                //System.out.println(entry.getKey()+":"+ entry.getValue());
                if(Math.abs(entry.getValue()-max) < 0.0001) {
                    //System.out.println(entry.getKey());
                    maxPerson.add(entry.getKey());
                    //System.out.println(entry.getKey()+"+");
                } else {
                    //System.out.println(entry.getKey()+"-");
                }
            }
            //Collections.sort(maxPerson);
            for(String s : maxPerson)
                System.out.println(s);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: "+ args[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
