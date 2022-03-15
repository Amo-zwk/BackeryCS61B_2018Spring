import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

public class ArrayDequeTest {

    //判空的测试
    @Test
    public void empty() {
        ArrayDeque<String> dq = new ArrayDeque<>() ;
        assertTrue(dq.isEmpty());
    }
    //判断size和头插
    @Test
    public void size() {
        ArrayDeque<String> dq = new ArrayDeque<>() ;
        dq.addFirst("first");
        String first = dq.removeFirst() ;
        assertEquals("first",first);
        dq.addFirst("second");
        assertEquals(1,dq.size());
    }
}
