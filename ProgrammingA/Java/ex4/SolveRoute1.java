import java.io.*;

public class SolveRoute1 {
    static int M, N;
    public static int Route(int m, int n) {
        if (m>M || n>N) {
            return 0;
        }
        if (m==M && n==N) {
            return 1;
        }
        return Route(m+1, n) + Route(m, n+1);
    }
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Input M: ");
            M = Integer.parseInt(reader.readLine());
            System.out.print("Input N: ");
            N = Integer.parseInt(reader.readLine());
            int ans = Route(0, 0);
            System.out.println(ans);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}