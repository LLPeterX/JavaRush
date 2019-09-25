package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.Charset;
import java.util.SortedMap;

/* 
Смена кодировки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String fileIn = args[0];
        String fileOut = args[1];

        FileInputStream input = new FileInputStream(fileIn);
        FileOutputStream output = new FileOutputStream(fileOut);
        String str;
        byte[] bytes = new byte[1024];
        while(input.available()>0) {
            input.read(bytes);
            String strIn = new String(bytes,Charset.forName("windows-1251"));
            bytes = strIn.getBytes(Charset.forName("UTF-8"));
            output.write(bytes);
        }
        input.close();
        output.close();
    }
}
