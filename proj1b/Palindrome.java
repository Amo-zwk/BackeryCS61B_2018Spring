public class Palindrome {
    //接口泛型
    public Deque<Character> wordToDeque(String word){
        //接口实现类，类一定要继承了该接口才行，然后类的对象执行接口的方法
        //即编译看左边，执行看右边
        Deque<Character> deque = new LinkedListDeque<>();
        //开始遍历获取字符串的每个元素用charAt
        //题目也说了，类似于LinkedListDeque的addLast
        for(int i = 0 ; i < word.length() ; i++)  {
            deque.addLast(word.charAt(i));
        }
        //题目说了返回一个deque
        return deque ; //实际返回的是new LinkedListDeque<>()
    }
    //判断回文字符串，用接口调用比较好
    private boolean isPalindrome(Deque<Character> wordInDeque) { //但是这里还是要传入接口对象
        while (wordInDeque.size() > 1) { //但是我们可以当作new LinkedListDeque<>()来用
            return wordInDeque.removeFirst() == wordInDeque.removeLast() && isPalindrome(wordInDeque);
            //这里删除完一个，返回的元素相同，则继续对比下一个元素，即递归
        }
        //这是长度等于0或者等于1的时候，返回True，题目说的
        return true;
    }
    //wordToDeque(word)返回一个接口对象，但是操作的时候是当成new LinkedListDeque<>()来用
    public boolean isPalindrome(String word){
        return isPalindrome(wordToDeque(word)) ;
    }
    //
    private boolean isPalindrome(Deque<Character> wordInDeque, CharacterComparator cc) {
        while (wordInDeque.size() > 1) {
            return cc.equalChars(wordInDeque.removeFirst(), wordInDeque.removeLast()) && isPalindrome(wordInDeque, cc);
        }
        return true;
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindrome(wordToDeque(word), cc);
    }
}
