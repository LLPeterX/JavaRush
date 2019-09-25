package com.javarush.task.task19.task1926;

/* 
Перевертыши
*/

//import com.sun.deploy.util.ArrayUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String fileName = console.readLine();
        console.close();
        BufferedReader input = new BufferedReader(new FileReader(fileName));
        String str;
        while((str=input.readLine())!=null) {
            char[] chars = str.toCharArray();
//            Collections.reverse(Arrays.asList(chars));
              for(int i=0; i<chars.length/2; i++) {
                  char tmp = chars[i];
                  chars[i] = chars[chars.length-i-1];
                  chars[chars.length-i-1] = tmp;
              }
            System.out.println(chars);
        }
        input.close();
    }
}
