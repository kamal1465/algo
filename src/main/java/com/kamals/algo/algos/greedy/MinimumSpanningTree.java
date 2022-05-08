package com.kamals.algo.algos.greedy;

import com.kamals.algo.algos.ds.Graph;
import com.kamals.algo.algos.ds.Graph2;

import java.util.*;

public class MinimumSpanningTree
{
    public static void main(String[] args)
    {
        int N = 5;
        List<Graph.Node> adjList = Graph.generate(N, 50, 9, true);
        Graph.print(adjList);

        Collection<Graph.Node> mst = computePrimsMST(adjList);
        Graph.print(mst);

        Graph2 graph2 = new Graph2(N);
        graph2.print();
        computeKruskalsMST(graph2);
    }

    private static Collection<Graph.Node> computePrimsMST(List<Graph.Node> adjList)
    {
        List<Graph.Node> mst = new ArrayList<>();

        Map<Graph.Node, Graph.Edge> found = new LinkedHashMap<>();
        Set<Graph.Node> added = new HashSet<>();

        Graph.Node s = adjList.get(0);
        mst.add(new Graph.Node(s.getName()));
        added.add(s);

        System.out.println("=====");
        while (true)
        {
            Graph.print(mst);
            System.out.println(found);
            System.out.println("=====");
            for (Graph.Edge edge : s.getEdges())
            {
                //System.out.println(edge);
                Graph.Node n = edge.getNode();
                int weight = edge.getWeight();

                if (!added.contains(n))
                {
                    Graph.Edge e = found.get(n);
                    if (e == null || e.getWeight() > weight)
                    {
                        found.put(n, new Graph.Edge(s, weight));
                    }
                }
            }
            s = null;
            int minWeight = -1;
            for (Map.Entry<Graph.Node, Graph.Edge> entry : found.entrySet())
            {
                Graph.Edge e = entry.getValue();
                if (minWeight == -1 || minWeight > e.getWeight())
                {
                    minWeight = e.getWeight();
                    s = entry.getKey();
                }
            }
            if (s != null)
            {
                Graph.Edge edge = found.remove(s);
                Graph.Node node = new Graph.Node(s.getName());
                node.addEdge(edge);
                mst.add(node);
                added.add(s);
            }
            else
            {
                break;
            }
        }

        return mst;
    }

    private static Collection<Graph.Node> computeKruskalsMST(Graph2 graph2)
    {
        List<Graph.Node> mst = new ArrayList<>();

        //Sort edges min to max

        //Union-find all vertices

        //for every edge x-y until Max setSize < N
        //if find(x) != find(y)
        //take edge x-y and union(x, y)

        return mst;
    }

}
