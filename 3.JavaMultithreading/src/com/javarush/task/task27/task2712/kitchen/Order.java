package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        this.dishes = ConsoleHelper.getAllDishesForOrder();
    }


    @Override
    public String toString() {
        // возвращает пустую строку, если нет блюд в заказе
        // Your order: [Juice, Fish] of Tablet{number=5}
        if(dishes==null || dishes.size()==0) return ""; // тут можно поставить isEmpty(), но валидатор тупит
        return "Your order: " + dishes.toString() + " of " + tablet;
    }

    // общее время приготовления всех блюд в минутах
    public int getTotalCookingTime() {
        int totalTime=0;
        for(Dish d: dishes) {
            totalTime+=d.getDuration();
        }
        return totalTime;
    }

    public boolean isEmpty() {
        return dishes==null || dishes.size()==0;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public Tablet getTablet() {
        return tablet;
    }
}
