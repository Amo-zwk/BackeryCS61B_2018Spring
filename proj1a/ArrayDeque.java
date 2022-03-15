public class ArrayDeque<T> {

    /**
     * 要求
     * add和remove的方法必须是常数时间，即O(1)
     * get和size的方法也必须是常数时间,也就是要定义一个size变量
     * 数组大小为8
     * 功能和上一个链表模拟双端队列一样
     * 强烈建议你将数组视为循环数组
     */

    //本题是用数组模拟的双端队列，用的是循环数组模拟双端队列

    private T[] items; //数组元素
    private int size; //数组大小
    private int capacity; //表示当前数组的长度
    private int nextFirst; //表示addFirst的位置,注意是可以转弯的
    private int nextLast; //表示addLast的位置,注意是可以转弯的

    //初始化双端队列的数组
    public ArrayDeque() {
        items = (T[]) new Object[8];
        /**
         * 以上items = (T[]) new objects[8]的解释
         * 我无法让 Java 创建泛型对象数组！
         * A：使用我们在 1 月 30 日讲座中看到的奇怪语法，
         * 即T[] a = (T[]) new Object[1000];. 这里，T是一个泛型类型
         * 它是其他对象类型的占位符，例如“字符串”或“整数”。
         */
        this.capacity = items.length; //为了扩展用的,capacity 一开始只有 8个长度
        nextFirst = capacity - 1; //First由于是往前插,所以设置为数组大小-1合适
        nextLast = 0; //Last是往后插,所以设置为0的位置好点
    }

    //数组的扩容,resize是调整的意思,数组满了才需要扩容
    private void resize(int length) {
        T[] a = (T[]) new Object[length];
        //从nextFirst的右边或者从nextLast的左边开始,遍历次数为size,就可以拷贝整个数组了
        for (int i = 1; i <= size; i++) {
            //从当前的头节点开始复制,一直到最后一个元素
            a[i] = items[(++nextFirst) % this.capacity]; //要保证在范围内
            //为什么从1开始因为后面为了给nextFirst = 0埋下伏笔
        }
        //扩容后的初始化
        this.capacity = length;
        //这么设置的话，下一次往前插和往后插都是非常方便的
        nextFirst = 0; //下一个前插的位置
        nextLast = size + 1; //下一个后插的位置
        //因为把下标从0到7的数值复制到下标从1到8,所以此时nextFirst是0,nextLast是size+1
        items = a; //然后把数组a拷贝给items
    }

    //头插
    public void addFirst(T item) {
        //capacity表示的是容量
        //size表示的是当前的数组下标
        //如果此时的size等于容量说明已经满了就要扩容
        if (size == capacity) {
            resize(capacity * 2);
        }
        //没满直接插入
        items[nextFirst] = item;
        //size表示当前的元素个数
        size++;
        //因为是往前插,所以可以出现负数,所以要特判
        //如果等于0了,那么下一次往前插就要到capacity-1的下标索引了
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
        //因为Last是往后插，为了保证不超出范围，就要+1然后%capacity,即表示下一个尾插的位置
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
        //从nextFirst+1也就是当前的位置
        //为什么加1呢,因为定义的nextFirst是容量-1的下标,所以如果nextFirst不变的话,+1就是8了,所以要%容量
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
        //nextFirst+1的话,就是要头删的位置然后%capacity是为了避免越界
        nextFirst = (nextFirst + 1) % capacity; //要删除的位置，往后移，就是防止超出范围了
        T temp = items[nextFirst]; //要删除的元素
        items[nextFirst] = null; //删除的元素为null就表示删除了
        size--;
        /**
         * 想要表达size/items.length<0.25，即使是(double)size/items.length<0.25也不行
         * 只能：size < (items.length / 4)
         * 对于长度为 16 或更长的数组，您的使用系数应始终至少为 25%。对于较小的阵列，您的使用系数可以任意低。
         */
        if (capacity >= 16 && size < capacity / 4) {
            resize(capacity / 2);
        }
        return temp;
    }

    //尾删操作
    //尾删也同理和头删同理
    //尾插是往后插，所以尾删要往前删
    //往前的话，注意0的时候是转折点
    //往后的话，才要注意要保持在数组长度范围内，就要对数组长度取模
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        //因为你这个尾删的话,你尾插是往右的,所以尾删就要往左,避免出现负数,所以0是一个转折点
        //往左为了出现负数就要特判一下
        nextLast = nextLast == 0 ? capacity - 1 : nextLast - 1;
        T temp = items[nextLast];
        items[nextLast] = null;
        size--;
        //
        if (capacity >= 16 && size < capacity / 4) {
            resize(capacity / 2);
        }
        return temp;
    }

    //根据索引获取元素
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        //元素在First的右边,右边都会有越界的风险,处理的方式就是%capacity
        //从第一个元素开始找
        //是以nextFirst+1为下标为0的第一个元素开始
        return items[(nextFirst + 1 + index) % capacity];
    }

    //
}
