public class LinkedListDeque<T> {

    /**
     * 要求 1
     * add和remove操作不得涉及任何循环或递归。单个这样的操作必须花费 "恒定时间"
     * 即执行时间不应取决于双端队列的大小。
     * 即时间复杂度为O(n)
     */

    /**
     * 要求 2
     * get必须使用迭代，而不是递归
     * 即使用循环
     */

    /**
     * 要求 3
     * size必须花费恒定的时间
     * 即 要开一个全局变量size
     */
    /**
     * 创建一个节点类对象
     */

    //双链表类
    private class IntNode {
        private T item; //item
        private IntNode pre; //前指针
        private IntNode next; //后指针

        //构造函数
        IntNode(T item, IntNode pre, IntNode next) {
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
     * 但是双链表有个带first和last指针的有两个情况
     * 第一个是  :  添加两个虚拟节点
     * 第二个是  ： 添加一个虚拟节点然后循环,即双向循环链表
     * 在这里我们选择第二个,即创建两个虚拟节点的双链表,这是常用的双链表的操作
     */

    /**
     * 这里初始化有个技巧
     * 为了避免就是头插或者头删等操作的时候，因为头插如果插入的是第一个元素就要移动last指针
     * 这里我们可以设置两个虚拟节点，就可以避免这个问题，所以Y总双链表那题就是这么个思路
     * 这样的话，我们就用双链表模拟就好了，不用循环了
     */

    //双链表的构造函数,带两个虚拟节点的
    public LinkedListDeque() {
        size = 0; //一开始没有元素
        //初始化两个空节点
        first = new IntNode(null, null, null);
        last = new IntNode(null, null, null);
        //让第一个虚拟节点的next等于last
        //让第二个虚拟节点的pre等于first
        //画图即可得证
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

    //判断大小
    public int size() {
        return size;
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
        //头插四步
        //第一步是 节点的next
        //第二步是 节点的pre
        //第三步是 节点的next的pre
        //第四步是 first的next
        IntNode intNode = new IntNode(item, first, first.next);
        intNode.next.pre = intNode;
        first.next = intNode;
        size++;
    }

    //尾插
    public void addLast(T item) {
        //尾插四步
        //第一步是 节点的next
        //第二步是 节点的pre
        //第三步是 节点的next的pre
        //第四步是 first的next
        IntNode intNode = new IntNode(item, last.pre, last);
        last.pre = intNode;
        intNode.pre.next = intNode;
        size++;
    }

    //头删
    public T removeFirst() {
        //判空
        if (isEmpty()) {
            return null;
        }
        //不为空
        //头删两步
        //第一步是 节点的pre的next等于它的next
        //第二步是 节点的next的pre等于它的pre
        T number = first.next.item;
        first.next = first.next.next;
        first.next.pre = first;
        size--;
        //返回被删除的number
        return number;
    }

    //尾删
    public T removeLast() {
        //判空
        if (isEmpty()) {
            return null;
        }
        //尾删分两步
        //画图即可
        T number = last.pre.item;
        last.pre = last.pre.pre;
        last.pre.next = last;
        size--;
        //返回要删除的数字
        return number;
    }

    //获取元素
    public T get(int index) {
        //如果index的范围不合法,返回空
        if (index >= size()) {
            return null;
        }
        //若index的范围合法
        //从第一个元素开始遍历
        IntNode intNode = first.next;
        //循环index次
        int i = 0;
        while (i < index) {
            intNode = intNode.next;
            i++;
        }
        //返回index的item
        return intNode.item;
    }

    //递归的辅助函数
    private T getRecursiveHelper(IntNode intNode, int index) {
        //递归结束的条件是index为0,比如1--->3,要查找index为1的元素也就是3，那么index-1等于0的时候就是该元素
        if (index == 0) {
            //index为0说明已经找到了,直接返回它的item
            return intNode.item;
        } else {
            //否则继续递归往下找,index要减1
            return getRecursiveHelper(intNode.next, index - 1);
        }
    }

    //递归获取元素,递归的辅助函数，用helper来写
    public T getRecursive(int index) {
        //如果index范围不合法,就return null
        if (index >= size()) {
            return null;
        }
        IntNode intNode = first.next; //从第一个元素开始递归查找
        //这个递归查找的方法好像在之前的ppt有讲到过,就是利用index一直减1,然后直到为0为止
        return getRecursiveHelper(intNode, index);
    }

    //输出双端队列,迭代的方法写的
    public void printDeque() {
        IntNode intNode = first.next;
        while (intNode != last) {
            System.out.println(intNode.item);
            System.out.println(" ");
            intNode = intNode.next;
        }
        System.out.println('\n');
    }

    //
}

