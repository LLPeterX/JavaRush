package com.javarush.task.task18.task1804;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Самые редкие байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        Map<Integer,Integer> bytes = new HashMap<Integer,Integer>();
        FileInputStream input = new FileInputStream(fileName);
        int min_count=Integer.MAX_VALUE;
        while(input.available()>0) {
            int b = input.read();
            if(bytes.containsKey(b)) {
                bytes.put(b,bytes.get(b)+1);
            } else {
                bytes.put(b,1);
            }
        }
        input.close();
        // min value
        for(int i: bytes.values())
            if(i < min_count) min_count = i;
        //System.out.println("min count="+min_count);
        for(Map.Entry<Integer,Integer> entry : bytes.entrySet()) {
            if(entry.getValue() == min_count)
                System.out.print(""+entry.getKey()+" ");
        }
        System.out.println();

    }
}
