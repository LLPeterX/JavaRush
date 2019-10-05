package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

public class LoginCommand implements Command {
    private String cardNumber = "123456789012";
    private String cardPIN = "1234";


    @Override
    public void execute() throws InterruptOperationException {
        // запросить N карты и пин
        while(true) {
            ConsoleHelper.writeMessage("Введите N карты и пин:");
            //String cardStr = ConsoleHelper.readString();
            //String[] cardInfo = cardStr.split(" ");
            // это пиздец, товарищи. Компонеты, оказывается, надо вводить в рахных строках :-()
            String inputCardNumber =  ConsoleHelper.readString();
            String inputPIN = ConsoleHelper.readString();
            if(inputCardNumber.matches("\\d{12}") && inputPIN.matches("\\d{4}")) {
                if(inputCardNumber.equals(cardNumber) && inputPIN.equals(cardPIN)) {
                    ConsoleHelper.writeMessage("Верификация прошла успешно");
                    break;
                } else {
                    ConsoleHelper.writeMessage("Данные невалидные");
                }
//            } else {
//                ConsoleHelper.writeMessage("Неверные данные");
            }
        }

    }
}
