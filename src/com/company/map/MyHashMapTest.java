package com.company.map;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MyHashMapTest {

    @Test
    public void testSize() {
        MyHashMap<Integer, Long> hashMap = new MyHashMap<>();
        assertThat(hashMap.size(), is(0));
        hashMap.put(1, 1L);
        assertThat(hashMap.size(), is(1));
        hashMap.put(2, 2L);
        assertThat(hashMap.size(), is(2));
    }

    @Test
    public void testIsEmpty() {
        MyHashMap<Integer, Long> hashMap = new MyHashMap<>();
        assertThat(hashMap.isEmpty(), is(true));
        hashMap.put(1, 1L);
        assertThat(hashMap.isEmpty(), is(false));
    }

    @Test
    public void testGet() {
        MyHashMap<Integer, Long> hashMap = new MyHashMap<>();
        hashMap.put(1, 1L);
        hashMap.put(2, 2L);
        assertThat(hashMap.get(1), is(1L));
        assertThat(hashMap.get(2), is(2L));
    }

    @Test
    public void testPut() {
        MyHashMap<Integer, Long> hashMap = new MyHashMap<>();
        hashMap.put(1, 1L);
        Assert.assertEquals(1L, (long) hashMap.get(1));
    }

}