package deque;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class EqualsAndIteratorTests {

    @Test
    public void testEqualsLinkedListDequeAndArrayDeque() {
        LinkedListDeque<Integer> linkedListDeque = new LinkedListDeque<>();
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();

        // Add elements
        for (int i = 0; i < 5; i++) {
            linkedListDeque.addLast(i);
            arrayDeque.addLast(i);
        }

        assertTrue("LinkedListDeque and ArrayDeque with the same elements should be equal", linkedListDeque.equals(arrayDeque));
    }

    @Test
    public void testEqualsArrayDequeAndLinkedListDeque() {
        LinkedListDeque<Integer> linkedListDeque = new LinkedListDeque<>();
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();

        // Add elements
        for (int i = 0; i < 5; i++) {
            linkedListDeque.addLast(i);
            arrayDeque.addLast(i);
        }

        assertTrue("ArrayDeque and LinkedListDeque with the same elements should be equal", arrayDeque.equals(linkedListDeque));
    }

    @Test
    public void testIteratorHasNext() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        for (int i = 0; i < 5; i++) {
            arrayDeque.addLast(i);
        }

        Iterator<Integer> iterator = arrayDeque.iterator();
        for (int i = 0; i < 5; i++) {
            assertTrue("Iterator should have next", iterator.hasNext());
            iterator.next();
        }
        assertFalse("Iterator should not have next after reaching the end", iterator.hasNext());
    }

    @Test
    public void testIteratorNextValues() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        for (int i = 0; i < 5; i++) {
            arrayDeque.addLast(i);
        }

        Iterator<Integer> iterator = arrayDeque.iterator();
        for (int i = 0; i < 5; i++) {
            assertEquals("Iterator next value should match", Integer.valueOf(i), iterator.next());
        }
    }

    @Test
    public void testHasNextOnEmptyIterator() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        Iterator<Integer> iterator = arrayDeque.iterator();
        assertFalse("hasNext should return false on an empty iterator", iterator.hasNext());
    }

    @Test
    public void testMultipleIterators() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        for (int i = 0; i < 3; i++) {
            arrayDeque.addLast(i);
        }

        Iterator<Integer> iterator1 = arrayDeque.iterator();
        Iterator<Integer> iterator2 = arrayDeque.iterator();

        assertTrue("First iterator should have next", iterator1.hasNext());
        assertEquals("First iterator next value", Integer.valueOf(0), iterator1.next());

        assertTrue("Second iterator should have next", iterator2.hasNext());
        assertEquals("Second iterator next value", Integer.valueOf(0), iterator2.next());
    }
}
