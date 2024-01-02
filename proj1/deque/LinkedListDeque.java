package deque;

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class Node {
        public T item;
        public Node next;
        public Node prev;

        public Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque () {
        sentinel = new Node(null, sentinel, sentinel);
        size = 0;
    }

    private void initialiseList(T elem){
        Node initialNode = new Node(elem, sentinel, sentinel);
        sentinel.next = initialNode;
        sentinel.prev = initialNode;
    }

    @Override
    public void addFirst(T elem){
        if (size == 0){
            initialiseList(elem);
        }
        else {
            Node NewNode = new Node(elem, sentinel, sentinel.next);
            sentinel.next.prev = NewNode;
            sentinel.next = NewNode;
        }
        size += 1;
    }

    @Override
    public void addLast(T elem){
        if (size == 0){
            initialiseList(elem);
        }
        else {
            Node NewNode = new Node(elem, sentinel.prev, sentinel);
            sentinel.prev = NewNode;
            sentinel.prev.prev.next = NewNode;
        }
        size += 1;
    }

    @Override
    public T removeFirst(){
        if (size == 0){
            System.out.println("Nothing happened...list is already empty");
            return null;
        }
        else {
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
    public T removeLast(){
        if (size == 0){
            System.out.println("Nothing happened...list is already empty");
            return null;
        }
        else {
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
    public T get(int index){
        if (index >= size){
            System.out.println("Cannot retrieve item! Index is larger than list");
            return null;
        }
        int counter = 0;
        Node currentNode = sentinel;
        while (counter <= index){
            currentNode = currentNode.next;
            counter += 1;
        }
        return currentNode.item;
    }

    public T getRecursive(int index){
        return getRecursiveHelper(index, sentinel.next).item;
    }

    public Node getRecursiveHelper(int index, Node n){
        if (index == 0){
            return n;
        }
        else {
            return getRecursiveHelper(index-1, n.next);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque(){
        Node p = sentinel.next;
        while (p.item != null){
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    @Override
    public boolean equals(Object other){
        if (this == other) {return true;}
        if (other instanceof LinkedListDeque otherLinkedList) {
            if (otherLinkedList.size != this.size) {
                return false;
            }
            for (int i = 0; i < size; i += 1){
                if (this.get(i) != otherLinkedList.get(i)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator(){
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node currentNode = null;
        public LinkedListIterator() {
            currentNode = sentinel.next;
        }
        public boolean hasNext() {
            return currentNode.item != null;
        }

        public T next() {
            Node returnNode = currentNode;
            currentNode = currentNode.next;
            return returnNode.item;
        }

    }

    public static void main (String[] args){

    }
}
