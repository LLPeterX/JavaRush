package com.javarush.task.task13.task1326;

/* 
Сортировка четных чисел из файла
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) {
        // напишите тут ваш код
        BufferedReader reader;
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        FileInputStream input;
        try {
            reader = new BufferedReader((new InputStreamReader(System.in)));
            String fileName = reader.readLine();
            input = new FileInputStream(fileName);
            byte[] content = new byte[input.available()];
            input.read(content);
            input.close();
            reader.close();

            String str = new String(content);
            String[] strNumbers = str.split("\n|\r");
            for(String s : strNumbers) {
                int num = Integer.parseInt(s);
                if(num%2==0) numbers.add(num);
            }
            Collections.sort(numbers);
            for(Integer i: numbers)
                System.out.println(i);

        } catch (NumberFormatException e) {
              System.out.println("Invalid number in file");
        } catch (FileNotFoundException e) {
              System.out.println("File not found;");
        } catch (IOException e) {
              e.printStackTrace();
        }


    }
}
