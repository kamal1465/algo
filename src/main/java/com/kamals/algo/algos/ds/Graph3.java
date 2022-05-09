package com.kamals.algo.algos.ds;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Graph3
{
    private final boolean directed;
    private final boolean negativeEdge;
    private final int N;
    private final List<Node> nodes;

    public Graph3(boolean directed, boolean negativeEdge, int N)
    {
        this.directed = directed;
        this.negativeEdge = negativeEdge;
        this.N = N;
        nodes = new ArrayList<>();
    }

    public void generate(int edgeProbability, int maxEdgeVal)
    {
        Character c = 'A';
        nodes.clear();
        for (int i = 0; i < N; i++)
        {
            Node node = new Node(c.toString());
            nodes.add(node);
            c++;
        }
        Random random = new Random();
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                if (i != j)
                {
                    int edgeProb = random.nextInt(100);
                    if (edgeProb < edgeProbability)
                    {
                        int edgeVal = random.nextInt(maxEdgeVal) + 1;
                        if (negativeEdge)
                        {
                            if (5 > random.nextInt(10))
                            {
                                edgeVal = -edgeVal;
                            }
                        }
                        Edge edge = new Edge(nodes.get(i), nodes.get(j), edgeVal);
                        nodes.get(i).addEdge(edge);
                    }
                }
            }
        }
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("Graph3{" +
                "directed=" + directed +
                ", negativeEdge=" + negativeEdge +
                ", N=" + N + '}');
        sb.append('\n');
        for (Node node : nodes)
        {
            sb.append(node).append("=>").append(node.getEdges()).append("\n");
        }
        return sb.toString();
    }

    public boolean isDirected()
    {
        return directed;
    }

    public boolean isNegativeEdge()
    {
        return negativeEdge;
    }

    public List<Node> getNodes()
    {
        return nodes;
    }

    public class Node
    {
        private final String name;
        private final List<Edge> edges;

        public Node(String name)
        {
            this.name = name;
            edges = new ArrayList<>();
        }

        private void addEdge(Edge edge)
        {
            edges.add(edge);
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
            return "{" + name + '}';
        }
    }

    public class Edge
    {
        private final int val;
        private final Node src;
        private final Node dest;

        public Edge(Node src, Node dest, int val)
        {
            this.src = src;
            this.dest = dest;
            this.val = val;
        }

        public Node getSrc()
        {
            return src;
        }

        public int getVal()
        {
            return val;
        }

        public Node getDest()
        {
            return dest;
        }

        @Override
        public String toString()
        {
            return "{" + src.getName() + "=>" + dest.getName() + ", " + val + '}';
        }
    }
}
