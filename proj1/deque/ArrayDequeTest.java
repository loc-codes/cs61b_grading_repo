package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class ArrayDequeTest {

//    @Test
//    public void testAdd() {
//        ArrayDeque<String> testArray = new ArrayDeque<String>();
//        assertEquals(4, testArray.frontIndex);
//        assertEquals(5, testArray.backIndex);
//        testArray.addLast("a");
//        assertEquals(4, testArray.frontIndex);
//        assertEquals(6, testArray.backIndex);
//        testArray.addLast("b");
//        assertEquals(4, testArray.frontIndex);
//        assertEquals(7, testArray.backIndex);
//        testArray.addFirst("c");
//        assertEquals(3, testArray.frontIndex);
//        assertEquals(7, testArray.backIndex);
//        testArray.addLast("d");
//        assertEquals(3, testArray.frontIndex);
//        assertEquals(0, testArray.backIndex);
//        assertEquals(4, testArray.size());
//    }

    @Test
    public void getTest() {
        ArrayDeque<String> testArray = new ArrayDeque<String>();
        testArray.addLast("b");
        testArray.addLast("c");
        testArray.addFirst("a");
        testArray.addLast("d");
        testArray.addLast("e");
        testArray.addLast("f");
        assertEquals("a", testArray.get(0));
        assertEquals("f", testArray.get(testArray.size()-1));
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
//        assertEquals(2, testArray.frontIndex, testArray.backIndex);
        assertEquals(8, testArray.size());
        assertEquals(testArray.get(0), testArray.get(0));
        testArray.addLast("i");
        assertEquals(9, testArray.size());
        assertEquals("a", testArray.get(0));
        assertEquals("i", testArray.get(testArray.size()-1));
        assertEquals("a", testArray.get(0));
        assertEquals("b", testArray.get(1));
        assertEquals("g", testArray.get(6));
        assertEquals("h", testArray.get(7));
        assertEquals("i", testArray.get(8));
    }

    @Test
    public void printDequeTest() {
        ArrayDeque<Integer> testArray = new ArrayDeque<Integer>();
        testArray.addLast(2);
        testArray.addLast(4);
        testArray.addLast(6);
        testArray.printDeque();
    }

    @Test
    public void equalsTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        ArrayDeque<Integer> ad2 = new ArrayDeque<Integer>();
        ad1.addLast(2);
        ad1.addLast(4);
        ad1.addLast(6);

        ad2.addLast(2);
        ad2.addLast(4);
        ad2.addLast(6);
        assertTrue(ad1.equals(ad2));
        ad2.removeLast();
        assertFalse(ad1.equals(ad2));
    }


    @Test
    public void randomizedTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();

        int N = 1000000;
        for (int i = 0; i < N; i += 1) {
            System.out.println("Operation " + i);
            int operationNumber = StdRandom.uniform(0, 6);
            if (operationNumber == 0) {
                int randVal = StdRandom.uniform(0, 100);
                ad.addFirst(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                int randVal = StdRandom.uniform(0, 100);
                ad.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 2) {
                System.out.println("isEmpty(): " + ad.isEmpty());
            } else if (operationNumber == 3) {
                int size = ad.size();
                System.out.println("size: " + size);
            } else if (operationNumber == 4 && !ad.isEmpty()) {
                int first = ad.removeFirst();
                System.out.println("removeFirst() removed " + first);
            } else if (operationNumber == 5 && !ad.isEmpty()) {
                int last = ad.removeLast();
                System.out.println("removeLast() removed " + last);
            } else if (operationNumber == 6 && !ad.isEmpty()) {
                int size = ad.size();
                int randVal = StdRandom.uniform(0, size);
                int get = ad.get(ad.size() - 1);
                System.out.println("get(" + randVal + ") = " + get);
            }
        }
    }
}
