package Disc03;

public class SLList {
    private class IntNode {
        public int item;
        public IntNode next;
        public IntNode(int item, IntNode next) {
            this.item = item;
            this.next = next;
        }
    }
    private IntNode first;
    public void addFirst(int x) {
        first = new IntNode(x, first);
    }
    public void insert(int item , int position){
        //如果一个元素都没有的时候
        if (first == null || position == 0){
            addFirst(item); // 头插
            return ;
        }
        IntNode currentNode = first ;
        while(position > 1 && currentNode.next != null){
            position-- ;
            currentNode = currentNode.next ;
        }
        IntNode newNode = new IntNode(item, currentNode);
        currentNode.next = newNode ;
    }
}
