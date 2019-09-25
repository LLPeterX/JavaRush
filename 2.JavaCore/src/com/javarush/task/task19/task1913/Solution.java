package com.javarush.task.task19.task1913;

/* 
Выводим только цифры
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream consoleStream = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream outStream = new PrintStream(outputStream);
        System.setOut(outStream);
        testString.printSomething();
        String parts[] = outputStream.toString().split(" ");
        String outStr="";
        for(int i=0; i<parts.length; i++) {
            for(int j=0; j<parts[i].length(); j++) {
                if(parts[i].charAt(j) >='0' && parts[i].charAt(j)<='9') {
                    outStr = outStr+(char)parts[i].charAt(j);
                }
            }
        }
        System.setOut(consoleStream);
        System.out.println(outStr);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's 1 a 23 text 4 f5-6or7 tes8ting");
        }
    }
}
