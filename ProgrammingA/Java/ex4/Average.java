import java.io.*;

public class Average {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("数値をスペース区切りで入力してください:");
        String line = reader.readLine();
        // line.trim()で前後の空白を削除し、split("\\s+")でスペース区切りで分割
        // "\\s+"は正規表現で、1つ以上の空白文字にマッチします。
        // isEmpty()で空行をチェック
        if (line == null || line.trim().isEmpty()) {
            System.out.println("入力がありません。");
            return;
        }
        String[] parts = line.trim().split("\\s+");
        
        // 入力を整数配列に変換
        int[] numbers = new int[parts.length];
        int sum = 0;
        for (int i = 0; i < parts.length; i++) {
            numbers[i] = Integer.parseInt(parts[i]);
            sum += numbers[i];
        }
        
        // 平均値を計算
        double avg = (double) sum / numbers.length;
        System.out.printf("平均値 %.1f%n", avg);
        
        // 平均以上を出力
        System.out.print("平均以上 ");
        for (int num : numbers) {
            if (num >= avg) {
                System.out.print(num + " ");
            }
        }
        System.out.println();
    }
}
