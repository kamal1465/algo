package com.kamals.algo.algos.graph;

import java.util.*;

public class FindCycleUndirectedBFS
{
    public static List<Integer> findCycle(Map<Integer, List<Integer>> adjList)
    {
        List<Integer> cycle = new ArrayList<>();
        Map<Integer, Integer> bfsTree = new LinkedHashMap<>();

        for (int node : adjList.keySet())
        {
            if (!bfsTree.containsKey(node))
            {
                cycle = BFS(adjList, bfsTree, node);
                if (!cycle.isEmpty())
                {
                    return cycle;
                }
            }
        }
        return cycle;
    }

    private static List<Integer> BFS(Map<Integer, List<Integer>> adjList, Map<Integer, Integer> bfsTree, int s)
    {
        List<Integer> cycle = new ArrayList<>();
        Set<Integer> curr = new LinkedHashSet<>();
        bfsTree.put(s, 0);
        curr.add(s);
        System.out.println("Starting at source: " + s);
        while (!curr.isEmpty())
        {
            Set<Integer> next = new LinkedHashSet<>();
            for (int i : curr)
            {
                for (int j : adjList.get(i))
                {
                    Integer k = bfsTree.get(j);
                    if (k == null)
                    {
                        System.out.println("Added " + j + " from " + i);
                        next.add(j);
                        bfsTree.put(j, i);
                    }
                    else if (curr.contains(j))
                    {
                        System.out.println("Found odd cycle: " + i + " " + j);
                        return computeCycle(bfsTree, i, j);
                    }
                    else if (next.contains(j))
                    {
                        System.out.println("Found even cycle: " + j + "  and " + i + " " + k);
                        return computeCycle(bfsTree, k, i, j);
                    }
                }
            }
            curr = next;
        }

        return cycle;
    }

    private static List<Integer> computeCycle(Map<Integer, Integer> bfsTree, int a, int b)
    {
        return computeCycle(bfsTree, a, b, null);
    }

    private static List<Integer> computeCycle(Map<Integer, Integer> bfsTree, int a, int b, Integer c)
    {
        List<Integer> l1 = new ArrayList<>();
        Stack<Integer> l2 = new Stack<>();

        if (c != null)
        {
            l1.add(c);
        }

        while (a != b)
        {
            l1.add(a);
            l2.add(b);
            a = bfsTree.get(a);
            b = bfsTree.get(b);
        }

        l1.add(a);
        l1.addAll(l2);

        l1.add(l1.get(0));

        return l1;
    }

    public static void main(String[] args)
    {
        Map<Integer, List<Integer>> adjList = generateGraph(5);
        System.out.println(adjList);
        List<Integer> cycle = findCycle(adjList);
        System.out.println(cycle);
    }

    public static Map<Integer, List<Integer>> generateGraph(int n)
    {
        Map<Integer, List<Integer>> adjList = new LinkedHashMap<>();
        for (int i = 1; i <= n; i++)
        {
            adjList.put(i, new ArrayList<>());
        }

        List<Integer> set = new ArrayList<>();
        set.add(1);

        Random random = new Random();

        for (int i = 2; i <= n; i++)
        {
            int w = random.nextInt(set.size());
            int x = set.get(w);
            adjList.get(i).add(x);
            adjList.get(x).add(i);
            set.add(i);
        }

        int a = random.nextInt(n) + 1;
        int b = random.nextInt(n) + 1;
        while (b == a || adjList.get(a).contains(b))
        {
            b = random.nextInt(n) + 1;
        }

        adjList.get(a).add(b);
        adjList.get(b).add(a);

        return adjList;
    }
}
