package com.kamals.algo.crypto;

import org.jasypt.util.text.AES256TextEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;
import org.jasypt.util.text.TextEncryptor;
import org.springframework.util.Assert;

import java.security.SecureRandom;

public class JsyptTest
{
    public static void main(String[] args)
    {
        encDec();
    }

    public static void encDec()
    {
        //String pwd = "storeordersituat";
        String pwd2 = "uP7IcKJZcFvfrbdW";

        BasicTextEncryptor encryptor1 = new BasicTextEncryptor();
        StrongTextEncryptor encryptor2 = new StrongTextEncryptor();
        AES256TextEncryptor encryptor3 = new AES256TextEncryptor();
        encryptor1.setPassword(pwd2);
        encryptor2.setPassword(pwd2);
        encryptor3.setPassword(pwd2);

        //generateRandomPassword();

        TextEncryptor[] textEncryptors = new TextEncryptor[]{encryptor1, encryptor2, encryptor3};

        String[] texts = new String[]{"Kamal", "datta123", "7ecETGlHjzs", "jioLower%40#2020!", "T6JH75aghHI9fVh"};

        for (String t : texts)
        {
            System.out.println(t);
            for (TextEncryptor textEncryptor : textEncryptors)
            {
                String encryptedText = textEncryptor.encrypt(t);
                System.out.print(encryptedText);
                String decryptedText = textEncryptor.decrypt(encryptedText);
                //System.out.print("|" + decryptedText);
                Assert.isTrue(t.equals(decryptedText));
                System.out.println();
            }
        }
    }

    private static void generateRandomPassword()
    {
        String alph = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        int n = alph.length();

        int k = 16;
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++)
        {
            int x = random.nextInt(n);
            sb.append(alph.charAt(x));
        }
        System.out.println(sb);
    }

    /*public static void enc2(String[] args)
    {
        PBEConfigurableTextEncryptor encryptor = new PBEConfigurableTextEncryptor();
        encryptor.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        encryptor.setPassword("hello");
        String encryptedText = encryptor.encrypt("Kamal");
        System.out.println("Encrypted Text: " + encryptedText);
    }

    public static void dec2(String[] args)
    {
        PBEConfigurableTextEncryptor encryptor = new PBEConfigurableTextEncryptor();
        encryptor.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        encryptor.setPassword("hello");
        String decryptedText = encryptor.decrypt(encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
    }*/

}
