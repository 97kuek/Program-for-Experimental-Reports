public class Point {
    int x;
    int y;

    // コンストラクタ(インスタンス生成時に呼ばれる特殊なメソッド)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // toStringメソッド
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // mainメソッド
    public static void main(String[] args) {
        // 3点のPointインスタンスを配列に格納
        Point[] points = {
            new Point(100, 10),
            new Point(-5, 20),
            new Point(0, 0)
        };

        // 合計用変数
        int sumX = 0;
        int sumY = 0;

        int i;

        // 各点の表示と合計の計算
        for (i = 0; i<points.length; i++) {
            System.out.println(points[i].toString());
            sumX += points[i].x;
            sumY += points[i].y;
        }

        // 合計の表示
        System.out.println("x 座標値の合計 = " + sumX);
        System.out.println("y 座標値の合計 = " + sumY);
    }
}
