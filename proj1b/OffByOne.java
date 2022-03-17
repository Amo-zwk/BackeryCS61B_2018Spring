public class OffByOne implements CharacterComparator {

    /**
     * step 1.创建一个名为OffByOne实现类CharacterComparator
     * step 2.TestOffByOne为类中的equalChars方法添加测试OffByOne(OK)
     * step 3.完成该equalChars方法并验证它是否有效(OK)
     * step 4.添加一个重载的新方法isPalindrome(OK)
     * step 5.添加测试以TestPalindrome测试您的新方法isPalindrome(OK)
     * step 6.完成新方法isPalindrome并验证它是否有效
     */
    //要重写接口中的所有方法
    @Override
    public boolean equalChars(char x, char y) {
        //题目要求
        return x - y == 1 || y - x == 1;
        //又来了,如果||是两个为假才是假,一个真则为真
        //意思就是说||左边为假的时候,右边也是要执行的,如果左边为真,右边不执行
    }
}
