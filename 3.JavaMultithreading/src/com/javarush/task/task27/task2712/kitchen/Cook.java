package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;

// повар "наблюдатель" и "наблюдаемый"
public class Cook extends Observable implements Observer {
    private String name; // имя повара

    // конструктор
    public Cook(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        //- observable - объект, который отправил нам значение
        //- arg - само значение, в нашем случае - это объект Order
        Order order = (Order)arg;
        ConsoleHelper.writeMessage("Start cooking - " + order + ", cooking time " + order.getTotalCookingTime() + "min");
        setChanged();
        notifyObservers(arg); // уведомляет waiter и пр.
        // регистрируем событие
        // String tabletName, String cookName, int cookingTimeSeconds, List<Dish> cookingDishs
        //new StatisticManager().register(new CookedOrderEventDataRow());
        StatisticManager statisticManager = StatisticManager.getInstance();
        // CookedOrderEventDataRow(String tabletName, String cookName, int cookingTimeSeconds, List<Dish> cookingDishs)
        CookedOrderEventDataRow data = new CookedOrderEventDataRow(order.getTablet().toString(), this.name,
                order.getTotalCookingTime(), order.getDishes());
        statisticManager.register(data);
    }

    @Override
    public String toString() { // вернуть имя повара
        return this.name;
    }
}
