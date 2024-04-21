package Simple_Columnar_Transposition;

import java.util.Scanner;
import java.util.InputMismatchException;

public class SimpleColDec {

    private static Scanner in;

    public static void main(String[] args) {
        System.out.println("Dekriptimi i Simple Columnar Transposition Cipher");
        in = new Scanner(System.in);
        dekriptimi(); // Thirrja e metodes per dekriptim
    }

    private static void dekriptimi() {
        System.out.print("\nDekriptimi i Cipher Text: ");
         // Consumes the newline character
        String cipherText = in.nextLine().toUpperCase().replace(" ", "");
        StringBuilder decryptedText = new StringBuilder();

        System.out.print("Jepni qelesin (numrin e kolonave): ");
        int key = in.nextInt();

        int numOfRows = (int) Math.ceil((double) cipherText.length() / key);

        char[][] arr = new char[numOfRows][key];

        int z = 0;
        for (int j = 0; j < key; j++) {
            for (int i = 0; i < numOfRows; i++) {
                arr[i][j] = cipherText.charAt(z);
                z++;
            }
        }

        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < key; j++) {
                decryptedText.append(arr[i][j]);
            }
        }

        System.out.println("Plain Text: " + decryptedText.toString().replace("#", ""));
    }
}
