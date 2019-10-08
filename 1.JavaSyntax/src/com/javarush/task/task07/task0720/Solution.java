package com.javarush.task.task07.task0720;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 
Перестановочка подоспела
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //напишите тут ваш код
        ArrayList<String> arr = new ArrayList<String>();
        int n, m;
        n = Integer.parseInt(reader.readLine());
        m = Integer.parseInt(reader.readLine());
        // ввести N строк и запонить ими список
        for(int i=0; i<n; i++) {
            arr.add(reader.readLine());
        }
        // переставить M первых строк в конец списка
        /*
        ArrayList<String> out = new ArrayList<String>();
        out.addAll(arr.subList(m,arr.size()));
        out.addAll(arr.subList(0,m));
         */
        for(int i=0; i<m; i++) {
            arr.add(arr.remove(0));
        }
        for(String s: arr)
          System.out.println(s);
    }
}
