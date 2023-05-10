package com.kamals.algo.test;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TestString
{
    public static void main(String[] args)
    {
        String value = " Paytm postpaid, abc ";

        String[] valArr = value.split(",");
        System.out.println(Arrays.stream(valArr).map(String::trim).collect(Collectors.toList()));
    }
}
