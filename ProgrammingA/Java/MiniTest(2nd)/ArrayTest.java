public class ArrayTest {
    public static void main(String[] args) {
        int[] vector = {10, 2, -12, 8, 24, 32, 47, -8};
        int[][] matrix = {
            {56, 75, -70, 86, -55, -45},
            {79, -62, 0, -10, -21},
            {34, 3}
        };

        String str1 = "waseda";
        String str2 = "keio";

        System.out.println(vector[5]);
        System.out.println(vector.length);
        System.out.println(matrix[0][1] + matrix[1][2] + matrix[2][0]);
        System.out.println(matrix[2].length);
        System.out.println(matrix[1][0] % vector[3]);
        System.out.println(str1 + " vs " + str2);
        System.out.println(str1.length() + str2.length());
        System.out.println(str1.charAt(2));
        System.out.println(str1.replace('a', 'b'));
        System.out.println(str1.substring(3));
    }
}
