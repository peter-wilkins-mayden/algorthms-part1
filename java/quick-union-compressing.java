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

    public int rootTwoPass(int i) {
        int root = i;
        while (root != id[i]) {
            root = id[root];
        }
        while (i != id[i]) {
            id[i] = root; // untested!!!!
            i = id[i];
        }
        return root;
    }

    public int rootOnePass(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]]; // make every other node in path point to it's grandparent (halving path length)
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
