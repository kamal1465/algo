package com.kamals.algo.example;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class StringTest
{
    public static void main(String[] args)
    {
        List<Integer> ml1 = new ArrayList<>();
//        System.out.println("*");
//        System.out.println(ml1.stream().map(e -> e.toString()).collect(Collectors.joining("")));
//        System.out.println("*");
        //char c = 192;
        //System.out.println(c);
        //testStringTokenize("a,b,c ");
        //testSubstringBetween();

        //testSplit();

        String s = "{\"firstname\"\t: \"Abc\", \"lastname\"\t: \"Xyz\",  \"dob\"\t\t\t: \"25-12-1994\", \"gender\"\t\t: \"M\", \n\"mobileno\"\t: \"9090909090\", \n\"email\"\t\t: \"abc.xyz@abc.com\", \n\"pincode\"\t\t: \"560010\", \n\"city\"\t\t: \"Bangalore\", \n\"store\"\t\t: \"5917\",\n\"tnc\"\t\t\t: true\n}";

        System.out.println(s);
    }

    private static void testStringTokenize(String s)
    {
        List<String> list = Arrays.asList(s.split(","));

        System.out.println(list);
    }


    private static void testSubstringBetween()
    {
        String s = "fq={!tag=fk4?fk5}availability_string:Exclude\\+out\\+of\\+Stock{!tag=fk2}";

        String tag = StringUtils.substringBetween(s, "{!tag=", "}");
        tag = tag.replaceAll("(\\w+).*", "$1");
        System.out.println(tag);

        s = "AOC APC";
        if (s.endsWith("AOC"))
        {
            s = s.substring(0, s.length()-4);
            s = s + "(AOC)";
        }
        if (s.endsWith("APC"))
        {
            s = s.substring(0, s.length()-4);
            s = s + "%28APC%29";
        }
        System.out.println(s);
    }

    private static void test1()
    {
        String solrQueryStr = "_query_:\"\\{\\!multiMaxScore\\ tie=0.0\\}\\(\\(keywords_text_en\\:apple\\^20.0\\)\\ OR\\ \\(name_text_en\\:apple\\^50.0\\)\\ OR\\ \\(brandName_text_en_mv\\:apple\\^80.0\\)\\ OR\\ \\(code_string\\:apple\\^90.0\\)\\ OR\\ \\(categoryName_text_en_mv\\:apple\\^60.0\\)\\)@#%\\{\\!multiMaxScore\\ tie=0.0\\}\\(\\(keywords_text_en\\:apple\\^20.0\\)\\ OR\\ \\(name_text_en\\:apple\\^50.0\\)\\ OR\\ \\(brandName_text_en_mv\\:apple\\^80.0\\)\\ OR\\ \\(code_string\\:apple\\^90.0\\)\\ OR\\ \\(categoryName_text_en_mv\\:apple\\^60.0\\)\\)\\ OR\\ \\(\\(name_text_en\\:\\\"apple\\\"\\~2.0\\^100.0\\)\\)@#%\\{\\!multiMaxScore\\ tie=0.0\\}\\(\\(keywords_text_en\\:apple\\~\\^10.0\\)\\ OR\\ \\(brandName_text_en_mv\\:apple\\~\\^5.0\\)\\ OR\\ \\(categoryName_text_en_mv\\:apple\\~\\^5.0\\)\\)\" AND ({!func v=\"sum(map(query({!v='code_string\\:491297353'}),0,0,0,1000000.0),map(query({!v='code_string\\:491297354'}),0,0,0,995000.0),map(query({!v='code_string\\:491297355'}),0,0,0,990000.0))\"})";
        String subQuery = "";
        boolean appendQuery = false;
        if (solrQueryStr.startsWith("_query_:\""))
        {
            solrQueryStr = solrQueryStr.substring(9, solrQueryStr.length());
            int queryEnd = solrQueryStr.indexOf("\"");

            if (solrQueryStr.length() > queryEnd+1)
            {
                subQuery = solrQueryStr.substring(queryEnd+1);
            }
            solrQueryStr = solrQueryStr.substring(0, queryEnd);

            appendQuery = true;
        }
        String splitRegex = "@#%";
        String[] allQueries = solrQueryStr.split(splitRegex);
        for (int queryIterator = 0; queryIterator < allQueries.length; queryIterator++)
        {
            String query = allQueries[queryIterator];
            if (appendQuery)
            {
                query = "_query_:\"" + query + "\"" + subQuery;
                System.out.println(query);
            }
        }
    }

    private static void testSplit()
    {
        String shiftType = "    heavy1  |   nonheavy   ";

        {
            shiftType = shiftType.toLowerCase();
            String[] s = shiftType.split("[|]");
            boolean heavy = false, nonheavy = false;
            for (String t : s)
            {
                heavy = heavy || "heavy".equals(t.trim());
                nonheavy = nonheavy || "nonheavy".equals(t.trim());;
            }
            System.out.println(heavy + " " + nonheavy);
        }
    }

    private static void getParams()
    {
        Map<String, String> params = new HashMap<>();
        params.put("1", "abc");
        params.put("2", "def");
        //params.put("3", "ghi");

        StringBuilder queryParams = new StringBuilder();
        String[] list = new String[]{"3", "1", "2"};
        for (String s : list)
        {
            if (params.get(s) != null)
            {
                if (queryParams.length() > 0)
                {
                    queryParams.append("&");
                }
                queryParams.append(s).append("=").append(params.get(s));
            }
        }
        System.out.println(queryParams.toString());
    }

}
