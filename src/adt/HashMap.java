package adt;

import java.io.Serializable;

public class HashMap<K, V> implements MapInterface<K, V>, Serializable {

    private static final int DEFAULT_CAPACITY = 16;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private Entry<K, V>[] table;
    private int size;
    private int capacity;
    private double loadFactor;

    public HashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HashMap(int initialCapacity, double loadFactor) {
        this.capacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.table = new Entry[capacity];
        this.size = 0;
    }

    @Override
    public V put(K key, V value) {
        ensureCapacity();
        int index = calculateIndex(key);
        Entry<K, V> newEntry = new Entry<>(key, value);

        if (table[index] == null) {
            table[index] = newEntry;
            size++;
        } else {
            Entry<K, V> current = table[index];
            while (current.getNext() != null) {
                if (current.getKey().equals(key)) {
                    V oldValue = current.getValue();
                    current.setValue(value);
                    return oldValue;
                }
                current = current.getNext();
            }
            current.setNext(newEntry);
            size++;
        }
        return null;
    }

    @Override
    public V get(K key) {
        int index = calculateIndex(key);
        Entry<K, V> current = table[index];

        while (current != null) {
            if (current.getKey().equals(key)) {
                return current.getValue();
            }
            current = current.getNext();
        }

        return null;
    }

    @Override
    public V remove(K key) {
        int index = calculateIndex(key);
        Entry<K, V> current = table[index];
        Entry<K, V> previous = null;

        while (current != null) {
            if (current.getKey().equals(key)) {
                if (previous == null) {
                    table[index] = current.getNext();
                } else {
                    previous.setNext(current.getNext());
                }
                size--;
                return current.getValue();
            }
            previous = current;
            current = current.getNext();
        }

        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int index = calculateIndex(key);
        Entry<K, V> current = table[index];

        while (current != null) {
            if (current.getKey().equals(key)) {
                return true;
            }
            current = current.getNext();
        }

        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void ensureCapacity() {
        if ((double) size / capacity > loadFactor) {
            capacity *= 2;
            Entry<K, V>[] newTable = new Entry[capacity];
            transferEntries(newTable);
            table = newTable;
        }
    }

    private void transferEntries(Entry<K, V>[] newTable) {
        for (Entry<K, V> entry : table) {
            while (entry != null) {
                int index = calculateIndex(entry.getKey(), newTable.length);
                Entry<K, V> temp = entry.getNext();
                entry.setNext(newTable[index]);
                newTable[index] = entry;
                entry = temp;
            }
        }
    }

    private int calculateIndex(K key) {
        return calculateIndex(key, capacity);
    }

    private int calculateIndex(K key, int tableLength) {
        return Math.abs(key.hashCode()) % tableLength;
    }

    public static class Entry<K, V> implements KeyValuePair<K, V> {

        private final K key;
        private V value;
        private Entry<K, V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Entry<K, V> getNext() {
            return next;
        }

        public void setNext(Entry<K, V> next) {
            this.next = next;
        }
    }

    public LinkedList<KeyValuePair<K, V>> getAllEntries() {
        LinkedList<KeyValuePair<K, V>> entries = new LinkedList<>();

        for (Entry<K, V> entry : table) {
            while (entry != null) {
                entries.add(entry);
                entry = entry.getNext();
            }
        }

        return entries;
    }
    
    //author: Yong Choy Mun
    public LinkedList<V> getAllValue() {
        LinkedList<V> entries = new LinkedList<>();

        for (Entry<K, V> entry : table) {
            while (entry != null) {
                entries.add(entry.getValue());
                entry = entry.getNext();
            }
        }

        return entries;
    }
    
    //author: Yong Choy Mun
    public V getOrDefault(K key, V defaultValue){
        if(containsKey(key)){
            return get(key);
        }else{
            return defaultValue;
        }
    }

}
