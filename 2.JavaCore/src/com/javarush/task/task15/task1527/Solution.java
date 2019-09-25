package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/* 
Парсер реквестов
*/

public class Solution {
    public static void main(String[] args) {
        //add your code here
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String url = reader.readLine();
            String[] params = url.split("\\?|&");
            //System.out.println(Arrays.toString(params));
            for(int i=1; i<params.length;i++) {
                String[] v = params[i].split("=");
                System.out.print(v[0]+" ");
            }
            System.out.println();
            for(int i=1; i<params.length;i++) {
                String[] v = params[i].split("=");
                if(v.length>0)
                    if(v[0].equals("obj")) {
                        try {
                            double number = Double.parseDouble(v[1]);
                            alert(number);
                        } catch (NumberFormatException e) {
                            alert(v[ 1 ]);
                        }
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void alert(double value) {
        System.out.println("double: " + value);
    }

    public static void alert(String value) {
        System.out.println("String: " + value);
    }
}
