package com.kamals.algo.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.StringMap;

import java.io.*;
import java.util.List;
import java.util.Map;

public class ConvertJsonToTable
{

    public static void main(String[] args) throws Exception
    {
        String json = "[{\"600507931\":[{\"thumbnailUrl\":\"/medias/8ecb20df-40ea-4c4d-8ae1-a9ef6e006f9f-1200Wx1200H-96Wx96H?context=bWFzdGVyfGltYWdlc3w2ODU1fGltYWdlL2pwZWd8aW1hZ2VzL2hjZi9oMmQvODgwNTE2NTQzMjg2Mi5qcGd8NDMyNGQwZDg0NmUzZTYwY2Q5MGY1OTAyMGRjOTFhOThjNTNiNzZiOWNlNWY4NzY3NjFkNGJmZTg4ZjdmZDJiYw\",\"zoomUrl\":\"/medias/8ecb20df-40ea-4c4d-8ae1-a9ef6e006f9f-1200Wx1200H?context=bWFzdGVyfGltYWdlc3wxMzAwMzl8aW1hZ2UvanBlZ3xpbWFnZXMvaDkxL2g1MC84ODA1MTY1MzY3MzI2LmpwZ3w1N2RiNGZiMzA3ZGU5YmJjZWNjNDVjMzdjMGJhMjVkYTEyMmQwN2U4Y2E4ZDRiMDYxMTkxNmRmNTdjNTQ2NTYx\"}]},{\"600518641\":[{\"thumbnailUrl\":\"/medias/resQ-Care-Plan-RCP-Extended-Warranty-3-Year-RO-Water-Purifier-without-Consumables-1200Wx1200H-96Wx96H?context=bWFzdGVyfGltYWdlc3w3MTU1fGltYWdlL2pwZWd8aW1hZ2VzL2hmYi9oMzYvODg5OTc3NjcwODYzOC5qcGd8ZGFjZWZmZTMyOGE3Y2E2OGNkYTFhMTc2ZjgzOTZhYTUyMjUzYjhjMmVhZGVhMjEzMjU5YzA3NjA1OTE3ZWExZA\",\"zoomUrl\":\"/medias/resQ-Care-Plan-RCP-Extended-Warranty-3-Year-RO-Water-Purifier-without-Consumables-1200Wx1200H?context=bWFzdGVyfGltYWdlc3wxMzgxNTF8aW1hZ2UvanBlZ3xpbWFnZXMvaGRiL2hjNS84ODk5Nzc2NjQzMTAyLmpwZ3w3OWFhY2MzNjQ3ZGQyZWI1NzAyMjI1MzVmMzM4YWM4MzY4YzAyMGFiNDNhOWYyMWYwNDRkYTU4MDQ4YTE0Njgz\"}]}]";

        String inputFilename = "/Users/kamal.sultania/Downloads/All_Image_Data.txt";
        String outputFilename = "/Users/kamal.sultania/Downloads/All_Image_Data.csv";

        File inputFile = new File(inputFilename);
        FileReader fileReader = new FileReader(inputFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        json = bufferedReader.readLine();

        bufferedReader.close();

        List data = null;
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        data = gson.fromJson(json, List.class);

        File outputFile = new File(outputFilename);
        FileWriter fileWriter = new FileWriter(outputFile);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (Object obj : data)
        {
            Map map = (Map) obj;
            for (Object obj2 : map.keySet())
            {
                String key = (String) obj2;
                Object obj3 = map.get(key);
                List<StringMap> list = (List<StringMap>) obj3;
                for (StringMap media : list)
                {
                    bufferedWriter.write(key + "," + media.get("thumbnailUrl") +
                                    "," + media.get("zoomUrl") + "\n");
                    //System.out.println();
                }
            }
        }
        bufferedWriter.close();
    }

    class Media
    {
        String thumbnailUrl;
        String zoomUrl;
    }
}
