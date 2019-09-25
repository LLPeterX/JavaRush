package com.javarush.task.task22.task2212;

import java.sql.SQLOutput;

/*
Проверка номера телефона

Метод checkTelNumber должен проверять, является ли аргумент telNumber валидным номером телефона.

Критерии валидности:
1) если номер начинается с '+', то он содержит 12 цифр
2) если номер начинается с цифры или открывающей скобки, то он содержит 10 цифр
3) может содержать 0-2 знаков '-', которые не могут идти подряд
4) может содержать 1 пару скобок '(' и ')' , причем если она есть, то она расположена левее знаков '-'
5) скобки внутри содержат четко 3 цифры
6) номер не содержит букв
7) номер заканчивается на цифру

Примеры:
+380501234567 - true
+38(050)1234567 - true
+38050123-45-67 - true
050123-4567 - true
+38)050(1234567 - false
+38(050)1-23-45-6-7 - false
050ххх4567 - false
050123456 - false
(0)501234567 - false



*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        if(telNumber == null || telNumber.length()==0) return false;
        String tmp = telNumber.replaceAll("\\D","");
        if(telNumber.matches("^\\+") && tmp.length()!=12) return false;
        if(((telNumber.charAt(0)>='0' && telNumber.charAt(0)<='9')  || telNumber.charAt(0)=='(') && tmp.length()!=10) return false;
        String regex = "\\+?(\\d+)(\\(?\\d+\\)?)?\\d+(\\-\\d+){0,2}\\d+";
        boolean result = telNumber.matches(regex);

        return result;
    }

    public static void main(String[] args) {
        String[] checkPhones = {
                "+380501234567",
                "+38(050)1234567",
                "+38050123-45-67",
                "050123-4567",
                "+38)050(1234567",
                "+38(050)1-23-45-6-7",
                "050ххх4567",
                "050123456",
                "(0)501234567"
        };
        for(String phone : checkPhones) {
            System.out.println(phone+" "+checkTelNumber(phone));
        }
        //System.out.println(checkPhones[1]+" - "+checkTelNumber(checkPhones[1]));
        //System.out.println(checkPhones[7]+" - "+checkTelNumber(checkPhones[7]));

    }
}
