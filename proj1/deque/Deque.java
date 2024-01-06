package deque;

import java.util.Iterator;
import java.util.Objects;

public interface Deque<T> {
    void addFirst(T elem);
    void addLast(T elem);
    default boolean isEmpty() {
        return size() == 0;
    };
    int size();
    void printDeque();
    T removeFirst();
    T removeLast();
    T get(int index);
    Iterator<T> iterator();
    default boolean equals(Deque other) {
        if (this == other) {
            return true;
        }
        if (other.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < size(); i += 1) {
            T item1 = this.get(i);
            Object item2 = other.get(i);
            if (!Objects.equals(item1, item2)) {
                return false;
            }
        }
        return true;
    }
}
