package com.company.map;

public interface HashMap<K, V> {

    void put(K key, V value);

    V get(K key);

    void increaseCapacity();

    boolean isEmpty();

    int size();

}