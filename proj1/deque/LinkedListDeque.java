package deque;

public class LinkedListDeque<T> {

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

    public void addFirst(T elem){
        if (size == 0){
            initialiseList(elem);
        }
        else {
            Node NewNode = new Node(elem, sentinel, sentinel.next);
            sentinel.next.next.prev = NewNode;
        }
        size += 1;
    }

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

    public T removeFirst(){
        if (size == 0){
            System.out.println("Nothing happened...list is already empty");
            return null;
        }
        else {
            T removedElem = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            size -= 1;
            return removedElem;
        }

    }

    public T removeLast(){
        if (size == 0){
            System.out.println("Nothing happened...list is already empty");
            return null;
        }
        else {
            T removedElem = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            size -= 1;
            return removedElem;
        }

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

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

    public void printDeque(){
        Node p = sentinel.next;
        while (p.item != null){
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
        //System.out.println("printDeque called");
    }

    public static void main (String[] args){

    }
}
