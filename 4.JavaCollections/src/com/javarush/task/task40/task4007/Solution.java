package com.javarush.task.task40.task4007;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        //напишите тут ваш код
        Calendar cal = Calendar.getInstance();
        // варианты:
        //   dd.m.yyyy HH:mm:ss
        //   dd.m.yyyy
        //   HH:mm:ss

        try {
            // сначала парсим dd.m.yyyy
            String[] dateTimeComponents = date.split(" "); // м.б. дата и время
            SimpleDateFormat format = new SimpleDateFormat("dd.M.yyyy");
            SimpleDateFormat timeFormat= new SimpleDateFormat("KK:mm:ss");
            String timePart;
            boolean isDate = false;
            if(dateTimeComponents[0].matches("\\d+\\.\\d+\\.\\d+")) {
                // дата или дата+время
                cal.setTime(format.parse(dateTimeComponents[0]));
                System.out.println("День: "+cal.get(Calendar.DAY_OF_MONTH));
                // DOW другой
                int dow = cal.get(Calendar.DAY_OF_WEEK);
                if(--dow == 0) dow = 7;
                System.out.println("День недели: "+dow);
                System.out.println("День месяца: "+cal.get(Calendar.DAY_OF_MONTH));
                System.out.println("День года: "+cal.get(Calendar.DAY_OF_YEAR));
                System.out.println("Неделя месяца: "+cal.get(Calendar.WEEK_OF_MONTH));
                System.out.println("Неделя года: "+cal.get(Calendar.WEEK_OF_YEAR));
                System.out.println("Месяц: "+(cal.get(Calendar.MONTH)+1));
                System.out.println("Год: "+cal.get(Calendar.YEAR));
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
                cal.setTime(timeFormat.parse(timePart));
                String ampm = cal.get(Calendar.AM_PM)==0 ? "AM" : "PM";
                System.out.println("AM или PM: " + ampm);
                System.out.println("Часы: " + cal.get(Calendar.HOUR));
                System.out.println("Часы дня: " + cal.get(Calendar.HOUR_OF_DAY));
                System.out.println("Минуты: " + cal.get(Calendar.MINUTE));
                System.out.println("Секунды: " + cal.get(Calendar.SECOND));
            }

    } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
