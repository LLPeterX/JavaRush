package com.javarush.task.task22.task2203;

import java.util.Calendar;
import java.util.Locale;

/*
Между табуляциями
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException{
        if(string == null || string.length()==0) {
            throw new TooShortStringException();
        }
        try {
            int start = string.indexOf("\t");
            int end = string.indexOf("\t", start + 1);
            return string.substring(start + 1, end);
        } catch (Exception e) {
            throw  new TooShortStringException();
        }
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
        //System.out.println(getPartOfString("JavaRush - лучший\t сервис обучения Java."));
        //System.out.printf(Locale.ENGLISH,"%1$tH:%1$tM:%1$tS", Calendar.getInstance());
    }
}
