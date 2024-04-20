# Siguria_e_te_dhenave

#Hill_Cipher_Algorithm

#Hill Cipher is a popular symmetric key encryption algorithm that was 
#developed by Lester S. Hill in 1929. It is a polygraphic substitution 
#cipher that encrypts plaintext by dividing it into blocks of n letters and 
#transforming those blocks using a matrix-based algorithm. Hill Cipher uses 
#matrix-based algebra to transform plaintext into ciphertext and vice versa.
#Hill Cipher can be implemented using different block sizes, allowing for 
#greater flexibility in encryption and decryption.

    #Encryption

        #Hill Cipher encrypts plaintext by dividing it into blocks of n letters 
        #and transforming those blocks using a matrix-based algorithm.
        #The encryption algorithm involves multiplying the block of plaintext 
        #by a key matrix to produce a block of ciphertext.
        #The key matrix used for encryption must be invertible, meaning that 
        #it has an inverse matrix that can be used for decryption.

        #Encryption formula: C=P*K mod 26 (C-ciphertext, P-plaintext, Key-key)

        #After starting execution of our program, you will be prompted to provide a 
        #plaintext (that will be encrypted) and a key. The key must be have more than 4 
        #characters and include either 4, 9, 16, 25 or 36 characters. In this way the
        #program will create a key square matrix that will devide the plaintext into
        #blocks the length of the key matrix size. After each block has been encrypted
        #u will get the ciphertext.

    #Decryption