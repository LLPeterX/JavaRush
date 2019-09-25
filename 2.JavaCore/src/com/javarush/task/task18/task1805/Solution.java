package com.javarush.task.task18.task1805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/* 
Сортировка байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        Set<Integer> set = new TreeSet<Integer>();
        FileInputStream input = new FileInputStream(fileName);
        while(input.available()>0) {
            set.add(input.read());
        }
        input.close();
        for(int i : set)
            System.out.print(""+i+" ");
        System.out.println();

    }
}
