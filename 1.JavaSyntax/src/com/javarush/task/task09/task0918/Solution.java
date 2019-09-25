package com.javarush.task.task09.task0918;

/* 
Все свои, даже исключения
*/

import java.io.FileNotFoundException;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) {
    }
    
    static class MyException extends Exception { // checked

    }

    static class MyException2 extends Exception {
    }

    static class MyException3 extends Error {
    }

    static class MyException4 extends RuntimeException {
    }
}

