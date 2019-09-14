class QU1 {
    private int[] id;

    public qf(int n) {
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }
// chase parent pointer till reach rootOnePass
    public int root(int i){
        while(i != id[i]){
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q) {
        return rootOnePass(p) == rootOnePass(q);
    }

    public void union(int p, int q) {
        int root-p = rootOnePass(p);
        int root-q = rootOnePass(q);
        id[root-p] = root-q
    }
}