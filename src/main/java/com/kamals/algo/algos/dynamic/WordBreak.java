package com.kamals.algo.algos.dynamic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordBreak
{
    public boolean wordBreakBacktracking(String s, List<String> wordDict)
    {
        return backtrack(s, 0, wordDict);
    }

    public boolean backtrack(String s, int i, List<String> wordDict)
    {
        int l = s.length() - i;
        if (l == 0)
        {
            return true;
        }
        for (String word : wordDict)
        {
            if (l >= word.length())
            {
                int j = i, k = 0, m = word.length();
                while (k < m && s.charAt(j) == word.charAt(k))
                {
                    j++;
                    k++;
                }
                if (k == m)
                {
                    if (backtrack(s, i + k, wordDict))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public boolean wordBreakBacktrackingMemoize(String s, List<String> wordDict)
    {
        Map<Integer, Boolean> memoize = new HashMap<>();
        return backtrackMemoize(s, 0, wordDict, memoize);
    }

    public boolean backtrackMemoize(String s, int i, List<String> wordDict, Map<Integer, Boolean> memoize)
    {
        int l = s.length() - i;
        if (l == 0)
        {
            return true;
        }
        if (memoize.containsKey(i))
        {
            return memoize.get(i);
        }
        for (String word : wordDict)
        {
            if (l >= word.length())
            {
                int j = i, k = 0, m = word.length();
                while (k < m && s.charAt(j) == word.charAt(k))
                {
                    j++;
                    k++;
                }
                if (k == m)
                {
                    if (backtrackMemoize(s, i + k, wordDict, memoize))
                    {
                        return true;
                    }
                }
            }
        }
        memoize.put(i, false);
        return false;
    }
}
