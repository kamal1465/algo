package com.kamals.algo.leetcode;

import com.kamals.algo.algos.util.Util;

import java.io.InputStream;
import java.util.*;

/**
 * You are given a 2D array of integers envelopes where envelopes[i] = [wi, hi] represents the width and the height of an envelope.
 * <p>
 * One envelope can fit into another if and only if both the width and height of one envelope are greater than the other envelope's width and height.
 * <p>
 * Return the maximum number of envelopes you can Russian doll (i.e., put one inside the other).
 * <p>
 * Note: You cannot rotate an envelope.
 * <p>
 * Example 1:
 * <p>
 * Input: envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * Output: 3
 * Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 * Example 2:
 * <p>
 * Input: envelopes = [[1,1],[1,1],[1,1]]
 * Output: 1
 * <p>
 * Constraints:
 * <p>
 * 1 <= envelopes.length <= 105
 * envelopes[i].length == 2
 * 1 <= wi, hi <= 105
 */
public class RussianDollEnvelopes
{
    //    private static int added = 0;
    //    private static int discoverd = 0;

    public static void main(String[] args) throws Exception
    {
        RussianDollEnvelopes russianDollEnvelopes = new RussianDollEnvelopes();
        //        Properties properties = new Properties();
        //        InputStream inputStream = russianDollEnvelopes.getClass().getResourceAsStream("local.properties");
        //        properties.load(inputStream);

        //        String s = properties.getProperty("russiandoll.input");
        //        System.out.println(s);

        //        int[][] envelopes = new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        int[][] envelopes = new int[][]{{856, 533}, {583, 772}, {980, 524}, {203, 666}, {987, 151}, {274, 802}, {982, 85}, {359, 160}, {58, 823}, {512, 381}, {796, 655}, {341, 427}, {145, 114}, {76, 306}, {760, 929}, {836, 751}, {922, 678}, {128, 317}, {185, 953}, {115, 845}, {829, 991}, {93, 694}, {317, 434}, {818, 571}, {352, 638}, {926, 780}, {819, 995}, {54, 69}, {191, 392}, {377, 180}, {669, 952}, {588, 920}, {335, 316}, {48, 769}, {188, 661}, {916, 933}, {674, 308}, {356, 556}, {350, 249}, {686, 851}, {600, 178}, {849, 439}, {597, 181}, {80, 382}, {647, 105}, {4, 836}, {901, 907}, {595, 347}, {214, 335}, {956, 382}, {77, 979}, {489, 365}, {80, 220}, {859, 270}, {676, 665}, {636, 46}, {906, 457}, {522, 769}, {2, 758}, {206, 586}, {444, 904}, {912, 370}, {64, 871}, {59, 409}, {599, 238}, {437, 58}, {309, 767}, {258, 440}, {922, 369}, {848, 650}, {478, 76}, {84, 704}, {314, 207}, {138, 823}, {994, 764}, {604, 595}, {537, 876}, {877, 253}, {945, 185}, {623, 497}, {968, 633}, {172, 705}, {577, 388}, {819, 763}, {409, 905}, {275, 532}, {729, 593}, {547, 226}, {445, 495}, {398, 544}, {243, 500}, {308, 24}, {652, 452}, {93, 885}, {75, 884}, {243, 113}, {600, 555}, {756, 596}, {892, 762}, {402, 653}, {916, 975}, {770, 220}, {455, 579}, {889, 68}, {306, 899}, {567, 290}, {809, 653}, {92, 329}, {370, 861}, {632, 754}, {321, 689}, {190, 812}, {88, 701}, {79, 310}, {917, 91}, {751, 480}, {750, 39}, {781, 978}, {778, 912}, {946, 559}, {529, 621}, {55, 295}, {473, 748}, {646, 854}, {930, 913}, {116, 734}, {647, 812}, {426, 172}, {122, 14}, {522, 843}, {88, 308}, {719, 602}, {712, 928}, {303, 890}, {973, 886}, {276, 354}, {660, 720}, {708, 387}, {776, 605}, {653, 815}, {448, 285}, {549, 959}, {139, 365}, {74, 952}, {372, 424}, {642, 504}, {361, 901}, {620, 612}, {313, 301}, {397, 225}, {446, 716}, {17, 361}, {160, 812}, {171, 529}, {180, 482}, {454, 600}, {228, 872}, {204, 492}, {607, 889}, {86, 79}, {494, 78}, {442, 404}, {462, 127}, {935, 402}, {509, 649}, {458, 941}, {219, 444}, {306, 57}, {674, 617}, {79, 652}, {73, 735}, {900, 756}, {649, 294}, {982, 754}, {521, 439}, {356, 265}, {240, 533}, {865, 44}, {744, 379}, {97, 454}, {65, 480}, {544, 191}, {18, 191}, {503, 38}, {696, 658}, {61, 884}, {793, 984}, {383, 364}, {280, 467}, {888, 662}, {133, 643}, {365, 512}, {610, 975}, {98, 584}, {40, 177}, {548, 102}, {80, 98}, {986, 951}, {264, 258}, {583, 734}, {353, 322}, {427, 551}, {80, 660}, {273, 609}, {980, 871}, {739, 802}, {366, 836}, {55, 509}, {889, 720}, {857, 661}, {48, 489}, {119, 26}, {31, 180}, {472, 673}, {960, 951}, {383, 500}, {928, 351}, {848, 705}, {969, 766}, {311, 714}, {861, 230}, {34, 596}, {38, 642}, {1, 955}, {698, 846}, {784, 791}, {760, 344}, {677, 239}, {969, 191}, {539, 644}, {470, 418}, {289, 357}, {269, 446}, {668, 245}, {293, 719}, {937, 103}, {575, 297}, {874, 656}, {714, 257}, {934, 396}, {109, 904}, {89, 635}, {374, 545}, {316, 587}, {158, 121}, {901, 969}, {284, 564}, {666, 568}, {993, 409}, {370, 637}, {443, 694}, {576, 160}, {262, 357}, {590, 729}, {194, 976}, {743, 376}, {348, 80}, {669, 527}, {338, 953}, {236, 785}, {144, 460}, {438, 457}, {517, 951}, {545, 647}, {158, 556}, {905, 591}, {793, 609}, {571, 643}, {9, 850}, {581, 490}, {804, 394}, {635, 483}, {457, 30}, {42, 621}, {65, 137}, {424, 864}, {536, 455}, {59, 492}, {645, 734}, {892, 571}, {762, 593}, {608, 384}, {558, 257}, {692, 420}, {973, 203}, {531, 51}, {349, 861}, {804, 649}, {3, 611}, {6, 468}, {298, 568}, {651, 767}, {251, 142}, {173, 974}, {117, 728}, {326, 562}, {894, 288}, {814, 555}, {420, 771}, {20, 775}, {445, 247}, {243, 592}, {186, 173}, {101, 800}, {590, 876}, {515, 534}, {73, 540}, {333, 215}, {902, 394}, {640, 787}, {596, 298}, {984, 712}, {307, 378}, {540, 646}, {473, 743}, {340, 387}, {756, 217}, {139, 493}, {9, 742}, {195, 25}, {763, 823}, {451, 693}, {24, 298}, {645, 595}, {224, 770}, {976, 41}, {832, 78}, {599, 705}, {487, 734}, {818, 134}, {225, 431}, {380, 566}, {395, 680}, {294, 320}, {915, 201}, {553, 480}, {318, 42}, {627, 94}, {164, 959}, {92, 715}, {588, 689}, {734, 983}, {976, 334}, {846, 573}, {676, 521}, {449, 69}, {745, 810}, {961, 722}, {416, 409}, {135, 406}, {234, 357}, {873, 61}, {20, 521}, {525, 31}, {659, 688}, {424, 554}, {203, 315}, {16, 240}, {288, 273}, {281, 623}, {651, 659}, {939, 32}, {732, 373}, {778, 728}, {340, 432}, {335, 80}, {33, 835}, {835, 651}, {317, 156}, {284, 119}, {543, 159}, {719, 820}, {961, 424}, {88, 178}, {621, 146}, {594, 649}, {659, 433}, {527, 441}, {118, 160}, {92, 217}, {489, 38}, {18, 359}, {833, 136}, {470, 897}, {106, 123}, {831, 674}, {181, 191}, {892, 780}, {377, 779}, {608, 618}, {618, 423}, {180, 323}, {390, 803}, {562, 412}, {107, 905}, {902, 281}, {718, 540}, {16, 966}, {678, 455}, {597, 135}, {840, 7}, {886, 45}, {719, 937}, {890, 173}};
        int max = russianDollEnvelopes.maxEnvelopes4(envelopes);
        System.out.println(max);
    }

