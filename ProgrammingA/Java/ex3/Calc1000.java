public class Calc1000 {
    public static void main(String[] args) {
        int sum = 0;
        int n = 0;

        while (sum <= 1000) {
            n++;
            sum += n;
        }

        System.out.println("1から" + n + "までの和が初めて1000を超えます。");
    }
}
