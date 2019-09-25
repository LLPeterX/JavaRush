package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;

public class Restaurant {
    public static void main(String[] args) throws Exception {
        Waiter waiter = new Waiter(); // официант
        Tablet t1 = new Tablet(5); // планшет
        Cook cook = new Cook("Peter"); // повар
        // для повара добавляем наблюдателя - официанта. Он дает указание повару
        cook.addObserver(waiter);
        // для планшета добавляем наблюдателя - повара (?) - непонятно почему не waiter
        t1.addObserver(cook);
        t1.createOrder();
        t1.createOrder();
        t1.createOrder();
        // создаем директорский планшет и выводим данные статистики по всем иметодам
        DirectorTablet dt = new DirectorTablet();
        dt.printActiveVideoSet();
        dt.printAdvertisementProfit();
        dt.printArchivedVideoSet();
        dt.printCookWorkloading();


    }

}
