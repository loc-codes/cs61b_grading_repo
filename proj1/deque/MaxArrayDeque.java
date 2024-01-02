package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;
    public MaxArrayDeque(Comparator<T> c) {
        //creates a MaxArrayDeque with the given Comparator
        comparator = c;
    }

    public T max() {
        //If the MaxArray is empty, simply return null  .
        if (isEmpty()) {
            return null;
        }
        //returns the maximum element in the deque as governed by the previously given Comparator
        return max(comparator);
    }


    public T max(Comparator<T> comp) {
        //If the MaxArray is empty, simply return null  .
        if (isEmpty()) {
            return null;
        }
        //returns the maximum element in the deque as governed by the parameter
        int maxIndex = 0;
        for (int i = 0; i < size(); i++) {
            if (comp.compare(get(i), get(maxIndex)) > 0) {
                maxIndex = i;
            }
        }
        return get(maxIndex);
    }
}
