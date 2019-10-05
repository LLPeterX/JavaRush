package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Locale;

class DepositCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        Locale.setDefault(Locale.ENGLISH);
        String kval = ConsoleHelper.askCurrencyCode();
        String[] valuta = ConsoleHelper.getValidTwoDigits(kval);
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(kval);
        manipulator.addAmount(Integer.parseInt(valuta[0]), Integer.parseInt(valuta[1]));
    }
}
