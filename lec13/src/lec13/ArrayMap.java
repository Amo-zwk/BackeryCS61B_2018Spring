package lec13;

package Map61B;

import java.util.List;
import java.util.ArrayList;

/***
 * An array-based implementation of Map61B.
 ***/

//this ArrayMap with map Generic (泛型)
// key - value
public class ArrayMap<K, V> implements Map61B<K, V> {
    //创建一下K,V的数组
    private K[] keys;
    private V[] values;
    int size;

    //初始化
    //记住泛型数组的初始化
    //语法是 K var = (K) new Object[100]
    //类似于一个强转
    public ArrayMap() {
        keys = (K[]) new Object[100];
        values = (V[]) new Object[100];
        size = 0;
    }

    /**
     * Returns the index of the key, if it exists. Otherwise returns -1.
     **/
    private int keyIndex(K key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                return i;
            }
            return -1;
        }

        public boolean containsKey(K key) {
            int index = keyIndex(key);
            return index > -1;
        }

        public void put(K key, V value) {
            int index = keyIndex(key);
            if (index == -1) {
                keys[size] = key;
                values[size] = value;
                size += 1;
            } else {
                values[index] = value;
            }
        }

        public V get(K key) {
            int index = keyIndex(key);
            return values[index];
        }

        public int size() {
            return size;
        }

        public List<K> keys() {
            List<K> keyList = new ArrayList<>();
            for (int i = 0; i != size; i++) {
                keyList.add(keys[i]);
            }
            return keyList;
        }
    }