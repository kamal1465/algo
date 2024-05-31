package com.kamals.algo.interview.google;

import java.util.*;

/**
 * You are the head chef for a new restaurant whose menu changes every night.
 * You have a large book of recipes, and you select a different set of recipes each night.
 * This is based on what you can make from the Raw Ingredients that you get each day.
 * The ingredient for each recipe could be a raw ingredient or something you can make with other raw ingredients.
 * Bread Raw Ingredients: Flour, Yeast
 * Ham & Cheese Sandwich Raw Ingredients: Ham, Cheese, Intermediate Ingredients: Bread
 * Caprese Salad Raw Ingredients: Cheese, Tomato, Basil
 * In order to make a "Ham & Cheese Sandwich" you need to make sure that you have the Flour, Yeast, Ham, and
 * Cheese “Raw Ingredients” available for the day.
 * If you have Ham, Tomato, Flour, Cheese, and Basil, you can only put “Caprese Salad” on the menu.
 * Given a set of recipes and a set of raw ingredients that you have on a given day,
 * write an algorithm that could calculate the list of possible recipes that you can put on the menu for the day?
 * <p>
 * Expectations - The problem is of MEDIUM difficulty - Expected optimal solution -
 * DFS + Memoizing if a recipe is possible (DP) - SH -
 * TC should get both algorithm and code perfectly without any errors - DFS + DP -
 */
public class FindPossibleRecipes
{
    public static void main(String[] args)
    {
        Map<String, List<String>> recipeBook = Map.of(
                "Bread", List.of("Flour", "Yeast"),
                "Ham & Cheese Sandwich", List.of("Ham", "Cheeses", "Bread"),
                "Caprese Salad", List.of("Cheese", "Tomato", "Basil"));
        Set<String> rawIngredients = Set.of("Ham", "Tomatos", "Flour", "Yeast", "Cheeses", "Basils");

        RestaurantDFS restaurantDFS = new RestaurantDFS(recipeBook);
        RestaurantBFS restaurantBFS = new RestaurantBFS(recipeBook);

        System.out.println(restaurantDFS.getMenu(rawIngredients));
        System.out.println(restaurantBFS.getMenu(rawIngredients));
    }

    static class RestaurantDFS
    {
        private final Map<String, List<String>> RECIPE_BOOK;

        public RestaurantDFS(Map<String, List<String>> rb)
        {
            RECIPE_BOOK = rb;
        }

        private static final int NEW = 0;
        private static final int PROCESSING = 1;
        private static final int COMPLETED = 2;
        private static final int NOT_FEASIBLE = 3;

        public List<String> getMenu(Set<String> rawIngredients)
        {
            List<String> menu = new ArrayList<>();
            Map<String, Integer> recipeState = new HashMap<>();
            RECIPE_BOOK.forEach((k, v) -> recipeState.put(k, NEW));
            for (String recipe : RECIPE_BOOK.keySet())
            {
                if (recipeState.get(recipe) == NEW)
                {
                    DFS(recipe, rawIngredients, recipeState, menu);
                }
            }
            return menu;
        }

        private void DFS(String recipe, Set<String> rawIngredients, Map<String, Integer> recipeState, List<String> menu)
        {
            recipeState.put(recipe, PROCESSING);
            for (String ingredient : RECIPE_BOOK.get(recipe))
            {
                //Ingredient is a Recipe
                if (RECIPE_BOOK.containsKey(ingredient))
                {
                    //Ingredient is unexplored, run DFS on it
                    if (recipeState.get(ingredient) == NEW)
                    {
                        DFS(ingredient, rawIngredients, recipeState, menu);
                    }
                    //Now, ingredient should be either COMPLETED or Processing (Not possible)
                    if (recipeState.get(ingredient) == PROCESSING)
                    {
                        //constituent recipe not possible = Not possible
                        return;
                    }
                }
                //Ingredient is a Raw Ingredient
                else if (!rawIngredients.contains(ingredient))
                {
                    //Ingredient unavailable = Not possible
                    return;
                }
            }

            recipeState.put(recipe, COMPLETED);
            menu.add(recipe);
        }
    }

    static class RestaurantBFS
    {
        private final Map<String, List<String>> recipeBook;
        private final Map<String, List<String>> dependencyGraph;
        private final List<String> recipesTopSort;

        public RestaurantBFS(Map<String, List<String>> recipeBook)
        {
            this.recipeBook = recipeBook;

            dependencyGraph = new HashMap<>();
            recipesTopSort = new ArrayList<>();

            Map<String, Integer> indegree = new HashMap<>();

            recipeBook.forEach((k, v) -> {
                dependencyGraph.put(k, new ArrayList<>());
                indegree.put(k, 0);
            });

            recipeBook.forEach((k, v) -> {
                for (String ingredient : v)
                {
                    if (recipeBook.containsKey(ingredient))
                    {
                        dependencyGraph.get(ingredient).add(k);
                        indegree.merge(k, 1, Integer::sum);
                    }
                }
            });

            indegree.forEach((k, v) -> {
                if (v == 0)
                {
                    recipesTopSort.add(k);
                }
            });

            for (int i = 0; i < recipesTopSort.size(); i++)
            {
                String recipe = recipesTopSort.get(i);
                for (String recipe2 : dependencyGraph.get(recipe))
                {
                    indegree.merge(recipe2, -1, Integer::sum);
                    if (indegree.get(recipe2) == 0)
                    {
                        recipesTopSort.add(recipe2);
                    }
                }
            }

            if (recipesTopSort.size() < recipeBook.size())
            {
                //Cycle present, Do nothing
            }

            if (recipesTopSort.isEmpty())
            {
                //No recipes are possible
                throw new IllegalArgumentException("No recipes are possible");
            }
        }

        public Set<String> getMenu(Set<String> rawIngredients)
        {
            Set<String> menu = new LinkedHashSet<>();
            for (String recipe : recipesTopSort)
            {
                boolean possible = true;
                for (String ingredient : recipeBook.get(recipe))
                {
                    if (!rawIngredients.contains(ingredient) && !menu.contains(ingredient))
                    {
                        possible = false;
                        break;
                    }
                }
                if (possible)
                {
                    menu.add(recipe);
                }
            }
            return menu;
        }
    }
}
