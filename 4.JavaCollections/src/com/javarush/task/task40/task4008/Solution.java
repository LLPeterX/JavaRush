package com.javarush.task.task40.task4008;

/*
Работа с Java 8 DateTime API
*/

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class Solution {
    public static void main(String[] args) {
        //printDate("21.4.2014 15:56:45");
        printDate("9.10.2017 5:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        //напишите тут ваш код
        // сначала парсим dd.m.yyyy
        String[] dateTimeComponents = date.split(" "); // м.б. дата и время
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d.M.yyyy");
        DateTimeFormatter timeFormat= DateTimeFormatter.ofPattern("H:m:s");
        String timePart;
        boolean isDate = false;
        if(dateTimeComponents[0].matches("\\d+\\.\\d+\\.\\d+")) {
            // дата или дата+время
            LocalDate locDate = LocalDate.parse(dateTimeComponents[0],format);
            System.out.println("День: "+locDate.getDayOfMonth());
            System.out.println("День недели: "+(locDate.getDayOfWeek().ordinal()+1));
            System.out.println("День месяца: "+locDate.getDayOfMonth());
            System.out.println("День года: "+locDate.getDayOfYear());
            WeekFields weekFields = WeekFields.of(Locale.getDefault());
            int weekMonth = locDate.get(weekFields.weekOfMonth());
            System.out.println("Неделя месяца: "+weekMonth);
            int weekYear = locDate.get(weekFields.weekOfWeekBasedYear());
            System.out.println("Неделя года: "+weekYear);
            System.out.println("Месяц: "+(locDate.getMonth().ordinal()+1));
            System.out.println("Год: "+locDate.getYear());
            isDate = true;
        }
        // далее проверяем, есть ли время в data
        // data м.б. время или вторым элементом массива
        if(dateTimeComponents.length>1) {
            timePart = dateTimeComponents[1];
        } else {
            timePart = date;
        }
        if(!isDate || dateTimeComponents.length>1) {
            LocalTime locTime = LocalTime.parse(timePart, timeFormat);
            int hour = locTime.getHour();
            String ampm = (hour>=12) ? "PM" : "AM";
            int hourDay = (hour >= 12) ? hour-12 : hour;
            System.out.println("AM или PM: " + ampm);
            System.out.println("Часы: " + hourDay);
            // часы дня с учетом AM/PM
            System.out.println("Часы дня: " + hour);
            System.out.println("Минуты: " + locTime.getMinute());
            System.out.println("Секунды: " + locTime.getSecond());
        }

    }

}
