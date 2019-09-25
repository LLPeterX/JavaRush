package com.javarush.task.task22.task2209;

import java.io.*;
import java.util.*;

/*
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //...
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String fileName = console.readLine();
        console.close();
//        BufferedReader input = new BufferedReader(new FileReader(fileName));
//        List<String> words = new ArrayList<>();
//        String str;
//        while((str=input.readLine())!=null) {
//            String[] w = str.split(" ");
//            for(String w1 : w)
//                words.add(w1);
//        }
        FileInputStream input = new FileInputStream(fileName);
        byte[] bytes = new byte[input.available()];
        input.read(bytes);
        input.close();
        String fileContent = new String(bytes).replaceAll("\n|\r","").trim();
        String[] words = fileContent.split(" ");

        StringBuilder result = getLine(words);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        StringBuilder result = new StringBuilder();
        if(words==null || words.length==0) return result;
        String[] strCcopy = words.clone();
        // не работает!!!
        List<String> tmpList = Arrays.asList(strCcopy);
        Map<Integer,String> countMap = new HashMap<>();
        int maxLen=0;
        for(int pass = 0; pass < 5000; pass++) {
            char lastChar = tmpList.get(0).toUpperCase().charAt(tmpList.get(0).length()-1);
            String foundString="";
            int count=0;
            for(int i=1; i<strCcopy.length; i++) {
                char firstChar = tmpList.get(i).toUpperCase().charAt(0);
                if(lastChar == firstChar) {
                    count++;
                    if(i==1)
                        foundString = tmpList.get(0)+" ";
                    foundString = foundString + tmpList.get(i) + " ";
                    countMap.put(count, foundString.trim());
                    //}
                } else {
                    count=0;
                    foundString="";
                }
                lastChar = tmpList.get(i).toUpperCase().charAt(tmpList.get(i).length()-1);
            }
            Collections.shuffle(tmpList);
        } // pass
        // собираем в StringBuilder
        int maxlen=0;
        for(Map.Entry<Integer,String> e : countMap.entrySet()) {
            if(e.getKey() > maxLen) maxLen = e.getKey();
        }
        String maxStr = countMap.get(maxLen);
        result.append(maxStr);
        // добавляем оставшееся - слова из strCopy, которых нет в maxStr
        List<String> foundWords = Arrays.asList(maxStr.split(" "));
        for(String w : strCcopy) {
            if(!foundWords.contains(w)) {
                result.append(" ");
                result.append(w);
            }
        }
        return result;
    }
}
