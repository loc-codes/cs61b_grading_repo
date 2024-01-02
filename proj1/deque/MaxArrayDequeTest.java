package deque;
import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import static org.junit.Assert.*;

public class MaxArrayDequeTest {
    @Test
    public void testNoParamMax() {
        MaxArrayDeque<Integer> madInt = new MaxArrayDeque<Integer>(new IntComparator());
        madInt.addLast(1);
        madInt.addLast(100);
        madInt.addLast(10);
        int maxInt = madInt.max();
        assertEquals(100, maxInt);

        MaxArrayDeque<String> madString = new MaxArrayDeque<String>(new StringComparator());
        madString.addLast("A short string");
        madString.addLast("A longer string");
        madString.addLast("A very very long string");
        String maxString = madString.max();
        assertEquals("A very very long string", maxString);
    }




    private static class IntComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer i1, Integer i2) {
            return i1 - i2;
        }
    }

    private static class StringComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s1.length() - s2.length();
        }
    }
}
