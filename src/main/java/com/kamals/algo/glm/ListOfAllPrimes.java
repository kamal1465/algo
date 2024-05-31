package com.kamals.algo.glm;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a number max, find all primes <= max
 * 
 * Sieve of Eratosthenes
 */
public class ListOfAllPrimes
{
    public static void main(String[] args)
    {
        int max = 100;
        List<Integer> primes = getPrimes(max);
        System.out.println(primes);
    }

    private static List<Integer> getPrimes(int max)
    {
        boolean[] flags = new boolean[max + 1];
        List<Integer> primes = new ArrayList<>();
        int prime = 2;
        primes.add(prime);
        
        int sqrt = (int) Math.sqrt(max);

        while (prime <= sqrt)
        {
            crossOff(flags, prime);
            prime = getNextPrime(flags, prime);
            primes.add(prime);
        }
        collectPrimes(flags, primes, prime);
        return primes;
    }

    private static void collectPrimes(boolean[] flags, List<Integer> primes, int prime)
    {
        prime = getNextPrime(flags, prime);
        while (prime < flags.length)
        {
            primes.add(prime);
            prime = getNextPrime(flags, prime);
        }
    }

    private static int getNextPrime(boolean[] flags, int prime)
    {
        int k = prime + 1;
        while (k < flags.length && flags[k])
        {
            k++;
        }
        return k;
    }

    private static void crossOff(boolean[] flags, int prime)
    {
        for (int k = prime * prime; k < flags.length; k += prime)
        {
            flags[k] = true;
        }
    }


}
