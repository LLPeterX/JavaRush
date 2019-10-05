package com.javarush.task.task26.task2613;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulatorFactory {
    private CurrencyManipulatorFactory instance = null;
    private static Map<String, CurrencyManipulator> map = new HashMap<>(); // код валюты => манипулятор


    // создаем новый манипулятор или возвразаем уже существующий
    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        String codeUppercase = currencyCode.toUpperCase();
        if(map.containsKey(codeUppercase)) {
            return map.get(codeUppercase);
        } else {
            CurrencyManipulator manipulator = new CurrencyManipulator(currencyCode.toUpperCase());
            map.put(codeUppercase,manipulator);
            return manipulator;
        }
    }

    // приватный конструкор - т.к. будем юзать getInstance()
    private CurrencyManipulatorFactory() {}

    public CurrencyManipulatorFactory getInstance() {
        if(instance == null) {
            return new CurrencyManipulatorFactory();
        } else return instance;
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        return map.values();
    }
}
