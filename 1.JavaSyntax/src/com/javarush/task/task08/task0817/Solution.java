package com.javarush.task.task08.task0817;

import java.util.HashMap;
import java.util.Map;

/* 
Нам повторы не нужны
*/

public class Solution {
    public static Map<String, String> createMap() {
        //напишите тут ваш код
       Map<String, String> map = new HashMap<String, String>();
       map.put("Лебедев","Петр");
       map.put("Иванов","Иван");
       map.put("Петров","Иван");
       map.put("Сидоров","Иван");
       map.put("Григин","Иван");
       map.put("Мишин","Иван");
       map.put("Яковлев","Иван");
       map.put("Обваленичев","Иван");
       map.put("Антонов","Гоги");
       map.put("Лялюшкин","Борис");
       return map;
    }

    public static void removeTheFirstNameDuplicates(Map<String, String> map) {
        //напишите тут ваш код
        Map<String, Integer> names = new HashMap<String, Integer>();
        for(String s : map.values()) {
            if(names.get(s)==null)
               names.put(s,1);
            else
              names.put(s,names.get(s)+1);
        }
        //System.out.println(names);
        for(Map.Entry<String,Integer> entry : names.entrySet()) {
            if(entry.getValue()>1) {
                //System.out.println("removing "+entry.getKey());
                removeItemFromMapByValue(map, entry.getKey());
            }
        }

    }

    public static void removeItemFromMapByValue(Map<String, String> map, String value) {
        Map<String, String> copy = new HashMap<>(map);
        for (Map.Entry<String, String> pair : copy.entrySet()) {
            if (pair.getValue().equals(value)) {
                map.remove(pair.getKey());
            }
        }
    }

    public static void main(String[] args) {
        Map<String, String> map = createMap();
        removeTheFirstNameDuplicates(map);
        //System.out.println(map.toString());
    }
}
