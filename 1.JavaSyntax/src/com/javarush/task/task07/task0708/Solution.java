package com.javarush.task.task07.task0708;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Самая длинная строка
*/

public class Solution {
    private static ArrayList<String> strings;

    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        strings = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // 1. Создай список строк.
        // 2. Считай с клавиатуры 5 строк и добавь в список.
        for (int i = 0; i < 5; i++) {
            strings.add(reader.readLine());
        }
        // 3. Используя цикл, найди самую длинную строку в списке.
        int m = 0;
        for (String s : strings)
            if (s.length() > m)
                m = s.length();
        // 4. Выведи найденную строку на экран.
        // 5. Если таких строк несколько, выведи каждую с новой строки.
        for (String s : strings)
            if (s.length() == m)
                System.out.println(s);
    }

}
