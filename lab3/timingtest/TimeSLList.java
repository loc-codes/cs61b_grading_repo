package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static SLList buildSLList(int n) {
        SLList<Integer> nElements = new SLList<Integer>();
        for (int i = 0; i < n; i += 1) {
            nElements.addLast(1);
        }
        return nElements;
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        int m = 10000;
        AList<Integer> Ns = new AList<Integer>();
        AList<Double> times = new AList<Double>();
        AList<Integer> opCounts = new AList<Integer>();
        Ns.addLast(1000);
        Ns.addLast(2000);
        Ns.addLast(4000);
        Ns.addLast(8000);
        Ns.addLast(16000);
        Ns.addLast(32000);
        Ns.addLast(64000);
        for (int i = 0; i < Ns.size(); i+= 1){
            SLList nElements = buildSLList(Ns.get(i));
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < m; j += 1){
                nElements.getLast();
            }
            double timeInSeconds = sw.elapsedTime();
            times.addLast(timeInSeconds);
            opCounts.addLast(m);
        }
        printTimingTable(Ns, times, opCounts);
//        1. Create an  SLList .
//        2. Add N items to the SLList .
//        3. Start the timer.
//        4. Perform M getLast operations on the SLList .
//        5. Check the timer. This gives the total time to complete all M operations.
    }

}
