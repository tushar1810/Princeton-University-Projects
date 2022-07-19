import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {


    private double[] frac;
    private int numtrials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n < 0 || trials < 0) {
            throw new IllegalArgumentException();
        }
        Percolation grid = new Percolation(n);
        int opens = 0;
        numtrials = trials;
        frac = new double[trials];
        for (int p = 0; p < trials; p++) {
            while (!grid.percolates()) {
                int i = StdRandom.uniform(1, n + 1);
                int j = StdRandom.uniform(1, n + 1);
                if (!grid.isOpen(i, j)) {
                    grid.open(i, j);
                    opens++;
                }
            }
            frac[p] = (double) opens / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(frac);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(frac);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return (double) (StdStats.mean(frac) - (1.96 * (StdStats.stddev(frac) / Math
                .sqrt(numtrials))));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return (double) (StdStats.mean(frac) + (1.96 * (StdStats.stddev(frac) / Math
                .sqrt(numtrials))));

    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats per = new PercolationStats(n, t);
        System.out.println("mean                    = " + per.mean());
        System.out.println("stddev                  = " + per.stddev());
        System.out.println(
                "95% confidence interval = [" + per.confidenceLo() + ", " + per.confidenceHi()
                        + "]");
    }

}
