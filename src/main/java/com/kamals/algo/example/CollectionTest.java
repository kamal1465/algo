package com.kamals.algo.example;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class CollectionTest
{
    public static void main(String[] args)
    {
        test();
    }

    public static void test()
    {
        List<String> list = new ArrayList<>();
        list.add("x");
        list.add("y");
        list.add("z");
        list.add("y");

        for (String s : list)
        {
            if (s.equals("y"))
            {
                //list.remove(s);
            }
        }

        System.out.println(list);


        String s = "Java";
        String n = "SE";
        BiFunction<String, String, String> sf = (s1, n1) -> s1.concat(n1);
        System.out.println(sf.apply(s, n));

        list.stream().collect(Collectors.joining(",", "'", ";"));
        System.out.println(list.stream().map(o -> "'" + o + "'").collect(Collectors.joining(",")));
    }

    private static void test2()
    {
        List<String> medis = new ArrayList<>();

        boolean x = medis.stream().noneMatch(o -> o.equalsIgnoreCase("abc"));

        System.out.println(x);
    }

    private static void test3()
    {
        String s = ".";
        String[] ss = s.split("\\.");
        System.out.println(ss.length);
    }
}
