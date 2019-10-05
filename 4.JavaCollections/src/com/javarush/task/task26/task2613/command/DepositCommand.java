package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

class DepositCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName()+".resources.deposit_en");

    @Override
    public void execute() throws InterruptOperationException {
        String kval = ConsoleHelper.askCurrencyCode(); // код валюты (USD, RUR...)
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(kval);
        String[] denomAndCount = ConsoleHelper.getValidTwoDigits(kval);
        try {
            int denom = Integer.parseInt(denomAndCount[0]);
            int count = Integer.parseInt(denomAndCount[1]);
            currencyManipulator.addAmount(denom, count);
            ConsoleHelper.writeMessage(String.format(res.getString("success.format"), denom * count, kval));
        } catch (NumberFormatException e) {
            ConsoleHelper.writeMessage(res.getString("invalid.data"));
        }
    }
}
