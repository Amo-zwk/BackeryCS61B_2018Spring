package synthesizer;

//以上是题目要求
//modify your BoundedQueue<T> interface so that it extends Iterable<T>
//该接口继承了Iterable接口,然后可以重写它的迭代方法

/**
 * 我们将从定义 BoundedQueue 接口开始。
 * BoundedQueue 类似于我们在项目 1 中的 Deque，但 API 更有限。
 * 具体来说，项目只能在队列的后面入队，并且只能从队列的前面出队。
 * 与我们的 Deque 不同，BoundedDeque 具有固定容量，如果队列已满，则不允许任何内容入队。
 */

public interface BoundedQueue<T> extends Iterable<T> {
    /**
     * 您的 BoundedQueue 接口应包含以下方法：
     */
    int capacity(); //返回缓冲区的大小
    int fillCount(); //返回当前缓冲区中的项数
    void enqueue(T x); //在末尾添加项目x
    T dequeue(); //从前面删除和返回项目
    T peek(); //从前面返回(但不删除)项目

    //以上都是题目的要求
    //is the buffer empty (fillCount equals zero)?
    default boolean isEmpty() {
        return fillCount() == 0;
    }
    //is the buffer full (fillCount is same as capacity)?
    default boolean isFull() {
        return fillCount() == capacity();
    }

}

