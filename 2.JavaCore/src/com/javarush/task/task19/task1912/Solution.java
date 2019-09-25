package com.javarush.task.task19.task1912;

/* 
Ридер обертка 2
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
        //String uppers = outputStream.toString().toUpperCase();
        String outStr = outputStream.toString().replaceAll("te","??");
        System.setOut(consoleStream);
        System.out.println(outStr);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}
