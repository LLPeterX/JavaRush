package com.javarush.task.task26.task2613;

import java.util.Map;

// класс для конкретной валюты
public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations; // номинал - количество

    // конструктор
    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

}
