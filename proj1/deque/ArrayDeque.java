package deque;

import java.util.Iterator;

public class ArrayDeque <T> implements Deque<T> {
    private T[] items;
    private int size;
    private int capacity = 8;
    public int frontIndex = 4;
    public int backIndex = 5;

    // Creates an empty list
    public ArrayDeque(){
        items = (T []) new Object[capacity];
        size = 0;
    }

    private void resize(int capacity) {
        T[] a = (T []) new Object[capacity];
        System.arraycopy(items,frontIndex+1,  a, 0, size-frontIndex-1);
        System.arraycopy(items,0,  a, size-frontIndex-1, frontIndex+1);
        items = a;
        frontIndex = capacity - 1;
        backIndex = size;
        this.capacity = capacity;
    }

    private int updateIndex(int index) {
        if (index == capacity) {
            index = 0;
        }
        else if (index == -1){
            index = capacity - 1;
        }
        return index;
    }

    private int updateBackIndex(int backIndex) {
        if (backIndex == capacity) {
            backIndex = 0;
        }
        else if (backIndex == -1){
            backIndex = capacity - 1;
        }
        return backIndex;
    }

    public void addFirst(T elem){
        if (size == capacity) {
            resize(capacity * 2);
        }
        items[frontIndex] = elem;
        size += 1;
        frontIndex = updateIndex(frontIndex -1);
        return;
    }

    public void addLast(T elem){
        if (size == capacity) {
            resize(capacity * 2);
        }
        items[backIndex] = elem;
        size += 1;
        backIndex = updateIndex(backIndex + 1);
    }


    public T get(int i) {
        int getIndex = frontIndex+i+1;
        if (getIndex >= capacity) {
            getIndex = getIndex-capacity;
        }
        return items[getIndex];
    }

    public T getFirst() {
        return get(0);
    }

    public T getLast() {
        return get(size-1);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public T removeFirst(){
        if (size == 0){
            System.out.println("Nothing happened...list is already empty");
            return null;
        }
        else {
            T removedElem = getFirst();
            frontIndex = updateIndex(frontIndex + 1);
            items[frontIndex] = null;
            size -= 1;
            return removedElem;
        }

    }

    public T removeLast(){
        if (size == 0){
            System.out.println("Nothing happened...list is already empty");
            return null;
        }
        T removedElem = getLast();
        backIndex = updateIndex(backIndex -1);
        items[backIndex] = null;
        size -= 1;
        return removedElem;
    }

    public void printDeque(){

    }

    public Iterator<T> iterator(){
        return null;
    }
}
