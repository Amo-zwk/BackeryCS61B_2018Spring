public class LinkedListDeque<T> {

    /**
     * 创建一个节点类对象
     */
    public class IntNode {
        private T item;
        private IntNode pre;
        private IntNode next;

        public IntNode(T item, IntNode pre, IntNode next) {
            this.item = item;
            this.pre = pre;
            this.next = next;
        }
    }

    //管理模式用带size和first和last指针的
    private IntNode first; //头指针
    private IntNode last; //尾指针
    private int size; //因为后面要保证O(1)的size函数的时间

    /**
     * 这里初始化有个技巧
     * 为了避免就是头插或者头删等操作的时候，因为头插如果插入的是第一个元素就要移动last指针
     * 这里我们可以设置两个虚拟节点，就可以避免这个问题，所以Y总双链表那题就是这么个思路
     * 这样的话，我们就用双链表模拟就好了，不用循环了
     */

    //双链表模拟,不循环
    public LinkedListDeque() {
        size = 0; //一开始没有元素
        first = new IntNode(null, null, null);
        last = new IntNode(null, null, null);
        first.next = last;
        last.pre = first;
    }

    /**
     * 规则
     * 1. add和remove不可以用looping或者recursion
     * 2. get函数必须要用迭代，即looping
     * 3. size函数必须要O(1)时间，即不可以用looping或者recursion，所以要用变量size
     */

    /**
     * 本类要求写一个用双链表而且是循环的模拟双端队列
     */

    //输出双端队列
    public void printDeque() {
        IntNode intNode = first.next;
        while (intNode != last) {
            System.out.println(intNode.item);
            System.out.println(" ");
            intNode = intNode.next;
        }
        System.out.println('\n');
    }

    //判断大小
    public int size() {
        return this.size;
    }

    //判空
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    //头插
    public void addFirst(T item) {
        //方便多了，对吧，只需要修改一下first和last，不需要考虑任何特殊情况
        IntNode intNode = new IntNode(item, first, first.next);
        intNode.next.pre = intNode;
        first.next = intNode;
        size++;
    }

    //尾插
    public void addLast(T item) {
        IntNode intNode = new IntNode(item, last.pre, last);
        last.pre = intNode;
        last.pre.next = intNode;
        size++;
    }

    //头删
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T number = first.next.item;
        first.next = first.next.next;
        first.next.pre = first;
        size--;
        return number;
    }

    //尾删
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T number = last.pre.item;
        last.pre = last.pre.pre;
        last.pre.next = last;
        size--;
        return number;
    }

    //获取元素
    public T get(int index) {
        if (index > size()) {
            return null;
        }
        IntNode intNode = first.next;
        int i = 0;
        while (i < index) {
            intNode = intNode.next;
            i++;
        }
        return intNode.item;
    }

    //递归的辅助函数
    public T getRecursiveHelper(IntNode intNode, int index) {
        if (index == 0) {
            return intNode.item;
        } else {
            return getRecursiveHelper(intNode.next, index - 1);
        }
    }

    //递归获取元素,递归的辅助函数，用helper来写
    public T getRecursive(int index) {
        if (index > size()) {
            return null;
        }
        IntNode intNode = first.next;
        return getRecursiveHelper(intNode, index);
    }

}
