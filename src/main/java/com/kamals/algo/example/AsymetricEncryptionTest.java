package com.kamals.algo.example;

import javax.crypto.Cipher;
import javax.xml.bind.DatatypeConverter;
import java.security.*;
import java.util.Scanner;

public class AsymetricEncryptionTest
{
    private static final String RSA = "RSA";
    private static final int KEY_SIZE = 512;
    private static Scanner sc;

    // Generating public & private keys
    // using RSA algorithm.
    public static KeyPair generateRSAKkeyPair() throws Exception
    {
        SecureRandom secureRandom = new SecureRandom();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);

        keyPairGenerator.initialize(KEY_SIZE, secureRandom);
        return keyPairGenerator.generateKeyPair();
    }

    // Encryption function which converts
    // the plainText into a cipherText
    // using private Key.
    public static byte[] do_RSAEncryption(String plainText, PublicKey privateKey) throws Exception
    {
        Cipher cipher = Cipher.getInstance(RSA);

        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        return cipher.doFinal(plainText.getBytes());
    }

    // Decryption function which converts
    // the ciphertext back to the
    // orginal plaintext.
    public static String do_RSADecryption(byte[] cipherText, PrivateKey publicKey) throws Exception
    {
        Cipher cipher = Cipher.getInstance(RSA);

        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] result = cipher.doFinal(cipherText);

        return new String(result);
    }

    // Driver code
    public static void main(String args[]) throws Exception
    {
        KeyPair keypair = generateRSAKkeyPair();

        String plainText = "4242420100058007|123|052026|Abc Xyz";

        PublicKey publicKey = keypair.getPublic();
        PrivateKey privateKey = keypair.getPrivate();

        System.out.println("The Public Key is: " + DatatypeConverter.printHexBinary(publicKey.getEncoded()));

        System.out.println("The Private Key is: " + DatatypeConverter.printHexBinary(privateKey.getEncoded()));

        byte[] cipherText = do_RSAEncryption(plainText, publicKey);

        System.out.println("The Encrypted Text is: " + DatatypeConverter.printHexBinary(cipherText));

        String decryptedText = do_RSADecryption(cipherText, privateKey);

        System.out.println("The Decrypted text is: " + decryptedText);
    }
}
