class StudentTest {
    public static void main(String[] args) {
        Student s1 = new Student("青木", 80);
        Student s2 = new Student("田中", 120);
        System.out.println(s1.getName() + "は卒業できる: " + s1.isOK());
        System.out.println(s2.getName() + "は卒業できる: " + s2.isOK());
    }
}
