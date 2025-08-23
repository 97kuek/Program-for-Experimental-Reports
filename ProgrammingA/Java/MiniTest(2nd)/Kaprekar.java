import java.io.*;
import java.util.*;

public class Kaprekar {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 桁がすべて同じなら "N/A"
        if (allSameDigits(N)) {
            System.out.println("N/A");
            return;
        }

        int count = 0;
        while (N != 6174) {
            int L = makeDesc(N); // 降順
            int S = makeAsc(N);  // 昇順
            N = L - S;
            count++;
        }
        System.out.println(count);
    }

    // 4桁を昇順に並べた整数を返す
    private static int makeAsc(int n) {
        int[] digits = getDigits(n);                    // 桁配列に分解
        Arrays.sort(digits);                            // 昇順にソート
        return toNumber(digits);                        // 桁配列を整数に変換
    }

    // 4桁を降順に並べた整数を返す
    private static int makeDesc(int n) {
        int[] digits = getDigits(n);
        Arrays.sort(digits);
        reverse(digits);                                // 降順に並べ替え
        return toNumber(digits);
    }

    // 数値を4桁の配列に分解
    private static int[] getDigits(int n) {
        int[] d = new int[4];
        for (int i = 3; i >= 0; i--) {                  // 下の桁から順に格納
            d[i] = n % 10;                              // iの位の数字を取り出す
            n /= 10;
        }
        return d;
    }

    // 配列を逆順にする
    private static void reverse(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int tmp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = tmp;
        }
    }

    // 桁配列を整数に変換
    private static int toNumber(int[] d) {
        return d[0] * 1000 + d[1] * 100 + d[2] * 10 + d[3];
    }

    // 桁がすべて同じかどうか
    private static boolean allSameDigits(int n) {
        int d = n % 10;
        for (int i = 0; i < 4; i++) {
            if (n % 10 != d) return false;
            n /= 10;
        }
        return true;
    }
}
