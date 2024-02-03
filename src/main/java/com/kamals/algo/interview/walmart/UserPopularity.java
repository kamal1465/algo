package com.kamals.algo.interview.walmart;

import java.util.*;

/**
 * Given Users and their popularity (an integer), Write program to efficiently answer most popular and least popular user any time.
 * If there is a tie, return older/newer user
 */
public class UserPopularity
{
    Map<String, Integer> userMap = new HashMap<>();
    TreeMap<Integer, List<String>> popMap = new TreeMap<>();

    void addUser(String user, int popularity)
    {
        Integer oldPop = userMap.put(user, popularity);
        if (oldPop != null)
        {
            List<String> list = popMap.get(oldPop);
            list.remove(user);
        }
        List<String> list = popMap.computeIfAbsent(popularity, k -> new LinkedList<>());
        list.add(user);
    }

    void removeUser(String user)
    {
        Integer pop = userMap.remove(user);
        if (pop != null)
        {
            List<String> list = popMap.get(pop);
            if (list != null)
            {
                list.remove(user);
            }
        }
    }

    void increasePopularity(String user, int pop)
    {
        Integer oldPop = userMap.merge(user, pop, Integer::sum);
        if (oldPop != null)
        {
            List<String> list = popMap.get(oldPop);
            list.remove(user);
        }
        List<String> list = popMap.computeIfAbsent(pop, k -> new LinkedList<>());
        list.add(user);
    }

    void decreasePopularity(String user, int pop)
    {
        Integer oldPop = userMap.merge(user, -pop, Integer::sum);
        if (oldPop != null)
        {
            List<String> list = popMap.get(oldPop);
            list.remove(user);
        }
        List<String> list = popMap.computeIfAbsent(userMap.get(user), k -> new LinkedList<>());
        list.add(user);
    }

    String getMostPopular()
    {
        String user = null;
        Map.Entry<Integer, List<String>> pop = popMap.firstEntry();
        if (pop != null && pop.getValue() != null)
        {
            user = pop.getValue().get(0);
        }
        return user;
    }

    String getLeastPopular()
    {
        String user = null;
        Map.Entry<Integer, List<String>> pop = popMap.lastEntry();
        if (pop != null && pop.getValue() != null)
        {
            user = pop.getValue().get(0);
        }
        return user;
    }
}
