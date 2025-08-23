public class University {
    // Student配列型のインスタンスフィールド
    Student[] students;

    // listメソッド: studentsの全要素について情報を表示
    public void list() {
        for (Student s : students) {
            System.out.println(s.getName() + "は卒業できる: " + s.isOK());
        }
    }
}
