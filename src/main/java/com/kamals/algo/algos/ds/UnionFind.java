package com.kamals.algo.algos.ds;

import java.util.*;

public class UnionFind<T>
{
    private final Map<T, Node<T>> records;

    public static void main(String[] args)
    {
        int N = 10;
        List<String> vals = new ArrayList<>();
        Character c = 'A';
        for (int i = 0; i < N; i++)
        {
            vals.add(c.toString());
            c++;
        }
        UnionFind<String> unionFind = new UnionFind<>(vals);
        unionFind.print();

        Random random = new Random();

        for (int k = 0; k < 20; k++)
        {
            int i = random.nextInt(N);
            int j = random.nextInt(N);
            String v1 = vals.get(i);
            String v2 = vals.get(j);
            String s1 = unionFind.find(v1);
            String s2 = unionFind.find(v2);
            System.out.print("Find(" + v1 + ") = " + s1 + "  ");
            System.out.print("Find(" + v2 + ") = " + s2 + "  ");

            if (s1 != s2)
            {
                unionFind.union(s1, s2);
            }
            else
            {
                System.out.println("Same Set");
            }
        }
        unionFind.print();
    }

    public UnionFind(Collection<T> vals)
    {
        records = new LinkedHashMap<>();
        for (T t : vals)
        {
            Node<T> node = new Node<>(t);
            records.put(t, node);
        }
    }

    public T find(T t)
    {
        Node<T> node = records.get(t);
        while (node.next != null)
        {
            node = node.next;
        }
        return node.val;
    }

    public void union(T t1, T t2)
    {
        Node<T> node1 = records.get(t1);
        Node<T> node2 = records.get(t2);

        int size1 = node1.setSize;
        int size2 = node2.setSize;

        node1.setSize = node2.setSize = size1 + size2;
        if (size1 > size2)
        {
            node2.next = node1;
            System.out.println("Union: " + node2);
        }
        else
        {
            node1.next = node2;
            System.out.println("Union: " + node1);
        }
    }

    public void print()
    {
        for (Node<T> node : records.values())
        {
            System.out.println(node);
        }
    }

    class Node<S>
    {
        final S val;
        int setSize;
        Node<S> next;

        public Node(S val)
        {
            this.val = val;
            this.setSize = 1;
            this.next = null;
        }

        @Override
        public String toString()
        {
            return val + "." + setSize + (next == null ? "" : " => " + next);
        }
    }
}
