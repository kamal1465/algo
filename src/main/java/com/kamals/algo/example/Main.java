package com.kamals.algo.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main
{
    public static void main(String[] args)
    {
        // write your code here
        System.out.println(checkJoiningOp());
    }


    public void maxSubarray(int[] a)
    {

    }

    public MaxSum maxSubarray(int[] a, int low, int high)
    {
        if (low == high)
        {
            return new MaxSum(low, high, a[low]);
        }

        int mid = (low + high) / 2;

        MaxSum leftSum = maxSubarray(a, low, mid);
        MaxSum rightSum = maxSubarray(a, mid + 1, high);
        MaxSum crossSum = maxCrossingSubarray(a, low, high);

        return null;
    }

    public MaxSum maxCrossingSubarray(int[] a, int low, int high)
    {
        return null;
    }

    class MaxSum
    {
        int left;
        int right;
        int sum;

        public MaxSum(int left, int right, int sum)
        {
            this.left = left;
            this.right = right;
            this.sum = sum;
        }
    }

    public static String checkJoiningOp()
    {
        List<String> missingAttrs = new ArrayList<>();
        //missingAttrs.add("Name");
        //missingAttrs.add("Description");
        //missingAttrs.add("Primary Image");
        //missingAttrs.add("Price");
        //missingAttrs.add("HSN SAC Code");
        //missingAttrs.add("ROOT Category");
        return missingAttrs.stream().collect(Collectors.joining(", "));
    }
}
