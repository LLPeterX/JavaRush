package com.javarush.task.task13.task1324;

import java.awt.*;

/* 
Один метод в классе
*/

public class Solution {
    public static void main(String[] args) throws Exception {
    }

    public interface Animal {
        static Color getColor() {return Color.CYAN;}

        static Integer getAge() { return 1;}
    }

    public static class Fox implements Animal {
        public String getName() {
            return "Fox";
        }
    }
}
