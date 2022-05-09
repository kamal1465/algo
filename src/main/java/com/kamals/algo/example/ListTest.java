package com.kamals.algo.example;

import java.util.Arrays;
import java.util.List;

public class ListTest
{

    public static void main(String[] args)
    {
        List<String> l1 = Arrays.asList("abc", "def");

        for (String a : l1)
        {
            System.out.println(a);
        }
    }
}
