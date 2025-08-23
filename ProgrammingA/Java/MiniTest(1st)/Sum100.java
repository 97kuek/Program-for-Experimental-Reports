// 問題1

public class Sum100 {
    public static void main(String[] args) {
        int sum = 0;

        // 1から100までの自然数を順に足す
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }

        // 結果を表示
        System.out.println("1から100までの自然数の和は" + sum + "です。");
    }
}
