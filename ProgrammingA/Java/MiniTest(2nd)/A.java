public class A {
    private static int counter = 0;      // A と B を合わせた生成数

    public A() {                         // 引数なしコンストラクタ
        counter++;                       // B 生成時も super() 経由でカウントされる
    }

    public String m() {                  // インスタンスメソッド m()
        return "a";
    }

    public static int getCounter() {     // 生成総数を返すクラスメソッド
        return counter;
    }
}
