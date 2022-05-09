package com.kamals.algo.algos.dynamic;

import com.kamals.algo.algos.ds.Graph3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Bellman Ford Shortest Path
 * computes shortest path s ~ t where graph may have negative edges
 * It has to compute all v ~ t paths for all vertices
 */
public class BellmanFordShortestPaths
{
    public static void main(String[] args)
    {
        int N = 6;
        Graph3 graph = new Graph3(true, true, N);
        graph.generate(50, 10);

        System.out.println(graph);

        List<Graph3.Node> nodes = graph.getNodes();

        bellmanFordShortestPath(graph, nodes.get(0), nodes.get(N - 1));
    }

    private static int bellmanFordShortestPath(Graph3 graph, Graph3.Node src, Graph3.Node dest)
    {
        List<Graph3.Node> nodes = graph.getNodes();
        int N = nodes.size();
        int[][] M = new int[N][N];

        src = nodes.get(0);
        dest = nodes.get(N - 1);

        Map<Graph3.Node, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++)
        {
            map.put(nodes.get(i), i);
            M[0][i] = Integer.MAX_VALUE;
        }
        for (Graph3.Edge edge : dest.getEdges())
        {
            Graph3.Node src2 = edge.getSrc();
            M[1][map.get(src2)] = edge.getVal();
        }
        for (int k = 2; k < N; k++)
        {
            for (int i = 0; i < N - 1; i++)
            {
                Graph3.Node node = nodes.get(i);

                List<Graph3.Edge> edges = node.getEdges();

                for (Graph3.Edge edge : edges)
                {
                    Graph3.Node n2 = edge.getDest();
                    int val = edge.getVal();

                    M[k][i] = Math.min(M[k-1][map.get(n2)] + val, M[k-1][i]);

                }
            }
        }

        printArray(M, nodes);

        return M[0][N - 1];
    }

    private static void printArray(int[][] M, List<Graph3.Node> nodes)
    {
        int k = 0;
        for (int[] i : M)
        {
            System.out.print(nodes.get(k++));
            for (int j : i)
            {
                System.out.printf("%11d", j);
            }
            System.out.println();
        }
    }
}
