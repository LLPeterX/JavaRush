package com.javarush.task.task18.task1816;

/* 
Английские буквы
*/

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) {
        int count=0;
        try {
            FileInputStream fin = new FileInputStream(args[ 0 ]);
            BufferedInputStream buffer = new BufferedInputStream(fin);
            while (buffer.available() > 0) {
                int letter = buffer.read();
                if ((letter >= 'A' && letter <= 'Z') || (letter >= 'a' && letter <= 'z')) count++;
            }
            buffer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(count);

    }
}
