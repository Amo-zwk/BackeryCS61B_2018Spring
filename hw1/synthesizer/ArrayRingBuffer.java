
package synthesizer;

import synthesizer.AbstractBoundedQueue;

import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.capacity = capacity;
        first = 0;
        last = 0;
        fillCount = 0;
        //Create new array with capacity elements.
        rb = (T[]) new Object[capacity]; //向下转型，子类对象指向父类对象，再把父类对象强制转换成子类对象
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    private int onePlus(int index) {
        if (index == capacity - 1) {
            return 0;
        }
        return index + 1;
    }

    @Override
    public void enqueue(T x) {
        //入队操作
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow");
        }
        //Adds x to the end of the ring buffer 在rb后尾插
        rb[last] = x; //插入
        last = onePlus(last); //下一个尾插的位置
        fillCount += 1; //当前项数+1
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        //出队操作
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        T item = rb[first];
        rb[first] = null;
        //这里是否可以直接first += 1 呢
        //不行 因为要判断如果满了 就从0开始
        first = onePlus(first);
        fillCount -= 1;
        return item;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        T item = rb[first];
        return item;
    }

}
