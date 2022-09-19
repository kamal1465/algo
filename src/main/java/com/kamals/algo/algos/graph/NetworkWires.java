package com.kamals.algo.algos.graph;

import java.util.*;

public class NetworkWires
{

    public static void main(String[] args)
    {

    }

    int solution(int n, int[][] wires)
    {
        Set<Integer> nonAdded = new HashSet<>();
        List<int[]>[] g = new List[n];
        for (int i = 0; i < n; i++)
        {
            nonAdded.add(i);
            g[i] = new ArrayList<>();
        }
        for (int[] wire : wires)
        {
            g[wire[0]].add(new int[]{wire[1], wire[2]});
            g[wire[1]].add(new int[]{wire[0], wire[2]});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));//((a, b) -> a[1] - b[1]);

        int total = 0;

        while (!nonAdded.isEmpty())
        {
            int v = nonAdded.iterator().next();
            addNode(g, nonAdded, pq, v);
            while (!pq.isEmpty())
            {
                int[] u = pq.poll();
                if (nonAdded.contains(u[0]))
                {
                    total += u[1];
                    addNode(g, nonAdded, pq, u[0]);
                }
            }
        }
        return total;
    }

    void addNode(List<int[]>[] g, Set<Integer> nonAdded, PriorityQueue<int[]> pq, int v)
    {
        nonAdded.remove(v);
        for (int[] w : g[v])
        {
            if (nonAdded.contains(w[0]))
            {
                pq.add(w);
            }
        }
    }
}
