package com.kamals.algo.algos.greedy;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Dijsktra's Algorithm - finds all shortest paths from a given node 'S'
 * It is a greedy algorithm and All edge weights should be non-negative
 */
public class Dijkstra
{
    private static final int MAX_WEIGHT = 9;

    public static void main(String[] args)
    {
        String token  = UUID.randomUUID().toString();
        System.out.println(token);
        if (token.contains("-"))
        {
            token = token.substring(token.lastIndexOf('-') + 1, token.length());
        }
        System.out.println(token);

        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("ddHHmmss");
        String stamp = sdf.format(now);
        System.out.println(stamp);


//        SecureRandom secureRandom = new SecureRandom();
//        secureRandom.

        /*
        List<Node> adjList = generateGraph(20, 10);

        DijkstraShortestPath(adjList);*/
    }

    private static void DijkstraShortestPath(List<Node> adjList)
    {
        Map<Node, Edge> explored = new LinkedHashMap<>();
        Map<Node, Edge> found = new LinkedHashMap<>();

        Node S = adjList.get(0);
        explored.put(S, new Edge(0, S));
        System.out.println("=====");
        Node next = S;
        while (true)
        {
            System.out.println(explored);
            System.out.println(found);
            System.out.println("=====");
            Edge path = explored.get(next);

            for (Edge edge : next.edges)
            {
                if (!explored.containsKey(edge.node))
                {
                    Node dest = edge.node;
                    int dist = edge.weight + path.weight;

                    Edge path2 = found.get(dest);
                    if (path2 == null)
                    {
                        found.put(dest, new Edge(dist, next));
                    }
                    else if (dist < path2.weight)
                    {
                        path2.weight = dist;
                        path2.node = next;
                    }
                }
            }
            next = null;
            int minDist = -1;
            for (Map.Entry<Node, Edge> f : found.entrySet())
            {
                if (minDist == -1 || minDist > f.getValue().weight)
                {
                    minDist = f.getValue().weight;
                    next = f.getKey();
                }
            }
            if (next != null)
            {
                explored.put(next, found.remove(next));
            }
            else
            {
                break;
            }
        }
    }

    private static void printGraph(List<Node> adjList)
    {
        for (Node node : adjList)
        {
            System.out.println(node + " ==> " + node.edges);
        }
    }

    private static List<Node> generateGraph(int N, int M)
    {
        List<Node> adjList = new ArrayList<>();

        Character c = 'A';

        for (int i = 0; i < N; i++)
        {
            adjList.add(new Node(c.toString()));
            c++;
        }
//        System.out.println(adjList);
        Random random = new Random();
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                if (i != j)
                {
                    //int x = random.nextInt(5);
                    //if (x >= 3)
                    //{
                        int weight = random.nextInt(MAX_WEIGHT) + 1;
                        adjList.get(i).addEdge(adjList.get(j), weight);
                    //}
                }
            }
        }
        printGraph(adjList);
        return adjList;
    }

    static class Node
    {
        String name;
        List<Edge> edges;

        Node(String name)
        {
            this.name = name;
            this.edges = new ArrayList<>();
        }

        void addEdge(Node node, int weight)
        {
            edges.add(new Edge(weight, node));
        }

        @Override
        public String toString()
        {
            return "[" + name + "]";
        }
    }

    static class Edge
    {
        int weight;
        Node node;

        Edge(int weight, Node node)
        {
            this.weight = weight;
            this.node = node;
        }

        @Override
        public String toString()
        {
            return "{" + node.name + ", " + weight + '}';
        }
    }
}
