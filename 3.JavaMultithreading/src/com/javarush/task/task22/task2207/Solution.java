package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.SocketImplFactory;
import java.util.*;

/* 
Обращенные слова

В методе main с консоли считать имя файла, который содержит слова, разделенные пробелами.
Найти в тексте все пары слов, которые являются обращением друг друга. Добавить их в result.
Использовать StringBuilder.
Кодировка файла - UTF-8.

Пример содержимого файла
рот тор торт о
о тот тот тот

Вывод:
рот тор
о о
тот тот

*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String filename = reader.readLine();
            reader.close();
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(filename), "UTF8"));
            //BufferedReader fileReader = new BufferedReader(new FileReader(bufferedReader.readLine()));
            String str;
            //Map<String,String> map = new HashMap<>();
            List<String> words = new ArrayList<>();

            while (input.ready()) {
                words.addAll(Arrays.asList(input.readLine().split(" ")));
            }
//            while((str=input.readLine())!=null) {
//                String[] strWords = str.split(" ");
//                for(String s : strWords) {
//                    words.add(s);
//                    //StringBuilder sb = new StringBuilder(s);
//                    //map.put(s,sb.reverse().toString());
//                }
//            }
            input.close();
            //System.out.println(Arrays.toString(words.toArray()));
//            for(Map.Entry<String,String> e : map.entrySet()) {
//                System.out.println(" C:"+e.getKey()+" : "+e.getValue() );
//            }
//            // поиск совпадений
//            for(String key : map.keySet()) {
//                for(String v : map.values()) {
//                    if(key.equals(v)) {
//                        Pair p = new Pair();
//                        p.first = key;
//                        p.second = v;
//                        result.add(p);
//                    }
//                }
//            }
            for (int i = 0; i < words.size(); i++) {
                for (int j = 0; j < words.size(); ) {
                    if (words.get(j).equals(new StringBuilder(words.get(i)).reverse().toString()) && j != i) {
                        Pair pair = new Pair();
                        pair.first = words.get(j);
                        pair.second = words.get(i);
                        result.add(pair);
                        words.remove(j);
                        words.remove(i);
                        j = 0;
                    } else {
                        j++;
                    }
                }
            }
            // output result
            for(Pair p : result)
                System.out.println(p);

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public static class Pair {
        String first;
        String second;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                        first == null ? second :
                            second == null ? first :
                                first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
