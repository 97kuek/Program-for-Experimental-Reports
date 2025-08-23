public class Rectangle {
    // 幅と高さ
    int width;
    int height;

    // 幅と高さを設定するメソッド
    void setSize(int w, int h) {
        width = w;
        height = h;
    }

    // 面積を求めるメソッド
    int getArea() {
        return width * height;
    }

    // toStringメソッド（面積も表示）
    public String toString() {
        return "幅:" + width + ", 高さ:" + height + " の長方形 (面積:" + getArea() + ")";
    }
}
