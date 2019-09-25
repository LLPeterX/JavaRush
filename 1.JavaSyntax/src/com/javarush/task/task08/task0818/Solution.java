package com.javarush.task.task08.task0818;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* 
Только для богачей
*/

public class Solution {
    public static Map<String, Integer> createMap() {
        //напишите тут ваш код
        Map<String, Integer> map = new HashMap<String,Integer>();
        map.put("Лебедев",1000);
        map.put("Сидоров",400);
        map.put("Плотникова",1100);
        map.put("Петров",200);
        map.put("Обваленичев",900);
        map.put("Яковлев",0);
        map.put("Иванов",350);
        map.put("Сергеев",450);
        map.put("Лошара",100);
        map.put("Тестов",100);
        return map;
    }

    public static void removeItemFromMap(Map<String, Integer> map) {
        //напишите тут ваш код
        Iterator<Map.Entry<String,Integer>> itr = map.entrySet().iterator();
/*
        for(Map.Entry<String,Integer> entry : map.entrySet())
          if(entry.getValue()<500)
             map.remove(entry.getKey());
 */
        while(itr.hasNext()) {
            Map.Entry<String, Integer> e = itr.next();
            if(e.getValue()<500)
                itr.remove();
        }
    }

    public static void main(String[] args) {
        Map<String,Integer> map = createMap();
        removeItemFromMap(map);
        //System.out.println(map.toString());
    }
}
