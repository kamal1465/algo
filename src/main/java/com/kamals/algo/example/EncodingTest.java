package com.kamals.algo.example;

import java.net.URLEncoder;

public class EncodingTest
{

    public static void main(String[] args) throws Exception
    {
        String s = URLEncoder.encode("%3Arelevance%3A10103428_02_Purifying_Technology%3ARO+%2B+UV", "UTF-8");


        String s2 = ":relevance:101039_03_Battery_Type:Lithium Polymer (Li-Poly)";

        String[] a = s2.split(":");

        for (String s3 : a)
        {
            System.out.println(s3);
        }

        for (int i = 0; i < a.length; i++)
        {
            System.out.println("Coupon" + (i + 1));
        }
//        System.out.println(s);
    }
}
