package com.javarush.task.task15.task1523;

/* 
Перегрузка конструкторов
*/

public class Solution {
    public static void main(String[] args) {

    }

    public Solution() {};
    private Solution(int intValue) {}
    protected Solution(double doubleValue) {};
    Solution(String stringValue) {};

}

