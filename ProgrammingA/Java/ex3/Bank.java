import java.io.*;

public class Bank {
    public static void main(String[] args) {
        final int PIN = 1111;                   // 正しい暗証番号
        int balance = 100000;                   // 初期残高10万円
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            int attempts = 0;                   // 入力回数カウンタ
            int inputPin = 0;

            // 暗証番号入力 (最大3回まで)
            while (true) {
                System.out.print("暗証番号を入力してください: ");
                inputPin = Integer.parseInt(reader.readLine());
                if (inputPin == PIN) {
                    System.out.println("認証に成功しました。");
                    break;                     // 正しい暗証番号の場合、ループを抜ける
                } else {
                    attempts++;
                    if (attempts >= 3) {
                        System.out.println("暗証番号を3回間違えたため、処理を終了します。");
                        System.exit(0); // System.exit(0)でプログラムを終了
                    }
                    System.out.println("暗証番号が違います。もう一度入力してください。");
                }
            }

            // 引き出し額入力
            System.out.print("引き出す金額を入力してください: ");
            int withdraw = Integer.parseInt(reader.readLine());

            if (withdraw > balance) {
                System.out.println("残高が足りません。");
            } else {
                balance -= withdraw;
                System.out.println(withdraw + "円を引き出しました。");
            }

            // 残高表示
            System.out.println("現在の残高は " + balance + "円です。");

        } catch (IOException e) {
            System.out.println("入力エラー: " + e);
        } catch (NumberFormatException e) {
            System.out.println("数値を入力してください: " + e);
        }
    }
}
