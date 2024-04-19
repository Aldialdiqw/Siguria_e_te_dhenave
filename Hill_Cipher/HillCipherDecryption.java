package Hill_Cipher;

import Hill_Cipher.MatrixInverter;

import java.math.BigInteger;

import static Hill_Cipher.MatrixInverter.bigIntegerToInt;
import static Hill_Cipher.MatrixOperations.*;

public class HillCipherDecryption {

    // Decrypt ciphertext using Hill cipher
    public static String decrypt(String cipherText, int[][] keyMatrix) {
        // Convert key matrix to BigInteger[][] matrix
        BigInteger[][] bigKeyMatrix = MatrixInverter.intToBigInteger(keyMatrix);

        // Calculate determinant and inverse of key matrix
        BigInteger det = MatrixInverter.determinant(bigKeyMatrix);
        BigInteger mod = BigInteger.valueOf(26);
        BigInteger modInverse = MatrixInverter.modInverse(det, mod);
        BigInteger[][] inverseKeyMatrix = MatrixInverter.inverse(bigKeyMatrix);

        // Multiply modular inverse of determinant with the inverse key matrix
        BigInteger[][] inverseKey = MatrixInverter.multiply(modInverse, inverseKeyMatrix);

        // Convert ciphertext to matrix
        int[][] cipherMatrix = textToMatrix(cipherText, keyMatrix.length);

        // Multiply ciphertext matrix with inverse key matrix
        int[][] decryptedMatrix = multiplyMatrices(cipherMatrix, bigIntegerToInt(inverseKey));

        // Convert decrypted matrix to text
        return matrixToText(decryptedMatrix);
    }
}
