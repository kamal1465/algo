package com.kamals.algo.algos.graph;

import com.kamals.algo.algos.ds.Graph;

import java.util.List;

public class FindCycle
{
    public static void main(String[] args)
    {
        List<Graph.Node> graph = Graph.generate(100);
        boolean hasCycle = hasCycle(graph);
        System.out.println(hasCycle);
    }

    private static boolean hasCycle(List<Graph.Node> graph)
    {
        System.out.println(graph);



        return false;
    }
}
