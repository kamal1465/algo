package com.kamals.algo.example;

import java.util.*;

public class PhasesStates
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();

        boolean[] output = new boolean[T];

        for (int k = 0; k < T; k++)
        {
            int P = in.nextInt();
            int S = in.nextInt();

            int[] x = new int[P];
            int[] y = new int[S];

            int sum1 = 0, sum2 = 0, c = 0;

            for (int i = 0; i < P; i++, c++)
            {
                x[c] = in.nextInt();
                sum1 += x[c];
                if (x[c] == 0)
                {
                    c--;
                }
            }
            P = c + 1;
            c = 0;
            for (int i = 0; i < S; i++, c++)
            {
                y[c] = in.nextInt();
                sum2 += y[c];
                if (y[c] == 0)
                {
                    c--;
                }
            }
            S = c + 1;
            if (sum1 != sum2)
            {
                output[k] = false;
                break;
            }

            LinkedList<Node> nodes = new LinkedList<>();
            int V = P*S + P + S + 2;
            Node[][] graph = new Node[P+1][S+1];

            for (int i = 0; i <= P; i++)
            {
                for (int j = 0; j <= S; j++)
                {
                    graph[i][j] = new Node(i, j);
                }
            }
            Node sink = new Node(P+1, S+1);
            for (int i = 1; i <= P; i++)
            {
                nodes.add(graph[i][0]);
                Edge edge = new Edge(graph[i][0], x[i-1]);
                graph[0][0].edges.add(edge);
                edge = new Edge(graph[0][0], 0);
                graph[i][0].edges.add(edge);
            }
            for (int i = 1; i <= P; i++)
            {
                for (int j = 1; j <= S; j++)
                {
                    nodes.add(graph[i][j]);
                    Edge edge = new Edge(graph[i][j], 1);
                    graph[i][0].edges.add(edge);
                    edge = new Edge(graph[i][0], 0);
                    graph[i][j].edges.add(edge);
                    edge = new Edge(graph[0][j], 1);
                    graph[i][j].edges.add(edge);
                    edge = new Edge(graph[i][j], 0);
                    graph[0][j].edges.add(edge);
                }
            }
            for (int j = 1; j <= S; j++)
            {
                nodes.add(graph[0][j]);
                Edge edge = new Edge(sink, y[j-1]);
                graph[0][j].edges.add(edge);
                edge = new Edge(graph[0][j], 0);
                sink.edges.add(edge);
            }

            int[][][][] flow = new int[P+2][S+2][P+2][S+2];

            graph[0][0].height = V;
            for (Edge edge : graph[0][0].edges)
            {
                flow[0][0][edge.node.a][edge.node.b] = edge.cap;
                flow[edge.node.a][edge.node.b][0][0] = -edge.cap;
                edge.node.store = edge.cap;
            }
            output[k] = true;

            Iterator<Node> iterator = nodes.iterator();

            outer:
            while (iterator.hasNext())
            {
                Node node = iterator.next();
                int oldHeight = node.height;

                while (node.store > 0)
                {
                    if (node.current >= node.edges.size())
                    {
                        if (node.neighbors.isEmpty())
                        {
                            node.neighbors.addAll(node.edges);
                        }

                        while (true)
                        {
                            Edge e = node.neighbors.poll();
                            int cf = e.cap - flow[node.a][node.b][e.node.a][e.node.b];
                            if (cf > 0)
                            {
                                node.height = 1 + e.node.height;
                                break;
                            }
                        }

                        if (node.height > V)
                        {
                            output[k] = false;
                            break outer;
                        }
                        node.current = 0;
                    }
                    else
                    {
                        Edge e = node.edges.get(node.current);

                        if (node.height == e.node.height + 1)
                        {
                            int cf = e.cap - flow[node.a][node.b][e.node.a][e.node.b];
                            if (cf > 0)
                            {
                                int df = Math.min(cf, node.store);
                                flow[node.a][node.b][e.node.a][e.node.b] += df;
                                flow[e.node.a][e.node.b][node.a][node.b] = -flow[node.a][node.b][e.node.a][e.node.b];
                                node.store -= df;
                                e.node.store += df;
                            }
                            else
                            {
                                node.current++;
                            }
                        }
                        else
                        {
                            node.current++;
                        }
                    }
                }
                node.neighbors.clear();
                if (node.height > oldHeight)
                {
                    iterator.remove();
                    nodes.add(0, node);
                    iterator = nodes.iterator();
                    iterator.next();
                }
            }
        }
        for (int k = 0; k < T; k++)
        {
            System.out.println(output[k] ? "YES" : "NO");
        }
    }

    static class Node
    {
        int a, b;
        List<Edge> edges = new ArrayList<>();
        PriorityQueue<Edge> neighbors = new PriorityQueue<>(10, new Comparator<Edge>()
        {
            @Override
            public int compare(Edge o1, Edge o2)
            {
                return o1.node.height-o2.node.height;
            }
        });
        int store = 0;
        int height = 0;
        int current = 0;
        public Node(int a, int b)
        {
            this.a = a;
            this.b = b;
        }
    }
    static class Edge
    {
        Node node;
        int cap;
        int fl = 0;
        public Edge(Node node, int cap)
        {
            this.node = node;
            this.cap = cap;
        }
    }
}