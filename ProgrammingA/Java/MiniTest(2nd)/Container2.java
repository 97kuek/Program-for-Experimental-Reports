public class Container2 {
    private int[] values;  // 探索対象の配列

    // コンストラクタで配列を受け取る
    Container2(int[] temp) {
        values = temp;
    }

    // 配列中の全要素を表示
    void printArray() {
        for (int i = 0; i < values.length; i++) {
            System.out.print(values[i]);
            if (i < values.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    // 配列の中から k を探索して見つかればインデックスを返す。なければ -1。
    int search(int k) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] == k) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = {
            100, 10, 1000, 50, 10000, 999, 5
        };
        int key = 10000;

        Container2 c = new Container2(array);
        c.printArray();
        System.out.println(key + " at " + c.search(key));
    }
}