    public int maxEnvelopes5(int[][] envelopes)
    {
        long startTime = System.nanoTime();
        //        Util.printArr2(envelopes);
        Arrays.sort(envelopes, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        //        Util.printArr2(envelopes);
        int[] russianDollsSizeLastIndex = new int[envelopes.length + 1];
        Arrays.fill(russianDollsSizeLastIndex, -1);
        russianDollsSizeLastIndex[1] = 0;
        int max = 1;
        for (int index = 1; index < envelopes.length; index++)
        {
            max = find(envelopes, russianDollsSizeLastIndex, index, max);
            Util.printArr2(russianDollsSizeLastIndex);
        }
        long endTime = System.nanoTime();
        long t1 = (endTime - startTime) / 1000000;
        System.out.println("Time taken = " + t1);
        return max;
    }

    private int find(int[][] envelopes, int[] russianDollsSizeLastIndex, int index, int max)
    {
        int start = 1, end = max, maxIn = -1;

        while (start <= end)
        {
            int mid = (start + end) / 2;
            int k = russianDollsSizeLastIndex[mid];
            if (envelopes[index][1] > envelopes[k][1])
            {
                maxIn = mid;
                start = mid + 1;
            }
            else
            {
                end = mid - 1;
            }
        }

        if (maxIn > -1)
        {
            russianDollsSizeLastIndex[maxIn + 1] = index;
            max = Math.max(maxIn + 1, max);
        }
        else
        {
            russianDollsSizeLastIndex[1] = index;
        }
        return max;
    }

    public int maxEnvelopes4(int[][] envelopes)
    {
        long startTime = System.nanoTime();
        //        Util.printArr2(envelopes);
        Arrays.sort(envelopes, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        //        Util.printArr2(envelopes);
        int[] russianDollsSizeLastIndex = new int[envelopes.length + 1];
        Arrays.fill(russianDollsSizeLastIndex, -1);
        russianDollsSizeLastIndex[1] = 0;
        int max = 1;
        for (int index = 1; index < envelopes.length; index++)
        {
            boolean found = false;
            for (int mapIndex = max; mapIndex > 0; mapIndex--)
            {
                int k = russianDollsSizeLastIndex[mapIndex];
                if (envelopes[index][1] > envelopes[k][1])
                {
                    found = true;
                    russianDollsSizeLastIndex[mapIndex + 1] = index;
                    max = Math.max(mapIndex + 1, max);
                    break;
                }
            }
            if (!found)
            {
                russianDollsSizeLastIndex[1] = index;
            }
            //            Util.printArr2(russianDollsSizeLastIndex);
        }
        long endTime = System.nanoTime();
        long t1 = (endTime - startTime) / 1000000;
        System.out.println("Time taken = " + t1);
        return max;
    }

    public int maxEnvelopes3(int[][] envelopes)
    {
        long startTime = System.nanoTime();
        int[] cache = new int[envelopes.length];
        int[] runningMax = new int[envelopes.length + 1];
        //Util.printArr2(envelopes);
        Arrays.sort(envelopes, Comparator.comparingInt(o -> o[0]));
        //Util.printArr2(envelopes);
        int max1 = 0;
        for (int index = envelopes.length - 1; index >= 0; index--)
        {
            int max2 = 0;
            for (int nextIndex = index + 1; nextIndex < envelopes.length; nextIndex++)
            {
                if (max2 >= runningMax[nextIndex])
                {
                    break;
                }
                if (envelopes[index][0] < envelopes[nextIndex][0] && envelopes[index][1] < envelopes[nextIndex][1])
                {
                    max2 = Math.max(cache[nextIndex], max2);
                }
            }
            cache[index] = max2 + 1;
            runningMax[index] = Math.max(cache[index], runningMax[index + 1]);
            //System.out.println(index + " " + cache[index]);
            max1 = Math.max(cache[index], max1);
        }
        long endTime = System.nanoTime();
        long t1 = (endTime - startTime) / 1000000;
        System.out.println("Time taken = " + t1);
        return max1;
    }

    public int maxEnvelopes2(int[][] envelopes)
    {
        long startTime = System.nanoTime();
        List<Node> list = new ArrayList<>();
        for (int[] env : envelopes)
        {
            Node node = new Node(env[0], env[1]);
            list.add(node);
        }

        for (int i = 0; i < envelopes.length - 1; i++)
        {
            for (int j = i + 1; j < envelopes.length; j++)
            {
                if (envelopes[i][0] < envelopes[j][0] && envelopes[i][1] < envelopes[j][1])
                {
                    list.get(i).addNode(list.get(j));
                }
                else if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1])
                {
                    list.get(j).addNode(list.get(i));
                }
            }
        }
        for (Node node : list)
        {
            System.out.println(node);
        }
        long processTime = System.nanoTime();
        int max = DFS2(list);
        long endTime = System.nanoTime();
        long t1 = (processTime - startTime) / 1000000;
        long t2 = (endTime - processTime) / 1000000;
        System.out.println("Time taken = " + t1 + " " + t2);

        return max;
    }

