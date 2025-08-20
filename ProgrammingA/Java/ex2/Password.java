import java.io.*;

public class Password {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            // ユーザ名入力
            System.out.println("ユーザ名を入力してください:");
            String username = reader.readLine();
            System.out.println(username + "さんですね。パスワードを入力してください:");
            String password = reader.readLine();

            // 照合処理
            // 文字列同士の比較はequals()メソッドを使用
            if ((username.equals("user1") && password.equals("pasuwa-do")) ||
                (username.equals("user2") && password.equals("0dc6a4")) ||
                (username.equals("user3") && password.equals("hirakegoma\"!"))) {
                System.out.println("OK");
            } else {
                System.out.println("NG");
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
