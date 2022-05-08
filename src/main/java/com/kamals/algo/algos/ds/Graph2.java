package com.kamals.algo.algos.ds;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Graph2
{
    private static final int MAX_WEIGHT = 9;
    private static final int EDGE_PROBABILITY = 40;//out of 100

    private final List<Node> adjList;
    private final List<Edge> edges;

    public static void main(String[] args)
    {
        Graph2 graph2 = new Graph2(10);
        graph2.print();
    }

    public Graph2(int N)
    {
        this(N, EDGE_PROBABILITY, MAX_WEIGHT, false);
    }

    public Graph2(int N, boolean directional)
    {
        this(N, EDGE_PROBABILITY, MAX_WEIGHT, directional);
    }

    public Graph2(int N, int edgeProbability, int maxWeight, boolean directional)
    {
        adjList = new ArrayList<>();
        edges = new ArrayList<>();

        Random random = new Random();

        Character c = 'A';

        for (int i = 0; i < N; i++)
        {
            adjList.add(new Node(c.toString()));
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
                        Edge edge = new Edge(adjList.get(i), adjList.get(j), weight);
                        edges.add(edge);
                        adjList.get(i).addEdge(edge);
                        if (!directional)
                        {
                            adjList.get(j).addEdge(edge);
                        }
                    }
                }
            }
        }
    }

    public void print()
    {
        for (Node node : adjList)
        {
            System.out.println(node + " ==> " + node.edges);
        }
    }

    public List<Node> getAdjList()
    {
        return adjList;
    }

    public List<Edge> getEdges()
    {
        return edges;
    }

    public class Node
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

    public class Edge
    {
        final Node node1;
        final Node node2;
        final int weight;

        public Edge(Node node1, Node node2, int weight)
        {
            this.node1 = node1;
            this.node2 = node2;
            this.weight = weight;
        }

        public Node getNode1()
        {
            return node1;
        }

        public int getWeight()
        {
            return weight;
        }

        @Override
        public String toString()
        {
            return '(' + node1.name + "-" + node2.name + ")(" + weight + ')';
        }
    }
}
