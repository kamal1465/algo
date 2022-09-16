package com.kamals.algo.algos.graph;

import java.util.*;

public class BFS {

    public static void main(String[] args) {

    }

    /**
     * Given a tree find maximum height between any two Nodes
     *
     * @param n
     * @param tree
     * @return
     */
    int solution(int n, int[][] tree) {
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        int k = 0;
        for (int[] t : tree) {
            adjMap.computeIfAbsent(t[0], o -> new ArrayList<>()).add(t[1]);
            adjMap.computeIfAbsent(t[1], o -> new ArrayList<>()).add(t[0]);
        }
        List<Integer> leafs = new ArrayList<>();
        while (!adjMap.isEmpty()) {
            for (Map.Entry<Integer, List<Integer>> e : adjMap.entrySet()) {
                if (e.getValue().size() == 1) {
                    leafs.add(e.getKey());
                }
            }
            if (leafs.size() == 2) {
                k = k + adjMap.size() - 1;
                break;
            }
            k += 2;
            for (Integer i : leafs) {
                List<Integer> l = adjMap.remove(i);
                List<Integer> l2 = adjMap.get(l.get(0));
                l2.remove(i);
                if (l2.isEmpty()) {
                    adjMap.remove(l.get(0));
                }
            }
            leafs.clear();
        }

        return k;
    }

    /**
     * Given a tree find maximum height between any two Nodes
     *
     * @param n
     * @param tree
     * @return
     */
    int solution2(int n, int[][] tree) {
        if (tree.length == 0) return 0;
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        for (int[] t : tree) {
            adjMap.computeIfAbsent(t[0], o -> new ArrayList<>()).add(t[1]);
            adjMap.computeIfAbsent(t[1], o -> new ArrayList<>()).add(t[0]);
        }
        int[] res = deepest(adjMap, tree[0][0]);
        res = deepest(adjMap, res[0]);
        return res[1] - 1;
    }

    /**
     * Find the most distant node from Given node alongwith its distance
     *
     * @return
     */
    int[] deepest(Map<Integer, List<Integer>> adjMap, int from) {
        int[] res = new int[2];
        Set<Integer> curr = new HashSet<>();
        Set<Integer> visited = new HashSet<>();

        curr.add(from);
        visited.add(from);

        while (!curr.isEmpty()) {
            Set<Integer> next = new HashSet<>();
            for (int i : curr) {
                res[0] = i;
                next.addAll(adjMap.get(i));
            }
            res[1]++;
            next.removeAll(visited);
            visited.addAll(next);
            curr = next;
        }

        return res;
    }

}
