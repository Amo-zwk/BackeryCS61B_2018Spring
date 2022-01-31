package synthesizer;

//这是一个类实现了BoundedQueue接口，注意都是泛型的哈
public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {

    protected int fillCount; //表示当前队列的数量
    protected int capacity; //表示队列的总数量
    public int capacity() {
        return capacity;
    }
    public int fillCount() {
        return fillCount;
    }
}

