package com.company.map;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class MyHashMap<K, V> implements HashMap<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;
    private Node<K, V>[] nodes;
    private int countOfElem;

    @Override
    public void put(K key, V value) {
        if (isEmpty()) {
            nodes = new Node[DEFAULT_CAPACITY];
        }
        if (countOfElem >= nodes.length * LOAD_FACTOR) {
            increaseCapacity();
        }
        int index = generateIndex(key, nodes.length);
        Node<K, V> currentNode = nodes[index];
        Node<K, V> newNode = new Node(key, value);
        if (Objects.isNull(currentNode)) {
            nodes[index] = newNode;
            countOfElem++;
        } else {
            if (Objects.isNull(key) && Objects.nonNull(value) || currentNode.getKey().equals(key)) {
                nodes[index] = new Node(null, currentNode.getKey(), currentNode.getValue());
                countOfElem++;
            } else {
                throw new NoSuchElementException("The key " + key + " is not found.");
            }
            if (currentNode.hasNext()) {
                while (currentNode.hasNext()) {
                    if (currentNode.getKey().equals(key)) {
                        currentNode.setValue(value);
                    } else {
                        currentNode = currentNode.getNext();
                    }
                }
                nodes[index] = new Node(null, currentNode.getKey(), currentNode.getValue());
                countOfElem++;
            }
        }
    }

    @Override
    public V get(K key) {
        for (Node<K, V> node : nodes) {
            if (Objects.nonNull(node)) {
                while (node.hasNext()) {
                    if (node.getKey().equals(key)) {
                        return node.getValue();
                    } else {
                        node = node.getNext();
                    }
                }
                if (Objects.nonNull(key) && Objects.nonNull(node.getKey())
                        && node.getKey().equals(key) && !node.hasNext() || Objects.isNull(key)
                        && Objects.isNull(node.getKey()) && !node.hasNext()) {
                    return node.getValue();
                }
            }
        }
        throw new NoSuchElementException("The value with key " + key + " is not found.");
    }

    @Override
    public void increaseCapacity() {
        Node<K, V>[] newNode = new Node[newCapacity()];
        for (int i = 0; i < nodes.length; i++) {
            if (Objects.nonNull(nodes[i])) {
                K oldKey = nodes[i].getKey();
                int index = generateIndex(oldKey, newNode.length);
                if (Objects.isNull(newNode[index])) {
                    newNode[index] = nodes[i];
                } else {
                    K newKey = newNode[index].getKey();
                    while (Objects.nonNull(nodes[i].getNext())) {
                        if (Objects.isNull(newNode[index])) {
                            newNode[index] = nodes[i];
                            nodes[i] = nodes[i].getNext();
                        } else {
                            boolean hasNext = newNode[index].hasNext();
                            Node<K, V> temp = newNode[index].getNext();
                            while (hasNext) {
                                K nextKey = newNode[index].getNext().getKey();
                                if (newKey.equals(nextKey)) {
                                    newNode[index].getNext().setValue(newNode[index].getValue());
                                    hasNext = false;
                                } else {
                                    temp = temp.getNext();
                                }
                            }
                            temp = new Node(null, nodes[i].getKey(), nodes[i].getValue());
                        }
                        countOfElem++;
                    }
                }
            }
        }
        nodes = Arrays.copyOf(newNode, newCapacity());
    }

    private int newCapacity() {
        return nodes.length * 2;
    }

    @Override
    public boolean isEmpty() {
        return countOfElem == 0;
    }

    @Override
    public int size() {
        return countOfElem;
    }

    private int hash(K key) {
        return Objects.isNull(key) ? 0 : key.hashCode();
    }

    private int generateIndex(K key, int length) {
        return hash(key) % length;
    }

    private static class Node<K, V> {
        private Node<K, V> next;
        private K key;
        private V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        Node(Node<K, V> next, K key, V value) {
            this.next = next;
            this.key = key;
            this.value = value;
        }

        K getKey() {
            return key;
        }

        V getValue() {
            return value;
        }

        void setValue(V value) {
            this.value = value;
        }

        boolean hasNext() {
            return next != null;
        }

        Node<K, V> getNext() {
            return next;
        }
    }

}
