public class ArrayDeque<T> {

    /**
     * 要求
     * add和remove的方法必须是常数时间，即O(1)
     * get和size的方法也必须是常数时间,也就是要定义一个size变量
     * 数组大小为8
     * 功能和上一个链表模拟双端队列一样
     * 强烈建议你将数组视为循环数组
     */

    //本题是用数组模拟的双端队列，用的是循环数组

    private T[] items; //数组元素
    private int size; //数组大小
    private int capacity; //表示当前数组的长度
    private int nextFirst; //表示addFirst的位置,注意是可以转弯的
    private int nextLast; //表示addLast的位置,注意是可以转弯的

    //初始化
    public ArrayDeque() {
        items = (T[]) new Object[8];
        this.capacity = items.length;
        nextFirst = capacity - 1; //First由于是往前插,所以设置为数组大小-1合适
        nextLast = 0; //Last是往后插,所以设置为0的位置好点
    }

    //数组的扩容,resize是调整的意思,数组满了才需要扩容
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        //从nextFirst的右边或者从nextLast的左边开始,遍历次数为size,就可以拷贝整个数组了
        for (int i = 1; i <= size; i++) {
            a[i] = items[(++nextFirst) % this.capacity]; //要保证在范围内
            //为什么从1开始因为后面为了给nextFirst = 0埋下伏笔
        }
        //扩容后的初始化
        this.capacity = capacity;
        //这么设置的话，下一次往前插和往后插都是非常方便的
        nextFirst = 0;
        nextLast = size + 1;
        items = a;
    }

    //头插
    public void addFirst(T item) {
        if (size == capacity) {
            resize(capacity * 2);
        }
        items[nextFirst] = item;
        size++;
        //因为是往前插,所以可以出现负数,所以要特判
        nextFirst = nextFirst == 0 ? capacity - 1 : nextFirst - 1;
    }

    //尾插
    public void addLast(T item) {
        //满了要扩容
        if (size == capacity) {
            resize(capacity * 2);
        }
        //没满直接插入
        items[nextLast] = item;
        size++;
        //因为Last是往后插，为了保证不超出范围，就要+1然后%size
        nextLast = (nextLast + 1) % capacity;
    }

    //判空
    public boolean isEmpty() {
        return size == 0;
    }

    //数组长度
    public int size() {
        return size;
    }

    //输出数组
    public void printDeque() {
        //nextFirst有可能指向最后一个位置,为了确保nextFirst在范围内
        //可以自己写一组数据来模拟一下
        //从nextFirst的右边开始
        for (int i = (nextFirst + 1) % capacity; i != nextLast - 1; i = (i + 1) % capacity) {
            System.out.print(items[i] + " ");
        }

        System.out.print(items[nextLast - 1]);
    }

    //头删操作
    public T removeFirst() {
        //当数组的内容为空的时候，才无法进行remove操作
        if (size == 0) {
            return null;
        }
        //否则是可以删除的
        //因为addFirst是往前插的,所以removeFirst就是往后删除
        //往后的动作会超出边界,为了不超出边界,就要%size
        //加上删除的是nextFirst右边的才是要删除的,所以就有下面那行代码了
        nextFirst = (nextFirst + 1) % capacity; //要删除的位置，往后移，就是防止超出范围了
        T temp = items[nextFirst]; //要删除的元素
        items[nextFirst] = null; //删除的元素为null就表示删除了
        size--;
        if (capacity >= 16 && size < capacity / 4) {
            resize(capacity / 2);
        }
        return temp;
    }

    //尾删也同理和头删同理
    //尾插是往后插，所以尾删要往前删
    //往前的话，注意0的时候是转折点
    //往后的话，才要注意要保持在数组长度范围内，就要对数组长度取模
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast = nextLast == 0 ? capacity - 1 : nextLast - 1;
        T temp = items[nextLast];
        items[nextLast] = null;
        size--;
//        if (capacity >= 16 && size < capacity / 4) {
//            resize(capacity / 2);
//        }
        return temp;
    }

    //根据索引获取元素
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return items[(nextFirst + 1 + index) % capacity];
    }
}
