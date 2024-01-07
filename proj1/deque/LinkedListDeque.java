package deque;

import java.util.Iterator;
import java.util.Objects;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class Node {
        private T item;
        private Node next;
        private Node prev;

        Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, sentinel, sentinel);
        size = 0;
    }

    private void initialiseList(T elem) {
        Node initialNode = new Node(elem, sentinel, sentinel);
        sentinel.next = initialNode;
        sentinel.prev = initialNode;
    }

    @Override
    public void addFirst(T elem) {
        if (size == 0) {
            initialiseList(elem);
        } else {
            Node newNode = new Node(elem, sentinel, sentinel.next);
            sentinel.next.prev = newNode;
            sentinel.next = newNode;
        }
        size += 1;
    }

    @Override
    public void addLast(T elem) {
        if (size == 0) {
            initialiseList(elem);
        } else {
            Node newNode = new Node(elem, sentinel.prev, sentinel);
            sentinel.prev = newNode;
            sentinel.prev.prev.next = newNode;
        }
        size += 1;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            System.out.println("Nothing happened...list is already empty");
            return null;
        } else {
            Node removedNode = sentinel.next;
            T removedElem = removedNode.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            removedNode.prev = null;
            removedNode.next = null;
            size -= 1;
            return removedElem;
        }

    }

    @Override
    public T removeLast() {
        if (size == 0) {
            System.out.println("Nothing happened...list is already empty");
            return null;
        } else {
            Node removedNode = sentinel.prev;
            T removedElem = removedNode.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            removedNode.prev = null;
            removedNode.next = null;
            size -= 1;
            return removedElem;
        }

    }


    @Override
    public T get(int index) {
        if (index >= size) {
            System.out.println("Cannot retrieve item! Index is larger than list");
            return null;
        }
        int counter = 0;
        Node currentNode = sentinel;
        while (counter <= index) {
            currentNode = currentNode.next;
            counter += 1;
        }
        return currentNode.item;
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(index, sentinel.next).item;
    }

    private Node getRecursiveHelper(int index, Node n) {
        if (index == 0) {
            return n;
        } else {
            return getRecursiveHelper(index - 1, n.next);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null) return false;

        Deque<?> otherDeque;
        try {
            otherDeque = (Deque<?>) other;
        } catch (ClassCastException e) {
            return false;
        }

        if (this.size() != otherDeque.size()) return false;
        for (int i = 0; i < this.size(); i++) {
            if (!Objects.equals(this.get(i), otherDeque.get(i))) return false;
        }
        return true;
    }

    @Override
    public void printDeque() {
        Node p = sentinel.next;
        while (p.item != null) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node currentNode = null;
        LinkedListIterator() {
            currentNode = sentinel.next;
        }
        public boolean hasNext() {
            if (currentNode == null) {
                return false;
            }
            return currentNode.item != null;
        }

        public T next() {
            Node returnNode = currentNode;
            currentNode = currentNode.next;
            return returnNode.item;
        }
    }
}
