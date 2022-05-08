package com.kamals.algo.algos.util;

import java.util.List;

public class FileSync
{
    public static void main(String[] args)
    {
        Node cdrive = new Node("C:\\");
        Node ddrive = new Node("D:\\");
        randomGenerate(cdrive);
        randomGenerate(ddrive);

    }

    private static void randomGenerate(Node root)
    {

    }

    static class Node
    {
        private String name;
        private List<Node> subfolders;

        Node(String name)
        {
            this.name = name;
        }

        public String getName()
        {
            return name;
        }

        public List<Node> getSubfolders()
        {
            return subfolders;
        }

        public void setSubfolders(List<Node> subfolders)
        {
            this.subfolders = subfolders;
        }
    }
}
