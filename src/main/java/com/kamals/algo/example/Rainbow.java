package com.kamals.algo.example;

import java.util.ArrayDeque;
import java.util.Queue;

public class Rainbow
{
    public enum MyColor
    {
        RED(0xff0000), GREEN(0x00ff00), BLUE(0x0000ff);
        private final int rgb;

        MyColor(int rgb)
        {
            this.rgb = rgb;
        }

        public int getRGB()
        {
            return rgb;
        }
    }


    public static void main(String[] args)
    {
        // Line n1
        Queue<String> products = new ArrayDeque<String>();
        products.add("p1");
        products.add("p2");
        products.add("p3");
        products.add("p1");

        products.removeIf(o -> o.equals("p1"));

        System.out.print(products);


    }

    static class Feline {
        public String type = "f ";
        public Feline() {
            System.out.print("feline ");
        }
    }
    static class Cat extends Feline {
        public Cat() {
            System.out.print("cat ");
        }
        void go() {
            type = "c ";
            System.out.print(this.type + super.type);
        }
    }

}
