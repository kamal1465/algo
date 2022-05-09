package com.kamals.algo.algos.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Starter
{

    private static <P extends Person> List<P> randomize(List<P> people)
    {
        List<P> newSet = new ArrayList<>();

        int N = people.size();

        Random random = new Random();
        while (N > 0)
        {
            int i = 0;
            if (N > 1)
            {
                i = random.nextInt(N);
            }
            P p = people.remove(i);
            N = people.size();
            newSet.add(p);
        }
        return newSet;
    }

    public static void main(String[] args)
    {
        //Number of men/women in the world
        int N = 10;

        //create men
        List<Man> men = new ArrayList<>();
        for (int i = 0; i < N; i++)
        {
            men.add(new Man("m" + (i + 1)));
        }

        //create women
        List<Woman> women = new ArrayList<>();
        for (int i = 0; i < N; i++)
        {
            women.add(new Woman("w" + (i + 1)));
        }

        //Set desire list for every man and woman
        for (int i = 0; i < N; i++)
        {
            men.get(i).setDesireList(randomize(women));
            women.get(i).setDesireList(randomize(men));
        }

    }
}
