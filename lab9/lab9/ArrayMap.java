package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * An array based implementation of the Map61B class.
 * @author Josh Hug (mostly done in lecture)
 */
public class ArrayMap<K, V> implements Map61B<K, V> {

    /**
     * 每个buckets都有一个ArrayMap,一个ArrayMap的大小为100
     */
    private K[] keys;
    private V[] values;
    int size;

    /**
     * 这是泛型的创建数组的方法,每一个bucket都有一个key和value为100然后size为0的ArrayMap数组
     */
    public ArrayMap() {
        keys = (K[]) new Object[100];
        values = (V[]) new Object[100];
        size = 0;
    }

    /** Returns the index of the given key if it exists,
     *  -1 otherwise. */
    /**
     *  keyIndex()方法
     *  找一下key的Index,如果不存在返回-1,存在则返回该Index
     */
    private int keyIndex(K key) {
        for (int i = 0; i < size; i += 1) {
            if (keys[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * containsKey()方法
     * 是否包含这个key的index,如果大于-1,说明存在了,即返回true
     * 否则就是不存在,即返回false
     */
    @Override
    public boolean containsKey(K key) {
        int index = keyIndex(key);
        return index > -1;
    }

    /**
     * ArrayMap的数组的put()方法
     */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Null key not allowed.");
        }
        if (value == null) {
            throw new IllegalArgumentException("Null values not allowed.");
        }
        //获取key的index
        int index = keyIndex(key);
        //index为-1,说明该数组没有重复的key,就可以直接插入
        if (index == -1) {
            //插入要判断元素
            // 是否满了,满了要扩展
            if (size == keys.length) {
                resize(keys.length * 2);
            }
            //因为是个数组,每个单位都有一个key和value
            //没满直接插入
            keys[size] = key;
            values[size] = value;
            size += 1;
            return;
        }
        //如果重复了,直接替换值就好了
        values[index] = value;
    }

    private void resize(int capacity) {
        K[] newKeys = (K[]) new Object[capacity];
        V[] newValues = (V[]) new Object[capacity];
        System.arraycopy(keys, 0, newKeys, 0, size);
        System.arraycopy(values, 0, newValues, 0, size);
        keys = newKeys;
        values = newValues;
    }

    @Override
    public V get(K key) {
        int index = keyIndex(key);
        if (index == -1) {
            return null;
        }
        return values[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keyset = new HashSet<>();
        for (int i = 0; i < size; i += 1) {
            keyset.add(keys[i]);
        }
        return keyset;
    }

    @Override
    public V remove(K key) {
        int keyLocation = keyIndex(key);
        V returnValue = null;
        if (keyLocation > -1) {
            returnValue = values[keyLocation];
            keys[keyLocation] = keys[size - 1];
            values[keyLocation] = values[size - 1];
            size -= 1;
        }
        return returnValue;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    @Override
    public void clear() {
        keys = (K[]) new Object[100];
        values = (V[]) new Object[100];
        size = 0;
    }
    //
    @Override
    public V remove(K key, V value) {
        int keyLocation = keyIndex(key);
        V returnValue = null;
        if (keyLocation > -1 && values[keyLocation].equals(value)) {
            returnValue = values[keyLocation];
            keys[keyLocation] = keys[size - 1];
            values[keyLocation] = values[size - 1];
            size -= 1;
        }
        return returnValue;
    }
}
