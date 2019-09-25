package com.javarush.task.task26.task2607;

/* 
Вежливость - это искусственно созданное хорошее настроение
*/
public class Solution {
    public static void main(String[] args) {
    }

     public static class IntegerHolder {
        private int value; // не д.б. volatile, т.к. get()  и set() уже synchronized

        public synchronized int get() {
            return value;
        }

        public synchronized void set(int value) {
            this.value = value;
        }
    }
}
