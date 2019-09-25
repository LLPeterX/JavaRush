package com.javarush.task.task18.task1817;

/* 
Пробелы
*/

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) {
        //System.out.println("Start");
        try {
            int numSpaces=0, numAll=0;
            BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(args[0]));
            //numAll = buffer.available();
            while(buffer.available()>0) {
                int b = buffer.read();
                if(b == 32) numSpaces++;
                if(b!=10 && b!=13) numAll++;
            }
            buffer.close();
            double rate = (double)numSpaces/numAll*100.0;
            //System.out.println("spaces="+numSpaces+", all="+numAll+", rate="+rate);
            String s = String.format("%.2f",rate).replace(',','.');
            System.out.println(s);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Must specify the file name");
        }

    }
}
