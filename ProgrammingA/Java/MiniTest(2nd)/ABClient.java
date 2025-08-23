public class ABClient {
    public static void main(String[] args) {
        System.out.println(A.getCounter());           // A, B の生成数（最初は 0）

        System.out.println(new A().m());              // A の m() → "a"

        System.out.println(new B(new A()).m());       // B に A を渡す → "bab"

        System.out.println(new B(new B(new A())).m()); // B に B(A) を渡す → "bbabb"

        System.out.println(A.getCounter());           // 生成総数を再度表示
    }
}
