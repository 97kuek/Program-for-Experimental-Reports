public class Container {
    // 配列中の全要素を表示
    static void printArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
            if (i < a.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    // 配列の中から k を探索して見つかればインデックスを返す。なければ -1。
    static int search(int[] a, int k) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == k) {
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
        printArray(array);
        System.out.println(key + " at " + search(array, key));
    }
}
