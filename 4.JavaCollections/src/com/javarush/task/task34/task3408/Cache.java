package com.javarush.task.task34.task3408;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;
// Класс Cache - универсальный параметризированный класс для кеширования объектов.
//Он работает с классами (дженерик тип Т), у которых обязан быть:
//   а) публичный конструктор с одним параметром типа K;
//   б) метод K getKey() с любым модификатором доступа.
public class Cache<K, V> {
    // Map<K, V> cache должен хранить ключи, на которые есть активные ссылки.
    private Map<K, V> cache = new WeakHashMap<K,V>();   //TODO add your code here

    // 2.1. Верни объект из cache для ключа key.
    //2.2. Если объекта не существует в кэше, то добавьте в кэш новый экземпляр используя рефлексию, см. пункт а).
    public V getByKey(K key, Class<V> clazz) throws Exception {
        //TODO add your code here
        //return null;
        if(cache.containsKey(key))
            return cache.get(key);
        // Если объекта не существует в кэше, то добавьте в кэш новый экземпляр используя рефлексию, см. пункт а).
        // используем ClassBuiler для построения класс и объекта
        Class keyClass = key.getClass();
        V obj = clazz.getConstructor(keyClass).newInstance(key);
        cache.put(key, obj);
        return obj;
    }

    public boolean put(V obj) {
        //TODO add your code here
        //3.1. Используя рефлексию получи ссылку на метод, описанный в пункте б).
        //3.2. Используя рефлексию разреши к нему доступ.
        //3.3. Используя рефлексию вызови метод getKey у объекта obj, таким образом ты получишь ключ key.
        //3.4. Добавь в кэш пару <key, obj>.
        //3.5. Верни true, если метод отработал корректно, false в противном случае. Исключения игнорируй.
        K key;
        boolean result = true;
        try {
            Method method = obj.getClass().getDeclaredMethod("getKey");
            method.setAccessible(true);
            key = (K) method.invoke(obj); // вывзваем метод getKey() объекта obj
            cache.put(key,obj);
            result = true;
        } catch (Exception e ) {
            result=false;
        }
        return result;
    }

    public int size() {
        return cache.size();
    }
}
