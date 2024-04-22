package adt;

public interface MapInterface<K, V> {

    V put(K key, V value);

    V get(K key);

    V remove(K key);

    boolean containsKey(K key);

    boolean isEmpty();

    int size();
    
    
    
}
