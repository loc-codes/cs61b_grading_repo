package deque;
import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private int size;
    private T[] items;
    private int capacity;
    private int frontIndex = 4;
    private int backIndex = 5;

    // Creates an empty list
    public ArrayDeque() {
        this.capacity = 8;
        items = (T []) new Object[capacity];
        size = 0;
    }


    private void resize(int newCapacity) {
        T[] resizedArray = (T []) new Object[newCapacity];
        // This variable accounts for "wrapping" of front index.
        // Eg: if front index = 8, capacity = 8, oneAfter will be 0 not 9
        int oneAfterFrontIndex = updateIndex(frontIndex + 1);
        //Handles resizing down of lists that don't wrap
        // eg: frontIndex = 17, backIndex = 49, capacity = 128.
        if (frontIndex + 1 == capacity && backIndex == size) {
            System.arraycopy(items, oneAfterFrontIndex, resizedArray, 0, size);
        } else if (backIndex - frontIndex == newCapacity) {
            System.arraycopy(items, oneAfterFrontIndex, resizedArray, 0, size);
        } else {
            //Handles up and down sizing of all other lists
            int midPoint = this.capacity - oneAfterFrontIndex;
            System.arraycopy(items, oneAfterFrontIndex, resizedArray, 0, midPoint);
            System.arraycopy(items, 0, resizedArray, midPoint, backIndex);
        }
        items = resizedArray;
        frontIndex = newCapacity - 1;
        backIndex = size;
        this.capacity = newCapacity;
    }

    private int updateIndex(int index) {
        if (index == capacity) {
            index = 0;
        } else if (index == -1) {
            index = capacity - 1;
        }
        return index;
    }

    @Override
    public void addFirst(T elem) {
        if (size == capacity) {
            resize(capacity * 2);
        }
        items[frontIndex] = elem;
        size += 1;
        frontIndex = updateIndex(frontIndex - 1);
    }

    @Override
    public void addLast(T elem) {
        if (size == capacity) {
            resize(capacity * 2);
        }
        items[backIndex] = elem;
        size += 1;
        backIndex = updateIndex(backIndex + 1);
    }


    @Override
    public T get(int i) {
        int getIndex = frontIndex + i + 1;
        if (getIndex >= capacity) {
            getIndex = getIndex - capacity;
        }
        return items[getIndex];
    }

//    public T getFirst() {
//        return get(0);
//    }
//    public T getLast() {
//        return get(size - 1);
//    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else if ((size < items.length / 4) && (items.length >= 16)) {
            resize(items.length / 4);
        }

        T removedElem = get(0);
        frontIndex = updateIndex(frontIndex + 1);
        items[frontIndex] = null;
        size -= 1;
        return removedElem;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        } else if ((size < items.length / 4) && (items.length >= 16)) {
            resize(items.length / 4);
        }

        T removedElem = get(size - 1);
        backIndex = updateIndex(backIndex - 1);
        items[backIndex] = null;
        size -= 1;
        return removedElem;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i += 1) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        ArrayDeque otherArray = (ArrayDeque) other;
        if (otherArray.size != this.size) {
            return false;
        }
        for (int i = 0; i < size; i += 1) {
            if (!this.get(i).equals(otherArray.get(i))) {
                return false;
            }
        }
        return true;
    }

//    public boolean equals(Object other) {
//        if (this == other) {return true;}
//        if (other instanceof ArrayDeque otherArray) {
//            if (otherArray.size != this.size) {
//                return false;
//            }
//            for (int i = 0; i < size; i += 1) {
//                if (this.get(i) != otherArray.get(i)) {
//                    return false;
//                }
//            }
//            return true;
//        }
//        return false;
//    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDeque.ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<T> {
        private int index;

        ArrayListIterator() {
            index = frontIndex;
        }

        public boolean hasNext() {
            return index != backIndex;
        }

        public T next() {
            T returnItem = items[index];
            index = updateIndex(index + 1);
            return returnItem;
        }
    }
}
