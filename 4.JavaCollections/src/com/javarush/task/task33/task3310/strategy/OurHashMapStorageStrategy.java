package com.javarush.task.task33.task3310.strategy;

public class OurHashMapStorageStrategy implements StorageStrategy {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];
    private int size;
    private int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    private float loadFactor = DEFAULT_LOAD_FACTOR;


    // вспомогательные методы
    private int hash(Long k) {
        int h;
        return (k == null) ? 0 : (h = k.hashCode()) ^ (h >>> 16);
    }

    private int indexFor(int hash, int length) {
        return hash & (length-1);
    }

    private Entry getEntry(Long key) {
        //return table[this.hash(key)]; // ?
        int hash = (key == null) ? 0 : hash((long)key.hashCode() );
        int i = indexFor(hash,table.length );
        for (Entry e = table[i]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals( k ))))
                return e;
        }
        return null;
    }

    private void resize(int newCapacity) {
        // создаем новый массив и копируем в него через transfer() старые данные
        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        this.table=newTable;
        threshold = (int)(newCapacity * loadFactor);
    }

    private void transfer(Entry[] newTable) {
        for(int i=0; i<this.size; i++) {
            newTable[i] = this.table[i];
        }
    }

    // public Entry(int hash, Long key, String value, Entry next) {
    private void createEntry(int hash, Long key, String value, int bucketIndex) {
        Entry oldEntry = table[bucketIndex];
        Entry newEntry = new Entry(hash,key,value,oldEntry);
        table[bucketIndex] = newEntry;
        size++;
    }

    // код спизжен из JDK 1.7
    private void addEntry(int hash, Long key, String value, int bucketIndex) {
        //Entry e = table[bucketIndex];
        //table[bucketIndex] = new Entry(hash,key,value,e);
        createEntry(hash, key, value, bucketIndex);
        if(size > threshold)
            resize(2*table.length);
    }
// ----------- переопрпделения методов интерфейса --------------------------------
    @Override
    public boolean containsKey(Long key) {
        int hash = (key == null) ? 0 : hash((long) key.hashCode());
        int index = indexFor(hash, table.length); // индекс в таблице table
        for (Entry e = table[index]; e != null; e = e.next) {
            Long k;
            if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(String value) {
        for(Entry e : table) {
            if(e!=null && e.value.equals(value))
                return true;
        }
        return false;
    }

    @Override
    public void put(Long key, String value) {
        if(key == null) return;
        int hash = hash( (long) key.hashCode());
        int i = indexFor(hash,table.length);
        for (Entry e = table[i]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                e.value = value;
            }
        }
        size++;
        addEntry(hash, key, value, i);
    }

    @Override
    public Long getKey(String value) {
        for(Entry e : table) {
            if(e.value.equals(value))
                return e.key;
        }
        return -1L;
    }

    @Override
    public String getValue(Long key) {
        if(containsKey(key)) {
            for(Entry e : table) {
                if(e!=null && e.key == key)
                    return e.value;
            }
        }
//        if(key == null) return null;
//        int hash = hash(key);
//        int i = indexFor(hash,table.length);
//        for (Entry e = table[i]; e != null; e = e.next) {
//            Long k;
//            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
//                return e.value;
//            }
//        }
        return null;
    }

}
