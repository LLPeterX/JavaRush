package com.javarush.task.task18.task1803;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Самые частые байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        Map<Integer,Integer> bytes = new HashMap<Integer,Integer>();
        FileInputStream input = new FileInputStream(fileName);
        int prev_byte=Integer.MIN_VALUE;
        int count=0;
        int max_count=0;
        while(input.available()>0) {
            int b = input.read();
//            if(b == prev_byte) {
//                count++;
//                if(count > max_count) max_count = count;
//                 bytes.put(b,count);
//            } else {
//                count=1;
//            }
//            prev_byte = b;
            // Иной подход - сколько разных байтов есть
            if(bytes.containsKey(b)) {
                bytes.put(b,bytes.get(b)+1);
            } else {
                bytes.put(b,1);
            }
        }
        input.close();
        // max value
        for(int i: bytes.values())
            if(i > max_count) max_count = i;
        //System.out.println("max count="+max_count);
        for(Map.Entry<Integer,Integer> entry : bytes.entrySet()) {
            if(entry.getValue() == max_count)
                System.out.print(""+entry.getKey()+" ");
        }
        System.out.println();

    }
}
