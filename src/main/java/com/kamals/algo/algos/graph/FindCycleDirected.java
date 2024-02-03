package com.kamals.algo.algos.graph;

import java.util.*;

public class FindCycleDirected
{

    public static void main(String[] args)
    {
        int n = 4;
        int[] edges = new int[]{3, 3, 4, 2, 3};
        FindCycleDirected findCycleDirected = new FindCycleDirected();

        System.out.println(findCycleDirected.longestCycle2(edges));
    }


    class Solution
    {
        public int longestCycle(int[] edges)
        {
            boolean[] visited = new boolean[edges.length];
            int max = -1;
            for (int i = 0; i < edges.length; i++)
            {
                if (!visited[i])
                {
                    visited[i] = true;
                    int destination = edges[i];
                    int length = 1;
                    HashMap<Integer, Integer> nodeToDistance = new HashMap<>();
                    nodeToDistance.put(i, 0);
                    while (destination != -1 && !visited[destination])
                    {
                        nodeToDistance.put(destination, length);
                        visited[destination] = true;
                        destination = edges[destination];
                        length++;
                    }
                    if (destination != -1 && nodeToDistance.containsKey(destination))
                    {
                        System.out.println(nodeToDistance);
                        max = Math.max(length - nodeToDistance.get(destination), max);
                    }
                }
            }
            return max;
        }
    }

    public int longestCycle2(int[] edges)
    {
        int n = edges.length;
        int[] visited = new int[n];
        Map<Integer, Integer> path = new HashMap<>();
        int maxLen = -1;

        for (int i = 0; i < n; i++)
        {
            if (visited[i] == 0)
            {
                int index = 0;
                System.out.println(i + " " + index + " " + path);
                visited[i] = 1;
                path.put(i, index++);
                int j = edges[i];
                while (j >= 0 && visited[j] == 0)
                {
                    System.out.println(j + " " + index + " " + path);
                    visited[j] = 1;
                    path.put(j, index++);
                    j = edges[j];
                }
                if (j >= 0 && path.containsKey(j))
                {
                    maxLen = Math.max(maxLen, path.size() - path.get(j));
                }
                path.clear();
            }
        }
        return maxLen;
    }

    public int longestCycle(int[] edges)
    {
        int n = edges.length;

        int[] visited = new int[n];

        List<Integer> path = new ArrayList<>();
        int maxLen = -1;

        for (int i = 0; i < n; i++)
        {
            if (visited[i] == 0)
            {
                int len = DFS(edges, visited, path, i);
                if (len > 0)
                {
                    maxLen = Math.max(maxLen, len);
                }
            }
        }
        return maxLen;
    }

    private int DFS(int[] edges, int[] visited, List<Integer> path, int i)
    {
        int x = path.indexOf(i);
        if (x >= 0)
        {
            //visited[i] = 1;
            return path.size() - x;
        }
        if (edges[i] >= 0 && visited[edges[i]] == 0)
        {
            path.add(i);
            x = DFS(edges, visited, path, edges[i]);
            path.remove(path.size() - 1);
        }
        visited[i] = 1;
        return Math.max(x, 0);
    }

}
