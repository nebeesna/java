import java.util.Random;
public class Matrix
{
    private int[][] array;
    private int m;
    private int n;
    private static Random random = new Random();

    public Matrix()
    {
        m = 0;
        n = 0;
        array = new int[m][n];
    }
    public Matrix(int a, int b)
    {
        m = a;
        n = b;
        array = new int[m][n];
        SetRandomValues();
    }
    public int getM()
    {
        return m;
    }
    public int getN()
    {
        return n;
    }
    public int[][] getArr()
    {
        return array;
    }
    static private int GetRandomNumbers(int min, int max)
    {
        return random.nextInt(min, max);
    }
    public void SetRandomValues()
    {
        for (int i = 0; i < this.m; i++)
        {
            for (int j = 0; j < this.n; j++)
            {
                this.array[i][j] = GetRandomNumbers(0, 100);
            }
        }
    }
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < this.m; i++)
        {
            for (int j = 0; j < this.n; j++)
            {
                res.append(array[i][j]+"\t");
            }
            res.append("\n");
        }
        return res.toString();
    }
}