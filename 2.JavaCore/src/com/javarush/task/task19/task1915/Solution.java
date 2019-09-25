package com.javarush.task.task19.task1915;

/* 
Дублируем текст
*/

import java.io.*;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream consoleStream = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream outStream = new PrintStream(outputStream);
        System.setOut(outStream);
        testString.printSomething();
        // duplicate to file

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fname = reader.readLine();
            reader.close();
            BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(fname));
            String outStr = outputStream.toString();
            output.write(outputStream.toByteArray());
            output.close();
            System.setOut(consoleStream);
            System.out.println(outStr);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}

