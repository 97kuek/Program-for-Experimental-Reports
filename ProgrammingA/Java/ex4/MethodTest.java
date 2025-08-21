public class MethodTest {
    public static void main(String[] args) {
        int n = 10000;
        System.out.println("1から" + n + "までの総和は " + Sum1(n) + " です。");
        System.out.println("1から" + n + "までの総和は " + Sum2(n) + " です。");
        System.out.println("1から" + n + "までの総和は " + Sum3(n) + " です。");
    }

    // 公式による計算
    public static int Sum1(int k) {
        return k * (k + 1) / 2;
    }

    // ループによる計算
    public static int Sum2(int k) {
        int tmp = 0;
        for (int i = 1; i <= k; i++) { 
            tmp = tmp + i;
        }
        return tmp;              
    }

    // 再帰による計算
    public static int Sum3(int k) {
        if (k == 0)
            return 0;            
        else
            return k + Sum3(k - 1);  
    }
}
