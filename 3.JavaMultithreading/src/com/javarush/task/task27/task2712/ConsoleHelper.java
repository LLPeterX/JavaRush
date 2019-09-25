package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return reader.readLine();
    }

    // считать с консоли список блюд
    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> dishes = new ArrayList<>();
        while (true) {
            // весвти список блюд
            writeMessage(Dish.allDishesToString());
            String bludo = readString();
            if(bludo.equals("exit")) break;
            boolean isSelected=false;
            for(Dish d : Dish.values()) {
                if(d.toString().equals(bludo)) {
                    dishes.add(d);
                    isSelected=true;
                }
            }
            if(!isSelected) {
                ConsoleHelper.writeMessage("Такого блюда нет");
            }
        }
        return dishes;
    }

}
