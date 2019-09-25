package com.javarush.task.task09.task0927;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* 
Десять котов
*/

public class Solution {
    public static void main(String[] args) {
        Map<String, Cat> map = createMap();
        Set<Cat> set = convertMapToSet(map);
        printCatSet(set);
    }

    public static Map<String, Cat> createMap() {
        //напишите тут ваш код
        Map<String,Cat> cats = new HashMap<String,Cat>();
        cats.put("Барсик",new Cat("Барсик"));
        cats.put("Васька",new Cat("Васька"));
        cats.put("Рыжик",new Cat("Рыжик"));
        cats.put("Компот",new Cat("Компот"));
        cats.put("Белочка",new Cat("Белочка"));
        cats.put("Иваныч",new Cat("Иваныч"));
        cats.put("Кокос",new Cat("Кокос"));
        cats.put("Мурзик",new Cat("Мурзик"));
        cats.put("Сажик",new Cat("Сажик"));
        cats.put("Мурка",new Cat("Мурка"));
        return cats;
    }

    public static Set<Cat> convertMapToSet(Map<String, Cat> map) {
        //напишите тут ваш код
        Set<Cat> cats = new HashSet<Cat>();
        for(Cat c : map.values())
            cats.add(c);
        return cats;
    }

    public static void printCatSet(Set<Cat> set) {
        for (Cat cat : set) {
            System.out.println(cat);
        }
    }

    public static class Cat {
        private String name;

        public Cat(String name) {
            this.name = name;
        }

        public String toString() {
            return "Cat " + this.name;
        }
    }


}
