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
        assertEquals(buggy, truth);

        buggy.removeLast();
        truth.removeLast();
        assertEquals(buggy, truth);
    }
}
