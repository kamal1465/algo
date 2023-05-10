package com.kamals.algo.interview.google;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Given letters and depths create Huffman tree
 * Input: [a:2, b:2, c:3, d:3]
 */
public class HuffmanTreeBuilder
{
    static class Node implements Comparable<Node>
    {
        final String code;
        final int dep;
        final int len;

        Node left;
        Node right;

        public Node(String code, int dep, int len)
        {
            this.code = code;
            this.dep = dep;
            this.len = len;
        }

        @Override
        public int compareTo(Node o)
        {
            if (this.dep == o.dep)
            {
                if (this.len == o.len)
                {
                    return this.code.compareTo(o.code);
                }
                return this.len - o.len;
            }
            return this.dep - o.dep;
        }

        public int compareTo2(Node o)
        {
            if (this.dep != o.dep)
            {
                return this.dep - o.dep;
            }
            if (this.len != o.len)
            {
                return this.len - o.len;
            }
            return this.code.compareTo(o.code);
        }

        public int compareTo3(Node o)
        {
            return this.dep == o.dep ? this.len == o.len ? this.code.compareTo(o.code) : this.len - o.len : this.dep - o.dep;
        }

        @Override
        public String toString()
        {
            return "[" + code + ", " + dep +
                    (left != null ? ", left=" + left : "") +
                    (right != null ? ", right=" + right : "") + ']';
        }
    }

    Node construct(Map<Character, Integer> input)
    {
        TreeSet<Node> treeSet = new TreeSet<>();
        for (Map.Entry<Character, Integer> e : input.entrySet())
        {
            Node node = new Node(e.getKey().toString(), e.getValue(), 0);
            treeSet.add(node);
        }

        while (treeSet.size() > 1)
        {
            Node n1 = treeSet.pollLast();
            Node n2 = treeSet.pollLast();

            if (n1.dep != n2.dep)
            {
                throw new IllegalArgumentException("Invalid input");
            }
            Node n3 = new Node(n2.code + n1.code, n1.dep - 1, Math.max(n2.len, n1.len) + 1);
            n3.left = n2;
            n3.right = n1;
            treeSet.add(n3);
        }
        return treeSet.first();
    }

    public static void main(String[] args)
    {
        HuffmanTreeBuilder huffmanTreeBuilder = new HuffmanTreeBuilder();
        Map<Character, Integer> map = new HashMap<>();
        map.put('a', 2);
        map.put('c', 2);
        map.put('b', 2);
        map.put('d', 2);
        Node node = huffmanTreeBuilder.construct(map);
        System.out.println(node);
    }
}