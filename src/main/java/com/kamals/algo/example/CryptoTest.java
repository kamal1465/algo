package com.kamals.algo.example;

//Legacy java 8 import
//import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

import org.apache.xerces.impl.dv.util.HexBin;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class CryptoTest
{

    public static void main(String[] args) throws Exception
    {
        String key = "Kamal Sultania";

        System.out.println(UUID.randomUUID());

        System.out.println(md5Hash(key));
        System.out.println(sha256Hash(key));
    }

    private static String md5Hash(String key) throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        final MessageDigest md = MessageDigest.getInstance("MD5");
        final byte[] digest = md.digest(key.getBytes("UTF-8"));

        final StringBuilder sb = new StringBuilder();
        for (byte b : digest)
        {
            sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
        }
        System.out.println(HexBin.encode(digest));
        return sb.toString();
    }

    private static String sha256Hash(String key) throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        final MessageDigest md = MessageDigest.getInstance("SHA-256");
        final byte[] digest = md.digest(key.getBytes("UTF-8"));

        final StringBuilder sb = new StringBuilder();
        for (byte b : digest)
        {
            sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
        }
        System.out.println(HexBin.encode(digest));
        return sb.toString();
    }
}
