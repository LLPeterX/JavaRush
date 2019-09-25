package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.NoAvailableVideoEventDataRow;

import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet extends Observable {
    protected final int number;
    static java.util.logging.Logger logger = Logger.getLogger(Tablet.class.getName());

    public Tablet(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public Order  createOrder() {
        Order order = null;
        try {
            order = new Order(this);
            //4. Не забудь сразу после создания заказа и вывода информации о нем в консоль (найдите это место в коде) сделать следующее:
            //4.1. Установить флаг setChanged()
            //4.2. Отправить обсерверу заказ - notifyObservers(order);
            if(!order.isEmpty()) {
                ConsoleHelper.writeMessage(order.toString());
                setChanged();
                notifyObservers(order);
            }
            int time = order.getTotalCookingTime() * 60; // время в Dish в минутах, а параметр в секундах
            new AdvertisementManager(time).processVideos();
        } catch (NoVideoAvailableException e) {
            StatisticManager.getInstance().register(new NoAvailableVideoEventDataRow(order.getTotalCookingTime() * 60)); // пересчет в сек.
            logger.log(Level.INFO, "No video is available for the order " + order);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
        return order; // вернуть заказ или null
    }

    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }
}
