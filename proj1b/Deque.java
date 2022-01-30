public interface Deque<T> {
    //因为是泛型,所以要在类名后面加<T>
    //接口都是这样的形式，后面是冒号
    void addFirst(T item);
    void addLast(T item);
    T removeFirst();
    T removeLast();
    boolean isEmpty();
    int size();
    void printDeque();
    T get(int index);
}
