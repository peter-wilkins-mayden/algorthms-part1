import edu.princeton.cs.algs4.StdIn;

public class Permutation {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        if (k > 0) {
            RandomizedQueue<String> rq = new RandomizedQueue<>();
            while (!StdIn.isEmpty()) {
                rq.enqueue(StdIn.readString());
            }
            int i = 0;
            for (String val : rq) {
                if (++i > k) break;
                System.out.println(val);
            }
        }
    }
}