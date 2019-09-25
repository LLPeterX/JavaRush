package com.javarush.task.task19.task1914;

/* 
Решаем пример
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
        int num1 = Integer.parseInt(parts[0]);
        String operation = parts[1];
        int num2 = Integer.parseInt(parts[2]);
        int result=0;
        switch(operation) {
            case "+":
                result=num1+num2;
                break;
            case "*":
                result = num1*num2;
                break;
            case "-":
                result = num1-num2;
                break;
        }
        System.setOut(consoleStream);
        System.out.println(parts[0]+" "+parts[1]+" "+parts[2]+" = "+result);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

