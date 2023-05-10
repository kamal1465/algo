package com.kamals.algo.algos.graph;

import java.util.*;

public class FindCycleUndirectedDFS
{
    private static final int WHITE = 0;
    private static final int GREY = 1;
    private static final int BLACK = 2;

    Map<Integer, Integer> color = new HashMap<>();
    List<Integer> cycle = new ArrayList<>();

    public void findCycle(Map<Integer, List<Integer>> adjList)
    {
        Map<Integer, Integer> dfsTree = new LinkedHashMap<>();

        for (int node : adjList.keySet())
        {
            color.put(node, WHITE);
        }
        for (int node : adjList.keySet())
        {
            if (color.get(node) == WHITE && cycle.isEmpty())
            {
                dfsTree.put(node, 0);
                DFS(adjList, dfsTree, node);
            }
        }
    }

    private void DFS(Map<Integer, List<Integer>> adjList, Map<Integer, Integer> dfsTree, int s)
    {
        if (!cycle.isEmpty())
        {
            return;
        }
        color.put(s, GREY);

        for (int next : adjList.get(s))
        {
            if (color.get(next) == WHITE)
            {
                dfsTree.put(next, s);
                DFS(adjList, dfsTree, next);
            }
            else if (color.get(next) == GREY && dfsTree.get(s) != next)
            {
                System.out.println("Found cycle: " + next + " " + s);
                cycle.add(next);
                int t = s;
                while (t != next)
                {
                    System.out.println("Adding " + t + " to the cycle");
                    cycle.add(t);
                    t = dfsTree.get(t);
                }
                cycle.add(next);
                break;
            }
        }

        color.put(s, BLACK);
    }

    public static void main(String[] args)
    {
        Map<Integer, List<Integer>> adjList = generateGraph(5);
        System.out.println(adjList);
        FindCycleUndirectedDFS findCycleUndirectedDFS = new FindCycleUndirectedDFS();
        findCycleUndirectedDFS.findCycle(adjList);
        System.out.println(findCycleUndirectedDFS.cycle);
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
