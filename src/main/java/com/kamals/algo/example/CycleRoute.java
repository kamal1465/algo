package com.kamals.algo.example;

import java.util.*;

public class CycleRoute
{
    public static void main(String args[]) throws Exception
    {

        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();

        List<Road>[] f = new List[N + 1];

        for (int i = 0; i <= N; i++)
        {
            f[i] = new ArrayList<Road>();
        }
        for (int i = 0; i < M; i++)
        {
            int u = in.nextInt();
            int v = in.nextInt();
            int t = in.nextInt();
            int h = in.nextInt();

            f[u].add(new Road(v, h-t));
        }

//        for (int i = 1; i <= N; i++)
//        {
//            System.out.println(f[i]);
//        }

        int minRoads = Integer.MAX_VALUE;
        long maxMargin = 0l;

        for (int i = 1; i <= N; i++)
        {
//            System.out.println("For node " + i);
            int pathLength = 1;
            Map<Integer, Long> A1 = new HashMap<>();
            Map<Integer, Long> A2 = new HashMap<>();

            Set<Integer> A3 = new HashSet<>();

            for (Road r : f[i])
            {
                A1.put(r.n, r.m);

                A3.add(r.n);
            }
//            System.out.println(A1);

            while (true)
            {
                boolean found = false;
                for (Map.Entry<Integer, Long> r : A1.entrySet())
                {
                    //System.out.println(r.getKey() + " " + r.getValue());
                    long mxM = 0;

                    for (Road x : f[r.getKey()])
                    {
                        if (!A3.contains(x.n))
                        {
                            A3.add(x.n);
                            long mgn = x.m + r.getValue();
                            //System.out.println(x.n + " " + mgn);
                            A2.put(x.n, mgn);

                            if (i == x.n && mgn > 0)
                            {
                                found = true;
                            }
                        }
                    }
                }
                pathLength++;
//                System.out.println(A2);

                if (A2.isEmpty())
                {
                    break;
                }

                Map<Integer, Long> tmp = A1;
                A1 = A2;
                A2 = tmp;
                A2.clear();

                if (found)
                {
                    if (pathLength < minRoads)
                    {
                        minRoads = pathLength;
                        maxMargin = Integer.MIN_VALUE;
                        for (Map.Entry<Integer, Long> r : A1.entrySet())
                        {
                            if (r.getKey() == i)
                            {
                                maxMargin = Math.max(maxMargin, r.getValue());
                            }
                        }
                    }
                    else if (pathLength == minRoads)
                    {
                        for (Map.Entry<Integer, Long> r : A1.entrySet())
                        {
                            if (r.getKey() == i)
                            {
                                maxMargin = Math.max(maxMargin, r.getValue());
                            }
                        }
                    }
//                    System.out.println("Found " + minRoads + " " + maxMargin);
                    break;
                }

            }

        }

        System.out.print(minRoads + " " + maxMargin);
    }

    static class Road
    {
        int n;
        long m;

        public Road(int n, long m)
        {
            this.n = n;
            this.m = m;
        }
    }

    static class Route
    {
        int n;
        long margin;

        public Route(int n, long margin)
        {
            this.n = n;
            this.margin = margin;
        }
    }
}
