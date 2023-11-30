package com.kamals.algo.test;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TestString
{
    public static void main(String[] args)
    {
        String value = " Paytm postpaid, abc ";

        String[] valArr = value.split(",");
        //System.out.println(Arrays.stream(valArr).map(String::trim).collect(Collectors.toList()));

        System.out.println(mask("901,.uiehdewu3624329#^$&99abcd04543", 2, 2));
    }

    private static String mask(String s, int first, int last)
    {
        int len = s.length();

        if (len <= first + last)
        {
            return s;
        }

        String res = s.substring(first, len - last);
        res = res.replaceAll("[a-zA-Z0-9]", "*");

        if (first > 0)
        {
            res = s.substring(0, first) + res;
        }

        if (last > 0)
        {
            res = res + s.substring(len - last, len);
        }
        return res;
    }
}
