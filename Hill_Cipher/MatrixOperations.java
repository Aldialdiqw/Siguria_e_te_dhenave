package Hill_Cipher;

public class MatrixOperations {

    // Krijimi i matrices dhe kthimi i tekstit ne ASCII
    public static int[][] textToMatrix(String text, int blockSize) {
        int[][] matrix = new int[text.length() / blockSize][blockSize];
        int row = 0, col = 0;

        //ascii
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isUpperCase(ch)) {
                matrix[row][col] = ch - 'A';//0 deri 25
            } else if (Character.isLowerCase(ch)) {
                matrix[row][col] = ch - 'a' + 26; //26 deri 51
            }
            col++;
            if (col == blockSize) {     //nested loop counter
                col = 0;
                row++;
            }
        }
        return matrix;
    }
    // Shumzimi i matrices
    public static int[][] multiplyMatrices(int[][] A, int[][] B) {
        int rowsA = A.length;
        int colsA = A[0].length;
        int colsB = B[0].length;
        int[][] C = new int[rowsA][colsB];


        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
                C[i][j] %= 26;
            }
        }
        return C;
    }
    // Kthimi i numrave te matrices ne shkronja
    public static String matrixToText(int[][] matrix) {
        StringBuilder builder = new StringBuilder();

        for (int[] row : matrix) {

            for (int val : row) {

                if (val < 26) {
                    builder.append((char) (val + 'A')); // shkronjat e mëdha
                } else {
                    builder.append((char) (val - 26 + 'a')); // shkronjat e vogla
                }
            }
        }
        return builder.toString();
    }

}
