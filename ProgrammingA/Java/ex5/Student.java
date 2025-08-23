public class Student {
    String name;
    int credit;

    // コンストラクタ
    public Student(String name, int credit) {
        this.name = name;
        this.credit = credit;
    }

    // 名前を返すメソッド
    public String getName() {
        return name;
    }

    // 卒業判定メソッド
    public boolean isOK() {
        return credit >= 100;
    }
}
