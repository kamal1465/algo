package com.kamals.algo.test;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.util.Base64Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class TestBase64
{
    public static void main(String[] args) throws IOException
    {
        String decoded = new String(Base64.getDecoder().decode("MS1hamlv"));
        System.out.println(decoded);
        decoded = new String(Base64.getDecoder().decode("U1RPUkVfQURNSU4="));
        System.out.println(decoded);

        String encoded = Base64.getEncoder().encodeToString("store_admin".getBytes());
        System.out.println(encoded);

        encodeFile();
    }

    static void encodeFile() throws IOException
    {
        String inputFilename = "/Users/kamal.sultania/Downloads/bulk_node_priority_upload_sheet.18128d4d.xlsx";

        File inputFile = new File(inputFilename);
        byte[] ba = FileUtils.readFileToByteArray(inputFile);
        ba = Base64Utils.encode(ba);
        String s = new String(ba, StandardCharsets.UTF_8);
        System.out.println(s);

    }
}
