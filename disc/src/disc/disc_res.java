package disc;

public class disc_res {

    //内部类结点结构
    private class IntNode {
        public int item ;
        public IntNode next ;
        public IntNode(int item, IntNode next) {
            this.item = item;
            this.next = next;
        }
    }

    //first
    public IntNode first ;

    //构造
    public disc_res() {
        first = new IntNode(1,null) ;
    }

    //头插
    public void addFirst(int x) {
        first = new IntNode(x,first) ;
    }

    //按位置插入
    public void insert(int item, int position) {
        //判断位置是否合理
        //特判一下pos为0的时候,是头插
        if(position == 0) {
            addFirst(item);
            return ;
        }
        //需要两个指针
        IntNode q = first ;
        int index = 0 ;
        while(index + 1 != position) {
            q = q.next ;
            index += 1 ;
        }
        IntNode s = new IntNode(item,null) ;
        s.next = q.next ;
        q.next = s ;
    }

    //查看SLList
    public void show() {
        IntNode q = first ;
        while(q != null) {
            System.out.print(q.item);
            System.out.print("---->");
            q = q.next ;
        }
        System.out.print("null");
    }

    public void reverseList() {
        first = reverseListHelper(first) ;
    }
    //翻转递归
    private IntNode reverseListHelper(IntNode head) {
        if(head == null || head.next == null) {
            return head ;
        }
        IntNode cur = reverseListHelper(head.next) ;
        head.next.next = head ;
        head.next = null ;
        return cur ;
    }

    //翻转迭代
    public void reverse() {
        IntNode res = new IntNode(first.item,null);
        res.next = null ; //置空
        IntNode q = first , s = q.next ;
        while(s != null) {
            q = q.next ;
            res = new IntNode(q.item,res) ;
            s = q.next ;
        }
        //此时q已经是最后一个元素了
        first = res ;
    }

    //main方法
    public static void main(String[] args) {
        disc_res slList = new disc_res(); //此时slList->first->一个item为1,而next为null的结点
        slList.addFirst(2);
        slList.addFirst(3);
        slList.addFirst(4);
        slList.addFirst(5);
        slList.reverseList() ;
        slList.show();
    }

}
