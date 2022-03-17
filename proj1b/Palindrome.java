public class Palindrome {
    //接口泛型
    public Deque<Character> wordToDeque(String word) {
        //接口实现类，类一定要继承了该接口才行，然后类的对象执行接口的方法
        //即编译看左边，执行看右边
        //既然题目说的是返回一个Deque,那么我们自然要创建一个Deque对象
        Deque<Character> deque = new LinkedListDeque<>();
        //开始遍历获取字符串的每个元素用charAt
        //题目也说了，类似于LinkedListDeque的addLast
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
            //接口只是方法,实际执行的是对应的类
            //所以这里调用接口的方法的时候,实际上是new LinkedListDeque<>()这个对象在执行
        }
        //题目说了返回一个deque
        return deque;
        //返回接口实际是返回接口的实例,也就是deque的实例
        //实际返回的是new LinkedListDeque<>()
        //也就是new LinkedListDeque<>()执行一系列动作后的结果,即deque对应的类的实例
    }

    //判断回文字符串，用接口调用比较好
    //While you can technically not use a Deque at all, we strongly encourage you to do so.
    // It’s a good exercise in understanding how your choice of data structures
    // (in this case, Deque) will have a profound affect on how you write your code.
    //也就是说,希望你通过用Deque来实现这个函数功能
    //提示：考虑递归。有一个使用递归的非常漂亮的解决方案。
    // 您需要创建一个私有帮助器方法才能使其工作。
    //提示：不要使用getDeque 的方法。那只会使事情变得不必要地复杂。
    private boolean isPalindrome(Deque<Character> wordInDeque) { //但是这里还是要传入接口对象
        //因为特判了size为0和1的情况,现在的就是size大于1的情况
        while (wordInDeque.size() > 1) { //但是我们可以当作new LinkedListDeque<>()来用
            return wordInDeque.removeFirst() == wordInDeque.removeLast()
                    && isPalindrome(wordInDeque);
            //所以把wordToDeque(word)给了wordInDeque
            //当调用wordInDeque的时候就是调用的是它对应的类的实例也就是new LinkedListDeque<>()
            //new LinkedListDeque<>()
            //实际调用方法的话是这个wordInDeque的类的实例来执行接口的方法
            //C语言中&&两个真才为真,一个假就是假。相反||一个真则真，两个假才假
            //所以&&左边为真的时候,右边要执行
            //这里删除完一个，返回的元素相同，则继续对比下一个元素，即递归
        }
        //这是长度等于0或者等于1的时候，返回True，题目说的
        //任何长度为 1 或 0 的单词都是回文
        return true;
    }

    //wordToDeque(word)返回一个接口对象，但是操作的时候是当成new LinkedListDeque<>()来用
    public boolean isPalindrome(String word) {
        return isPalindrome(wordToDeque(word));
        //由于wordToDeque返回的是一个deque,所以它需要用一个deque的类型来接受
        //它返回的是一个deque,然后用另外一个deque来接受,但是它们都是同一个类的实例,就是对new LinkedListDeque<>操作
        //也就是这个wordToDeque(word)就是wordInDeque,而就是wordInDeque就是new LinkedListDeque<>()的实例
    }

    //用双指针是思想判断一下回文
//    public boolean isPalindrome(String word) {
//        if (word == null) {
//            return true;
//        }
//        int i = 0, j = word.length() - 1;
//        while (i < j) {
//            if (word.charAt(i) == word.charAt(j)) {
//                i++;
//                j--;
//            //否则不等,直接break
//            } else {
//                break;
//            }
//        }
//        boolean flag = true;
//        if (i >= j) {
//            flag = true;
//        } else {
//            flag = false;
//        }
//        return flag;
//    }

    //都是接口作为参数,接口只是负责提供做什么,实际去做的是接口对应的类的实例
    private boolean isPalindrome(Deque<Character> wordInDeque, CharacterComparator cc) {
        //如果每个
        while (wordInDeque.size() > 1) {
            //这里也是,cc有个比较的方法,但是实际执行的还是继承了该接口的类的实例
            //判断每个字符的差为1则是回文的
            //&&一个假就直接返回false
            return cc.equalChars(wordInDeque.removeFirst(), wordInDeque.removeLast())
                    && isPalindrome(wordInDeque, cc);
        }
        //否则的话,就肯定是size是小于等于1的
        //还是判断回文,和上面一样
        return true;
    }

    //Palindrome在此任务中，您的最终目标是使用以下签名向您的类添加第三个公共方法
    //public boolean isPalindrome(String word, CharacterComparator cc
    //CharacterComparator是一个有equalChars的接口
    //Returns true if characters are equal by the rules of the implementing class
    public boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindrome(wordToDeque(word), cc);
    }
}
