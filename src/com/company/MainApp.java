package com.company;

import com.company.map.MyHashMap;

public class MainApp {

    public static void main(String[] args) {
        MyHashMap<Integer, Long> hashMap = new MyHashMap<>();
        long value = 100;
        for (int key = 1; key < 20; key++) {
            hashMap.put(key, value);
            value++;
        }
        hashMap.put(null, 500L);
        System.out.println(hashMap.get(1)); // 100
        System.out.println(hashMap.get(2)); // 101
        System.out.println(hashMap.get(null));  // 500
        //System.out.println(hashMap.get("-1")); // The value with key -1 is not found.
        System.out.println(hashMap.get(3)); // 102
        System.out.println(hashMap.get(4)); // 103
        System.out.println(hashMap.get(5)); // 104
        System.out.println(hashMap.get(6)); // 105
        System.out.println(hashMap.get(18)); // 117
    }
}
