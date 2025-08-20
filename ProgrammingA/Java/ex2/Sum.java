import java.io.*;

public class Sum {
    public static void main(String[] args) {
        int n;
        String line;                                // line は文字列型 String

        // BufferedReaderを使用して標準入力を読み込む
        // InputStreamReaderはバイトストリームを文字ストリームに変換する
        // System.inは標準入力を表す
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // tryy-catch文で例外処理を行う
        try {
            System.out.print("nの値を入力してください: ");
            line = reader.readLine();               // readLine()は1行読み込む
            n = Integer.parseInt(line);             // parseInt(A)はAを整数に変換する

            if (n <= 0) {
                System.out.println("正の整数を入力してください。");
            } else {
                System.out.println("1+2+...+nの値は" + n * (n + 1) / 2 + "です。");
            }
        } catch (NumberFormatException e) {         // NumberFormatExceptionは数値変換例外
            System.out.println(e);
        } catch (IOException e) {                   // IOExceptionは入出力例外
            System.out.println(e);
        }
    }
}
