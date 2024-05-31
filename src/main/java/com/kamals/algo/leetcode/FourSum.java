package com.kamals.algo.leetcode;

import com.kamals.algo.algos.util.Util;

import java.util.*;

public class FourSum
{
    public static void main(String[] args)
    {
        FourSum fourSum = new FourSum();
        fourSum.test2();
//        fourSum.test3();
    }

    private void test1()
    {
        int[] nums = new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90};

        int target = 200;

        int n = nums.length;

        int c = n * (n - 1) / 2;

        System.out.println(n + " " + c);

        System.out.println(fourSum(nums, target));
    }

    private void test2()
    {
        int[] nums = new int[]{1000000000, 1000000000, 1000000000, 1000000000};
        int target = -294967296;
        long sum = 0;
        for (int i : nums)
        {
            System.out.println(sum);
            sum += i;
        }

        System.out.println(sum);

        int n = nums.length;

        int c = n * (n - 1) / 2;

        System.out.println(n + " " + c);

        System.out.println(fourSum3(nums, target));
    }

    private void test3()
    {
        int[] nums = new int[]{-1,0,-5,-2,-2,-4,0,1,-2};
        int target = -9;
        long sum = 0;
        for (int i : nums)
        {
            System.out.println(sum);
            sum += i;
        }

        System.out.println(sum);

        int n = nums.length;

        int c = n * (n - 1) / 2;

        System.out.println(n + " " + c);

        System.out.println(fourSum3(nums, target));
    }


    public List<List<Integer>> fourSum(int[] nums, int target)
    {
        Arrays.sort(nums);
        int n = nums.length;

        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < n; i++)
        {
            while (i > 0 && i < n - 1 && nums[i] == nums[i - 1])
            {
                i++;
            }

            for (int j = i + 1; j < n; j++)
            {
                while (j > i + 1 && j < n - 1 && nums[j] == nums[j - 1])
                {
                    j++;
                }

                int start = j + 1;
                int end = n - 1;

                long sum = nums[i] + nums[j];

                while (start < end)
                {
                    long sum2 = sum + nums[start] + +nums[end];

                    if (sum2 == target)
                    {
                        res.add(Arrays.asList(nums[i], nums[j], nums[start], nums[end]));
                        start++;
                        while (start < end && nums[start] == nums[start - 1])
                        {
                            start++;
                        }
                        end--;
                        while (start < end && nums[end] == nums[end + 1])
                        {
                            end--;
                        }
                    }
                    else if (sum2 < target)
                    {
                        start++;
                        while (start < end && nums[start] == nums[start - 1])
                        {
                            start++;
                        }
                    }
                    else
                    {
                        end--;
                        while (start < end && nums[end] == nums[end + 1])
                        {
                            end--;
                        }
                    }
                }
            }
        }
        return res;
    }

    public List<List<Integer>> fourSum2(int[] nums, int target)
    {
        TreeMap<Integer, Integer> sortCount = new TreeMap<>();

        for (int i : nums)
        {
            sortCount.merge(i, 1, (a, b) -> Math.min(a + b, 4));
        }

        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> e : sortCount.entrySet())
        {
            for (int i = 0; i < e.getValue(); i++)
            {
                list.add(e.getKey());
            }
        }

        TreeMap<Long, List<List<Integer>>> twoSums = new TreeMap<>();
        int n = list.size();
        for (int i = 0; i < n - 1; i++)
        {
            for (int j = i + 1; j < n; j++)
            {
                List<List<Integer>> v = twoSums.computeIfAbsent((long) list.get(i) + list.get(j), o -> new ArrayList<>());
                v.add(Arrays.asList(i, j));
            }
        }

        Set<List<Integer>> fourSums = new HashSet<>();

        for (Long x : twoSums.keySet())
        {
            long y = (long) target - x;
            if (y < x)
            {
                break;
            }
            List<List<Integer>> l1 = twoSums.get(y);
            if (l1 != null)
            {
                List<List<Integer>> l2 = twoSums.get(x);
                for (List<Integer> v1 : l1)
                {
                    for (List<Integer> v2 : l2)
                    {
                        Set<Integer> s = new HashSet<>();
                        s.addAll(v1);
                        s.addAll(v2);
                        if (s.size() == 4)
                        {
                            List<Integer> t = new ArrayList<>();
                            for (int z : s)
                            {
                                t.add(list.get(z));
                            }
                            Collections.sort(t);
                            fourSums.add(t);
                        }
                    }
                }
            }
        }

        return new ArrayList<>(fourSums);
    }


    public List<List<Integer>> fourSum3(int[] nums, int target)
    {
        Arrays.sort(nums);
        int n = nums.length;

        Util.printArr2(nums);

        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < n; i++)
        {
            while (i > 0 && i < n - 1 && nums[i] == nums[i - 1])
            {
                i++;
            }

            for (int j = i + 1; j < n; j++)
            {
                while (j > i + 1 && j < n - 1 && nums[j] == nums[j - 1])
                {
                    j++;
                }

                for (int k = j + 1; k < n; k++)
                {
                    while (k > j + 1 && k < n - 1 && nums[k] == nums[k - 1])
                    {
                        k++;
                    }

                    long sum = (long) nums[i] + nums[j] + nums[k];
                    System.out.println("Sum = " + sum);

                    //if (sum < target)
                    //{
                        int start = k + 1;
                        int end = n - 1;

                        long y =  target - sum;

                    System.out.println("Y = " + y);

                        while (start <= end)
                        {
                            int x = (start + end) / 2;

                            if (nums[x] == y)
                            {
                                res.add(Arrays.asList(nums[i], nums[j], nums[k], nums[x]));
                                break;
                            }
                            else if (nums[x] < y)
                            {
                                start = x + 1;
                            }
                            else
                            {
                                end = x - 1;
                            }
                        }
                    //}
                }
            }
        }
        return res;
    }

}
