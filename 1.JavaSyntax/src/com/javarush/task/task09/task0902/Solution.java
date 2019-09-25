package com.javarush.task.task09.task0902;

/* 
И снова StackTrace
*/

public class Solution {
    public static void main(String[] args) {
        method1();
    }

    public static String method1() {
        method2();
        //напишите тут ваш код
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String method = stackTraceElements[2].getMethodName();
//        System.out.println("method1: "+method);
        return method;
    }

    public static String method2() {
        method3();
        //напишите тут ваш код
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String method = stackTraceElements[2].getMethodName();
  //      System.out.println("method2: "+method);
        return method;
    }

    public static String method3() {
        method4();
        //напишите тут ваш код
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String method = stackTraceElements[2].getMethodName();
        //System.out.println("method3: "+method);
        return method;
    }

    public static String method4() {
        method5();
        //напишите тут ваш код
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String method = stackTraceElements[2].getMethodName();
        //System.out.println("method4: "+method);
        return method;
    }

    public static String method5() {
        //напишите тут ваш код
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String method = stackTraceElements[2].getMethodName();
        //System.out.println("method5: "+method);
        return method;
    }
}
