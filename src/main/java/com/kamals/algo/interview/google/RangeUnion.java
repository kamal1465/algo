package com.kamals.algo.interview.google;

import java.util.*;

/**
 * Provide a class that acts, effectively, as the union of a set of ranges.
 * That is to say that ranges can be inserted into an instance of this class and then the instance can be queried to check if a particular point is in any of the ranges that have been inserted.
 * <p>
 * Example:
 * <p>
 * Ranges Inserted:
 * <p>
 * 2-5
 * 9-13
 * Points Queried
 * <p>
 * 0 -> False
 * 2 -> True
 * 10 -> True
 */
public class RangeUnion
{
    static class Range
    {
        private int min;
        private int max;

        Range(int min, int max) throws Exception
        {
            if (min > max)
            {
                throw new Exception("Invalid range");
            }
            this.min = min;
            this.max = max;
        }

        boolean contains(int x)
        {
            return x >= min && x <= max;
        }

        @Override
        public String toString()
        {
            return "{" + min + ", " + max + '}';
        }
    }


    class RangeSet
    {
        Collection<Range> ranges = new ArrayList<>();

        public void addRange(Range r)
        {
            ranges.add(r);
        }

        public boolean query(int x)
        {
            for (Range range : ranges)
            {
                if (range.contains(x))
                {
                    return true;
                }
            }
            return false;
        }
    }

    static class RangeTree
    {
        Range range;
        RangeTree left;
        RangeTree right;

        RangeTree(Range range)
        {
            this.range = range;
        }

        @Override
        public String toString()
        {
            return "[" + range + ", left=" + left + ", right=" + right + ']';
        }
    }


    static class RangeSet2
    {
        RangeTree root;

        void add(Range range)
        {
            if (root == null)
            {
                root = new RangeTree(range);
                return;
            }
            add(root, range);
        }

        private void add(RangeTree rt, Range range)
        {
            if (rt.range.min > range.max + 1)
            {
                if (rt.left == null)
                {
                    rt.left = new RangeTree(range);
                }
                else
                {
                    add(rt.left, range);
                }
            }
            else if (rt.range.max + 1 < range.min)
            {
                if (rt.right == null)
                {
                    rt.right = new RangeTree(range);
                }
                else
                {
                    add(rt.right, range);
                }
            }
            else
            {
                //root.range.min = Math.min(root.range.min, range.min);
                //root.range.max = Math.max(root.range.max, range.max);

                if (range.min < rt.range.min)
                {
                    rt.range.min = range.min;

                    while (rt.left != null && rt.left.range.max + 1 >= rt.range.min)
                    {
                        rt.range.min = Math.min(rt.range.min, rt.left.range.min);
                        rt.left = rt.left.left;
                    }
                }
                if (range.max > rt.range.max)
                {
                    rt.range.max = range.max;

                    while (rt.right != null && rt.right.range.min - 1 <= rt.range.max)
                    {
                        rt.range.max = Math.max(rt.range.max, rt.right.range.max);
                        rt.right = rt.right.right;
                    }
                }
            }
        }

        boolean query(int x)
        {
            return query(root, x);
        }

        private boolean query(RangeTree rangeTree, int x)
        {
            if (rangeTree != null)
            {
                if (rangeTree.range.contains(x))
                {
                    return true;
                }
                if (x < rangeTree.range.min)
                {
                    return query(rangeTree.left, x);
                }
                else
                {
                    return query(rangeTree.right, x);
                }
            }
            return false;
        }

        @Override
        public String toString()
        {
            return root.toString();
        }
    }

    public static void main(String[] args) throws Exception
    {
        int n = 30, max = 100, max2 = max + 20;
        Random random = new Random();
//        RangeSet2 rangeSet2 = new RangeSet2();
        RangeUnion rangeSet2 = new RangeUnion();
        RangeTest rangeTest = new RangeTest(max2);
        for (int i = 0; i < n; i++)
        {
            int x = random.nextInt(max);
            int y = x + 10;
            Range range = x < y ? new Range(x, y) : new Range(y, x);
            System.out.println(range);
            rangeSet2.add(range);
            rangeTest.add(range);
            System.out.println(rangeSet2);
        }
        System.out.println(rangeSet2);
        //System.out.println(rangeTest);

        for (int i = 0; i < max2; i++)
        {
            if (rangeSet2.query(i) != rangeTest.query(i))
            {
                System.out.println("Failed for " + i + " " + rangeSet2.query(i) + " " + rangeTest.query(i));
            }
        }
        /*Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        while (x != -1)
        {
            System.out.println(rangeSet2.query(x));
            x = in.nextInt();
        }*/
    }

    static class RangeTest
    {
        private final boolean[] nums;

        public RangeTest(int max)
        {
            nums = new boolean[max];
        }

        void add(Range range)
        {
            for (int i = range.min; i <= range.max; i++)
            {
                nums[i] = true;
            }
        }

        boolean query(int x)
        {
            return nums[x];
        }

        @Override
        public String toString()
        {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < nums.length; i++)
            {
                sb.append(i).
                        append(" ").
                        append(nums[i]).
                        append('\n');
            }
            return sb.toString();
        }
    }

    private final TreeMap<Integer, Integer> rangeMap = new TreeMap<>();

    public void add(Range range)
    {
        int x = range.min, y = range.max;
        Map.Entry<Integer, Integer> e = rangeMap.floorEntry(y);
        if (e == null || e.getValue() + 1 < x)
        {
            rangeMap.put(x, y);
        }
        else if (e.getKey() <= x && e.getValue() < y)
        {
            rangeMap.put(e.getKey(), y);
        }
        else if (e.getKey() > x)
        {
            range.max = Math.max(y, e.getValue());
            rangeMap.remove(e.getKey());
            add(range);
        }
    }

    public void add2(Range range)
    {
        int x = range.min, y = range.max;
        Map.Entry<Integer, Integer> e = rangeMap.floorEntry(y);
        if (e == null || e.getValue() + 1 < x)
        {
            rangeMap.put(x, y);
        }
        else
        {
            range.min = Math.min(x, e.getKey());
            range.max = Math.max(y, e.getValue());
            rangeMap.remove(e.getKey());
            add2(range);
        }
    }

    public boolean query(int x)
    {
        Map.Entry<Integer, Integer> e = rangeMap.floorEntry(x);
        return e != null && e.getValue() >= x;
    }

    @Override
    public String toString()
    {
        return rangeMap.toString();
    }
}
