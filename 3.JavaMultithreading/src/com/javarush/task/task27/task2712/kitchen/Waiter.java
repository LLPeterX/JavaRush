package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;

import java.util.Observable;
import java.util.Observer;

// класс официант
//
// Что мы имеем? Повар приготовил блюда, пора их уносить, а официант не знает об этом.
////Нужно уведомить официанта, что пора забирать заказ с кухни.
////Ситуация аналогична предыдущему заданию, поэтому снова будем использовать паттерн Observer.
// Waiter - наш наблюдатель. Он передает сообзение другим обхектам (Cook)
public class Waiter implements Observer {

    @Override
    public void update(Observable o, Object arg) {

        ConsoleHelper.writeMessage(arg + " was cooked by " + o);
    }
}
