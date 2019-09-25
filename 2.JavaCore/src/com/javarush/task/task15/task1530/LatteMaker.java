package com.javarush.task.task15.task1530;

public class LatteMaker extends DrinkMaker {
    @Override
    void getRightCup() {
        System.out.println("Берем чашку для латте");
    }

    @Override
    void putIngredient() {
        System.out.println("Делаем кофе");
        //System.out.println("Заливаем молоком с пенкой");
    }

    @Override
    void pour() {
        //System.out.println("Делаем кофе");
        System.out.println("Заливаем молоком с пенкой");
    }
}
