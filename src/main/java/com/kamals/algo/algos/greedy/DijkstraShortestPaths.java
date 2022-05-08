package com.kamals.algo.algos.greedy;

import com.kamals.algo.algos.ds.Graph;

import java.util.*;

/**
 * Dijkstra's Shortest Paths algorithm - finds all shortest paths for a given source node S
 * It is a greedy algorithm and doesn't work if negative edges are allowed
 * Graph is directed. (can be undirected also)
 */
public class DijkstraShortestPaths
{
    public static void main(String[] args)
    {
        int N = 5;
        List<Graph.Node> adjList = Graph.generate(N, 70, 9, true);
        Graph.print(adjList);
        computeDijkstrasShortestPaths2(adjList);
    }

    private static void computeDijkstrasShortestPaths(List<Graph.Node> adjList)
    {
        Map<Graph.Node, Graph.Edge> explored = new LinkedHashMap<>();
        Map<Graph.Node, Graph.Edge> found = new LinkedHashMap<>();

        Graph.Node S = adjList.get(0);
        int pathWeight = 0;
        explored.put(S, new Graph.Edge(S, 0));

        System.out.println("========");
        while (S != null)
        {
            System.out.println(explored);
            for (Graph.Edge edge : S.getEdges())
            {
                Graph.Node node = edge.getNode();

                if (!explored.containsKey(node))
                {
                    int weight = pathWeight + edge.getWeight();
                    Graph.Edge e2 = found.get(node);
                    if (e2 == null || e2.getWeight() > weight)
                    {
                        found.put(node, new Graph.Edge(S, weight));
                    }
                }
            }

            System.out.println(found);
            System.out.println("========");

            pathWeight = -1;
            S = null;

            for (Map.Entry<Graph.Node, Graph.Edge> entry : found.entrySet())
            {
                Graph.Node node = entry.getKey();
                Graph.Edge edge = entry.getValue();

                if (pathWeight == -1 || pathWeight > edge.getWeight())
                {
                    pathWeight = edge.getWeight();
                    S = node;
                }
            }

            if (S != null)
            {
                explored.put(S, found.remove(S));
            }
        }
    }

    private static void computeDijkstrasShortestPaths2(List<Graph.Node> adjList)
    {
        Map<Graph.Node, Path> explored = new LinkedHashMap<>();
        Map<Graph.Node, Path> found = new LinkedHashMap<>();

        PriorityQueue<Path> priorityQueue = new PriorityQueue<>();

        Graph.Node S = adjList.get(0);
        int pathWeight = 0;
        explored.put(S, new Path(S, null, 0));

        System.out.println("========");
        while (S != null)
        {
            System.out.println(explored);
            for (Graph.Edge edge : S.getEdges())
            {
                Graph.Node node = edge.getNode();

                if (!explored.containsKey(node))
                {
                    int weight = pathWeight + edge.getWeight();
                    Path path = found.get(node);
                    if (path == null || path.getDistance() > weight)
                    {
                        path = new Path(node, S, weight);
                        found.put(node, path);
                        priorityQueue.add(path);
                    }
                }
            }

            System.out.println(found);
            System.out.println("========");

            S = null;

            Path path = priorityQueue.poll();
            while (path != null && explored.containsKey(path.getNode()))
            {
                path = priorityQueue.poll();
            }
            if (path != null)
            {
                S = path.getNode();
                pathWeight = path.getDistance();
                explored.put(S, found.remove(S));
            }
        }
    }

    static class Path implements Comparable
    {
        final Graph.Node node;
        final Graph.Node parent;
        final int distance;

        public Path(Graph.Node node, Graph.Node parent, int distance)
        {
            this.node = node;
            this.parent = parent;
            this.distance = distance;
        }

        @Override
        public int compareTo(Object o)
        {
            return distance - ((Path) o).distance;
        }

        public Graph.Node getNode()
        {
            return node;
        }

        public Graph.Node getParent()
        {
            return parent;
        }

        public int getDistance()
        {
            return distance;
        }

        @Override
        public String toString()
        {
            return "{" + (parent == null ? "" : parent.getName() + ", ") + distance + '}';
        }
    }
}
