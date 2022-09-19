package com.kamals.algo.algos.dynamic;

/**
 * You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the ith job, you have to finish all the jobs j where 0 <= j < i).
 * <p>
 * You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties of each day of the d days. The difficulty of a day is the maximum difficulty of a job done on that day.
 * <p>
 * You are given an integer array jobDifficulty and an integer d. The difficulty of the ith job is jobDifficulty[i].
 * <p>
 * Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.
 */
public class MinimumDifficultyJobSchedule {

    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d)
        {
            return -1;
        }
        String s= "";




        return -1;
    }
}
