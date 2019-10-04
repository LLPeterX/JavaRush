package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Уникальные подстроки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
        if(s == null || s.isEmpty())
            return 0;
        List<Character> charsInSubstring = new ArrayList<>();
        int maxlen=1;
        for(int i=0; i<s.length();i++) {
            charsInSubstring.clear();
            for (Character c : s.substring(i).toCharArray()) {
                if (charsInSubstring.contains(c)) {
                    maxlen = Math.max(maxlen, charsInSubstring.size());
                    charsInSubstring.clear();
                } else {
                    charsInSubstring.add(c);
                }
            }
            maxlen = Math.max(maxlen,charsInSubstring.size());
        }
        return maxlen;
    }
}
