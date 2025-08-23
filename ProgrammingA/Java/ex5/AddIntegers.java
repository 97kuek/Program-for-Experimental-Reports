public class AddIntegers {
    public static void main(String[] args) {
        if (args.length != 2) {     // argsはコマンドライン引数を格納する配列
            System.out.println("使用方法: java AddIntegers <整数1> <整数2>");
            return;
        }

        try {
            // コマンドライン引数を整数に変換
            int a = Integer.parseInt(args[0]);
            int b = Integer.parseInt(args[1]);

            // 加算結果を表示
            int sum = a + b;
            System.out.println("合計: " + sum);
        } catch (NumberFormatException e) {
            System.out.println("エラー: 引数は整数を指定してください。");
        }
    }
}
