16
https://raw.githubusercontent.com/Chitturiarunkrishna/Hackerrank30DaysOfCode/master/Day%2029%20-%20Bitwise%20AND.java
import java.util.Scanner;

public class BitwiseAND 
{
    public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();

        for (int i = 0; i < T; i++) {
        
            int N = in.nextInt();
            int K = in.nextInt();

            int max = 0;

            for (int j = 1; j < N; j++) 
            {
                for (int k = j + 1; k <= N; k++) 
                {
                    int h = j & k;
                    
                    if ((h < K) && (max < h))
                      max = h;
                }
            }

            System.out.println(max);
        }
    }
}