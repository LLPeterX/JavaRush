package com.javarush.task.task35.task3509;

import java.util.*;


/* 
Collections & Generics
Реализуй вспомогательныe методы в классе Solution, которые должны создавать соответствующую коллекцию
и помещать туда переданные объекты.
Методы newArrayList, newHashSet параметризируй типом T.
Метод newHashMap параметризируй типами К(ключ) и V(значение). Аргументы метода newHashMap должны принимать списки, в которых содержатся наследники типов K и V.
Возвращаемые коллекции должны быть такого же типа, что и переданные в метод объекты.

Подсказка: в методе newHashMap нужно проверить чтобы списки ключей и значений совпадали по размерам, в противном случае кинь IllegalArgumentException.


Требования:
1. Метод newArrayList должен быть параметризован типом Т.
2. Метод newArrayList должен возвращать ArrayList, который содержит переданные в метод объекты.
3. Метод newHashSet должен быть параметризован типом Т.
4. Метод newHashSet должен возвращать HashSet, который содержит переданные в метод объекты.
5. Метод newHashMap должен быть параметризован типом K и V.
6. Метод newHashMap должен возвращать HashMap, который содержит переданные в метод ключи и значения.
7. Метод newHashMap должен кидать IllegalArgumentException, если списки ключей и значений не совпадают по размеру.
*/
public class Solution {

    public static void main(String[] args) {
    }

    public static <T> ArrayList<T> newArrayList(T... elements) {
        //напишите тут ваш код
        //return null
        ArrayList list = new ArrayList<>();
        for(Object o : elements)
            list.add(o);
        return list;
        //ArrayList<T> list =(T<Arrays.asList(elements);
    }

    public static <T> HashSet<T> newHashSet(T... elements) {
        //напишите тут ваш код
        //return null
        HashSet set = new HashSet<>();
        for(Object o : elements)
            set.add(o);
        return set;
    }

    public static <K,V> HashMap<K,V> newHashMap(List<? extends K> keys, List<? extends V> values) {
        //напишите тут ваш код
        //return null;
        if(keys.size()!=values.size())
            throw new IllegalArgumentException();
        HashMap map = new HashMap();
        for(int i=0; i<keys.size(); i++)
            map.put(keys.get(i),values.get(i));
        return map;
    }
}
