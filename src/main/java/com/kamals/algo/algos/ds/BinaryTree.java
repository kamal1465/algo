package com.kamals.algo.algos.ds;

public class BinaryTree
{
    public static class Node<T>
    {
        private final T val;
        private final Node left, right;

        public Node(T val, Node left, Node right)
        {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public T getVal()
        {
            return val;
        }

        public Node<T> getLeft()
        {
            return left;
        }

        public Node<T> getRight()
        {
            return right;
        }

        @Override
        public String toString()
        {
            return "{" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
