package synthesizer;

//这是一个类实现了BoundedQueue接口，注意都是泛型的哈
//Create a new abstract class in a .java file called AbstractBoundedQueue.
//java that implements BoundedQueue
public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    //public abstract class AbstractBoundedQueue是题目要求的
    //这两个是实例变量
    //您的AbstractBoundedQueue类应具有以下方法和字段(字段只是实例变量的另一个词)
    protected int fillCount; //表示当前队列的数量
    protected int capacity; //表示队列的总数量
    //capacity()和fillCount()并capacity和fillCount
    public int capacity() {
        return capacity;
    }
    //
    public int fillCount() {
        return fillCount;
    }
    /**
     * 注意 , isEmpty, isFull, peek, dequeue,enqueue继承自 BoundedQueue，
     * 因此您不应在 AbstractBoundedQueue.java文件中显式声明这些。
     * 上面这个神秘的protected关键词是我们将在 2/21 的讲座中讨论的内容。
     * 它只是意味着只有AbstractBoundedQueue
     * 同一个包中的子类和类 AbstractBoundedQueue才能访问这个变量。
     */
}

