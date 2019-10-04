package com.javarush.task.task26.task2613;

import java.io.*;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        try {
            return bis.readLine();
        } catch (IOException e) {
            return "";
        }
    }

    // ввод кода валюты
    public static String askCurrencyCode() {
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
    public static String[] getValidTwoDigits(String currencyCode) {
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

}
