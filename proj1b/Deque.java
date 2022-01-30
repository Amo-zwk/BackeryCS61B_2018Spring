public interface Deque<T> {
    //因为是泛型,所以要在类名后面加<T>
    //接口都是这样的形式，后面是冒号
    public void addFirst(T item) ;
    public void addLast(T item);
    public T removeFirst();
    public T removeLast();
    public boolean isEmpty();
    public int size() ;
    public void printDeque() ;
    public T get(int index);
}
