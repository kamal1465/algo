package com.kamals.algo.example;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.util.Base64;

public class EncryptionTest
{

    public static void main(String[] args) throws Exception
    {
        String cardNumber = "9876543210";

        String[] encryptedString = encrypt(cardNumber);

        System.out.println("Key = " + encryptedString[0]);
        System.out.println("Encrypted string = " + encryptedString[1]);

        String decryptedString = decrypt(encryptedString[0], encryptedString[1]);

        System.out.println("Decrypted string = " + decryptedString);
    }

    private static String[] encrypt(String cardNumber) throws Exception
    {
        SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();

        String key = Base64.getEncoder().encodeToString(secretKey.getEncoded());

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] plainText  = cardNumber.getBytes("UTF-8");
        byte[] cipherText = cipher.doFinal(plainText);

        String encrypt = Base64.getEncoder().encodeToString(cipherText);

        return new String[]{key, encrypt};
    }

    private static String decrypt(String key, String encryptedString) throws Exception
    {
        byte[] decodedKey = Base64.getDecoder().decode(key);

        SecretKey secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(encryptedString));

        return new String(plainText, Charset.forName("UTF-8"));
    }
}
