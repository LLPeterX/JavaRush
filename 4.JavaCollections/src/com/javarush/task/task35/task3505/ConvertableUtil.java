package com.javarush.task.task35.task3505;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Расставьте в методе ConvertableUtil.convert дженерик типы
public class ConvertableUtil {

    // д.б. (K,V) где K = эл-ты list, V = эл-ты getKey()
    public static <K,V extends Convertable> Map<K,V> convert(List<? extends Convertable> list) { // list оьбъекты user
        Map result = new HashMap();
        for(int i=0; i<list.size(); i++) {
            // в list - объекты user, они наследуются от Convertable, а утого есть getKey()
            K key = (K)list.get(i).getKey(); //
            V value = (V) list.get(i); // значения
            result.put(key,value);
        }
        return result;
    }
}
