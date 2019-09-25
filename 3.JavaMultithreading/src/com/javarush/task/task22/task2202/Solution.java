package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
        System.out.println(getPartOfString("Амиго и Диего лучшие друзья!"));
    }

    public static String getPartOfString(String string) throws TooShortStringException {
//        if(string == null || string.length()==0)
//            throw new TooShortStringException();
//        try {
//            int start = string.indexOf(' ');
//            String[] words = string.split(" ");
//            int end = string.lastIndexOf(words[4]);
//            return string.substring(start+1,end+words[4].length()).trim();
//        } catch (StringIndexOutOfBoundsException e) {
//            throw new TooShortStringException();
//        } catch (ArrayIndexOutOfBoundsException e) {
//            throw new TooShortStringException();
//        }
        if (string == null || string.isEmpty()) throw new TooShortStringException();
        int firstSpaceIndex = string.indexOf(" ") + 1;
        char[] chars = string.toCharArray();
        int countSpace = 0;
        int lastSpaceIndex = 0; //
        for (int i = 0; i < string.length(); i++)
        {
            if (chars[i] == ' ')
            {
                countSpace++;
                if (countSpace == 4)
                {
                    lastSpaceIndex = string.length();
                } else if (countSpace == 5)
                {
                    lastSpaceIndex = i;
                    break;
                }
            }
        }
        if (countSpace < 4)
        {
            throw new TooShortStringException();
        }
        return string.substring(firstSpaceIndex, lastSpaceIndex);
    }

    public static class TooShortStringException extends RuntimeException {
    }


}
