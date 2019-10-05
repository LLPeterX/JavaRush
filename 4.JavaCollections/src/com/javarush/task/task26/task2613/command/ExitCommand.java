package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

class ExitCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage("Вы действительно хотите выйти (y/n):");
        String answer = ConsoleHelper.readString().toLowerCase();
        switch (answer) {
            case "y":
                ConsoleHelper.writeMessage("До свидания!");
                return;
            case "n":
                return;
            case "exit":
                throw new InterruptOperationException();
        }

    }
}
