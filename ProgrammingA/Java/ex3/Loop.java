import java.io.*;

public class Loop {
    public static void main(String[] args) {
        String line;
        int n;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                System.out.print("1より大きな整数を入力して下さい: ");
                line = reader.readLine();
                n = Integer.parseInt(line);
                if (n <= 1) { 
                    System.out.println("1より大きな整数を入力する必要があります。");
                    continue;                                   
                } else {
                    break;                                      
                }
            }

            int exponent = 1;
            int product = n;
            while (true) {                      // trueを使って無限ループ
                if (product >= 100000000) {
                    break;                                    
                }
                product = product * n;                       
                exponent++;
            }
            System.out.println(n + "を" + exponent + "乗すると1億以上になります。");
        } catch (NumberFormatException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
