package Hill_Cipher;

import static Hill_Cipher.MatrixOperations.*;

public class HillCipherEncryption {
    // Thirr metoden per enkriptim

    public static String encrypt(String plainText, int[][] keyMatrix) {

        int[][] plainMatrix = textToMatrix(plainText, keyMatrix.length);

        int[][] cipherMatrix = multiplyMatrices(plainMatrix, keyMatrix);

        return matrixToText(cipherMatrix);
    }
}
