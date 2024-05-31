package com.kamals.algo.interview.google;

import java.math.BigInteger;
import java.util.*;

/**
 * Given Mapping 1->a, 2->b...26->z
 * Input :- 123
 * Interpretations = {abc, aw, lc}
 * Output: 3 (number of possible interpretations)
 * DP = optimal algorithm with O(N) time and space each.
 * Handle the case of 0
 */
public class FindNumPossibleInterpretationsV2
{
    private final Map<Integer, Character> map;

    public FindNumPossibleInterpretationsV2()
    {
        map = new HashMap<>();
        char c = 'a';
        for (int i = 1; i <= 26; i++)
        {
            map.put(i, c++);
        }
        System.out.println(map);
    }

    public static void main(String[] args)
    {
        FindNumPossibleInterpretationsV2 findNumPossibleInterpretationsV2 = new FindNumPossibleInterpretationsV2();
        long in = 123;
        System.out.println(findNumPossibleInterpretationsV2.numInterpretations(in));
        System.out.println(findNumPossibleInterpretationsV2.numInterpretations(in, true));
        String s = "12345";
        System.out.println(findNumPossibleInterpretationsV2.numInterpretations(s));
        System.out.println(findNumPossibleInterpretationsV2.numInterpretations(s, true));
    }

    public int numInterpretations(long l)
    {
        return numInterpretations(l, false);
    }

    public int numInterpretations(long l, boolean v2)
    {
        if (l <= 0)
        {
            return 0;
        }
        System.out.println(l);
        int[] digits = getDigits(l);
        System.out.println(numInterpretationsRecFullData(digits));
        return v2 ? numInterpretationsRec(digits) : numInterpretations(digits);
    }

    public int numInterpretations(String num)
    {
        return numInterpretations(num, false);
    }

    public int numInterpretations(String num, boolean v2)
    {
        System.out.println(num);
        BigInteger bi = new BigInteger(num);
        if (bi.compareTo(BigInteger.ZERO) <= 0)
        {
            return 0;
        }
        int[] digits = getDigits(bi);
        System.out.println(numInterpretationsRecFullData(digits));
        return v2 ? numInterpretationsRec(digits) : numInterpretations(digits);
    }

    public int numInterpretations(int[] digits)
    {
        //Util.printArr2(digits);
        int n = digits.length;
        int[] dp = new int[n + 1];
        dp[0] = 1; //Base case - empty input = 1 interpretation ie. {} empty string
        for (int i = 0; i < n; i++)
        {
            if (dp[i] > 0)
            {
                if (map.containsKey(digits[i]))
                {
                    dp[i + 1] += dp[i];
                }
                if (i < n - 1 && digits[i] != 0 && map.containsKey(digits[i] * 10 + digits[i + 1]))
                {
                    dp[i + 2] += dp[i];
                }
            }
            else if (dp[i - 1] == 0)
            {
                break;//no progress possible
            }
        }
        return dp[n];
    }

    public int numInterpretations2(int[] digits)
    {
        //Util.printArr2(digits);
        int n = digits.length;
        int[] dp = new int[n + 1];
        dp[0] = 1; //Base case - empty input = 1 interpretation ie. {} empty string
        for (int i = 1; i <= n; i++)
        {
            if (dp[i - 1] > 0 || dp[i - 2] > 0)
            {
                if (map.containsKey(digits[i - 1]))
                {
                    dp[i] = dp[i - 1];
                    if (i > 1 && map.containsKey(digits[i - 2] * 10 + digits[i - 1]))
                    {
                        dp[i] += dp[i - 2];
                    }
                }
            }
            else
            {
                break;//no progress possible
            }
        }
        return dp[n];
    }

    public int numInterpretations3(int[] digits)
    {
        //Util.printArr2(digits);
        int n = digits.length;
        int back1 = 1; //Base case - empty input = 1 interpretation ie. {} empty string
        int back2 = 0;
        int curr = 1;
        for (int i = 1; i <= n; i++)
        {
            curr = 0;
            if (back1 > 0 || back2 > 0)
            {
                if (map.containsKey(digits[i - 1]))
                {
                    curr = back1;
                    if (i > 1 && map.containsKey(digits[i - 2] * 10 + digits[i - 1]))
                    {
                        curr += back2;
                    }
                }
                back2 = back1;
                back1 = curr;
            }
            else
            {
                break;//no progress possible
            }
        }
        return curr;
    }

    public int numInterpretationsRec(int[] digits)
    {
        int n = digits.length;
        int[] mem = new int[n + 1];
        Arrays.fill(mem, -1);
        mem[n] = 1;
        return numInterpretationsRec(digits, 0, mem);
    }

    private int numInterpretationsRec(int[] digits, int i, int[] mem)
    {
        if (mem[i] != -1)
        {
            return mem[i];
        }
        int x = 0;
        if (map.containsKey(digits[i]))
        {
            x = numInterpretationsRec(digits, i + 1, mem);
            if (i < digits.length - 1 && map.containsKey(digits[i] * 10 + digits[i + 1]))
            {
                x += numInterpretationsRec(digits, i + 2, mem);
            }
        }
        mem[i] = x;
        return x;
    }

    public List<String> numInterpretationsRecFullData(int[] digits)
    {
        int n = digits.length;
        int[] mem = new int[n + 1];
        Arrays.fill(mem, -1);
        mem[n] = 1;
        List<List<String>> memData = new ArrayList<>();
        for (int i = 0; i <= n; i++)
        {
            memData.add(new ArrayList<>());
        }
        memData.get(n).add("");
        return numInterpretationsRecFullData(digits, 0, mem, memData);
    }

    private List<String> numInterpretationsRecFullData(int[] digits, int i, int[] mem, List<List<String>> memData)
    {
        if (mem[i] != -1)
        {
            return memData.get(i);
        }
        Character c = map.get(digits[i]);
        if (c != null)
        {
            for (String s : numInterpretationsRecFullData(digits, i + 1, mem, memData))
            {
                memData.get(i).add(c + s);
            }
            if (i < digits.length - 1)
            {
                c = map.get(digits[i] * 10 + digits[i + 1]);
                if (c != null)
                {
                    for (String w : numInterpretationsRecFullData(digits, i + 2, mem, memData))
                    {
                        memData.get(i).add(c + w);
                    }
                }
            }
        }
        mem[i] = memData.get(i).size();
//        System.out.println(i + " " + memData.get(i));
        return memData.get(i);
    }

    private int[] getDigits(long l)
    {
        return getDigits(BigInteger.valueOf(l));
    }

    private int[] getDigits(BigInteger num)
    {
        List<Integer> list = new ArrayList<>();
        while (num.compareTo(BigInteger.ZERO) > 0)
        {
            list.add(num.mod(BigInteger.TEN).intValue());
            num = num.divide(BigInteger.TEN);
        }
        int n = list.size();
        int[] digits = new int[n];
        for (int i = 0; i < n; i++)
        {
            digits[i] = list.get(n - 1 - i);
        }
        return digits;
    }
}
