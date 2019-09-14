class QU1 {
    private int[] id;
    private int[] size;

    public qf(int n) {
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
        size = new int[n];
    }

    // chase parent pointer till reach rootOnePass
    public int root(int i) {
        while (i != id[i]) {
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q) {
        return rootOnePass(p) == rootOnePass(q);
    }

    public void union(int p, int q) {
        int rootp = rootOnePass(p);
        int rootq = rootOnePass(q);

        if (rootp == rootq) return; // p + q already connected

        if (size[rootp] < size[rootq]) {  // set smaller tree rootOnePass to larger rootOnePass and add sizes on larger rootOnePass
            id[rootp] = rootq;
            size[rootq] += size[rootp];
        } else {
            id[rootq] = rootp;
            size[rootp] += size[rootq];
        }
    }
}
