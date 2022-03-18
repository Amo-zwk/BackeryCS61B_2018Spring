package synthesizer;
import java.util.Iterator;

//这是一个子类继承了AbstractBoundedQueue父类
//所以可以用到父类实现的接口
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first; // 队列中的队头指针
    /* Index for the next enqueue. */
    private int last; //队列中的队尾指针
    /* Array for storing the buffer data. */
    private T[] rb; //泛型队列，数组模拟的队列

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    //队列的初始化
    public ArrayRingBuffer(int capacity) {
        this.capacity = capacity;
        first = 0;
        last = 0;
        this.fillCount = 0;
        rb = (T[]) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */

    /**
     * In the last section of this homework, we’ll implement
     * our ArrayRingBuffer
     * to throw a run-time exception if the client attempts
     * to enqueue() into a full buffer or call dequeue() or peek()
     * on an empty buffer
     */

    //入队操作
    public void enqueue(T x) {
        //如果队列为空
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        } else {
            rb[last] = x;
            //数组模拟的循环队列
            last = (last + 1) % capacity;
            fillCount = fillCount + 1;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    //出队操作
    //模拟队列,是队尾进,队头出
    //last表示的是队尾,first表示的是队头
    //队头出就是往前走
    public T dequeue() {
        //队列为空
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        } else {
            //出队，直接让first往前走
            T temp = rb[first];
            first = (first + 1) % capacity;
            fillCount = fillCount - 1;
            return temp;
        }
    }

    /**
     * Return oldest item, but don't remove it.
     */
    //获取队头元素
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Defined by myself");
        } else {
            return rb[first];
        }
    }

    //该类继承了ABQ那个类，ABQ那个类又继承了BQ接口，BQ接口又继承了Iterable接口
    //所以可以在A这个类直接重写Iterable接口的方法就好了
    //迭代，返回的是一个接口的对象或者实现类(该实现类必须实现该接口)
    //然后BQ接口调用这个方法的时候，new这个类的时候就会调用这个类的这个方法
    //也就是接口给一个方法，每个类都可以重写这个方法
    //即面向接口编程
    public Iterator<T> iterator() {
        return new KeyIterator();
    }
    //该实现类，并且实现了该接口，用private隐藏具体实现细节
    private class KeyIterator implements Iterator<T> {
        private int position;

        //构造方法
        KeyIterator() {
            position = first;
        }

        //实现类必须重写hasNext()方法
        public boolean hasNext() {
            return (position + 1) % capacity != last; //直到队尾为止
        }

        //实现类必须重写next()方法
        //一个元素一个元素取出来
        public T next() {
            T value = rb[position];
            position = (position + 1) % capacity;
            return value;
        }
    }

}

