class PrintTest {
    public static void main(String[] args) {
        Rectangle r = new Rectangle();  // Rectangleオブジェクトを生成
        r.setSize(10, 20);          // 幅10、高さ20を設定   
        System.out.println(r);          // RectangleのtoString()

        Circle c = new Circle();        // Circleオブジェクトを生成
        c.setRadius(10);              // 半径10を設定
        System.out.println(c);          // CircleのtoString()
    }
}
