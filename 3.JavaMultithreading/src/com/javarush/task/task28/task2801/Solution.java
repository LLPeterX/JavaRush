package com.javarush.task.task28.task2801;

/* 
Осваиваем switch
*/
public class Solution {
    public static enum E1 {A, B, C, Y}

    public static enum E2 {D, E, F}

    public static enum E3 {D, E, F}

    public static void main(String[] args) {
        Solution.switchTest(E1.C);
        Solution.switchTest(E3.D);
        Solution.switchTest(E2.D);
        /* output
        it's E1.C
        undefined
        it's E2.D
         */
    }

    public static void switchTest(Enum obj) {
        //add your code here
        String className = obj.getClass().getSimpleName();
        //int key = obj.ordinal();
        String res="undefined";
        switch (className) {
            case "E1":
                try {
                    res="it's "+className+"."+((E1)obj).name();
                } catch (Exception e) {
                }
                break;
            case "E2":
                try {
                    res="it's "+className+"."+((E2)obj).name();
                } catch (Exception e) {

                }
                break;
            default:
//                try {
//                    res="It's "+className+"."+((E3)obj).name();
//                } catch (Exception e) {
//
//                }
                res="undefined";
        } // sw
        System.out.println(res);
    }
}
