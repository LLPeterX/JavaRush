package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

public class CachingProxyRetriever implements Retriever {
    private LRUCache cache;
    private OriginalRetriever retriever;
    Storage storage;

    public CachingProxyRetriever(Storage storage) {
        this.storage = storage;
        retriever = new OriginalRetriever(storage);
        cache = new LRUCache(10);
    }

    @Override
    public Object retrieve(long id) {
        Object obj = cache.find(id);
        if(obj == null) {
            obj = retriever.retrieve(id);
            cache.set(id,obj);
        }
        return obj;
    }
}
