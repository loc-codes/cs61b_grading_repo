package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Nullable;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AList<Integer> buggy = new BuggyAList<Integer>();
        AList<Integer> truth = new AListNoResizing<Integer>();
        buggy.addLast(4);
        buggy.addLast(5);
        buggy.addLast(6);

        truth.addLast(4);
        truth.addLast(5);
        truth.addLast(6);
        assertEquals(buggy.size(), truth.size());

        assertEquals(truth.removeLast(), buggy.removeLast());
        assertEquals(truth.removeLast(), buggy.removeLast());
        assertEquals(truth.removeLast(), buggy.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> noResize = new AListNoResizing<>();
        BuggyAList<Integer> buggy = new BuggyAList<>();

        int N = 50000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                noResize.addLast(randVal);
                buggy.addLast(randVal);
                assertEquals(noResize.size(), buggy.size());
                //System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = noResize.size();
                //System.out.println("size: " + size);
            } else if (operationNumber == 2 && noResize.size() > 0) {
                int last1 = noResize.getLast();
                int last2 = buggy.getLast();
                assertEquals(last1, last2);
                assertEquals(noResize.size(), buggy.size());
                //System.out.println("getLast() returned " + last1);
            } else if (operationNumber == 3 && noResize.size() > 0) {
                int last1 = noResize.removeLast();
                int last2 = buggy.removeLast();
                assertEquals(last1, last2);
                assertEquals(noResize.size(), buggy.size());
                //System.out.println("removeLast() removed " + last1);
            }
        }
    }
}
