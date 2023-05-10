package com.kamals.algo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 2402. Meeting Rooms III
 * Hard
 * 487
 * 31
 * company
 * Google
 * You are given an integer n. There are n rooms numbered from 0 to n - 1.
 * <p>
 * You are given a 2D integer array meetings where meetings[i] = [starti, endi] means that a meeting will be held during the half-closed time interval [starti, endi). All the values of starti are unique.
 * <p>
 * Meetings are allocated to rooms in the following manner:
 * <p>
 * Each meeting will take place in the unused room with the lowest number.
 * If there are no available rooms, the meeting will be delayed until a room becomes free. The delayed meeting should have the same duration as the original meeting.
 * When a room becomes unused, meetings that have an earlier original start time should be given the room.
 * Return the number of the room that held the most meetings. If there are multiple rooms, return the room with the lowest number.
 * <p>
 * A half-closed interval [a, b) is the interval between a and b including a and not including b.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 2, meetings = [[0,10],[1,5],[2,7],[3,4]]
 * Output: 0
 * Explanation:
 * - At time 0, both rooms are not being used. The first meeting starts in room 0.
 * - At time 1, only room 1 is not being used. The second meeting starts in room 1.
 * - At time 2, both rooms are being used. The third meeting is delayed.
 * - At time 3, both rooms are being used. The fourth meeting is delayed.
 * - At time 5, the meeting in room 1 finishes. The third meeting starts in room 1 for the time period [5,10).
 * - At time 10, the meetings in both rooms finish. The fourth meeting starts in room 0 for the time period [10,11).
 * Both rooms 0 and 1 held 2 meetings, so we return 0.
 * Example 2:
 * <p>
 * Input: n = 3, meetings = [[1,20],[2,10],[3,5],[4,9],[6,8]]
 * Output: 1
 * Explanation:
 * - At time 1, all three rooms are not being used. The first meeting starts in room 0.
 * - At time 2, rooms 1 and 2 are not being used. The second meeting starts in room 1.
 * - At time 3, only room 2 is not being used. The third meeting starts in room 2.
 * - At time 4, all three rooms are being used. The fourth meeting is delayed.
 * - At time 5, the meeting in room 2 finishes. The fourth meeting starts in room 2 for the time period [5,10).
 * - At time 6, all three rooms are being used. The fifth meeting is delayed.
 * - At time 10, the meetings in rooms 1 and 2 finish. The fifth meeting starts in room 1 for the time period [10,12).
 * Room 0 held 1 meeting while rooms 1 and 2 each held 2 meetings, so we return 1.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 100
 * 1 <= meetings.length <= 105
 * meetings[i].length == 2
 * 0 <= starti < endi <= 5 * 105
 * All the values of starti are unique.
 */
public class MeetingRoom3
{
    static class Room
    {
        int num;
        int busyTill;


    }
    public int mostBooked(int n, int[][] meetings)
    {
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);

        //Room -> int[] r, where r[0] = number, and r[1] = busy till
        //For busy rooms, sort on freeTime, break ties in favour of smaller index
        PriorityQueue<int[]> busyRooms = new PriorityQueue<>((a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        //For free rooms, sort on room number, since they are all already free
        PriorityQueue<int[]> freeRooms = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        //Initially, all rooms are free
        for (int i = 0; i < n; i++)
        {
            freeRooms.add(new int[]{i, 0});
        }

        int[] count = new int[n];

        for (int[] meeting : meetings)
        {
            int startTime = meeting[0];
            int endTime = meeting[1];

            //All the busy rooms, that become free before startTime, should be made free
            while (busyRooms.peek() != null && busyRooms.peek()[1] <= startTime)
            {
                //Remove from busy, add to free
                freeRooms.add(busyRooms.poll());
            }

            if (!freeRooms.isEmpty())
            {
                //Remove from free, add to busy
                int[] x = freeRooms.poll();
                x[1] = endTime;
                busyRooms.add(x);
                count[x[0]]++;
            }
            else
            {
                //Remove from busy, add to busy
                int[] busyRoom = busyRooms.poll();
                busyRoom[1] += (endTime - startTime);
                busyRooms.add(busyRoom);
                count[busyRoom[0]]++;
            }
        }
        int max = 0;
        List<Integer> all = new ArrayList<>();
        for (int i = 0; i < n; i++)
        {
            if (count[i] > max)
            {
                max = count[i];
                all.clear();
                all.add(i);
            }
            else if (count[i] == max)
            {
                all.add(i);
            }
        }
        return all.get(0);
    }
}
