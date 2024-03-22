package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int defaultInitialSize = 16;
    private int numberOfItems = 0;
    private int numberOfBuckets;
    private double maxLoad = 0.75;


    /** Constructors */
    public MyHashMap() {
        this.buckets = createTable(this.defaultInitialSize);
    }

    public MyHashMap(int initialSize) {
        this.defaultInitialSize = initialSize;
        this.buckets = createTable(initialSize);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.defaultInitialSize = initialSize;
        this.maxLoad = maxLoad;
        this.buckets = createTable(initialSize);
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<Node>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] newTable = new Collection[tableSize];
        for(int i = 0; i < tableSize; i += 1) {
            newTable[i] = createBucket();
        }
        this.numberOfBuckets = tableSize;
        return newTable;
    }

    private int getHashCode(K key) {
        return Math.floorMod(key.hashCode(),numberOfBuckets);
    }

    private void resize() {
        int oldNumberOfBuckets = this.numberOfBuckets;
        Collection<Node>[] newBuckets = createTable(numberOfBuckets * 2);
        for (int bucketIndex = 0; bucketIndex < oldNumberOfBuckets; bucketIndex += 1) {
            for (Node item : buckets[bucketIndex]) {
                int newHashCode = getHashCode(item.key);
                newBuckets[newHashCode].add(item);
            }
        }
        this.buckets = newBuckets;
    }

    @Override
    public void clear() {
        buckets = createTable(this.defaultInitialSize);
        this.numberOfItems = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        Node returnNode = getNode(key);
        if (returnNode != null) {
            return returnNode.value;
        }
        return null;
    }

    public Node getNode(K key) {
        int hashCode = getHashCode(key);
        if (buckets != null) {
            for (Node item : buckets[hashCode]) {
                if (item.key.equals(key)) {
                    return item;
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return this.numberOfItems;
    }

    @Override
    public void put(K key, V value) {
        int hashCode = getHashCode(key);
        Collection<Node> searchBucket = buckets[hashCode];
        boolean keyExists = false;
        for (Node item : searchBucket) {
            if (item.key.equals(key)) {
                item.value = value;
                keyExists = true;
                break;
            }
        }
        if (!keyExists) {
            searchBucket.add(new Node(key, value));
            numberOfItems++;
        }
        double loadFactor = (double) numberOfItems / numberOfBuckets;
        if (loadFactor > maxLoad) {
            resize();
        }
    }

    @Override
    public Set<K> keySet() {
        HashSet<K> keys = new HashSet<K>();
        for(int bucketIndex = 0; bucketIndex < numberOfBuckets; bucketIndex += 1) {
            for (Node item : buckets[bucketIndex]) {
                keys.add(item.key);
            }
        }
        return keys;
    }

    @Override
    public V remove(K key) {
        int hashCode = getHashCode(key);
        Collection<Node> searchBucket = buckets[hashCode];
        Node removeNode = getNode(key);
        if (removeNode != null) {
            searchBucket.remove(removeNode);
            return removeNode.value;
        }
        return null;
    }

    @Override
    public V remove(K key, V value) {
        return remove(key);
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
