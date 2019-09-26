package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) { // repeatCount - сколько значений может хранится по одному ключу
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        // должен возвращать количество значений в нашей коллекции (не количество ключей в map!)
        int size = 0;
        for(K key : map.keySet())
            size += map.get(key).size();
        return size;
    }

    @Override
    public V put(K key, V value) {
        //напишите тут ваш код
        // V put(K key, V value) - должен добавить элемент value по ключу key.
        // Если в мапе такой ключ уже есть, и количество значений по этому ключу меньше, чем repeatCount -
        // то добавь элемент value в конец листа в объекте map.
        // Если по такому ключу количество значений равняется repeatCount - то удали из листа в объекте map
        // элемент с индексом ноль, и добавь в конец листа value.
        // Метод должен возвращать значение последнего добавленного элемента по ключу key
        // (но не значение, которое мы сейчас добавляем). Если по ключу key значений еще нет - верни null.
        V result = null;
        if(map.containsKey(key)) {
            List<V> data = map.get(key);
            if(data.size()<repeatCount) {
                data.add(value);
            } else {
                data.remove(0);
                data.add(value);
            }
            if(map.size()>1)
                result = data.get(data.size()-2);

        } else {
            // map еще не содержит ничего
            List<V> data = new ArrayList<>();
            data.add(value);
            map.put(key,data);
        }
        return result;
    }

    @Override
    public V remove(Object key) {
        //3) V remove(Object key) - должен удалить элемент по ключу key.
        // Если по этому ключу хранится несколько элементов - должен удаляться элемент из листа с индексом ноль.
        // Если по какому-то ключу хранится лист размером ноль элементов - удали такую пару ключ : значение.
        // Метод должен возвращать элемент, который ты удалил. Если в мапе нет ключа key - верни null.
        V result = null;
        if(map.containsKey(key)) {
            List<V> data = map.get(key);
            if(data.size()>0) {
                result = data.remove(0);
                // если размер стал 0, то удалить всё из map
                if(data.size()==0)
                    map.remove(key);
            } else {
                map.remove(key);
            }
        }
        return result;
    }

    @Override
    public Set<K> keySet() {
        // должен вернуть сет всех ключей, которые есть в мапе map
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        // должен вернуть ArrayList<V> всех значений. Порядок значений в листе не имеет значения
        //return map.values();
        List<V> reasultList = new ArrayList<>();
        for(K key : map.keySet()) {
            for(V value : map.get(key)) {
                reasultList.add(value); // можно заменить на Collections.addAll(), но так понятнее (IMHO)
            }
        }
        return reasultList;
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        // под value подразумевается не значение в map (List), а значение в самом списке List
        for(K key : map.keySet()) {
            for(V v : map.get(key)) {
                if(v.equals(value))
                    return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}