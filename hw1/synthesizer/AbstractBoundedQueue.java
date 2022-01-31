package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {

    protected int fillCount;
    protected int capacity;

    @Override
    public int capacity() {
        return fillCount;
    } //题目要求

    @Override
    public int fillCount() {
        return fillCount;
    } //题目要求

}
