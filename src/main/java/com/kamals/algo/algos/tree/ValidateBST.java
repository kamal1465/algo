package com.kamals.algo.algos.tree;

import java.util.ArrayList;
import java.util.List;

public class ValidateBST
{

    //Definition for a binary tree node.
    public class TreeNode
    {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode()
        {
        }

        TreeNode(int val)
        {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right)
        {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean isValidBST(TreeNode root)
    {
        List<Integer> l = inOrderList(root);
        Integer k = null;
        for (int x : l)
        {
            if (k != null && k >= x)
            {
                return false;
            }
            k = x;
        }
        return true;
    }

    public List<Integer> inOrderList(TreeNode root)
    {
        if (root == null)
        {
            return new ArrayList<>();
        }
        List<Integer> l = inOrderList(root.left);
        l.add(root.val);
        if (root.right != null)
        {
            l.addAll(inOrderList(root.right));
        }
        return l;
    }

    public boolean isValidBST2(TreeNode root)
    {
        return isValidBST2(root, null, null);
    }

    public boolean isValidBST2(TreeNode root, Integer min, Integer max)
    {
        if (root == null)
        {
            return true;
        }
        return (min == null || root.val > min) && (max == null || root.val < max) &&
                isValidBST2(root.left, min, root.val) && isValidBST2(root.right, root.val, max);
    }
}
