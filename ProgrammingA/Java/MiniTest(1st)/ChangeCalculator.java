// 問題4
import java.io.*;

public class ChangeCalculator {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("金額を打ち込んでください: ");
        int amount = Integer.parseInt(br.readLine());  // 文字列入力 → 整数変換

        // 紙幣・貨幣の種類を大きい順に配列で定義
        int[] money = {10000, 5000, 1000, 500, 100, 50, 10, 5, 1};
        String[] names = {
            "10000円札", "5000円札", "1000円札",
            "500円玉", "100円玉", "50円玉",
            "10円玉", "5円玉", "1円玉"
        };

        // 各紙幣・硬貨の枚数を計算して出力
        for (int i = 0; i < money.length; i++) {
            int count = amount / money[i];                      // i番目に大きい紙幣・硬貨の枚数
            amount %= money[i];                                 // 残りの金額
            System.out.println(names[i] + ": " + count + "枚"); // 結果を表示
        }
    }
}
