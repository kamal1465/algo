package com.kamals.algo.crypto;

import org.jasypt.util.text.AES256TextEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;
import org.jasypt.util.text.TextEncryptor;
import org.springframework.util.Assert;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class JsyptTest
{
    public static void main(String[] args)
    {
        //uncomment to generate ciphers
//        encrypt();

        decrypt();

//        generateRandomPassword();
//        generateRandomPassword2();
    }

    public static void encrypt()
    {
        String pwd = "MWJBSzN0THVG";
        //String pwd = "Tkd1YlA3Z2dBYUlyazNFYmlU";

        BasicTextEncryptor encryptor1 = new BasicTextEncryptor();
        StrongTextEncryptor encryptor2 = new StrongTextEncryptor();
        AES256TextEncryptor encryptor3 = new AES256TextEncryptor();
        encryptor1.setPassword(pwd);
        encryptor2.setPassword(pwd);
        encryptor3.setPassword(pwd);

        //generateRandomPassword();

        //TextEncryptor[] textEncryptors = new TextEncryptor[]{encryptor1, encryptor2, encryptor3};
        TextEncryptor[] textEncryptors = new TextEncryptor[]{encryptor3};

        //String[] texts = new String[]{"Kamal", "datta123", "7ecETGlHjzs", "jioLower%40#2020!", "T6JH75aghHI9fVh", "A46LUu74hdgCGR2T9g", "7ecETGlHjzs"};

        String[] texts = new String[]{"tibcouat",
                "12345",
                "12345",
                "12345",
                "qa_uat",
                "nodeconfig",
                "VLtttjuOMhV0ncfGxSuhJ4t71L6vJ8iNySY1RmdtEkO",
                "tibcouat",
                "tibcouat",
                "somuser",
                "tibcouat",
                "qa_uat"};

        String[] textsRepl = new String[]{"tibcoadmin",
                "12345",
                "12345",
                "12345",
                "qa_uat",
                "7N0dEcOnFIg@531$R",
                "changeit",
                "tibcoadmin",
                "tibcoadmin",
                "somuser",
                "com_user",
                "qa_uat"};

        String[] textsProd = new String[]{"relevancy_ajio@user",
                "2d5r4d31-142o1-87h-g546-876hdf0a0341",
                "2d5r4d31-142o1-87h-g546-876hdf0a0341",
                "99td3-zxan2-d6f779y5-54sdfy7lk-xkd89",
                "SOM_USER",
                "7N0dEcOnFIg@531$R",
                "changeit",
                "relevancy_ajio@user",
                "relevancy_trnd@user",
                "somuser",
                "com_user",
                "SOM_USER"};

        String[] ciphers = new String[]{"weWSIuhBOFgzmR8kGeQ5SQBZoNAS4PGIRrUuTycbow2IGXrDojJHwaYYXCSNg7Lm"};

        for (String t : textsProd)
        {
            System.out.print(t + " ENC(");
            for (TextEncryptor textEncryptor : textEncryptors)
            {
                String encryptedText = textEncryptor.encrypt(t);
                System.out.print(encryptedText);
                String decryptedText = textEncryptor.decrypt(encryptedText);
                //System.out.print("|" + decryptedText);
                Assert.isTrue(t.equals(decryptedText));
                System.out.println(")");
            }
        }

        System.out.println();

        /*for (String t : ciphers)
        {
            System.out.print(t + " DEC(");
            for (TextEncryptor textEncryptor : textEncryptors)
            {
                String decryptedText = textEncryptor.decrypt(t);
                System.out.println(decryptedText + ")");
            }
        }*/
    }

    public static void decrypt()
    {
        String pwd = "storeordersituat";
        //String pwd = "Tkd1YlA3Z2dBYUlyazNFYmlU";

        BasicTextEncryptor encryptor1 = new BasicTextEncryptor();
        StrongTextEncryptor encryptor2 = new StrongTextEncryptor();
        AES256TextEncryptor encryptor3 = new AES256TextEncryptor();
        encryptor1.setPassword(pwd);
        encryptor2.setPassword(pwd);
        encryptor3.setPassword(pwd);
        TextEncryptor[] textEncryptors = new TextEncryptor[]{encryptor3};
        String[] ciphers = new String[]{"cE57M3PBT6eLPdn1BMw3ocWHhXkcLR1ZQ/RDZjyBsTnTDe+jQy80UEqwF12plBmJiNrRICUT1DuDyYU+nKhBxA=="};

        for (String t : ciphers)
        {
            System.out.print(t + " ");
            for (TextEncryptor textEncryptor : textEncryptors)
            {
                String decryptedText = textEncryptor.decrypt(t);
                System.out.println(decryptedText);
                //Assert.isTrue(t.equals(decryptedText));
                //System.out.println(")");
            }
        }
    }

    private static void generateRandomPassword()
    {
        String alph = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        int n = alph.length();

        int k = 18;
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++)
        {
            int x = random.nextInt(n);
            sb.append(alph.charAt(x));
        }
        System.out.println(sb);
    }

    private static void generateRandomPassword2()
    {
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[32]; // 32 bytes for a 256-bit key
        secureRandom.nextBytes(key);
        String str = new String(key, StandardCharsets.UTF_8);
        System.out.println(str);
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
