public class ArrayTest {
    public static void main(String[] args) {
        int i,j;
 
        int[][] array1;
        array1 = new int[3][4];                         // new で領域を確保
 
        int[][] array2;
        array2 = new int[3][]; 
        for(i = 0; i < 3; i++) { 
            array2[i] = new int[4]; 
        }
 
        for(i = 0; i < 3; i++) {
            for(j = 0; j < 4; j++) {
                if (i == 0) {
                    array1[i][j] = i + j;
                    array2[i][j] = i - j;
                } else {
                    array1[i][j] = array1[i-1][j] + j;
                    array2[i][j] = array2[i-1][j] - j;
                }
            }
        }
    }
}
