package com.kamals.algo.example;

import java.util.Comparator;
import java.util.PriorityQueue;

public class SortTest
{
    public static void main(String[] args)
    {
        PriorityQueue<PhasesStates.Edge> neighbors = new PriorityQueue<>(10, new Comparator<PhasesStates.Edge>()
        {
            @Override
            public int compare(PhasesStates.Edge o1, PhasesStates.Edge o2)
            {
                return o1.node.height-o2.node.height;
            }
        });

        PhasesStates.Node n1 = new PhasesStates.Node(0,0);
        PhasesStates.Node n2 = new PhasesStates.Node(1, 1);
        PhasesStates.Node n3 = new PhasesStates.Node(2, 2);
        PhasesStates.Edge e1 = new PhasesStates.Edge(n1, 0);
        PhasesStates.Edge e2 = new PhasesStates.Edge(n2, 0);
        PhasesStates.Edge e3 = new PhasesStates.Edge(n3, 0);

        n1.height = 2;
        n2.height = 1;
        n3.height = 3;

        neighbors.add(e1);
        neighbors.add(e2);
        neighbors.add(e3);

        System.out.println(neighbors.poll().node.a);
        System.out.println(neighbors.poll().node.a);
        System.out.println(neighbors.poll().node.a);
    }
}
