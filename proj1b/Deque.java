public interface Deque<T> {
    //因为是泛型,所以要在类名后面加<T>
    //接口都是这样的形式，后面是冒号
    //接口的时候省略public
    boolean isEmpty();
    int size();
    void addFirst(T item);
    void addLast(T item);
    void printDeque();
    T removeFirst();
    T removeLast();
    T get(int index);
}
