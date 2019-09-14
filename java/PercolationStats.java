import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;

    public PercolationStats(int gridsize, int trials) {
        if (gridsize <= 0) {
            throw new IllegalArgumentException();
        }
        if (trials <= 0) {
            throw new IllegalArgumentException();
        }

        double[] percolationThresholds = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(gridsize);

            int runs = 0;
            while (!percolation.percolates()) {
                int column;
                int row;

                do {
                    column = 1 + StdRandom.uniform(gridsize);
                    row = 1 + StdRandom.uniform(gridsize);
                } while (percolation.isOpen(row, column));

                percolation.open(row, column);
                runs++;
            }

            percolationThresholds[i] = runs / (double) (gridsize * gridsize);
        }

        mean = StdStats.mean(percolationThresholds);
        stddev = StdStats.stddev(percolationThresholds);
        double confidenceFraction = (1.96 * stddev()) / Math.sqrt(trials);
        confidenceLo = mean - confidenceFraction;
        confidenceHi = mean + confidenceFraction;
    }

    public double mean() {  // sample mean of percolation threshold
        return mean;
    }

    public double stddev() {    // sample standard deviation of percolation threshold
        return stddev;
    }

    public double confidenceLo() {  // low  endpoint of 95% confidence interval
        return confidenceLo;
    }

    public double confidenceHi() {   // high endpoint of 95% confidence interval
        return confidenceHi;
    }


    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(N, T);
        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }
}
