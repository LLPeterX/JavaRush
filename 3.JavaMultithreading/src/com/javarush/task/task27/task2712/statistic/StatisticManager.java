package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

// Нам нужно будет реализовать следующую функциональность:
//1. подсчет статистики
//2. отображение статистики директору
//
// Начнем с получения реальных данных - реальной статистики.
//
//Идея такая:
//Есть хранилище событий.
//Когда у нас случается событие, то мы будем регистрировать это событие в хранилище.
//На запрос директора мы будем фильтровать события из хранилища, вычислять необходимые данные и отображать их.
//
// С  помощью StatisticManager будем регистрировать события в хранилище
// это не статик-класс, а singleton
public class StatisticManager {
    private static StatisticManager instance = null;
    private StatisticStorage statisticStorage = new StatisticStorage();
    Set<Cook> cooks = new HashSet<>();

    private StatisticManager() {}

    public static StatisticManager getInstance() {
        if(instance == null)
            instance = new StatisticManager();
        return instance;
    }


    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }
    public void register(Cook cook) {
        cooks.add(cook);
    }
    // --------------------- sotorage ----------------------------------
    // хранилище д.б. приватным inner-классом, доступ к нему только у StatisticManager
    private static class StatisticStorage {
        private Map<EventType, List<EventDataRow>> storage = new TreeMap<>(Collections.reverseOrder());

        public StatisticStorage() {
            EventType[] eventTypes = EventType.values();
            for (int i = 0; i < eventTypes.length; i++) {
                storage.put(eventTypes[i], new ArrayList<EventDataRow>());
            }
        }

        private void put(EventDataRow data) {
            //storage.put(data.getType(), new ArrayList<EventDataRow>());
            storage.get(data.getType()).add(data);
        }

        public Map<EventType, List<EventDataRow>> getStorage() {
            return storage;
        }
    } // -------- StatisticStorage --------------

/*
    Давай подумаем что нужно сделать, чтобы директор мог посмотреть:
1. какую сумму заработали на рекламе, сгруппировать по дням;
2. загрузка (рабочее время) повара, сгруппировать по дням;
3. список активных роликов и оставшееся количество показов по каждому;
4. список неактивных роликов (с оставшемся количеством показов равным нулю).

Для каждого пункта добавим соответствующий метод в StatisticManager.
Директор будет вызывать метод, StatisticManager будет делать различные подсчеты.
*/

 // метод считает общую сумму прибыли за каждый день
//    2. В StatisticManager создай метод (придумать самостоятельно), который из хранилища достанет все данные,
//    относящиеся к отображению рекламы, и посчитает общую прибыль за каждый день.
 public Map<Date, Double> amountPerDay() {
     Map<Date, Double> result = new TreeMap<>(Collections.reverseOrder());
     List<EventDataRow> eventDataRowList = statisticStorage.getStorage().get(EventType.SELECTED_VIDEOS);
     double amount;
     Date date;
     Calendar calendar;
     for (EventDataRow eventDataRow : eventDataRowList) {
         VideoSelectedEventDataRow adVideo = (VideoSelectedEventDataRow) eventDataRow;
         amount = adVideo.getAmount()/100.0;
         calendar = Calendar.getInstance();
         calendar.setTime(adVideo.getDate());
         GregorianCalendar gc = new GregorianCalendar(
                 calendar.get(Calendar.YEAR),
                 calendar.get(Calendar.MONTH),
                 calendar.get(Calendar.DAY_OF_MONTH)
         );
         date = gc.getTime();
         if (result.containsKey(date))
             amount += result.get(date);
         result.put(date, amount);
     }
     return result;
 }

    public Map<Date, Map<String, Integer>> cookLoading() {
        Map<Date, Map<String, Integer>> tempCookingMap = new TreeMap<>();

        List<EventDataRow> tempEventList = statisticStorage.getStorage().get(EventType.COOKED_ORDER);

        for (EventDataRow eventDataRowOne : tempEventList) {
            CookedOrderEventDataRow cookedEvent = (CookedOrderEventDataRow) eventDataRowOne;
            Date date = cookedEvent.getDate();
            Map<String, Integer> cookMap = new TreeMap<>();

            for (EventDataRow eventDataRowTwo : tempEventList) {
                CookedOrderEventDataRow cookedOrderEv = (CookedOrderEventDataRow) eventDataRowTwo;
                String nameOfCook = cookedOrderEv.getCookName();
                int cookTime = cookedOrderEv.getTime();

                if (cookedEvent.getDate() == date) {
                    if (!cookMap.containsKey(nameOfCook)) {
                        cookMap.put(nameOfCook, cookTime);
                    } else {
                        int tempTime = cookMap.get(nameOfCook) + eventDataRowTwo.getTime();
                        cookMap.put(nameOfCook, tempTime);
                    }
                }
            }

            tempCookingMap.put(date, cookMap);
        }

        return tempCookingMap;
    }

}
