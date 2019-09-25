package com.javarush.task.task19.task1907;

/* 
Считаем слово
*/

//import com.sun.deploy.util.StringUtils;

import java.io.*;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String filename = reader.readLine();
            reader.close();
//            File f = new File(filename);
//            int fileSize = (int)f.length();
//            char[] content = new char[ fileSize ];
//            FileReader input = new FileReader(filename);
//            input.read(content);
//            input.close();
//            String str = new String(content);
//            //String[] words = str.split()
//            int count = str.split(Pattern.quote("world"), -1).length - 1;
//            System.out.println(count);
            BufferedReader input = new BufferedReader(new FileReader(filename));
            String str;
            int count=0;
            while((str = input.readLine())!=null) {
                String[] parts = str.split("[\\p{Punct}\\s]+");
                for(int i=0; i<parts.length;i++) {
                    if(parts[i].trim().equals("world"))
                        count++;
                }
            }
            input.close();
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
