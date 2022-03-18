import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestArrayDequeGold {
    @Test
    public void testStudentArrayDeque() {
        StudentArrayDeque<Integer> actual = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> expected = new ArrayDequeSolution<>();
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < 100; ++i) {
            double randomNumber = StdRandom.uniform();
            if (randomNumber < 0.5) {
                actual.addLast(i);
                expected.addLast(i);
                message.append("addLast(").append(i).append(")\n");
            } else {
                actual.addFirst(i);
                expected.addFirst(i);
                message.append("addFirst(").append(i).append(")\n");
            }
        }

        for (int i = 0; i < 100; ++i) {
            double randomNumber = StdRandom.uniform();
            Integer x, y;
            if (randomNumber < 0.5) {
                x = expected.removeLast();
                y = actual.removeLast();
                message.append("removeLast()\n");
            } else {
                x = expected.removeFirst();
                y = actual.removeFirst();
                message.append("removeFirst()\n");
            }
            assertEquals(message.toString(), x, y);
        }

    }
}
