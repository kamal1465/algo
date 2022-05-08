package com.kamals.algo.algos.util;

public class FuncTest
{
    public static void main(String[] args)
    {
        String s = "Abc";
        String s2 = process("abc", x -> x.concat(s));

        int i = process(123, o -> o/10);

        System.out.println(s2);
        System.out.println(i);
    }

    interface Func<T>
    {
        T run(T t);
    }

    static <T> T process(T t, Func<T> func)
    {
        return func.run(t);
    }
}
