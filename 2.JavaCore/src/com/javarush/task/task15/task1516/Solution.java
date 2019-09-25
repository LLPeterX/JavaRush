package com.javarush.task.task15.task1516;

/* 
Значения по умолчанию
*/

public class Solution {
     public   int intVar;
     public   double doubleVar;
     public   Double DoubleVar;
     public  boolean booleanVar;
     public   Object ObjectVar;
     public   Exception ExceptionVar;
     public   String StringVar;

    // static {
    //     intVar = 0;
    //     doubleVar = 0.0;
    //     DoubleVar = new Double(0.0);
    //     ObjectVar = new Object();
    //     ExceptionVar = new Exception();
    //     StringVar = null;
    // }
    
    
    public static void main(String[] args) {
        System.out.println(new Solution().intVar);
        System.out.println(new Solution().doubleVar);
        System.out.println(new Solution().DoubleVar);
        System.out.println(new Solution().booleanVar);
        System.out.println(new Solution().ObjectVar);
        System.out.println(new Solution().ExceptionVar);
        System.out.println(new Solution().StringVar);
    }
}

