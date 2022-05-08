package com.kamals.algo.algos.greedy;

import com.kamals.algo.algos.ds.BinaryTree;
import javafx.util.Pair;

import java.util.*;

public class HuffmanEncoding
{

    public static void main(String[] args)
    {
        String input = "sdgcdsbccuybsdcbdbccjbdcjdkcbsdcbyuqbebcwehxewnwlxwxwbcylwcy";

        Map<Character, Integer> frequencies = new TreeMap<>();

        for (char c : input.toCharArray())
        {
            frequencies.merge(c, 1, (a, b) -> a + b);
        }

        System.out.println(frequencies);

        Comparator<BinaryTree.Node<Pair<Character, Integer>>> comp = Comparator.comparingInt(o -> o.getVal().getValue());
        //(a, b) -> a.getVal().getValue() - b.getVal().getValue();

        PriorityQueue<BinaryTree.Node<Pair<Character, Integer>>> priorityQueue = new PriorityQueue<>(comp);

        for (Map.Entry<Character, Integer> entry : frequencies.entrySet())
        {
            Pair<Character, Integer> pair = new Pair<>(entry.getKey(), entry.getValue());
            BinaryTree.Node<Pair<Character, Integer>> node = new BinaryTree.Node<>(pair, null, null);
            priorityQueue.add(node);
        }

        while (priorityQueue.size() > 1)
        {
            BinaryTree.Node<Pair<Character, Integer>> node1 = priorityQueue.poll();
            BinaryTree.Node<Pair<Character, Integer>> node2 = priorityQueue.poll();

            Pair<Character, Integer> pair = new Pair<>(null, node1.getVal().getValue() + node2.getVal().getValue());

            BinaryTree.Node<Pair<Character, Integer>> node = new BinaryTree.Node<>(pair, node1, node2);

            priorityQueue.add(node);
        }

        Map<Character, StringBuffer> encoding = new LinkedHashMap<>();
        BinaryTree.Node<Pair<Character, Integer>> node = priorityQueue.peek();

        System.out.println(node);

        convertEncoding(node, new StringBuffer(), encoding);

        printEncoding(encoding);

        int N = input.length();
        double sum = 0;
        for (Map.Entry<Character, Integer> entry : frequencies.entrySet())
        {
            Character c = entry.getKey();
            int f = entry.getValue();
            int el = encoding.get(c).length();
            sum += (f * el);
        }
        System.out.println(sum + " over " + N + " = " + sum / N);
    }

    private static void convertEncoding(BinaryTree.Node<Pair<Character, Integer>> node, StringBuffer bits, Map<Character, StringBuffer> encoding)
    {
        Pair<Character, Integer> val = node.getVal();
        Character c = val.getKey();
        if (c != null)
        {
            encoding.put(c, bits);
            return;
        }
        StringBuffer bits2 = new StringBuffer(bits);

        bits.append('0');
        bits2.append('1');
        convertEncoding(node.getLeft(), bits, encoding);
        convertEncoding(node.getRight(), bits2, encoding);
    }

    private static void printEncoding(Map<Character, StringBuffer> encoding)
    {
        int i = 1;
        for (Map.Entry<Character, StringBuffer> entry : encoding.entrySet())
        {
            System.out.println(i++ + ". " + entry.getKey() + " = " + entry.getValue());
        }
    }
}
