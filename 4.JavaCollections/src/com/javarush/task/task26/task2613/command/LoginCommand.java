package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command {
    // ниже комменты - от Банкомат(11) - убрать
//    private String cardNumber = "123456789012";
//    private String cardPIN = "1234";
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.class.getPackage().getName()+".resources.verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName()+".resources.login_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            String inputCardNum = ConsoleHelper.readString();
            String inputCardPIN = ConsoleHelper.readString();
            if (validCreditCards.containsKey(inputCardNum)) {
                if (validCreditCards.getString(inputCardNum).equals(inputCardPIN)) {
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), inputCardNum));
                } else {
                    ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), inputCardNum));
                    ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                    continue;
                }
            } else {
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), inputCardNum));
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                continue;
            }

            break;
        }
    } // execute

    /*
    private boolean isCardValid(String cardNum, String cardPIN) {
        if(validCreditCards.containsKey(cardNum)) {
            return validCreditCards.getString(cardNum).equals(cardPIN);
        }
        return false;
    }

     */
}
