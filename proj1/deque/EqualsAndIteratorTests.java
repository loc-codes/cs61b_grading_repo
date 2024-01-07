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
        for (int i = 0; i < 128; i++) {
            linkedListDeque.addLast(i);
            arrayDeque.addLast(i);
        }

        assertTrue(linkedListDeque.equals(arrayDeque));
    }

    @Test
    public void testEqualsArrayDequeAndLinkedListDeque() {
        LinkedListDeque<Integer> linkedListDeque = new LinkedListDeque<>();
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();

        // Add elements
        for (int i = 0; i < 128; i++) {
            linkedListDeque.addLast(i);
            arrayDeque.addLast(i);
        }

        assertTrue(arrayDeque.equals(linkedListDeque));
    }

    @Test
    public void testDeepEquals() {
        LinkedListDeque<Object> linkedListDeque1 = new LinkedListDeque<>();
        ArrayDeque<Object> arrayDeque1 = new ArrayDeque<>();
        LinkedListDeque<Object> linkedListDeque2 = new LinkedListDeque<>();
        ArrayDeque<Object> arrayDeque2 = new ArrayDeque<>();

        // Adding same elements
        Object[] elements = {1, "2", null, 3.0, "four"};
        for (Object elem : elements) {
            linkedListDeque1.addLast(elem);
            arrayDeque1.addLast(elem);
        }

        // Adding same elements in a different order
        for (int i = elements.length - 1; i >= 0; i--) {
            linkedListDeque2.addLast(elements[i]);
            arrayDeque2.addLast(elements[i]);
        }

        assertEquals(linkedListDeque1, arrayDeque1);
        assertEquals(arrayDeque1, linkedListDeque1);
        assertNotEquals(linkedListDeque1, linkedListDeque2);
        assertNotEquals(arrayDeque1, arrayDeque2);
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
        for (int i = 0; i < 64; i++) {
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
