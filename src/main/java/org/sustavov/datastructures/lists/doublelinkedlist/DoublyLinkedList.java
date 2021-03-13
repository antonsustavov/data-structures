package org.sustavov.datastructures.lists.doublelinkedlist;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Optional;

public class DoublyLinkedList<T> implements Iterable<T> {

    private int size;
    private Node<T> head;
    private Node<T> tail;

    private class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data.toString() +
                    '}';
        }
    }

    public void clear() {
        for (Node<T> cur = head; cur != null;) {
            Node<T> next = cur.next;
            cur.data = null;
            cur.next = null;
            cur.prev = null;
            cur = next;
        }
        head = tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void add(T element) {
        addLast(element);
    }

    private void addLast(T element) {
        if (isEmpty()) {
            head = tail = new Node<>(element, null, null);
        } else {
            tail.next = new Node<>(element, tail, null);
            tail = tail.next;
        }
        size++;
    }

    public void addFirst(T element) {
        if (isEmpty()) {
            head = tail = new Node<>(element, null, null);
        } else {
            head.prev = new Node<>(element, null, head);
            head = head.prev;
        }
        size++;
    }

    public T peekFirst() {
        if (isEmpty()) throw new RuntimeException("Empty list");
        return head.data;
    }

    public T peekLast() {
        if (isEmpty()) throw new RuntimeException("Empty list");
        return tail.data;
    }

    public T removeFirst() {
        if (isEmpty()) throw new RuntimeException("Empty list");
        T data = head.data;
        head = head.next;
        size--;
        if (isEmpty()) {
            tail = null;
        } else {
            head.prev = null;
        }
        return data;
    }

    public T removeLast() {
        if (isEmpty()) throw new RuntimeException("Empty list");
        T data = tail.data;
        tail = tail.prev;
        size--;
        if (isEmpty()) {
            head = null;
        } else {
            tail.next = null;
        }

        return data;
    }

    public T removeAt(int index) {
        if (index < 0 || index >= size) throw new IllegalArgumentException();

        Node<T> curr;
        int i;

        if (index < size / 2) {
            for (i = 0, curr = head; i != index; i++) {
                curr = curr.next;
            }
        } else {
            for (i = size - 1, curr = tail; i != index; i--) {
                curr = curr.prev;
            }
        }

        return remove(curr);
    }

    public boolean remove(T object) {
        Node<T> curr = head;
        if (object == null) {
            for (curr = head; curr != null; curr = curr.next) {
                if (curr.data == null) {
                    remove(curr);
                    return true;
                }
            }
        } else {
            for (curr = head; curr != null; curr = curr.next) {
                if (object.equals(curr.data)) {
                    remove(curr);
                    return true;
                }
            }
        }

        return false;
    }

    public int indexAt(T object) {
        int index = 0;
        Node<T> curr = head;
        if (object == null) {
            for (curr = head; curr != null; curr = curr.next, index++) {
                if (curr.data == null) {
                    return index;
                }
            }
        } else {
            for (curr = head; curr != null; curr = curr.next, index++) {
                if (object.equals(curr.data)) {
                    return index;
                }
            }
        }

        return -1;
    }

    public boolean contain(T object) {
        return indexAt(object) != -1;
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> curr = head;
            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public T next() {
                T data = curr.data;
                curr = curr.next;
                return data;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ ");
        Node<T> curr = head;
        while (curr != null) {
            stringBuilder.append(curr.data + ", ");
            curr = curr.next;
        }
        stringBuilder.append("[ ");
        return stringBuilder.toString();
    }

    private T remove(Node<T> node) {
        if (isEmpty()) {
            return null;
        }
        if (node.prev == null) return removeFirst();
        if (node.next == null) return removeLast();

        node.prev.next = node.next;
        node.next.prev = node.prev;

        T data = node.data;
        node.data = null;
        node.prev = node.next = null;

        size--;

        return data;
    }
}
