package com.kamals.algo.example;

import java.util.*;

public class MaxRectangle
{
    public static void main(String args[]) throws Exception
    {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();

        int[] x = new int[N];
        int[] y = new int[N];
        char[] c = new char[N];

        List<Point> conList = new ArrayList<>();
        List<Point> bjpList = new LinkedList<>();

        for (int i = 0; i < N; i++)
        {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
            String s = in.next();
            c[i] = s.charAt(0);

            if (c[i] == 'C')
            {
                conList.add(new Point(x[i], y[i]));
            }
            else
            {
                bjpList.add(new Point(x[i], y[i]));
            }
        }

        int cNum = conList.size();

        if (cNum == 1)
        {
            System.out.println(1);
            System.out.println(0);
            System.exit(0);
        }

        BitSet cset = new BitSet(cNum);
        cset.set(0, cNum);
        System.out.println(cset);
        Set<BitSet> S1 = new HashSet<>();
        Set<BitSet> S2 = new HashSet<>();
        S1.add(cset);

        for (Point p : bjpList)
        {
            for (BitSet b : S1)
            {
                if (liesIn(b, p, conList))
                {
                    System.out.println("breaking");
                    Set<BitSet> s = breakRectangle(b, p, conList);
                    System.out.println(s);
                    S2.addAll(s);
                }
                else
                {
                    S2.add(b);
                }
            }
            Set<BitSet> tmp = S1;
            S1 = S2;
            S2 = tmp;
            S2.clear();
        }

        int maxNum = 0;

        for (BitSet b : S1)
        {
            maxNum = Math.max(maxNum, b.cardinality());
        }

        int minArea = Integer.MAX_VALUE;
        for (BitSet b : S1)
        {
            if (b.cardinality() == maxNum)
            {
                int area = rectangle(b, conList).area();
                minArea = Math.min(minArea, area);
            }
        }

        System.out.println(maxNum);
        System.out.println(minArea);
    }

    static Set<BitSet> breakRectangle(BitSet b, Point p, List<Point> conList)
    {
        Set<BitSet> x = new HashSet<>();

        int cNum = conList.size();
        BitSet b1 = new BitSet(cNum);
        BitSet b2 = new BitSet(cNum);
        BitSet b3 = new BitSet(cNum);
        BitSet b4 = new BitSet(cNum);

        for (int i = 0; i < cNum; i++)
        {
            if (b.get(i))
            {
                Point c = conList.get(i);

                if (p.x > c.x)
                {
                    b1.set(i);
                }
                if (p.x < c.x)
                {
                    b2.set(i);
                }
                if (p.y > c.y)
                {
                    b3.set(i);
                }
                if (p.y < c.y)
                {
                    b4.set(i);
                }
            }
        }
        if (b1.cardinality() > 0)
        {
            x.add(b1);
        }
        if (b2.cardinality() > 0)
        {
            x.add(b2);
        }
        if (b3.cardinality() > 0)
        {
            x.add(b3);
        }
        if (b4.cardinality() > 0)
        {
            x.add(b4);
        }
        return x;
    }

    static boolean liesIn(BitSet b, Point p, List<Point> conList)
    {
        Rectangle r = rectangle(b, conList);
        if (r != null)
        {
            return p.x >= r.x1 && p.x <= r.x2 && p.y >= r.y1 && p.y <= r.y2;
        }
        return false;
    }

    static Rectangle rectangle(BitSet b, List<Point> conList)
    {
        if (b.cardinality() > 0)
        {
            int x1 = Integer.MAX_VALUE, x2 = Integer.MIN_VALUE, y1 = Integer.MAX_VALUE, y2 = Integer.MIN_VALUE;
            for (int i = 0; i < conList.size(); i++)
            {
                if (b.get(i))
                {
                    Point p = conList.get(i);
                    x1 = Math.min(x1, p.x);
                    x2 = Math.max(x2, p.x);
                    y1 = Math.min(y1, p.y);
                    y2 = Math.max(y2, p.y);
                }
            }
            return new Rectangle(x1, x2, y1, y2);
        }
        return null;
    }


    static class Point
    {
        int x;
        int y;

        Point(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o)
            {
                return true;
            }
            if (o == null || getClass() != o.getClass())
            {
                return false;
            }
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(x, y);
        }
    }

    static class Rectangle
    {
        int x1;
        int x2;
        int y1;
        int y2;

        Rectangle(int x1, int x2, int y1, int y2)
        {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        }

        int area()
        {
            return (x2-x1) * (y2-y1);
        }
    }
}
