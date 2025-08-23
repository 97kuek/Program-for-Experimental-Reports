// 問題3
public class RandomStats {
    public static void main(String[] args) {
        int n = 1000;                // 乱数の個数
        double max = 0.0;            // 最大値
        double min = 100.0;          // 最小値
        double sum = 0.0;            // 合計

        for (int i = 0; i < n; i++) {
            double x = Math.random() * 100; // 0以上100未満の乱数
            if (x > max) max = x;           // xが最大値より大きければ更新
            if (x < min) min = x;           // xが最小値より小さければ更新
            sum += x;
        }

        double avg = sum / n; // 平均値

        // 結果を表示
        System.out.printf("最大値は%.4fです。\n", max);
        System.out.printf("最小値は%.4fです。\n", min);
        System.out.printf("平均値は%.4fです。\n", avg);
    }
}
