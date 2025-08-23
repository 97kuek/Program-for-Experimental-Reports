public class Circle {
    // 半径
    int radius;

    // 半径を設定するメソッド
    void setRadius(int r) {
        radius = r;
    }

    // 面積を求めるメソッド
    double getArea() {
        return 3.14 * radius * radius;
    }

    // toStringメソッド（半径と面積を表示）
    public String toString() {
        return "半径:" + radius + " の円 (面積:" + getArea() + ")";
    }
}
