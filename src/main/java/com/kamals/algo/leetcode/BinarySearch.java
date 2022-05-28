package com.kamals.algo.leetcode;

public class BinarySearch
{
    public int search(int[] nums, int target)
    {
        int start = 0, end = nums.length - 1;
        int mid;
        while (start <= end)
        {
            mid = (start + end) / 2;

            if (nums[mid] == target)
            {
                return mid;
            }
            else if (nums[mid] < target)
            {
                start = mid + 1;
            }
            else
            {
                end = mid - 1;
            }
        }

        return -1;
    }

    /**
     * leetcode 278. First bad version
     *
     * @param n
     * @return
     */
    public int firstBadVersion(int n)
    {
        int start = 1, end = n;
        int mid;
        int bad = -1;

        while (start <= end)
        {
            mid = start + (end - start) / 2;

            boolean flag = isBadVersion(mid);
            System.out.println(mid + " " + flag);
            if (flag)
            {
                bad = mid;
                end = mid - 1;
            }
            else
            {
                start = mid + 1;
            }
        }
        return bad;
    }

    private boolean isBadVersion(int version)
    {
        return version >= 1702766719;
    }

    /**
     * Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
     * <p>
     * You must write an algorithm with O(log n) runtime complexity.
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [1,3,5,6], target = 5
     * Output: 2
     * Example 2:
     * <p>
     * Input: nums = [1,3,5,6], target = 2
     * Output: 1
     * Example 3:
     * <p>
     * Input: nums = [1,3,5,6], target = 7
     * Output: 4
     */
    public int searchInsert(int[] nums, int target)
    {
        int len = nums.length;

        if (target <= nums[0])
        {
            return 0;
        }
        if (target > nums[len - 1])
        {
            return len;
        }
        int start = 0, end = nums.length - 1;
        int mid = 0;
        while (start <= end)
        {
            mid = (start + end) / 2;

            if (nums[mid] == target)
            {
                return mid;
            }
            else if (nums[mid] < target)
            {
                start = mid + 1;
            }
            else
            {
                end = mid - 1;
            }
        }
        return nums[mid] > target ? mid : mid + 1;
    }


    public static void main(String[] args)
    {
        BinarySearch binarySearch = new BinarySearch();

        System.out.println(binarySearch.firstBadVersion(2126753390));

        //        System.out.println(binarySearch.firstBadVersion(10));
    }
}
