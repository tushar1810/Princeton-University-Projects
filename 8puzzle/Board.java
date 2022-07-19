/* *****************************************************************************
 *  Name: Tushar Sethi
 *  Date: 01/07/2022
 *  Description: Board
 **************************************************************************** */

import java.util.Arrays;

public class Board {
    private char[] puzz;
    private int n;
    private int zeropos;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        // this.tiles = tiles;
        n = dimension();
        int k = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                puzz[k] = (char) tiles[i][j];
                k++;
            }
        }


    }

    // string representation of this board
    public String toString() {
        String s = "";
        n = dimension();
        s = s + (char) n;
        s = s + "\n";
        for (int i = 0; i <= n * n - 1; i++) {
            s = s + (char) puzz[i];
            s = s + " ";
            if ((i + 1) % (n) == 0) {
                s = s + "\n";
            }
        }
        return s;
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        n = dimension();
        int count = 0;
        for (int i = 0; i <= n * n - 1; i++) {
            if (puzz[i] != 0) {
                if (puzz[i] != i + 1) {
                    count++;
                }
            }

        }
        return count;
    }

    int row(int k) {
        return (int) Math.ceil((double) k / (double) n);
    }

    int col(int k) {
        if (k % n == 0) return n;
        return k % n;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        n = dimension();
        int ans = 0;
        for (int i = 0; i <= n * n - 1; i++) {
            if (puzz[i] != 0) {
                int col, row;
                ans += Math.abs(col(i + 1) - col(puzz[i])) + Math.abs(row(i + 1) - row(puzz[i]));
            }
        }
        return ans;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if (y.getClass() != this.getClass()) {
            return false;
        }
        Board temp = (Board) y;
        if (Arrays.equals(temp.puzz, this.puzz)) return true;
        return false;

    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
//this function is inspired from https://github.com/jdvalera/8puzzle/blob/master/src/Board.java
Queue<Board> neighbors = new Queue<Board>();
		int emptyIndex;
		int row = 0;
		int col = 0;
		int up = 0, right = 0, down = 0, left = 0;
		ArrayList<Integer> blocks = new ArrayList<Integer>();
		for (emptyIndex = 0; emptyIndex < board.length; emptyIndex++) {
			if (board[emptyIndex] == 0) {
				
				col = xyFrom1D(emptyIndex)[0];
				row = xyFrom1D(emptyIndex)[1];
				
				if (checkBoundary(row - 1, col)) {
					up = xyTo1D(row - 1, col);
					blocks.add(up);
				}
				
				if (checkBoundary(row, col + 1)) {
					right = xyTo1D(row, col + 1);
					blocks.add(right);
				}
				
				if (checkBoundary(row + 1, col)) {
					down = xyTo1D(row + 1, col);
					blocks.add(down);
				}
				
				if (checkBoundary(row, col - 1)) {
					left = xyTo1D(row, col - 1);
					blocks.add(left);
				}
				
				break;
			}
		}
		for (int i = 0; i < blocks.size(); i++) {
			//System.out.println("BLOCKS " + blocks.get(i));
			int[] temp1d;
			int[][] temp2d;
			temp1d = copy1DTo1D(board);
			exch1D(temp1d, emptyIndex, blocks.get(i));
			temp2d = copy1DTo2D(temp1d, this.width);
			Board tempBoard = new Board(temp2d);
			neighbors.enqueue(tempBoard);
		}
		
		return neighbors;
        

    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
		int row = 0;
		int col = 0;
		int up = 0, right = 0, down = 0, left = 0;
		
		int[][] twin2d = new int[width][width];
		int[] twin1d = new int[board.length];
		
		// copy board to twin1d
		for (int i = 0; i < board.length; i++) {
			twin1d[i] = board[i];
		}
		
		for (int i = 0; i < twin1d.length; i++) {
			if (twin1d[i] != 0) {
				col = xyFrom1D(i)[0];
				row = xyFrom1D(i)[1];
						
				if (checkBoundary(row - 1, col)) {
					up = xyTo1D(row - 1, col);
					if (twin1d[up] != 0) {
						exch1D(twin1d, i, up);
						break;
					}
				}
				
				if (checkBoundary(row, col + 1)) {
					right = xyTo1D(row, col + 1);
					if (twin1d[right] != 0) {
						exch1D(twin1d, i, right);
						break;
					}
				}
				
				if (checkBoundary(row + 1, col)) {
					down = xyTo1D(row + 1, col);
					if (twin1d[down] != 0) {
						exch1D(twin1d, i, down);
						break;
					}
				}
				
				if (checkBoundary(row, col - 1)) {
					left = xyTo1D(row, col - 1);
					if (twin1d[left] != 0) {
						exch1D(twin1d, i, left);
						break;
					}
				}
			}
		}
		
		twin2d = copy1DTo2D(twin1d, this.width);
		return new Board(twin2d);

    }

    // unit testing (not graded)
    public static void main(String[] args) {

    }

}
