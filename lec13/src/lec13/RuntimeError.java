package lec13;

public class RuntimeError {
    @Test
    public void test() {
        ArrayMap<Integer, Integer> am = new ArrayMap<Integer, Integer>();
        am.put(2, 5);
        int expected = 5;
        assertEquals(expected, am.get(2));
    }
    /**
     * 错误的原因是JUnit的assertEquals方法重载了
     * 我们只需要把最后一行代码改为assertEquals((Integer)expected, am.get(2));
     * 明确调用的是assertEquals(Object, Object)方法即可。
     */
}
