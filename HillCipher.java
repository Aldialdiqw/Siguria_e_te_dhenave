import java.util.Scanner;

public class HillCipher {
    private static final String KEY_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"+" ";
    private int[][] encryptKey;
    private int breakKey;

    public HillCipher(int[][] encryptKey) {
        this.encryptKey = modulus(encryptKey);
        checkDeterminant();
        this.breakKey = encryptKey.length;
    }

    private int[][] modulus(int[][] matrix) {
        int[][] result = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result[i][j] = matrix[i][j] % 37;
            }
        }
        return result;
    }

    private int replaceLetters(char letter) {
        return KEY_STRING.indexOf(letter);
    }

    private char replaceDigits(int num) {
        int index = num % 37;
        if (index < 26) {
            return (char) ('A' + index);
        } else {
            return (char) ('0' + (index - 26));
        }
    }
    private void checkDeterminant() {
        int det = determinant(encryptKey) % 37;
        if (det < 0) {
            det += 37;
        }
        if (greatestCommonDivisor(det, 37) != 1) {
            throw new IllegalArgumentException("Determinant modular 37 of encryption key is not co-prime w.r.t 37. Try another key.");
        }
    }

    private int determinant(int[][] matrix) {
        if (matrix.length == 1) {
            return matrix[0][0];
        }
        int sum = 0;
        int s;
        for (int i = 0; i < matrix.length; i++) {
            int[][] smaller = new int[matrix.length - 1][matrix.length - 1];
            for (int a = 1; a < matrix.length; a++) {
                for (int b = 0; b < matrix.length; b++) {
                    if (b < i) {
                        smaller[a - 1][b] = matrix[a][b];
                    } else if (b > i) {
                        smaller[a - 1][b - 1] = matrix[a][b];
                    }
                }
            }
            if (i % 2 == 0) {
                s = 1;
            } else {
                s = -1;
            }
            sum += s * matrix[0][i] * (determinant(smaller));
        }
        return sum;
    }

    private static int greatestCommonDivisor(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public String encrypt(String text) {
        text = processText(text.toUpperCase());
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < text.length(); i += breakKey) {
            int[] vec = new int[breakKey];
            for (int j = 0; j < breakKey; j++) {
                vec[j] = replaceLetters(text.charAt(i + j));
            }
            int[] batchEncrypted = matrixVectorMultiply(encryptKey, vec);
            for (int num : batchEncrypted) {
                encrypted.append(replaceDigits(num));
            }
        }
        return encrypted.toString();
    }
    public int[][] makeDecryptKey() {
        int det = determinant(encryptKey);
        int detInv = -1;
        for (int i = 0; i < 37; i++) {
            if ((det * i) % 37 == 1) {
                detInv = i;
                break;
            }
        }
        if (detInv == -1) {
            throw new IllegalArgumentException("No modular inverse found for the determinant. The encryption key might be invalid for decryption.");
        }

        int[][] invKey = new int[encryptKey.length][encryptKey[0].length];
        int[][] adjoint = adjointMatrix(encryptKey);
        for (int i = 0; i < adjoint.length; i++) {
            for (int j = 0; j < adjoint[0].length; j++) {
                invKey[i][j] = (detInv * adjoint[i][j]) % 37;
                if (invKey[i][j] < 0) {
                    invKey[i][j] += 37; // Ensure the result is non-negative
                }
            }
        }
        return invKey;
    }
    private int[][] adjointMatrix(int[][] matrix) {
        int[][] adjoint = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int sign = ((i + j) % 2 == 0) ? 1 : -1;
                int[][] minor = getMinor(matrix, i, j);
                adjoint[i][j] = sign * determinant(minor);
            }
        }
        return transpose(adjoint);
    }

    private int[][] getMinor(int[][] matrix, int row, int col) {
        int[][] minor = new int[matrix.length - 1][matrix[0].length - 1];
        for (int i = 0, mi = 0; i < matrix.length; i++) {
            if (i == row) continue;
            for (int j = 0, mj = 0; j < matrix[0].length; j++) {
                if (j == col) continue;
                minor[mi][mj] = matrix[i][j];
                mj++;
            }
            mi++;
        }
        return minor;
    }

    private int[][] transpose(int[][] matrix) {
        int[][] transposed = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }
        return transposed;
    }
    public String decrypt(String text) {
        text = text.toUpperCase();
        StringBuilder decrypted = new StringBuilder();
        int[][] decryptKey = makeDecryptKey();

        // Adjust the length of the text to ensure it's a multiple of breakKey
        int paddedLength = (text.length() / breakKey) * breakKey;
        text = text.substring(0, paddedLength);

        // Process complete batches of size breakKey
        for (int i = 0; i < paddedLength; i += breakKey) {
            int[] vec = new int[breakKey];
            for (int j = 0; j < breakKey; j++) {
                vec[j] = replaceLetters(text.charAt(i + j));
            }
            int[] batchDecrypted = matrixVectorMultiply(decryptKey, vec);
            for (int num : batchDecrypted) {
                decrypted.append(replaceDigits(num)); // Map the decrypted number back to a character from KEY_STRING
            }
        }
        return decrypted.toString();
    }

    private String processText(String text) {
        StringBuilder paddedText = new StringBuilder(text.toUpperCase());
        while (paddedText.length() % breakKey != 0) {
            paddedText.append(KEY_STRING.charAt(0)); // Padding with the first character of KEY_STRING
        }
        return paddedText.toString();
    }

    private int[] matrixVectorMultiply(int[][] matrix, int[] vector) {
        int[] result = new int[vector.length];
        for (int i = 0; i < matrix.length; i++) {
            int sum = 0; // Initialize sum for each row
            for (int j = 0; j < vector.length; j++) {
                sum += matrix[i][j] * vector[j];
            }
            result[i] = sum % 37; // Perform modulo operation after all multiplications
        }
        return result;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the order of the encryption key:");
        int order = scanner.nextInt();
        int[][] hillMatrix = new int[order][order];

        System.out.println("Enter each row of the " + order + "x" + order + " encryption key with space-separated integers");
        for (int i = 0; i < order; i++) {
            for (int j = 0; j < order; j++) {
                hillMatrix[i][j] = scanner.nextInt();
            }
        }

        HillCipher hc = new HillCipher(hillMatrix);

        System.out.println("Would you like to encrypt or decrypt some text? (1 or 2)");
        System.out.println("1. Encrypt");
        System.out.println("2. Decrypt");
        int option = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (option == 1) {
            System.out.println("What text would you like to encrypt?: ");
            String textE = scanner.nextLine();
            System.out.println("Your encrypted text is:");
            System.out.println(hc.encrypt(textE));
        } else if (option == 2) {
            System.out.println("What text would you like to decrypt?: ");
            String textD = scanner.nextLine();
            System.out.println("Your decrypted text is:");
            if(hc.decrypt(textD).charAt(hc.decrypt(textD).length()-1)=='A') {
                System.out.println(hc.decrypt(textD).substring(0, hc.decrypt(textD).length() - 1));
            }else{
                System.out.println(hc.decrypt(textD));
            }
        }
    }
}