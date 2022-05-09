package com.kamals.algo.designpatterns;

import java.util.*;

public class ProductVariantTest
{

    public static void main(String[] args)
    {
        Product iphone = new Product("iphone", "iPhone", null);

        Map<String, Object> p = new LinkedHashMap<>();
        p.put("color", "Red");
        Product iphoneRed = new VariantProduct(iphone, p);

        String card = "4242420100058007|123|12|2021|Abc Xyz";
        String[] cardDetails = card.split("\\|");
        int expiryMm = Integer.parseInt(cardDetails[2]);
        int expiryYy = Integer.parseInt(cardDetails[3]);

        for (String c : cardDetails)
        {
            System.out.println(c);
        }
    }


    static class Product
    {
        private final String code;
        private final String name;
        private final Map<String, Object> properties;
        private final List<VariantProduct> variants;

        public Product(String code, String name, Map<String, Object> properties)
        {
            this.code = code;
            this.name = name;
            this.properties = properties;
            this.variants = new ArrayList<>();
        }

        public Product(Product p)
        {
            this.code = p.code;
            this.name = p.name;
            this.properties = p.properties;
            this.variants = new ArrayList<>();
        }
    }

    static class VariantProduct extends Product
    {
        private final Product product;
        private final Map<String, Object> variantProperties;

        public VariantProduct(Product product, Map<String, Object> vp)
        {
            super(product);
            this.product = product;
            this.variantProperties = vp;
        }
    }
}
