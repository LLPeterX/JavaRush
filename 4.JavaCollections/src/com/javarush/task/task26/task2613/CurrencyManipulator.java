package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

// класс для конкретной валюты
public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>(); // номинал - количество

    // конструктор
    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            denominations.put(denomination, denominations.get(denomination) + count);
        } else {
            denominations.put(denomination, count);
        }
    }

    public int getTotalAmount() {
        int totalAmount=0;
        for(Map.Entry<Integer, Integer> e : denominations.entrySet()) {
            totalAmount += e.getKey()*e.getValue();
        }
        return totalAmount;
    }

    public boolean hasMoney() {
        return getTotalAmount()>0;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return expectedAmount<=getTotalAmount();
    }

    // выдаь деньги
    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        // т.к. при DEPOSIT добавляли значения в любом порядке, надо получить
        // отсортированную мапу, причем по убыванию номинала
        Map<Integer, Integer> sorted = new TreeMap<Integer, Integer>(Collections.reverseOrder());
        sorted.putAll(denominations); // сортируем
        Map<Integer, Integer> result = new TreeMap<Integer, Integer>(Collections.reverseOrder());
        if(expectedAmount > getTotalAmount())
            throw new NotEnoughMoneyException();
        // проходимся циклом по банкнотам и уменьшаем expectedAmount на сумму выданных банкнот
        for(Map.Entry<Integer, Integer> entry : sorted.entrySet()) {
            int denom = entry.getKey();
            int count = entry.getValue();
            if(expectedAmount >= denom*count) {
                // хватает на всё
                expectedAmount -= denom*count;
                result.put(denom, count);
                sorted.put(denom, sorted.get(denom)-count);
            } else {
                // вычисляем кол-во нужных банкнот текущего номинала
                count = expectedAmount/denom;
                if(count>0) {
                    result.put(denom, count);
                    expectedAmount -= denom*count;
                    sorted.put(denom, sorted.get(denom)-count);
                }
            }
        }
        if(expectedAmount != 0) {
            throw new NotEnoughMoneyException();
        } else {
            // вычитаем деньги - просто заносим в denominations новые данные из sorted
            //denominations.replaceAll((BiFunction<? super Integer, ? super Integer, ? extends Integer>) sorted); // ебать IDEA код подставила!
            // Нет, как говоримл Ленин - "мы пойдем другим путем!"
            for(Map.Entry<Integer, Integer> entry : sorted.entrySet()) {
                denominations.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

}
