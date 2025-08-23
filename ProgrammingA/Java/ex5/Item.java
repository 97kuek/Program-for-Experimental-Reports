public class Item {
    // 商品名と価格を表すフィールド
    private String name;
    private int price;

    // インスタンス数をカウントするクラス変数
    private static int numberOfInstances = 0;

    // コンストラクタ
    public Item(String name, int price) {
        this.name = name;
        this.price = price;
        numberOfInstances++;  // インスタンス生成時にカウント
    }

    // 商品名を返すゲッター
    public String getName() {
        return name;
    }

    // 価格を返すゲッター
    public int getPrice() {
        return price;
    }

    // インスタンス数を返すクラスメソッド
    public static int getNumberOfInstances() {
        return numberOfInstances;
    }

    // 商品情報を文字列として返す
    public String toString() {
        return name + ", " + price;
    }
}
