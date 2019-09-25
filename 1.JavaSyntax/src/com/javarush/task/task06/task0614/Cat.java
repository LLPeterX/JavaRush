package com.javarush.task.task06.task0614;

import java.util.ArrayList;

/* 
Статические коты
*/

public class Cat {
    //напишите тут ваш код
    public static ArrayList<Cat> cats;

    public Cat() {
            Cat.cats.add(this);
    }


    public static void main(String[] args) {
        //напишите тут ваш код
        Cat.cats = new ArrayList<Cat>();
        Cat c;
        for(int i=0; i<10; i++)
            Cat.cats.add(new Cat());

        printCats();
    }

    public static void printCats() {
        //напишите тут ваш код
        for (Cat c: Cat.cats) {
            System.out.println(c);

        }

    }
}
