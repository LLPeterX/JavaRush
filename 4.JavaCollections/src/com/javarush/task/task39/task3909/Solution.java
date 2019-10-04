package com.javarush.task.task39.task3909;

/* 
Одно изменение
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isOneEditAway("","a"));

    }

    // можно ли добавить, изменить, удалить символ в одной из строке, чтобы получить другую?
    public static boolean isOneEditAway(String first, String second) {
        if(first == null || second == null) return true;
        if(first.isEmpty() && second.isEmpty()) return true; // это же неправильно!
        if(first.equals(second)) return true;
        // Если обе строки одной длины, проверяем, что все сиволы, кроме одного, одинаковы
        if(first.length() == second.length()) {
            return (differences(first, second) == 1);
        }
        // в противном случае проверяем добавленние/удаление (разница д.б. ровно 1 символ)
        if(Math.abs(first.length()-second.length())>1) return false;
        // далее сложнее. символ м.б. внутри
        String shorter, longer;
        if(first.length()>second.length()) {
            shorter = second;
            longer = first;
        } else {
            shorter = first;
            longer = second;
        }
        // можно ли добавить/удалить символ?
        // Символ может вставлен/удален быть внутри (длинной строки)
        // цикл по shorter, втсавляя символ в i-ю позицию
        // Граничные условия - сначала или с конца
        if(differences(longer,shorter+"X")==1 || differences(longer,"X"+shorter)==1)
            return true;
        // иначе пробегаемся по
        for(int i=1; i<shorter.length(); i++) {
            String tmp = shorter.substring(0,i)+"X"+shorter.substring(i);
            if(differences(tmp,longer)==1) return true;
        }
        return false;
    }

    private static int differences(String str1, String str2) {
        int numberOfDifferences=0;
        for(int i=0; i<str1.length(); i++) {
            if(str1.charAt(i) != str2.charAt(i))
                numberOfDifferences++;
        }
        return numberOfDifferences;
    }
}