    private int DFS2(List<Node> nodes)
    {
        Map<Node, Integer> discoveredNodes = new HashMap<>();
        Stack<Node> stack = new Stack<>();

        for (Node node : nodes)
        {
            if (!discoveredNodes.containsKey(node))
            {
                System.out.println("Adding Node = [" + node.getWidth() + " " + node.getHeight() + "]");
                stack.push(node);
                DFS2(stack, discoveredNodes);
            }
        }

        return discoveredNodes.values().stream().max(Integer::compareTo).orElse(0);
    }

    private void DFS2(Stack<Node> stack, Map<Node, Integer> discoveredNodes)
    {
        while (!stack.isEmpty())
        {
            Node node = stack.peek();

            if (discoveredNodes.containsKey(node))
            {
                stack.pop();
                continue;
            }

            boolean discovered = true;

            for (Node next : node.getNext())
            {
                if (!discoveredNodes.containsKey(next))
                {
                    System.out.println("Adding Node = [" + next.getWidth() + " " + next.getHeight() + "]");
                    stack.push(next);
                    discovered = false;
                }
            }
            if (discovered)
            {
                int max = 0;
                for (Node nn : node.getNext())
                {
                    max = Math.max(discoveredNodes.get(nn), max);
                }
                stack.pop();
                discoveredNodes.put(node, ++max);
                System.out.println("Discovered Node = [" + node.getWidth() + " " + node.getHeight() + "]  Length => " + max);
            }
        }
    }

