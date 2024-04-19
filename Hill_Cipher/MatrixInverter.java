package Hill_Cipher;

import java.math.BigInteger;

public class MatrixInverter {
    // Function to calculate the determinant of a square matrix
    public static BigInteger determinant(BigInteger[][] matrix) {
        int n = matrix.length;
        if (n == 1) {
            return matrix[0][0];
        }
        BigInteger det = BigInteger.ZERO;
        for (int j = 0; j < n; j++) {
            BigInteger term = matrix[0][j]
                    .multiply(BigInteger.valueOf((long) Math.pow(-1, j)))
                    .multiply(determinant(minor(matrix, 0, j)));
            det = det.add(term);
        }
        return det;
    }

    // Function to compute the minor of a matrix
    public static BigInteger[][] minor(BigInteger[][] matrix, int row, int col) {
        int n = matrix.length;
        BigInteger[][] minor = new BigInteger[n - 1][n - 1];
        for (int i = 0, p = 0; i < n; i++) {
            if (i == row) continue;
            for (int j = 0, q = 0; j < n; j++) {
                if (j == col) continue;
                minor[p][q] = matrix[i][j];
                q++;
            }
            p++;
        }
        return minor;
    }

    // Function to calculate the adjugate of a matrix
    public static BigInteger[][] adjugate(BigInteger[][] matrix) {
        int n = matrix.length;
        BigInteger[][] adjugate = new BigInteger[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                BigInteger sign = BigInteger.valueOf((long) Math.pow(-1, i + j));
                BigInteger minorDet = determinant(minor(matrix, i, j));
                adjugate[i][j] = sign.multiply(minorDet);
            }
        }
        return adjugate;
    }

    // Function to calculate the inverse of a matrix
    public static BigInteger[][] inverse(BigInteger[][] matrix) {
        int n = matrix.length;
        BigInteger det = determinant(matrix);
        if (det.equals(BigInteger.ZERO)) {
            throw new ArithmeticException("Matrix is singular, cannot find its inverse.");
        }
        BigInteger[][] inverse = new BigInteger[n][n];
        BigInteger determinantBigInt = det;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                BigInteger sign = BigInteger.valueOf((long) Math.pow(-1, i + j));
                BigInteger minorDet = determinant(minor(matrix, j, i));
                inverse[i][j] = sign.multiply(minorDet).divide(determinantBigInt);
            }
        }
        return inverse;
    }
    // Function to calculate the modular inverse of a number
    public static BigInteger modInverse(BigInteger a, BigInteger m) {
        BigInteger m0 = m;
        BigInteger y = BigInteger.ZERO;
        BigInteger x = BigInteger.ONE;

        if (m.equals(BigInteger.ONE))
            return BigInteger.ZERO;

        while (a.compareTo(BigInteger.ONE) > 0) {
            BigInteger q = a.divide(m);
            BigInteger t = m;

            m = a.mod(m);
            a = t;
            t = y;

            y = x.subtract(q.multiply(y));
            x = t;
        }
        if (x.compareTo(BigInteger.ZERO) < 0)
            x = x.add(m0);

        return x;
    }
    public static BigInteger[][] multiply(BigInteger scalar, BigInteger[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        BigInteger[][] result = new BigInteger[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = scalar.multiply(matrix[i][j]);
            }
        }
        return result;
    }
    public static int[][] bigIntegerToInt(BigInteger[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                try {
                    result[i][j] = matrix[i][j].intValueExact();
                } catch (ArithmeticException e) {
                    throw new ArithmeticException("Conversion from BigInteger to int resulted in overflow or loss of precision.");
                }
            }
        }
        return result;
    }
    // Function to convert int[][] matrix to BigInteger[][] matrix
    public static BigInteger[][] intToBigInteger(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        BigInteger[][] result = new BigInteger[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = BigInteger.valueOf(matrix[i][j]);
            }
        }
        return result;
    }
}

