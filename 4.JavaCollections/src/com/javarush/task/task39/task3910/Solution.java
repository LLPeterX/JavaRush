package com.javarush.task.task39.task3910;

/* 
isPowerOfThree
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isPowerOfThree(729));

    }

    // вернуть true, если n является степенью тройки (напр. 9=true, 18 - false) - т.е. 3^x = n
    public static boolean isPowerOfThree(int n) {
        if(n==0) return false;
        if(n==1 || n==3) return true;
        int pow=1;
        while(pow<n) {
            pow*=3;
            if(pow == n)
                return true;
        }
        return false;
    }
}
