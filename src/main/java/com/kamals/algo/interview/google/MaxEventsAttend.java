package com.kamals.algo.interview.google;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MaxEventsAttend
{
    public int maxEvents(int[][] events)
    {
        Arrays.sort(events, Comparator.comparingInt(a -> a[0]));

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        int max = 1; //Arrays.stream(events).map(a -> a[1]).max(Integer::compare).get();

        int count = 0;
        int d = 1;
        int pos = 0;
        int n = events.length;

        while (pos < n || d <= max)
        {
            while (!queue.isEmpty() && queue.peek() < d)
            {
                System.out.println("Day - " + d + " Discarding - " + queue.peek());
                queue.poll();
            }

            if (queue.isEmpty() && pos < n)
            {
                d = events[pos][0];
            }

            while (pos < n && events[pos][0] <= d)
            {
                int a = events[pos][1];
                queue.offer(a);
                max = Math.max(max, a);
                pos++;
            }

            if (!queue.isEmpty())
            {
                int a = queue.poll();
                System.out.println("Day - " + d + " Doing - " + a);
                count++;
            }
            d++;
        }

        return count;
    }

    public static void main(String[] args)
    {
        MaxEventsAttend maxEventsAttend = new MaxEventsAttend();
        int[][] events = new int[][]{{1, 2}, {2, 2}, {3, 3}, {3, 4}, {3, 4}};
        int x = maxEventsAttend.maxEvents(events);
        System.out.println(x);
    }
}
