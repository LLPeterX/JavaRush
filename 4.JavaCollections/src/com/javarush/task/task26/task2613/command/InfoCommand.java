package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

class InfoCommand implements Command {
    @Override
    public void execute() {
        boolean money = false;
        for (CurrencyManipulator manipulator : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
            if (manipulator.hasMoney()) {
                if (manipulator.getTotalAmount() > 0) {
                    ConsoleHelper.writeMessage(manipulator.getCurrencyCode() + " - " + manipulator.getTotalAmount());
                    money = true;
                }
            }
        }
        if (!money) { // если хотя бы у одного манипулятора есть деньги.
            ConsoleHelper.writeMessage("No money available.");
        }
    }
}
