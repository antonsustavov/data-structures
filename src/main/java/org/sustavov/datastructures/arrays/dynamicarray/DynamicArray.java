package org.sustavov.datastructures.arrays.dynamicarray;

import java.util.*;
import java.util.function.Consumer;

@SuppressWarnings("unchecked")
public class DynamicArray<T> implements Iterable<T> {
    private T[] arr;
    private int length = 0;
    private int capacity = 0;

    public DynamicArray() {
        arr = (T[]) new Object[16];
    }

    public DynamicArray(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Illegal capacity " + capacity);
        this.capacity = capacity;
        arr = (T[]) new Object[capacity];
    }

    public int size() {
        return length;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T get(int position) {
        return arr[position];
    }

    public void set(int index, T element) {
        arr[index] = element;
    }

    public void clear() {
        for (int i = 0; i < capacity; i++) {
            arr[i] = null;
        }
        length = 0;
    }

    public boolean add(T value) {
        if (length+1 >= capacity) {
            arr = grow();
        }
        arr[length++] = value;
        return true;
    }

    public T removeAt(int index) {
        if (index > length || index < 0 ) throw new IndexOutOfBoundsException("");
        T data = arr[index];
        if (index+1 == length) {
            arr[index] = null;
        } else {
            System.arraycopy(arr, index+1, arr, index, length-index-1);
        }
        return data;
    }

    public boolean remove(Object obj) {
        for (int i = 0; i < length; i++) {
            if (arr[i].equals(obj)) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    public int indexOf(Object obj) {
        for (int i = 0; i < length; i++) {
            if (arr[i].equals(obj)) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                return index < length;
            }

            @Override
            public T next() {
                return arr[index++];
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {

    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }

    private T[] grow() {
        capacity = capacity*2;
        T[] newArr = (T[]) new Object[capacity];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        return newArr;
    }

    public static void main(String[] args) {
        DynamicArray<Integer> integers = new DynamicArray<>(3);
        integers.add(34);
        integers.add(35);
        integers.add(36);
        integers.add(37);
        integers.add(38);
        integers.add(39);
        integers.removeAt(1);
        System.out.println(integers.indexOf(38));
        System.out.println(integers.contains(39));
        for (Integer i :
                integers) {
            System.out.println(i);
        }
    }
}
