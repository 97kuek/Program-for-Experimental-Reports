import java.io.*;

public class SolveRoute2 {
    public static int comb(int n, int r){            // nCrの計算
        if (r == n || r == 0) {
            return 1;
        }
        return comb(n-1, r-1) + comb(n-1, r);       // 再帰的に計算
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Input M: ");
            int M = Integer.parseInt(reader.readLine());
            System.out.print("Input N: ");
            int N = Integer.parseInt(reader.readLine());
            int ans = comb(M + N, M);               // (M+N)C(M)を計算
            System.out.println(ans);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
