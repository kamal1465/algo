package com.kamals.algo.example;

import java.util.Random;

public class RandomTest
{
    public static void main(String[] args)
    {
        int x = 1000;
        Random random = new Random();
        int y = random.nextInt(31);
        int z = x - 1;
        if ((x & z) == 0)
        {
            y = (int) ((long) x * (long) y >> 31);
        }
        else
        {
            for (int i = y; i - (y = i % x) + z < 0; i = random.nextInt(31))
            {

            }
        }

        System.out.println(y);

    }
}
