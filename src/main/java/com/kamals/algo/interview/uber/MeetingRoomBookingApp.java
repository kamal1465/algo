package com.kamals.algo.interview.uber;

import java.util.*;

/**
 * Book a number of meeting rooms
 */
public class MeetingRoomBookingApp
{
    int N = 10;

    public int bookRoom(Date startTime, Date endTime)
    {

        return -1;
    }

    public boolean cancelBooking(int room, Date startTime, Date endTime)
    {
        return false;
    }

    class Interval implements Comparable<Interval>
    {
        int s;
        int f;

        public Interval(int s, int f)
        {
            this.s = s;
            this.f = f;
        }

        @Override
        public int compareTo(Interval o)
        {
            return this.s - o.s;
        }
    }

    public int minMeetingRooms(int[][] intervals)
    {
        List<Interval> queue = new ArrayList<>();
        for (int[] in : intervals)
        {
            queue.add(new Interval(in[0], in[1]));
        }
        Collections.sort(queue);
        PriorityQueue<Integer> macs = new PriorityQueue<>();
        for (Interval in : queue)
        {
            int s = in.s;
            int f = in.f;
            if (macs.size() > 0)
            {
                int minF = macs.peek();
                if (minF <= s)
                {
                    macs.poll();
                }
            }
            macs.add(f);
        }
        return macs.size();
    }

    public static void main(String[] args)
    {
        MeetingRoomBookingApp meetingRoomBookingApp = new MeetingRoomBookingApp();
        int[][] ints = new int[][]{{12, 13}, {6, 11}, {2, 19}};
        System.out.println(meetingRoomBookingApp.minMeetingRooms(ints));
    }
}
