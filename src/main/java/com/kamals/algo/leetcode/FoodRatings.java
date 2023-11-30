package com.kamals.algo.leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */
public class FoodRatings
{
    Map<String, Integer> foodRatingMap;
    Map<String, String> foodCuisineMap;
    Map<String, TreeSet<Food>> cuisineFoodMap;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings)
    {
        int n = foods.length;

        foodRatingMap = new HashMap<>();
        foodCuisineMap = new HashMap<>();
        cuisineFoodMap = new HashMap<>();

        for (int i = 0; i < n; i++)
        {
            foodRatingMap.put(foods[i], ratings[i]);
            foodCuisineMap.put(foods[i], cuisines[i]);
            TreeSet<Food> set = cuisineFoodMap.get(cuisines[i]);
            if (set == null)
            {
                set = new TreeSet<>(Comparator.comparingInt(Food::getRating).reversed().thenComparing(Food::getName));
                cuisineFoodMap.put(cuisines[i], set);
            }
            set.add(new Food(foods[i], ratings[i]));
        }
    }

    public void changeRating(String food, int newRating)
    {
        int x = foodRatingMap.put(food, newRating);
        if (x != newRating)
        {
            String cuisine = foodCuisineMap.get(food);
            TreeSet<Food> set = cuisineFoodMap.get(cuisine);
            set.remove(new Food(food, x));
            set.add(new Food(food, newRating));
        }
    }

    public String highestRated(String cuisine)
    {
        TreeSet<Food> set = cuisineFoodMap.get(cuisine);
        if (set != null)
        {
            return set.first().name;
        }
        return null;
    }

    class Food
    {
        String name;
        int rating;

        public Food(String name, int rating)
        {
            this.name = name;
            this.rating = rating;
        }

        String getName()
        {
            return name;
        }

        int getRating()
        {
            return rating;
        }
    }
}

