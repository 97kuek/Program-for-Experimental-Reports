import java.io.*;

public class Triangle {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            // 入力
            System.out.println("aの値を入力してください:");
            double a = Double.parseDouble(reader.readLine());

            System.out.println("bの値を入力してください:");
            double b = Double.parseDouble(reader.readLine());

            System.out.println("cの値を入力してください:");
            double c = Double.parseDouble(reader.readLine());

            // ヘロンの公式を用いて三角形の面積を計算する

            double s = (a + b + c) / 2.0;

            if (s - a > 0 && s - b > 0 && s - c > 0) {                      // ヘロンの公式の平方根内が正であればよい
                double area = Math.sqrt(s * (s - a) * (s - b) * (s - c));   // Math.sqrt()は平方根を求めるメソッド
                System.out.println("S=" + area);
            } else {
                System.out.println("三角形の成立条件が満たされません");
            }

        } catch (IOException e) {
            System.out.println("入力エラー: " + e);
        } catch (NumberFormatException e) {
            System.out.println("数値を入力してください: " + e);
        }
    }
}
