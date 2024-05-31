package com.kamals.algo.algos.graph;

import com.kamals.algo.algos.util.Util;

import java.util.*;

public class DAG
{
    static class SolutionDFS
    {
        static int WHITE = 1;
        static int GRAY = 2;
        static int BLACK = 3;

        boolean isPossible;
        Map<Integer, Integer> color;
        Map<Integer, List<Integer>> adjList;
        List<Integer> topologicalOrder;

        private void init(int numCourses)
        {
            this.isPossible = true;
            this.color = new HashMap<>();
            this.adjList = new HashMap<>();
            this.topologicalOrder = new ArrayList<>();

            // By default all vertices are WHITE
            for (int i = 0; i < numCourses; i++)
            {
                this.color.put(i, WHITE);
            }
        }

        private void dfs(int node)
        {
            // Don't recurse further if we found a cycle already
            if (!this.isPossible)
            {
                return;
            }

            // Start the recursion
            this.color.put(node, GRAY);

            // Traverse on neighboring vertices
            for (Integer neighbor : this.adjList.getOrDefault(node, new ArrayList<>()))
            {
                if (this.color.get(neighbor) == WHITE)
                {
                    this.dfs(neighbor);
                }
                else if (this.color.get(neighbor) == GRAY)
                {
                    // An edge to a GRAY vertex represents a cycle
                    this.isPossible = false;
                    break;
                }
            }

            // Recursion ends. We mark it as black
            this.color.put(node, BLACK);
            this.topologicalOrder.add(node);
        }

        public int[] findOrder(int numCourses, int[][] prerequisites)
        {
            this.init(numCourses);

            // Create the adjacency list representation of the graph
            for (int[] p : prerequisites)
            {
                int dest = p[0];
                int src = p[1];
                List<Integer> lst = adjList.getOrDefault(src, new ArrayList<>());
                lst.add(dest);
                adjList.put(src, lst);
            }

            // If the node is unprocessed, then call dfs on it.
            for (int i = 0; i < numCourses; i++)
            {
                if (this.color.get(i) == WHITE)
                {
                    this.dfs(i);
                }
            }

            int[] order;
            if (this.isPossible)
            {
                order = new int[numCourses];
                for (int i = 0; i < numCourses; i++)
                {
                    order[i] = this.topologicalOrder.get(numCourses - i - 1);
                }
            }
            else
            {
                order = new int[0];
            }

            return order;
        }
    }

    static class SolutionDAG
    {
        public int[] findOrder(int numCourses, int[][] prerequisites)
        {
            Map<Integer, List<Integer>> adjList = new HashMap<>();
            int[] indegree = new int[numCourses];
            int[] topologicalOrder = new int[numCourses];

            // Create the adjacency list representation of the graph
            for (int[] p : prerequisites)
            {
                int dest = p[0];
                int src = p[1];
                List<Integer> lst = adjList.getOrDefault(src, new ArrayList<>());
                lst.add(dest);
                adjList.put(src, lst);

                // Record in-degree of each vertex
                indegree[dest] += 1;
            }

            // Add all vertices with 0 in-degree to the queue
            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < numCourses; i++)
            {
                if (indegree[i] == 0)
                {
                    q.add(i);
                }
            }

            int i = 0;
            // Process until the Q becomes empty
            while (!q.isEmpty())
            {
                int node = q.remove();
                topologicalOrder[i++] = node;

                // Reduce the in-degree of each neighbor by 1
                if (adjList.containsKey(node))
                {
                    for (Integer neighbor : adjList.get(node))
                    {
                        indegree[neighbor]--;

                        // If in-degree of a neighbor becomes 0, add it to the Q
                        if (indegree[neighbor] == 0)
                        {
                            q.add(neighbor);
                        }
                    }
                }
            }

            // Check to see if topological sort is possible or not.
            if (i == numCourses)
            {
                return topologicalOrder;
            }

            return new int[0];
        }
    }

    private static int[] findOrder2(int n, int[][] p)
    {
        Map<Integer, Integer> nodeNumIncidentEdges = new LinkedHashMap<>();
        List[] a = new List[n];
        for (int i = 0; i < n; i++)
        {
            a[i] = new ArrayList<Integer>();
            nodeNumIncidentEdges.put(i, 0);
        }
        for (int[] r : p)
        {
            a[r[1]].add(r[0]);
            nodeNumIncidentEdges.put(r[0], 1 + nodeNumIncidentEdges.get(r[0]));
        }

        int[] res = new int[n];
        Set<Integer> added = new HashSet<>();
        int k = 0;

        while (k < n)
        {
            Set<Integer> active = new HashSet<>();
            for (Map.Entry<Integer, Integer> e : nodeNumIncidentEdges.entrySet())
            {
                int i = e.getKey();
                if (!added.contains(i) && e.getValue() == 0)
                {
                    System.out.println("Found " + i);
                    added.add(i);
                    active.add(i);
                    res[k] = i;
                    k++;
                }
            }
            if (active.isEmpty())
            {
                return new int[0];
            }
            for (int i : active)
            {
                for (Object o : a[i])
                {
                    int j = (int) o;
                    System.out.println("Removing " + i + " from " + j);
                    //a[j].remove((Integer) i);
                    nodeNumIncidentEdges.put(j, nodeNumIncidentEdges.get(j) - 1);
                }
            }
        }
        return res;
    }

    private static int[] findOrder(int n, int[][] p)
    {
        List[] a = new List[n];
        for (int i = 0; i < n; i++)
        {
            a[i] = new ArrayList<Integer>();
        }
        for (int[] r : p)
        {
            a[r[0]].add(r[1]);
        }

        int[] res = new int[n];
        Set<Integer> added = new HashSet<>();
        int k = 0;

        while (k < n)
        {
            boolean found = false;
            for (int i = 0; i < n; i++)
            {
                if (a[i].isEmpty() && !added.contains(i))
                {
                    System.out.println("Found " + i);
                    found = true;
                    added.add(i);
                    res[k] = i;
                    k++;
                    for (int j = 0; j < n; j++)
                    {
                        if (a[j].contains(i))
                        {
                            System.out.println("Removing " + i + " from " + j);
                            a[j].remove((Integer) i);
                        }
                    }
                }
            }
            if (!found)
            {
                return new int[0];
            }
        }
        return res;
    }

    public static void main(String[] args)
    {
        int[][] p = new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        Util.printArr2(findOrder2(4, p));
    }
}