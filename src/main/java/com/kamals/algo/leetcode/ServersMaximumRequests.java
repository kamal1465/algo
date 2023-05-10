package com.kamals.algo.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class ServersMaximumRequests
{
    public List<Integer> busiestServers(int k, int[] arrival, int[] load)
    {
        //int[] s -> s[0] = server id, s[1] = freeTime
        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        TreeSet<int[]> freeServers = new TreeSet<>((a, b) -> a[0] - b[0]);

        for (int i = 0; i < k; i++)
        {
            freeServers.add(new int[]{i, 0});
        }

        int[] numRequests = new int[k];

        int[] tmp = new int[2];

        for (int i = 0; i < arrival.length; i++)
        {
            int arr = arrival[i];
            int ld = load[i];

            while (!busyServers.isEmpty() && busyServers.peek()[1] <= arr)
            {
                freeServers.add(busyServers.poll());
            }

            if (!freeServers.isEmpty())
            {
                tmp[0] = i % k;

                int[] x = freeServers.ceiling(tmp);
                if (x == null)
                {
                    x = freeServers.first();
                }
                freeServers.remove(x);
                x[1] = arr + ld;
                busyServers.add(x);
                numRequests[x[0]]++;
            }
        }
        List<Integer> res = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < k; i++)
        {
            if (numRequests[i] > max)
            {
                max = numRequests[i];
                res.clear();
                res.add(i);
            }
            else if (max == numRequests[i])
            {
                res.add(i);
            }
        }
        return res;
    }

}
