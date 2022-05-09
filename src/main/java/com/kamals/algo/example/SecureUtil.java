package com.kamals.algo.example;

import javax.crypto.Cipher;
import javax.xml.bind.DatatypeConverter;
import java.security.*;

public class SecureUtil
{
    private static final String RSA = "RSA";
    private static final int KEY_SIZE = 512;

    private static PublicKey publicKey;
    private static Cipher enCipher;
    private static Cipher deCipher;

    static
    {
        try
        {
            SecureRandom secureRandom = new SecureRandom();
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
            keyPairGenerator.initialize(KEY_SIZE, secureRandom);
            KeyPair keypair = keyPairGenerator.generateKeyPair();

            publicKey = keypair.getPublic();
            enCipher = Cipher.getInstance(RSA);
            enCipher.init(Cipher.ENCRYPT_MODE, publicKey);

            deCipher = Cipher.getInstance(RSA);
            deCipher.init(Cipher.DECRYPT_MODE, keypair.getPrivate());
        }
        catch (Exception e)
        {

        }
    }

    public static String getPublicKey()
    {
        return DatatypeConverter.printHexBinary(publicKey.getEncoded());
    }

    public static String decrypt(String cipherText) throws Exception
    {
        return new String(deCipher.doFinal(DatatypeConverter.parseHexBinary(cipherText)));
    }

    public static String encrypt(String text) throws Exception
    {
        return DatatypeConverter.printHexBinary(enCipher.doFinal(text.getBytes()));
    }

    public static void main(String[] args) throws Exception
    {
        String text = "4242420100058007|123|052026|Abc Xyz";
        String cipherText = encrypt(text);
        String decryptText = decrypt((cipherText));

        System.out.println("The Public Key is: " + getPublicKey());
        System.out.println("The Encrypted Text is: " + cipherText);
        System.out.println("The Decrypted text is: " + decryptText);
    }
}
