package com.kamals.algo.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 116. Populating Next Right Pointers in Each Node
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * <p>
 * Initially, all next pointers are set to NULL.
 * <p>
 * Example 1:
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [1,#,2,3,#,4,5,6,7,#]
 * Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 * <p>
 * Example 2:
 * Input: root = []
 * Output: []
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 212 - 1].
 * -1000 <= Node.val <= 1000
 * <p>
 * Follow-up:
 * <p>
 * You may only use constant extra space.
 * The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.
 */
public class PopulatingNextPointerBinaryTree
{
    public static void main(String[] args)
    {

    }

    public Node connect(Node root)
    {
        if (root == null)
        {
            return null;
        }
        List<Node> l1 = new ArrayList<>();
        List<Node> l2 = new ArrayList<>();
        List<Node> l3;
        l1.add(root);
        while (!l1.isEmpty())
        {
            for (int i = 0; i < l1.size(); i++)
            {
                if (i < l1.size() - 1)
                {
                    l1.get(i).next = l1.get(i + 1);
                }
                if (l1.get(i).left != null && l1.get(i).right != null)
                {
                    l2.add(l1.get(i).left);
                    l2.add(l1.get(i).right);
                }
            }
            l3 = l1;
            l1 = l2;
            l2 = l3;
            l2.clear();
        }
        return root;
    }

    public Node connectRecursive(Node root)
    {
        if (root == null)
        {
            return null;
        }

        if (root.left != null && root.right != null)
        {
            root.left.next = root.right;
            if (root.next != null)
            {
                root.right.next = root.next.left;
            }
            connectRecursive(root.left);
            connectRecursive(root.right);
        }

        return root;
    }

    class Node
    {
        int val;
        Node left;
        Node right;
        Node next;

        public Node()
        {
        }

        public Node(int _val)
        {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next)
        {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
