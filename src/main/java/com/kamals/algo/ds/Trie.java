package com.kamals.algo.ds;

import java.util.HashMap;
import java.util.Map;

public class Trie
{
    private final Node root;

    public Trie()
    {
        root = new Node();
    }

    public void insert(String word)
    {
        Node node = root;
        for (char c : word.toCharArray())
        {
            node = node.links.computeIfAbsent(c, o -> new Node());
        }
        node.word = true;
    }

    public boolean search(String word)
    {
        Node node = root;
        char[] cs = word.toCharArray();
        for (char c : cs)
        {
            node = node.links.get(c);
            if (node == null)
            {
                return false;
            }
        }
        return node.word;
    }

    public boolean startsWith(String prefix)
    {
        char[] cs = prefix.toCharArray();
        Node node = root;
        for (char c : cs)
        {
            node = node.links.get(c);
            if (node == null)
            {
                return false;
            }
        }
        return true;
    }

    static class Node
    {
        private boolean word;
        private final Map<Character, Node> links;

        public Node()
        {
            this.links = new HashMap<>();
        }

        public void addLink(char c, Node node)
        {
            this.links.put(c, node);
        }
    }
}
