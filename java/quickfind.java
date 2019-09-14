public class qf {
    private int[] id;

    public qf(int n) {
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < n; i++) {
            if id[i] == pid {
                id[i] = qid; // at most 2N + 2 accesses
            }
        }
    }
}