package synthesizer;
//import java.util.Iterator;

//modify your BoundedQueue<T> interface so that it extends Iterable<T>
//该接口继承了Iterable接口,然后可以重写它的迭代方法
public interface BoundedQueue<T> extends Iterable<T> {
    int capacity();
    int fillCount();
    void enqueue(T x);
    T dequeue();
    T peek();
    default boolean isEmpty() {
        return fillCount() == 0;
    }
    default boolean isFull() {
        return fillCount() == capacity();
    }
}

