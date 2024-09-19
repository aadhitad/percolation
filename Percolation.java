import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation 
{
    private int count;
    private boolean[][] grid;
    private int n;
    private WeightedQuickUnionUF roots;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n)
    {
        if (n <= 0)
        {
            throw new IllegalArgumentException();
        }
        else
        {
            this.n = n;
            grid = new boolean[n][n];
            roots = new WeightedQuickUnionUF(n*n + 2);
        }
    }
    private int index(int row, int col)
    {
        return((n*(row-1)) + col);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col)
    {
        if (n <= 0)
        {
            throw new IllegalArgumentException();
        }
        int grrow = row-1;
        int grcol = col - 1; 
        grid[grrow][grcol] = true;
        count++;
        int index = index(row,col);
        if (row == 1)
        {
            roots.union(index, 0);
        }
        if (row == n-1)
        {
            roots.union(index, n*n-1);
        }
        if (col == 1)
        {
            grid[grrow][grcol + 1] = true;
            roots.union(index(row, col), index(row, col + 1));
        }
        if (col == n)
        {
            grid[grrow][grcol - 1] = true;
            roots.union(index(row, col), index(row, col - 1));
        }
        if (row != n)
        {
            if (grid[grrow + 1][grcol])
            {
                roots.union(index(row, col), index(row + 1, col));
            }
        }
        //if (row!=0)
        if (row != 1)
        {
            if (grid[grrow - 1][col])
            {
                roots.union(index(row, col), index(row - 1, col));
            }
        }
        if (!(col == 1) && !(col == n) && !(row == 1) && !(row == n))
        {
            if (grid[grrow + 1][col])
            {
                roots.union(index(row,col), index(row + 1, col));
            }
            if (grid[grrow - 1][col])
            {
                roots.union(index(row, col), index(row - 1, col));
            }
            if (grid[grrow][col + 1])
            {
                roots.union(index(row, col), index(row, col + 1));
            }
            if (grid[grrow][col - 1])
            {
                roots.union(index(row, col), index(row, col - 1));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col)
    {
        if (n <= 0)
        {
            throw new IllegalArgumentException();
        }
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col)
    {
        if (index(row, col) <= 0 )
        {
            throw new IllegalArgumentException();
        }
        //if (roots.find(index(row,col)) == roots.find(roots[0]) == roots.find(roots[n*n+1]));
        if (roots.find(index(row,col)) == roots.find(0))
        {
            return true;
        }
        else
        {
            return false;
        }
       
    }

    // returns the number of open sites
    public int numberOfOpenSites()
    {
        return count;
    }

    // does the system percolate?
    public boolean percolates()
    {
        //if (roots.find(roots[0]) == roots.find(roots[n*n+1]))
        if (roots.find(0) == roots.find(n*n+1))
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    // test client (optional)
    public static void main(String[] args)
    {
        
    }
}
