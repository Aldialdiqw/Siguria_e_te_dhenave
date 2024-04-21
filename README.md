
# Siguria_e_te_dhenave

#Hill_Cipher_Algorithm

##Overview

#Hill Cipher is a popular symmetric key encryption algorithm that was developed by Lester S. Hill in 1929. It is a polygraphic substitution cipher that encrypts plaintext by dividing it into blocks of n letters and transforming those blocks using a matrix-based algorithm. Hill Cipher uses matrix-based algebra to transform plaintext into ciphertext and vice versa. Hill Cipher can be implemented using different block sizes, allowing for greater flexibility in encryption and decryption.

## Instructions for Execution
Follow these step-by-step instructions to execute the program using any Java IDE:

1. **Open the Project**: Open your preferred Java IDE (Integrated Development Environment) such as IntelliJ IDEA, Eclipse, or NetBeans.

2. **Locate the HillCipher File**: Navigate to the directory where you have cloned the repository and locate the `HillCipher.java` file within the project structure.

3. **Compile the Program**: If necessary, compile the program within your IDE to ensure all dependencies are resolved.

4. **Run the Main Method**: In the `HillCipher.java` file, find the `main` method located at the bottom of the file. Run this method to start the program execution.

5. **Input Encryption Key**: Follow the prompts displayed in the console. You will be asked to enter the order of the encryption key matrix (the size of the matrix) and then input each row of the matrix with space-separated integers.

6. **Choose Operation**: After providing the encryption key, you will be prompted to choose whether you want to encrypt or decrypt text. Enter `1` to encrypt or `2` to decrypt.

7. **Enter Text**: Depending on your choice in the previous step, you will be asked to enter the text you wish to encrypt or decrypt.

8. **View Output**: Once you have provided the input text, the program will display the corresponding encrypted or decrypted text in the console.

## Algorithm Description

### Key Generation
1. **Encryption Key**: The user provides an encryption key matrix of a specified order (typically a square matrix).
2. **Determinant Calculation**: The determinant of the encryption key matrix is calculated. This determinant is used to ensure that the matrix is valid for decryption.
3. **Validity Check**: The algorithm verifies whether the determinant is coprime with 37 (the modulus used in the algorithm). If the determinant is not coprime with 37, the key is considered invalid for decryption.

### Encryption
1. **Plaintext Processing**: The plaintext is converted into blocks of fixed length. If the length of the plaintext is not a multiple of the block size, padding may be added.
2. **Matrix Multiplication**: Each plaintext block is multiplied by the encryption key matrix modulo 37. The result is a ciphertext block.
3. **Ciphertext Generation**: The resulting ciphertext blocks are concatenated to form the encrypted text.

### Decryption
1. **Modular Inverse Calculation**: To decrypt ciphertext, the algorithm calculates the modular inverse of the determinant of the encryption key matrix.
2. **Decryption Key Generation**: Using the modular inverse of the determinant, the algorithm generates the decryption key matrix.
3. **Matrix Multiplication**: Each ciphertext block is multiplied by the decryption key matrix modulo 37. This operation yields the original plaintext block.
4. **Padding Removal**: If padding was added during encryption, it is removed from the decrypted plaintext.
5. **Decrypted Text Generation**: The decrypted plaintext blocks are concatenated to form the original plaintext.

#Simple Columnar Transposition Cipher

#This Java program demonstrates the encryption using the Simple Columnar Transposition Cipher. The Simple Columnar Transposition Cipher is a basic form of transposition cipher where the text is written out in rows of a fixed length, and then read out again column by column. It provides a simple way of encrypting messages by rearranging the order of the letters.

    # #Encryption

    #The enkriptimi() method is responsible for encrypting the input message using 
    #the Simple Columnar Transposition Cipher. The program prompts the user to enter 
    #a message and the number of columns (key) for encryption.It ensures that the 
    #input key is a positive integer and handles invalid inputs gracefully.
    #The input message is converted to uppercase and spaces are removed before 
    #encryption. The message is padded with "#" characters if necessary to ensure it 
    #fits evenly into the specified number of columns. The message is then arranged 
    #into a table based on the specified key.The characters are read out from the 
    #table column by column to generate the cipher text.
    
#Example of the encryption of Simple Columnar Transposition Cipher:
![Shembull i enkriptimit te Simple Columnar Transposition Cipher](https://github.com/Aldialdiqw/Siguria_e_te_dhenave/assets/155023104/3bfc6699-bbd9-4b44-840f-a1ffa3e70fd1)

# Simple Columnar Transposition Cipher Decryption
#This program implements the decryption algorithm for the Simple Columnar Transposition Cipher. The decryption algorithm is a way to revert encrypted messages using the columnar transposition algorithm back to their original messages.
#Decryption stages:
#Choose a key: Firstly, you need to know the key used to encode the message. The key is the number of columns the message was divided into to create the encoded message.
#Decrypt the message: In this stage, the encoded message is read in a single line, and then it's divided into rows according to the number of columns in the key.
#Read the original message: After dividing the encoded message into rows based on the columns, it needs to be read from left to right to obtain the original message.
#Code structure: `SimpleColTr.java`: This is the main class containing the logic for decryption using the columnar transposition algorithm.



 ![Screenshot 2024-04-21 203724](https://github.com/Aldialdiqw/Siguria_e_te_dhenave/assets/98291577/ecd86ddb-a25b-4369-96bd-849ed763417e)

        
