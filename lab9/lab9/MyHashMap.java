package lab9;

import java.util.Iterator;
import java.util.Set;

/**
 * A hash table-backed Map implementation. Provides amortized constant time
 * access to elements via get(), remove(), and put() in the best case.
 *
 * @author Your name here
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int DEFAULT_SIZE = 16;
    private static final double MAX_LF = 0.75;

    /**
     * 这里每一个buckets对应一个ArrayMap
     * 而每个ArrayMap又是一个数组
     */
    private ArrayMap<K, V>[] buckets;
    private int size;

    /**
     * 负载因素
     */
    private int loadFactor() {
        return size / buckets.length;
    }

    /**
     * 构造函数,有16个buckets
     */
    public MyHashMap() {
        buckets = new ArrayMap[DEFAULT_SIZE];
        this.clear();
    }

    public MyHashMap(int size) {
        buckets = new ArrayMap[size];
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.buckets.length; i += 1) {
            this.buckets[i] = new ArrayMap<>();
        }
    }

    /** Computes the hash function of the given key. Consists of
     *  computing the hashcode, followed by modding by the number of buckets.
     *  To handle negative numbers properly, uses floorMod instead of %.
     */

    /**
     * 这里获取hash值
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        int numBuckets = buckets.length;
        return Math.floorMod(key.hashCode(), numBuckets); //这里返回的buckets的范围是0-15
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */

    /**
     * get函数
     */
    @Override
    public V get(K key) {
        if (key == null) {
            return null;
        }
        int i = hash(key); //获取一下它的buckets索引
        return buckets[i].get(key);
    }

    /**
     * put函数
     */
    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        //超过则调用到原来两倍,题目有说
        if (loadFactor() > MAX_LF) {
            resize(buckets.length * 2);
        }
        //如果数组里面key对应的是空的,即-1,即为false,就插入,然后size++
        if (!containsKey(key)) {
            size++;
        }
        //找到对应的bucket的索引
        int bucketindex = hash(key);
        //然后插入即可,bucket指向的是一个ArrayMap数组,所以可以调用它的put函数
        //一开始new的时候,bucket[0]指向的ArrayMap是一个所有成员变量都初始化为0或者null的数组
        buckets[bucketindex].put(key, value);
    }

    /**
     * resize()函数
     */
    private void resize(int newlength) {
        //创建一个新的bucket
        MyHashMap<K, V> myHashMap = new MyHashMap<>(newlength);
        //拷贝旧bucket数组的内容
        for (ArrayMap<K, V> bucket : buckets) { //遍历每个bucket,每个bucket都是一个ArrayMap
            for (K key : bucket) { //遍历每个bucket即每个ArrayMap的key
                myHashMap.put(key, bucket.get(key));
            }
        }
        //把旧数组指向新数组
        this.buckets = myHashMap.buckets;
        this.size = myHashMap.size;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        throw new UnsupportedOperationException();
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /* Removes the mapping for the specified key from this map if exists.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for this lab. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
