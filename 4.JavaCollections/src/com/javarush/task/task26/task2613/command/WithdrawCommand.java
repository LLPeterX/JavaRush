package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Map;

class WithdrawCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        // запросить код валюты и создать для нее манипулятор
        String kval = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(kval);
        // в цикле читаем сумму
        while(true) {
            ConsoleHelper.writeMessage("Введите сумму: ");
            try {
                String strSum = ConsoleHelper.readString();
                int sum = Integer.parseInt(strSum);
                if(!manipulator.isAmountAvailable(sum)) {
                    ConsoleHelper.writeMessage("Недостаточно денег. Повторите ввод");
                } else {
                    // выдать пользователю кол-во банкнот
                    Map<Integer, Integer> moneys = manipulator.withdrawAmount(sum);
                    // нарисовать их на экране
                    for(Map.Entry<Integer, Integer> entry : moneys.entrySet()) {
                        String output = String.format("\t%d - %d",entry.getKey(),entry.getValue());
                        ConsoleHelper.writeMessage(output);
                    }
                    ConsoleHelper.writeMessage("Успешная транзакция");
                    break;
                }

            } catch (NumberFormatException e ) {
                ConsoleHelper.writeMessage("Неверный ввод. Попробуйте снова.");
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage("Недостаточно денег в банкомате.");
            }
        }
    }
}
