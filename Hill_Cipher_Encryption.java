import java.util.Scanner;

public class Hill_Cipher_Encryption {


    public void userinput(){
        int n=0;
        do{
        System.out.println( "Enter the plain text: ");
    Scanner scan = new Scanner(System.in);
    String inputText = scan.nextLine();
    String result = inputText.replaceAll("\\s", "");
    char[] chars = inputText.toCharArray();
    
    n=chars.length;
        }while(n%2==1);
}

public void keyinput(){
    System.out.println("");

}



public  void CharToASCII(char [] letters) {
   
    // Create an int array to store ASCII values
    int[] asciiValues = new int[letters.length];

    // Convert each letter to ASCII value
    for (int i = 0; i < letters.length; i++) {
        asciiValues[i] = (int) letters[i];
    }

    // Print the ASCII values
    System.out.println("ASCII values:");
    for (int j : asciiValues) {
        asciiValues[j]= asciiValues[j]%26;

    }
}

}