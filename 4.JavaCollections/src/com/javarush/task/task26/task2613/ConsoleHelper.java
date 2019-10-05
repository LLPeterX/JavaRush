package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        try {
            String str = bis.readLine();
            if(str.equalsIgnoreCase("EXIT"))
                throw new InterruptOperationException();
            return str;
        } catch (IOException e) {
            return "";
        }
    }

    // ввод кода валюты
    public static String askCurrencyCode() throws InterruptOperationException {
        String codeval;
        while (true) {
            System.out.print("Введите код валюты: ");
            codeval = readString();
            if(codeval.length()==3) break;
            System.out.println("Данные некорретные");
        }
        return codeval.toUpperCase();
    }

    // считать номинал и число банкнот
    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        String inputStr;
        while (true) {
            System.out.print("Введите номинал и количество банкнот для "+currencyCode+": ");
            inputStr = readString();
            String[] parts = inputStr.split(" ");
            int nominal, count;
            try {
                if (parts.length != 2)
                    throw new IllegalArgumentException();
                nominal = Integer.parseInt(parts[ 0 ]);
                count = Integer.parseInt(parts[ 1 ]);
                if(nominal<0 || count<0)
                    throw new IllegalArgumentException();
                return parts;
            } catch (Exception e) {
                writeMessage("Неверные значения. Попробйте еще раз");
            }
        }
    }

    public static Operation askOperation() throws InterruptOperationException {
        String inputStr;
        while(true) {
            writeMessage("Введите код операции (1 - INFO, 2 - DEPOSIT, 3 - WITHDRAW, 4 - EXIT:");
            inputStr = readString();
            try {
                int opCode = Integer.parseInt(inputStr);
                if(opCode == 0) // для LOGIN надо бросить исключение
                    throw new IllegalArgumentException();
                return Operation.getAllowableOperationByOrdinal(opCode);
            } catch (Exception e) {
                writeMessage("Неверный ввод. Попробуйте еще раз");
            }
        }
    }

}
