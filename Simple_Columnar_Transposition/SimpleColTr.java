import java.util.Scanner;
import java.util.InputMismatchException;


public class SimpleColTr {

    private static Scanner in;

    public static void main(String[] args){
        System.out.println("Enkriptimi i Simple Columnar Transposition Cipher");
        in = new Scanner(System.in);
        enkriptimi(); // e thirrim metoden enkriptimi
    }

    private static void enkriptimi(){
        System.out.print("Shkruaj nje mesazh: ");
        String plainText = in.nextLine().toUpperCase().replace(" ", "");
        StringBuilder msg = new StringBuilder(plainText);

        int key = 0;
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Jepni qelesin(numrin e kolonave): ");
            try {
                key = in.nextInt();
                if (key <= 0) {
                    System.out.println("Vlere invalide. Numri i kolonave duhet te jete pozitiv!");
                } else {
                    validInput = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Vlere invalide. Vlera qe jepni duhet te jete integjer!");
                in.next();
            }
        }
        // i mbushim hapesirat me thurje #
        int extraLetters = msg.length() % key;
        int dummyCharacters = key - extraLetters;

        if (extraLetters != 0){
            for (int i = 0; i < dummyCharacters; i++) {
                msg.append("#");
            }
        }

        int numOfRows = msg.length() / key;

        // Konvertimi i mesazhit te dhene ne tabele
        char[][] arr = new char[numOfRows][key];

        int z = 0;
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < key; j++) {
                arr[i][j] = msg.charAt(z);
                z++;
            }
        }

        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < key; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

        StringBuilder cipherText = new StringBuilder();

        for (int j = 0; j < key; j++) {
            for (int i = 0; i < numOfRows; i++) {
                cipherText.append(arr[i][j]);
            }
        }

        System.out.println("Cipher Text: " + cipherText);
    }

}
