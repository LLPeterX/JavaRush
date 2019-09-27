package com.javarush.task.task33.task3310.strategy;

public class FileStorageStrategy implements StorageStrategy {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    //private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY]; // набор Path (файлов - элементов массива)
    static final long DEFAULT_BUCKET_SIZE_LIMIT = 10000L;
    private int size;
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    private long maxBucketSize;
    //private int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    //private float loadFactor = DEFAULT_LOAD_FACTOR;

    // конструктор. Инициализируем N пустых файлов
    public FileStorageStrategy() {
        for(int i=0; i< DEFAULT_INITIAL_CAPACITY; i++)
            table[i] = new FileBucket();
    }

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }

    // далее реализуем методы по аналогии с OurHashMapStorageStrategy
    private int hash(Long k) {
        int h;
        return (k == null) ? 0 : (h = k.hashCode()) ^ (h >>> 16);
    }
    private int indexFor(int hash, int length) {
        return hash & (length-1);
    }

    private Entry getEntry(Long key) {
        int hash = hash((long)key.hashCode());
        int i = indexFor(hash,table.length );
        if(table[i] != null) {
            Entry e = table[i].getEntry();
            while(e != null) {
                if(e.getKey() == key) {
                    return e;
                }
                e=e.next; // след.
            }
        }
        return null;
    }

    private void resize(int newCapacity) {
        // создаем новый массив и копируем в него через transfer() старые данные
        FileBucket[] newTable = new FileBucket[newCapacity];
        transfer(newTable);
        table = newTable;
    }

    // копирование одной table в другую.
    // Т.к. эл-ты суть файлы, то ненужные удаляем
    private void transfer(FileBucket[] newTable) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) continue;
            Entry entry = table[i].getEntry();
            while (entry != null) {
                Entry next = entry.next;
                int newIndex = indexFor(entry.hash, newTable.length);
                if (newTable[newIndex] == null) {
                    entry.next = null;
                    newTable[newIndex] = new FileBucket();
                }
                else {
                    entry.next = newTable[newIndex].getEntry();
                }
                newTable[newIndex].putEntry(entry);
                entry = next;
            }
            table[i].remove();
        }
    }

    private void addEntry(int hash, Long key, String value, int bucketIndex) {
        Entry entry = table[bucketIndex].getEntry();
        table[bucketIndex].putEntry(new Entry(hash, key, value, entry));
        size++;
        if (table[bucketIndex].getFileSize() > bucketSizeLimit)
            resize(2 * table.length); // как в условии
    }

    private void createEntry(int hash, Long key, String value, int bucketIndex) {
        table[bucketIndex] = new FileBucket();
        table[bucketIndex].putEntry(new Entry(hash, key, value, null));
        size++;
    }

    // ------------- переопределения методов интерфейса

    @Override
    public boolean containsKey(Long key) {
        return this.getEntry(key) == null;
    }

    @Override
    public boolean containsValue(String value) {
        for (int i = 0; i < this.table.length; i++) {
            if (table[i] == null) continue;
            Entry entry = table[i].getEntry();
            while (entry != null) {
                if (entry.value.equals(value))
                    return true;
                entry = entry.next;
            }
        }
        return false;
    }

    @Override
    public void put(Long key, String value) {
        if(key == null) return;
        int hash = hash( (long) key.hashCode());
        int i = indexFor(hash,table.length);
        if(table[i]!=null) {
            for (Entry e = table[i].getEntry(); e != null; e = e.next) {
                Object k;
                if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                    e.value = value;
                }
            }
            size++;
            addEntry(hash, key, value, i);
        } else {
            createEntry(hash, key, value, i);
        }
    }

    @Override
    public Long getKey(String value) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) continue;
            Entry entry = table[i].getEntry();
            while (entry != null) {
                if (entry.value.equals(value)) return entry.key;
                entry = entry.next;
            }
        }
        return null;
    }

    @Override
    public String getValue(Long key) {
        Entry entry = getEntry(key);
        if (entry != null) return entry.value;
        return null;
    }
}
