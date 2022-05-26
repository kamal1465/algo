package com.kamals.algo.example.madhuri;

import java.util.Scanner;

public class HelloWorld
{
    public static void main(String[] args)
    {
        String name;

        Scanner scanner = new Scanner(System.in);


        System.out.println("Please enter your name:");

        name = scanner.nextLine();

        System.out.println("Hi " + name + "!!");
        while (true)
        {
            System.out.println("What would you like to do today?");
            System.out.println("a. Addition");
            System.out.println("b. Subtraction");
            System.out.println("c. Multiplication");
            System.out.println("d. Division");
            System.out.println("q. Quit");

            String option = scanner.nextLine();

            if (option.equalsIgnoreCase("q"))
            {
                break;
            }

            if (option.equalsIgnoreCase("a"))
            {
                System.out.println("Please enter A and B:");
                int a = scanner.nextInt();
                int b = scanner.nextInt();

                System.out.println("Answer: " + a + " + " + b + add(a, b));
            }
            else if (option.equalsIgnoreCase("b"))
            {
                System.out.println("Please enter A and B:");
                int a = scanner.nextInt();
                int b = scanner.nextInt();

                System.out.println("Answer: " + a + " - " + b + subtract(a, b));
            }
            else if (option.equalsIgnoreCase("c"))
            {
                System.out.println("Please enter A and B:");
                int a = scanner.nextInt();
                int b = scanner.nextInt();

                System.out.println("Answer: " + a + " * " + b + multiply(a, b));
            }
            else if (option.equalsIgnoreCase("d"))
            {
                System.out.println("Please enter A and B:");
                int a = scanner.nextInt();
                int b = scanner.nextInt();

                System.out.println("Answer: " + a + " / " + b + divide(a, b));
            }
            else
            {
                System.out.println(option);
                System.out.println("WRONG OPTION. TRY AGAIN.");
            }
        }
    }

    private static int add(int a, int b)
    {
        return a + b;
    }

    private static int subtract(int a, int b)
    {
        return a - b;
    }

    private static int multiply(int a, int b)
    {
        return a * b;
    }

    private static int divide(int a, int b)
    {
        return a / b;
    }
}
