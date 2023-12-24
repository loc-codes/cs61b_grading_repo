package deque;

import java.util.Iterator;

public interface Deque<T> {
    public void addFirst(T elem);
    public void addLast(T elem);
    public boolean isEmpty();
    public int size();
    public void printDeque();
    public T removeFirst();
    public T removeLast();
    public T get(int index);
    public Iterator<T> iterator();
    public boolean equals(Object o);
}
