package com.kamals.algo.interview.walmart;

import java.util.*;

interface UserPopularity2
{
    void insert(long userId, int pop);

    void update(long userId, int pop);

    Integer delete(long userId);

    Long minimum();

    Long maximum();
}

class UserPopularity2Impl implements UserPopularity2
{
    private final Map<Long, Integer> userPop = new HashMap<>();
    private final TreeMap<Integer, Set<Long>> popData = new TreeMap<>();

    @Override
    public void insert(long userId, int p)
    {
        Set<Long> users = popData.computeIfAbsent(p, u -> new LinkedHashSet<>());
        users.add(userId);
        userPop.put(userId, p);
        popData.floorEntry(1);
        popData.higherEntry(1);
    }

    @Override
    public void update(long userId, int p)
    {
        Integer pop = userPop.get(userId);
        if (pop != null && pop == p)
        {
            System.out.println("Update(" + userId + ", " + p + ") -> Same data exists, no action needed");
            return;
        }
        if (pop != null)
        {
            delete(userId);
        }
        insert(userId, p);
    }

    @Override
    public Integer delete(long userId)
    {
        Integer p = userPop.remove(userId);
        if (p != null)
        {
            Set<Long> users = popData.get(p);
            if (users != null)
            {
                users.remove(userId);
                if (users.isEmpty())
                {
                    popData.remove(p);
                }
            }
        }
        return p;
    }

    @Override
    public Long minimum()
    {
        if (!popData.isEmpty())
        {
            return popData.firstEntry().getValue().iterator().next();
        }
        return null;
    }

    @Override
    public Long maximum()
    {
        if (!popData.isEmpty())
        {
            return popData.lastEntry().getValue().iterator().next();
        }
        return null;
    }
}
