import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {

    static Palindrome palindrome = new Palindrome();
    //确保不要删除奇怪的行static Palindrome palindrome = new Palindrome();

    //这是给的测试的代码
    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        //因为这个方法返回的是一个接口类型
        //接口作为返回值,实际返回的是接口的类的实例,也就是new LinkedListDeque<>()
        //所以执行d.removeFirst的时候,实际上是执行的是它的类的实例
        //即new LinkedListDeque<>()
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
            //这里是判断两个字符串等不等
            //所以这里调用接口的方法的时候,实际上是new LinkedListDeque<>()这个对象在执行
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindromeCc() {

        OffByOne obo = new OffByOne();
        assertTrue(palindrome.isPalindrome("", obo));
    }

    //判定回文的测试方法
    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("aba"));
        assertTrue(palindrome.isPalindrome("aaccbbbccaa"));
        assertFalse(palindrome.isPalindrome("ab"));
        assertFalse(palindrome.isPalindrome("ababba"));
    }

    //判定回文的测试方法
    @Test
    public void testIsOffByOnePalindrome() {
        //接口传入的是接口对象或者接口的实现类
        CharacterComparator cc = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake", cc));
        assertFalse(palindrome.isPalindrome("aba", cc));
    }
}



