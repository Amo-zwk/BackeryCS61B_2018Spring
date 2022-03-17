public interface Deque<T> {
    //Create an interface in a new file named Deque.java
    //that contains all of the methods that appear in both
    //ArrayDeque and LinkedListDeque.
    //因为是泛型,所以要在类名后面加<T>
    //接口都是这样的形式，后面是冒号
    //接口的时候省略public
    //接口只是告诉你做什么，并没有告诉你怎么做，要对应类的实现来告诉你怎么做
    boolean isEmpty();
    int size();
    void addFirst(T item);
    void addLast(T item);
    void printDeque();
    T removeFirst();
    T removeLast();
    T get(int index);
}
