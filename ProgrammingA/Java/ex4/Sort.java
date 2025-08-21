import java.io.*;
import java.util.*;

public class Sort {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("数値をスペース区切りで入力してください:");
        String line = reader.readLine();
        String[] parts = line.trim().split("\\s+");

        // 入力を整数配列に変換
        int[] numbers = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            numbers[i] = Integer.parseInt(parts[i]);
        }

        // 降順にソート
        // Arrays.sortを使用して昇順にソート
        Arrays.sort(numbers);
        // 配列を逆順に出力
        for (int i = numbers.length - 1; i >= 0; i--) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();
    }
}
