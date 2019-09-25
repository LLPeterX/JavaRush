package com.javarush.task.task34.task3413;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SoftCache {
    private Map<Long, SoftReference<AnyObject>> cacheMap = new ConcurrentHashMap<>();

    // Метод AnyObject get(Long key) должен возвращать объект типа AnyObject из мапы cacheMap по ключу key.
    // Если такого ключа в cacheMap нет - верни null.
    public AnyObject get(Long key) {
        SoftReference<AnyObject> softReference = cacheMap.get(key);
        //напишите тут ваш код
        if(cacheMap.containsKey(key))
            return cacheMap.get(key).get();
        return null;

    }

    // Метод AnyObject put(Long key, AnyObject value) должен добавлять в мапу пару key : value.
    // Метод должен вернуть null, если в cacheMap по такому ключу ранее не было значения.
    // Иначе (было значение) - верни предыдущее значение value по этому ключу.
    // Не забудь вызвать метод clear() у объекта типа SoftReference<AnyObject>.
    public AnyObject put(Long key, AnyObject value) {
        AnyObject object = null;
        if(cacheMap.containsKey(key)) {
            object = cacheMap.get(key).get();
            cacheMap.get(key).clear();
        }
        SoftReference<AnyObject> softReference = cacheMap.put(key, new SoftReference<>(value));
        return object;
    }

    // Метод AnyObject remove(Long key) должен удалить из мапы cacheMap пару key : value по ключу key.
    // Метод должен вернуть null, если в cacheMap по такому ключу ранее не было значения.
    // Иначе - верни предыдущее значение value по этому ключу.
    // Не забудь вызвать метод clear() у объекта типа SoftReference<AnyObject>
    public AnyObject remove(Long key) {
        if(!cacheMap.containsKey(key))
            return null;
        AnyObject object = cacheMap.get(key).get();
        SoftReference<AnyObject> softReference = cacheMap.remove(key);
        softReference.clear();
        return object;
    }
}