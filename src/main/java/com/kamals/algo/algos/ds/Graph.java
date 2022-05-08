package com.kamals.algo.algos.ds;

import java.util.*;

public class Graph
{
    private static final int MAX_WEIGHT = 9;
    private static final int EDGE_PROBABILITY = 40;//out of 100

    public static List<Node> generate(int N)
    {
        return generate(N, EDGE_PROBABILITY, MAX_WEIGHT, false);
    }

    public static List<Node> generate(int N, int edgeProbability, int maxWeight, boolean directional)
    {
        List<Node> adjList = new ArrayList<>();

        Random random = new Random();

        Character c = 'A';

        for (int i = 0; i < N; i++)
        {
            adjList.add(new Graph.Node(c.toString()));
            c++;
        }
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                if (directional ? i != j : i < j)
                {
                    int edgeProbs = random.nextInt(100);
                    if (edgeProbs <= edgeProbability)
                    {
                        int weight = random.nextInt(maxWeight - 1) + 1;
                        adjList.get(i).addEdge(adjList.get(j), weight);
                        if (!directional)
                        {
                            adjList.get(j).addEdge(adjList.get(i), weight);
                        }
                    }
                }
            }
        }
        return adjList;
    }

    public static void print(Collection<Node> adjList)
    {
        for (Node node : adjList)
        {
            System.out.println(node + " ==> " + node.edges);
        }
    }

    public static class Node
    {
        final String name;
        final List<Edge> edges;

        public Node(String name)
        {
            this.name = name;
            this.edges = new ArrayList<>();
        }

        public void addEdge(Edge edge)
        {
            this.edges.add(edge);
        }

        public void addEdge(Node node, int weight)
        {
            this.edges.add(new Edge(node, weight));
        }

        public String getName()
        {
            return name;
        }

        public List<Edge> getEdges()
        {
            return edges;
        }

        @Override
        public String toString()
        {
            return '[' + name + ']';
        }
    }

    public static class Edge
    {
        final Node node;
        final int weight;

        public Edge(Node node, int weight)
        {
            this.node = node;
            this.weight = weight;
        }

        public Node getNode()
        {
            return node;
        }

        public int getWeight()
        {
            return weight;
        }

        @Override
        public String toString()
        {
            return node.name + ':' + weight;
        }
    }
}
