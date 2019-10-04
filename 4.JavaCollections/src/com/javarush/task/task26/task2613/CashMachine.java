package com.javarush.task.task26.task2613;

import java.util.Locale;

public class CashMachine {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        String kval = ConsoleHelper.askCurrencyCode();
        String[] valuta = ConsoleHelper.getValidTwoDigits(kval);
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(kval);
        manipulator.addAmount(Integer.parseInt(valuta[0]), Integer.parseInt(valuta[1]));
    }
}
