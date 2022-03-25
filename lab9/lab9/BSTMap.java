package lab9;

import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 * <p>
 * We provide instance variables, a constructor, and a clear method.
 * Your goal is to implement get function, put function, and size function.
 * Other methods such as remove, keySet, and iterator are optional for this lab
 * and by default should throw an UnsupportedOperationException unless you choose to
 * implement them.For get function and put function, you may find it useful to use
 * the putHelper function and getHelper function helper methods we’ve provided you
 * in the skeleton.
 * <p>
 * We provide instance variables, a constructor, and a clear method.
 * Your goal is to implement get function, put function, and size function.
 * Other methods such as remove, keySet, and iterator are optional for this lab
 * and by default should throw an UnsupportedOperationException unless you choose to
 * implement them.For get function and put function, you may find it useful to use
 * the putHelper function and getHelper function helper methods we’ve provided you
 * in the skeleton.
 * <p>
 * We provide instance variables, a constructor, and a clear method.
 * Your goal is to implement get function, put function, and size function.
 * Other methods such as remove, keySet, and iterator are optional for this lab
 * and by default should throw an UnsupportedOperationException unless you choose to
 * implement them.For get function and put function, you may find it useful to use
 * the putHelper function and getHelper function helper methods we’ve provided you
 * in the skeleton.
 */

/**
 * We provide instance variables, a constructor, and a clear method.
 * Your goal is to implement get function, put function, and size function.
 * Other methods such as remove, keySet, and iterator are optional for this lab
 * and by default should throw an UnsupportedOperationException unless you choose to
 * implement them.For get function and put function, you may find it useful to use
 * the putHelper function and getHelper function helper methods we’ve provided you
 * in the skeleton.
 */

/**
 * Notes (about the BST we should use the generic)
 * 但是要注意要重写一下Comparable的方法,在Slides里面有提到过
 * Note that the BSTMap class is declared such that the generic keys
 * extend Comparable<K>, so you should use the compareTo method found
 * in the Comparable interface to compare keys.
 */

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    //Notes : the K is Comparable with the value so it should extends Comparable

    /**
     * 节点类的设置和构造函数
     */
    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /**
     * 定义一个头节点和一个size
     */
    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */

    /**
     * 构造一个空的BST
     */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */

    /**
     * 这是一个clear函数,用来清空size并且让root指向空
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */

    /**
     * 这是一个getHelper函数,要返回一个value
     */
    private V getHelper(Node x, K key) {
        if (key == null) {
            return null;
        }
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return getHelper(x.left, key);
        } else if (cmp > 0) {
            return getHelper(x.right, key);
        } else {
            return x.value;
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */

    /**
     * get主函数
     */
    @Override
    public V get(K key) {
        return getHelper(root, key); // 通过一个private的函数
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
     * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    //要返回的是一个Node

    /**
     * 这是一个putHelper函数,要返回一个Node
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            return new Node(key, value);
        }
        if (p.key.compareTo(key) == 0) {
            p.value = value;
        } else if (p.key.compareTo(key) > 0) {
            p.left = putHelper(key, value, p.left);
        } else {
            p.right = putHelper(key, value, p.right);
        }
        return p; //返回头节点
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */

    /**
     * put主函数
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root); //让root指向头节点,即更改root
        this.size++;
    }

    /* Returns the number of key-value mappings in this map. */

    /**
     * size主函数
     */
    @Override
    public int size() {
        if (this == null) {
            return 0;
        } else {
            return this.size;
        }
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }
    //

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
