package gh2;

import deque.Deque;
import deque.LinkedListDeque;
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
     private Deque<Double> buffer;
     private int stringCapacity;

    /* Create a guitar string of the given frequency.  */
    public GuitarString (double frequency) {
        stringCapacity = (int) Math.round(SR / frequency);
        buffer = new LinkedListDeque<Double>();
        for (int i = 0; i < stringCapacity; i++){
            buffer.addLast(.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        while (!buffer.isEmpty()) {
            buffer.removeLast();
        }
        for (int i = 0; i < stringCapacity; i++) {
            double toAdd = Math.random() - 0.5;
            buffer.addLast(toAdd);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        double newDouble = DECAY * (buffer.removeFirst() + buffer.get(0))/2;
        buffer.addLast(newDouble);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.get(0);
    }
}