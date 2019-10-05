package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.command.CommandExecutor;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import java.util.Locale;

public class CashMachine {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        try {
            // сначала выполнить LOGIN, а потом остальной цикл
            CommandExecutor.execute(Operation.LOGIN);
            Operation op;
            do {
                op = ConsoleHelper.askOperation();
                CommandExecutor.execute(op);
            } while (op != Operation.EXIT);
        } catch (InterruptOperationException e) {
            ConsoleHelper.writeMessage("Пока!");
        }
    }
}
