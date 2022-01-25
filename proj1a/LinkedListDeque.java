public class LinkedListDeque<T> {

    /**
     * 创建一个节点类对象
     */
    public class IntNode {
        public T item;
        public IntNode pre;
        public IntNode next;

        public IntNode(T item, IntNode pre, IntNode next) {
            this.item = item;
            this.pre = pre;
            this.next = next;
        }
    }

    /**
     * 初始化节点对象,带头节点的
     */
    private IntNode first;
    private IntNode last;
    private int size = 0; //因为后面要保证O(1)的size函数的时间

    public void BuyNode() {
        this.first = new IntNode(null, null, null);
        this.first.pre = this.first;
        this.first.next = this.first;
        this.last = this.first;
    }

    //初始化
    public LinkedListDeque() {
        BuyNode();
    }

    //初始化
    public LinkedListDeque(T item) {
        BuyNode();
        this.first.next = new IntNode(item, null, null);
        this.last = this.last.next;
        this.last.pre = this.first;
        this.last.next = this.first;
        this.first.pre = this.last;
        size += 1;
    }

    /**
     * 规则
     * 1. add和remove不可以用looping或者recursion
     * 2. get函数必须要用迭代，即looping
     * 3. size函数必须要O(1)时间，即不可以用looping或者recursion，所以要用变量size
     */

    //输出双端队列
    public void printDeque() {
        if(isEmpty()) return ;
        IntNode q = this.first.next ;
        while ( q != this.first ){
            System.out.print(q.item + " " );
            q = q.next ;
        }
    }

    //判断大小
    public int size() {
        return this.size;
    }

    //判空
    public boolean isEmpty() {
        if (this.size == 0) return true;
        return false;
    }

    //头插
    public void addFirst(T item) {
        //如果是第一个元素,要修改last指针
        if (this.size == 0) addLast(item);
        else {
            IntNode s = new IntNode(item, null, null);
            s.next = this.first.next;
            s.pre = this.first;
            this.first.next.pre = s;
            this.first.next = s;
            size += 1;
        }
    }

    //尾插
    public void addLast(T item) {
        this.last.next = new IntNode(item, null, null);
        this.last.next.pre = this.last;
        this.last.next.next = this.first;
        this.last = this.last.next;
        this.first.pre = this.last;
        size += 1;
    }

    //头删
    public T removeFirst() {
        if (this.size == 1) removeLast();
        T number = this.first.next.item;
        this.first.next = this.first.next.next;
        this.first.next.next.pre = this.first;
        this.size -= 1;
        return number ;
    }

    //尾删
    public T removeLast() {
        T number = this.last.item;
        this.last = this.last.pre;
        this.last.next = this.first;
        this.first.pre = this.last;
        this.size -= 1;
        return number;
    }

    //获取元素
    public T get(int index) {
        if (isEmpty()) return null; //为空则没元素
        int i = 0 ;
        T element = null ;
        IntNode p = first.next ;
        while ( p != this.first ){
            if ( i == index ) {
                element = p.item ;
                break ;
            }
            else {
                p = p.next ;
                i += 1 ;
            }
        }
        return element ;
    }

    //递归获取元素
    public T getRecursion(int index) {
        if(isEmpty()) return null ;
        IntNode p = first.next ;
        //找元素
        if ( index < 0 ) return null ;
        if (index == 0) return p.item ;
        else {
            p = p.next ;
            getRecursion(index-1) ;
        }
        //没找到
        return null ;
    }

    //main方法
    public static void main(String[] args) {
        //LinkedListDeque<Object> L = new LinkedListDeque(); //创建一个头指针
    }

}
