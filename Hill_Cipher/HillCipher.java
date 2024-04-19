package Hill_Cipher;

import java.util.Scanner;

import static Hill_Cipher.HillCipherDecryption.decrypt;
import static Hill_Cipher.HillCipherEncryption.encrypt;
import static Hill_Cipher.MatrixOperations.*;

public class HillCipher {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the plaintext: ");
        String plainText = scanner.nextLine().replaceAll("[^A-Za-z]", "");

       
        System.out.print("Enter the key matrix size (2 or 3): ");
        int keySize = scanner.nextInt();

        
        int[][] keyMatrix = new int[keySize][keySize];
        System.out.println("Enter the key matrix with spaces in between:");
      
        for (int i = 0; i < keySize; i++) {
            for (int j = 0; j < keySize; j++) {
                keyMatrix[i][j] = scanner.nextInt();
            }
        }

        for (int i = 0; i < keySize; i++) {
            for (int j = 0; j < keySize; j++) {
                System.out.print(keyMatrix[i][j]+" ");
            }
            System.out.println();
        }

        // Kontrollon nese ka nevoj per vlera x per me vlejt shumzimi i matrices me qels
        int padding = keySize - (plainText.length() % keySize);
        if (padding != keySize && padding != 0) {
            for (int i = 0; i < padding; i++) {
                plainText += 'X';
            }
        }

        String cipherText = encrypt(plainText.toUpperCase(), keyMatrix);
     
        System.out.println("Encrypted Text: " + cipherText);

        String cipherToPlain=decrypt(cipherText, keyMatrix);

        System.out.println("Decrypted Text: "+cipherToPlain);
    }
}
