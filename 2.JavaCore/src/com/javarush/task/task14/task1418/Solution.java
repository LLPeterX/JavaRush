package com.javarush.task.task14.task1418;

import java.util.LinkedList;
import java.util.List;

/* 
Исправь четыре ошибки
*/

public class Solution {
    public static void main(String[] args) {
        List<Number> list = new LinkedList<Number>();
        initList(list);

        //3
//        list.add(new Double(1000f));
//        list.add(new Double("123e-445632"));
//        list.add(new Float(-90 / -3));
//        list.remove(new Double("123e-445632"));

        //4 - Исправь 2 ошибки
//        for (int i = 0; i <= list.size(); i--) {
//            System.out.println(list.get(i));
//        }

        //5
//        for (Number object : list) {
//            //Исправь 2 ошибки
//            if (object instanceof Float) {
//                Double a = (Double) object;
//                System.out.println("Is float value defined? " + !(a.isNaN()));
//            } else if (object instanceof Double) {
//                Float a = (Float) object;
//                System.out.println("Is double value infinite? " + a.isInfinite());
//            }
//        }
    }

    public static void initList(List<Number> list) {
        list.add(new Double(1000d));
        list.add(new Double("123e-445632"));
        list.add(new Double(-90.0 / -3));
        list.remove(new Double("123e-445632"));
    }

    public static void printListValues(List<Number> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }

    public static void processCastedObjects(List<Number> list) {
        for (Number object : list) {
            //Исправь 2 ошибки
            if (object instanceof Float) {
                Float a = (Float) object;
                System.out.println("Is float value defined? " + !(a.isNaN()));
            } else if (object instanceof Double) {
                Double a = (Double) object;
                System.out.println("Is double value infinite? " + a.isInfinite());
            }
        }

    }
}
