import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats 
{
    private double[] sumOfTrials;
    private int trials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials)
    {
        this.trials = trials;
        sumOfTrials = new double[trials];
        if ( n <= 0 || trials <= 0)
        {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < trials; i++)
        {
            int row = 0;
            int col = 0;
            Percolation p = new Percolation(n);
            while (!p.percolates())
            {
                row = StdRandom.uniformInt(1, n+1);
                col = StdRandom.uniformInt(1, n+1);
                p.open(row, col);
            }
            int count = p.numberOfOpenSites();
            double probability = (double)count / (n*n);
            sumOfTrials[i] = probability;
        }



        
    }

    // sample mean of percolation threshold
    public double mean()
    {
        return StdStats.mean(sumOfTrials);

    }

    // sample standard deviation of percolation threshold
    public double stddev()
    {
        return StdStats.stddevp(sumOfTrials);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo()
    {
        return mean() - 1.96/Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi()
    {
        return mean() + 1.96/Math.sqrt(trials);
    }

   // test client (see below)
   public static void main(String[] args)
   {
    PercolationStats per = new PercolationStats(3, 30);
    System.out.printf("mean                       = %f" + per.mean());
    System.out.printf("stddev                  = %f" + per.stddev());
    System.out.printf("95%% confidence interval = [%f,%f]" + per.confidenceLo(), per.confidenceHi());
   }

}