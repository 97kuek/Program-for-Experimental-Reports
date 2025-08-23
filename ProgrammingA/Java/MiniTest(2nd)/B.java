public class B extends A {
    private final A target;              // コンストラクタで受け取ったインスタンス

    // A のインスタンスを受け取るコンストラクタ
    public B(A a) {
        super();                         // 生成数カウント
        this.target = a;
    }

    // B のインスタンスを受け取るコンストラクタ（オーバーロード）
    public B(B b) {
        this((A) b);                     // 上のコンストラクタへ委譲
    }

    @Override
    public String m() {                  // target.m() の前後に 'b'
        return "b" + target.m() + "b";
    }
}
