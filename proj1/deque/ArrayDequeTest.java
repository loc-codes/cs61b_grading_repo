package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void testAdd() {
        ArrayDeque<String> testArray = new ArrayDeque<String>();
        assertEquals(4, testArray.frontIndex);
        assertEquals(5, testArray.backIndex);
        testArray.addLast("a");
        assertEquals(4, testArray.frontIndex);
        assertEquals(6, testArray.backIndex);
        testArray.addLast("b");
        assertEquals(4, testArray.frontIndex);
        assertEquals(7, testArray.backIndex);
        testArray.addFirst("c");
        assertEquals(3, testArray.frontIndex);
        assertEquals(7, testArray.backIndex);
        testArray.addLast("d");
        assertEquals(3, testArray.frontIndex);
        assertEquals(0, testArray.backIndex);
        assertEquals(4, testArray.size());
    }

    @Test
    public void getTest() {
        ArrayDeque<String> testArray = new ArrayDeque<String>();
        testArray.addLast("b");
        testArray.addLast("c");
        testArray.addFirst("a");
        testArray.addLast("d");
        testArray.addLast("e");
        testArray.addLast("f");
        assertEquals("a", testArray.getFirst());
        assertEquals("f", testArray.getLast());
        assertEquals("c", testArray.get(2));
        assertEquals("e", testArray.get(4));
        assertEquals(6, testArray.size());
    }

    @Test
    public void removeTest() {
        ArrayDeque<String> testArray = new ArrayDeque<String>();
        testArray.addLast("c");
        testArray.addLast("d");
        testArray.addFirst("b");
        testArray.addLast("e");
        String removeBack1 = testArray.removeLast();
        assertEquals("e", removeBack1);
        testArray.addLast("e");
        testArray.addLast("f");
        testArray.addLast("g");
        testArray.addLast("h");
        testArray.addFirst("a");
        assertEquals(8, testArray.size());
        String removeFront = testArray.removeFirst();
        String removeBack2 = testArray.removeLast();
        assertEquals(6, testArray.size());
        assertEquals("a", removeFront);
        assertEquals("h", removeBack2);
    }

    @Test
    public void resizeTest() {
        ArrayDeque<String> testArray = new ArrayDeque<String>();
        testArray.addLast("c");
        testArray.addLast("d");
        testArray.addFirst("b");
        testArray.addLast("e");
        testArray.addLast("f");
        testArray.addLast("g");
        testArray.addLast("h");
        testArray.addFirst("a");
        assertEquals(2, testArray.frontIndex, testArray.backIndex);
        assertEquals(8, testArray.size());
        assertEquals(testArray.get(0), testArray.getFirst());
        testArray.addLast("i");
        assertEquals(9, testArray.size());
        assertEquals("a", testArray.getFirst());
        assertEquals("i", testArray.getLast());
        assertEquals("a", testArray.get(0));
        assertEquals("b", testArray.get(1));
        assertEquals("g", testArray.get(6));
        assertEquals("h", testArray.get(7));
        assertEquals("i", testArray.get(8));
    }
}
