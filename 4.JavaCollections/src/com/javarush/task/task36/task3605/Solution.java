package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/* 
Использование TreeSet

Первым параметром приходит имя файла: файл1.
файл1 содержит только буквы латинского алфавита, пробелы, знаки препинания, тире, символы перевода каретки.
Отсортируй буквы по алфавиту и выведи на экран первые 5 различных букв в одну строку без разделителей.
Если файл1 содержит менее 5 различных букв, то выведи их все.
Буквы различного регистра считаются одинаковыми.
Регистр выводимых букв не влияет на результат.
Закрой потоки.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        //String fileName = "c:/temp/test1.txt";
        Set<Character> set = new TreeSet<Character>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String str;
        while((str=reader.readLine())!=null) {
            for(Character c : str.toCharArray())
                if(Character.isLetter(c))
                    set.add(Character.toLowerCase(c));
        }
        reader.close();
        int maxChars = Math.min(set.size(),5);
        StringBuilder sb = new StringBuilder();
        Iterator<Character> iterator = set.iterator();
        int i=0;
        while(iterator.hasNext() && i++<maxChars)
            sb.append(iterator.next());

        System.out.println(sb.toString());
    }
}