    private int DFS(List<Node> nodes)
    {
        Map<Node, Integer> discoveredNodes = new HashMap<>();

        for (Node node : nodes)
        {
            DFS(node, discoveredNodes);
        }
        return discoveredNodes.values().stream().max(Integer::compareTo).orElse(0);
    }

    private void DFS(Node node, Map<Node, Integer> discoveredNodes)
    {
        if (!discoveredNodes.containsKey(node))
        {
            for (Node nn : node.getNext())
            {
                if (!discoveredNodes.containsKey(nn))
                {
                    DFS(nn, discoveredNodes);
                }
            }

            int max = 0;
            for (Node nn : node.getNext())
            {
                max = Math.max(discoveredNodes.get(nn), max);
            }
            discoveredNodes.put(node, max + 1);
        }
    }

    class Node implements Comparable<Node>
    {
        private final int width;
        private final int height;
        private final List<Node> next = new ArrayList<>();

        public Node(int width, int height)
        {
            this.width = width;
            this.height = height;
        }

        public int getWidth()
        {
            return width;
        }

        public int getHeight()
        {
            return height;
        }

        public List<Node> getNext()
        {
            return next;
        }

        public void addNode(Node next)
        {
            this.next.add(next);
        }

        @Override
        public int compareTo(Node o)
        {
            int widthDiff = this.getWidth() - o.getWidth();
            int heightDiff = this.getHeight() - o.getHeight();
            return widthDiff == 0 ? heightDiff : widthDiff;
        }

        @Override
        public String toString()
        {
            StringBuilder sb = new StringBuilder();
            sb.append("[").append(this.getWidth()).append(" ")
                    .append(this.getHeight()).append("] => ");
            for (Node node : this.getNext())
            {
                sb.append("[").append(node.getWidth()).append(" ")
                        .append(node.getHeight()).append("] ");
            }
            return sb.toString();
        }
    }
}
