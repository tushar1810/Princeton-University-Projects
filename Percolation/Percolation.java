import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final boolean[][] perc;
    private final int size;
    private int opens;
    private final int last;
    private final int start;

    private final WeightedQuickUnionUF wqu;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();
        size = n;
        last = n * n + 1;
        start = 0;
        opens = 0;
        perc = new boolean[n][n];
        wqu = new WeightedQuickUnionUF(n * n + 2);
    }

    public void open(int row, int col) {

        if (row <= 0 || row > size || col <= 0 || col > size) {
            throw new IllegalArgumentException();
        }
        if (!isOpen(row, col)) {
            opens++;
        }

        perc[row - 1][col - 1] = true;
        if (row == 1) {
            wqu.union(col, start);
        }
        if (row == size) {
            wqu.union((row - 1) * size + col, last);
        }
        // else {
        if (row > 1 && isOpen(row - 1, col)) {
            wqu.union((row - 1) * size + col, (row - 2) * size + col);
        }
        if (row < size && isOpen(row + 1, col)) {
            wqu.union((row - 1) * size + col, (row) * size + col);
        }
        if (col < size && isOpen(row, col + 1)) {
            wqu.union((row - 1) * size + col, (row - 1) * size + col + 1);

        }
        if (col > 1 && isOpen(row, col - 1)) {
            wqu.union((row - 1) * size + col, (row - 1) * size + col - 1);
        }
        
    }


    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size) {
            throw new IllegalArgumentException();
        }
        return perc[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size) {
            throw new IllegalArgumentException();
        }
        else return wqu.find(start) == wqu.find((row - 1) * size + col);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return opens;
    }


    // does the system percolate?
    public boolean percolates() {

        return wqu.find(start) == wqu.find(last);
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}
