package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {
        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
		lld1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

		lld1.addLast("middle");
		assertEquals(2, lld1.size());

		lld1.addLast("back");
		assertEquals(3, lld1.size());

		System.out.println("Printing out deque: ");
		lld1.printDeque();

    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty
		assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty
		assertFalse("lld1 should contain 1 item", lld1.isEmpty());

		lld1.removeFirst();
		// should be empty
		assertTrue("lld1 should be empty after removal", lld1.isEmpty());

    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {
        LinkedListDeque<String>  lld1 = new LinkedListDeque<String>();
        LinkedListDeque<Double>  lld2 = new LinkedListDeque<Double>();
        LinkedListDeque<Boolean> lld3 = new LinkedListDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }
    }

    @Test
    // Test get method
    public void getTest() {
        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();
        lld1.addFirst("front");
        lld1.addLast("middle");
        lld1.addLast("back");
        String actual1 = lld1.get(1);
        assertEquals("middle", actual1);
        String actual2 = lld1.get(3);
        assertEquals(null, actual2);
    }

    @Test
    // Test recursiveget method
    public void getRecursiveTest() {
        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();
        lld1.addFirst("front");
        lld1.addLast("middle");
        lld1.addLast("back");
        String actual1 = lld1.getRecursive(0);
        assertEquals("front", actual1);
        String actual2 = lld1.getRecursive(3);
        assertEquals(null, actual2);
        String actual3 = lld1.getRecursive(2);
        assertEquals("back", actual3);
    }

    @Test
    public void iteratorTest() {
        LinkedListDeque<Integer> lld = new LinkedListDeque<Integer>();
        lld.addLast(1);
        lld.addLast(2);
        lld.addLast(3);
        Iterator<Integer> lldIter = lld.iterator();
        int x = 0;
        while (lldIter.hasNext()){
            int getElem = lld.get(x);
            int iterElem = lldIter.next();
            assertEquals(getElem, iterElem);
            x += 1;
        }
    }

    @Test
    public void iterableTest() {
        LinkedListDeque<Integer> lld = new LinkedListDeque<Integer>();
        lld.addLast(2);
        lld.addLast(4);
        lld.addLast(6);
        Iterator<Integer> lldIter = lld.iterator();
        int x = 2;
        for (int i: lld){
            assertEquals(x, i);
            x += 2;
        }
    }

    @Test
    public void equalsTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        LinkedListDeque<Integer> lld2 = new LinkedListDeque<Integer>();
        lld1.addLast(2);
        lld1.addLast(4);
        lld1.addLast(6);

        lld2.addLast(2);
        lld2.addLast(4);
        lld2.addLast(6);
        assertTrue(lld1.equals(lld2));
        lld2.removeLast();
        assertFalse(lld1.equals(lld2));
    }

    @Test
    public void randomizedTest() {
        LinkedListDeque<Integer> lld = new LinkedListDeque<Integer>();

        int N = 50000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 6);
            if (operationNumber == 0) {
                int randVal = StdRandom.uniform(0, 100);
                lld.addFirst(randVal);
                System.out.println("addLast(" + randVal + ")");
            }
            else if (operationNumber == 1) {
                int randVal = StdRandom.uniform(0, 100);
                lld.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            }
            else if (operationNumber == 2) {
                System.out.println("isEmpty(): " + lld.isEmpty());
            }
            else if (operationNumber == 3) {
                int size = lld.size();
                System.out.println("size: " + size);
            }
            else if (operationNumber == 4 && !lld.isEmpty()) {
                int first = lld.removeFirst();
                System.out.println("removeFirst() removed " + first);
            }
            else if (operationNumber == 5 && !lld.isEmpty()) {
                int last = lld.removeLast();
                System.out.println("removeLast() removed " + last);
            }
            else if (operationNumber == 6 && !lld.isEmpty()) {
                int size = lld.size();
                int randVal = StdRandom.uniform(0, size);
                int get = lld.get(lld.size() -1);
                System.out.println("get(" + randVal + ") = " + get);
            }
        }
    }
}
