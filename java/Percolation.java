import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int gridsize;
    private final WeightedQuickUnionUF qf;
    private boolean[][] grid;
    private final int top;
    private final int bottom;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.gridsize = n;
        this.qf = new WeightedQuickUnionUF(n * n + 2);
        this.grid = new boolean[n][n];
        this.top = 0;
        this.bottom = n * n + 1;
    }

    private int fieldIndex(int row, int col) {
        return (row - 1) * this.gridsize + col;
    }

    private void connect(int fieldIndex, int x, int y) {
        try {
            if (isOpen(x, y)) {
                int neighbourFieldIndex = fieldIndex(x, y);
                qf.union(neighbourFieldIndex, fieldIndex);
            }
        }
        catch (IndexOutOfBoundsException e) {
            //
        }
    }

    public void open(int row, int col) {
        if (row > this.gridsize || row <= 0 || col > this.gridsize || col <= 0) {
            throw new IllegalArgumentException();
        }
        if (!this.isOpen(row, col)) {
            int field = this.fieldIndex(row, col);
            if (row == 1) {
                this.qf.union(this.top, field);
            }
            else if (row == this.gridsize) {
                this.qf.union(this.bottom, field);
            }
            connect(field, row + 1, col);
            connect(field, row - 1, col);
            connect(field, row, col - 1);
            connect(field, row, col + 1);

            grid[row - 1][col - 1] = true;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row > this.gridsize || row <= 0 || col > this.gridsize || col <= 0) {
            throw new IllegalArgumentException();
        }
        if (isOpen(row, col)) {
            return qf.connected(top, this.fieldIndex(row, col));
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int count = 0;
        for (boolean[] row : this.grid) {
            for (boolean b : row) {
                if (b) {
                    count++;
                }
            }
        }
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return qf.connected(this.top, this.bottom);
    }
}
