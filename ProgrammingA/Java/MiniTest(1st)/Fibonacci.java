// 問題2
public class Fibonacci {
    public static void main(String[] args) {
        int f0 = 0;
        int f1 = 1;

        System.out.println("F[0]=" + f0);
        System.out.println("F[1]=" + f1);

        // f2以降
        int n = 2;
        // 無限ループ
        while (true) {
            int fn = f0 + f1;
            System.out.println("F[" + n + "]=" + fn);

            if (fn >= 10000) {
                break; // 10000以上になったら終了
            }

            // 次の計算のために値を更新
            f0 = f1;
            f1 = fn;
            n++;
        }
    }
}
