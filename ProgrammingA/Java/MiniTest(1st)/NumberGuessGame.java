// 問題5
import java.io.*;

public class NumberGuessGame {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 0〜999 の乱数を生成
        int answer = (int)(Math.random() * 1000);

        int maxTries = 10; // 最大試行回数
        boolean guessed = false;

        for (int i = 1; i <= maxTries; i++) {
            System.out.print(i + "回目: 0～999までの整数を入力してください。\n");
            int guess = Integer.parseInt(br.readLine());

            if (guess == answer) {
                System.out.println(i + "回目で正解です。");
                guessed = true;
                break;
            } else if (guess < answer) {
                System.out.println("入力は正解よりも小さいです。");
            } else {
                System.out.println("入力は正解よりも大きいです。");
            }
        }

        if (!guessed) {     // 正解できなかった場合
            System.out.println("正解は " + answer + " でした。Game Over");
        }
    }
}
