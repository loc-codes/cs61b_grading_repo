package bstmap;

import java.util.Iterator;
import java.util.Set;
import java.lang.UnsupportedOperationException;

public class BSTMap<K extends Comparable<K>, V>  implements Map61B<K, V> {
    private class BSTNode implements Comparable {
        K key;
        V value;
        BSTNode left;
        BSTNode right;

        BSTNode(K k, V v, BSTNode l, BSTNode r) {
            key = k;
            value = v;
            left = l;
            right = r;
        }

        @Override
        public int compareTo(Object o) {
            return 0;
        }
    }

    private BSTNode root;
    private int size = 0;

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }


    @Override
    public void clear() {
        System.out.println("clear");
        root = null;
        size = 0;
        //throw new UnsupportedOperationException();
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key){
        System.out.println("containsKey");
        return key.equals(containsKeyHelper(key, root));
    }

    private K containsKeyHelper(K key, BSTNode tree) {
        if (tree == null) {
            return null;
        }
        else if (key.equals(tree.key)) {
            return tree.key;
        }
        else if (key.compareTo(tree.key) < 0) {
            return containsKeyHelper(key, tree.left);
        }
        else if (key.compareTo(tree.key) > 0) {
            return containsKeyHelper(key, tree.right);
        }
        return null;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    private V getHelper(K key, BSTNode tree) {
        if (tree == null) {
            return null;
        }
        else if (key.equals(tree.key)) {
            return tree.value;
        }
        else if (key.compareTo(tree.key) < 0) {
            return getHelper(key, tree.left);
        }
        else if (key.compareTo(tree.key) > 0) {
            return getHelper(key, tree.right);
        }
        return null;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        if (size() == 0) {
            root = new BSTNode(key, value, null, null);
        }
        else {
            putHelper(key, value, root);
        }
        size += 1;
    }

    private BSTNode putHelper(K key, V value, BSTNode tree) {
        if (tree == null) {
            return new BSTNode(key, value, null, null);
        }
        else if (key.compareTo(tree.key) < 0) {
            tree.left = putHelper(key, value, tree.left);
        }
        else if (key.compareTo(tree.key) > 0) {
            tree.right = putHelper(key, value, tree.right);
        }
        return tree;
    }

    public void printInOrder() {
        throw new UnsupportedOperationException();
    }

    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    @Override
    public Set<K> keySet(){
        throw new UnsupportedOperationException();
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key){
        throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value){
        throw new UnsupportedOperationException();
    };
}
