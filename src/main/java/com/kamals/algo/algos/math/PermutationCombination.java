package com.kamals.algo.algos.math;

import java.util.ArrayList;
import java.util.List;

public class PermutationCombination
{
    public static void main(String[] args)
    {
        PermutationCombination permutationCombination = new PermutationCombination();

        String alph = "abc";

        System.out.println(permutationCombination.permute(alph));

        int n = 2;
        System.out.println(permutationCombination.combinations(alph, n));
    }

    public List<String> permute(String alph)
    {
        List<String> all = new ArrayList<>();

        return all;
    }

    public List<String> combinations(String alph, int n)
    {
        List<String> all = new ArrayList<>();

        return all;
    }
}
