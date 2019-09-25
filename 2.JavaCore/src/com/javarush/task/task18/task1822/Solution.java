package com.javarush.task.task18.task1822;

/* 
Поиск данных внутри файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        //System.out.println("Start1822 "+args[0]);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String id = args[0];
            String filename = reader.readLine();
            BufferedReader input = new BufferedReader(new FileReader(filename));
            //System.out.println("Using filename "+filename);
            String str;
            while((str = input.readLine())!=null) {
                //System.out.println("Check "+str);
                String[] comp = str.split(" ");
                if(comp[0].equals(id)) {
                    System.out.println(str);
                    break;
                }
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
