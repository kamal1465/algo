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
public class FindPossibleRecipesV2OO
{
    public static void main(String[] args)
    {
        Ingredient ingredient1 = new Ingredient("Flour");
        Ingredient ingredient2 = new Ingredient("Yeast");
        Ingredient ingredient3 = new Ingredient("Ham");
        Ingredient ingredient4 = new Ingredient("Cheese");
        Ingredient ingredient5 = new Ingredient("Tomato");
        Ingredient ingredient6 = new Ingredient("Basil");
        Recipe recipe1 = new Recipe("Bread", Arrays.asList(ingredient1, ingredient2));
        Recipe recipe2 = new Recipe("Ham & Cheese Sandwich", Arrays.asList(ingredient3, ingredient4, recipe1));
        Recipe recipe3 = new Recipe("Caprese Salad", Arrays.asList(ingredient4, ingredient5, ingredient6));

        List<Recipe> RecipeBook = List.of(recipe1, recipe2, recipe3);

        Set<Ingredient> RawIngredients = Set.of(ingredient1, ingredient2, ingredient3, ingredient4, ingredient5);

        RestaurantDFS restaurantDFS = new RestaurantDFS(RecipeBook);
        RestaurantBFS restaurantBFS = new RestaurantBFS(RecipeBook);

        System.out.println(restaurantDFS.getMenu(RawIngredients));
        System.out.println(restaurantBFS.getMenu(RawIngredients));
    }
}

class RestaurantDFS
{
    private final List<Recipe> RECIPE_BOOK;

    public RestaurantDFS(List<Recipe> rb)
    {
        RECIPE_BOOK = new ArrayList<>(rb);
    }

    private static final int NEW = 0;
    private static final int PROCESSING = 1;
    private static final int COMPLETED = 2;
    private static final int NOT_FEASIBLE = 3;

    public List<Recipe> getMenu(Set<Ingredient> rawIngredients)
    {
        List<Recipe> menu = new ArrayList<>();
        Map<Recipe, Integer> recipeState = new HashMap<>();
        RECIPE_BOOK.forEach(k -> recipeState.put(k, NEW));
        RECIPE_BOOK.forEach(recipe -> {
            if (recipeState.get(recipe) == NEW)
            {
                DFS(recipe, rawIngredients, recipeState, menu);
            }
        });
        return menu;
    }

    private void DFS(Recipe recipe, Set<Ingredient> rawIngredients, Map<Recipe, Integer> recipeState, List<Recipe> menu)
    {
        recipeState.put(recipe, PROCESSING);
        for (Ingredient ingredient : recipe.getIngredientList())
        {
            //Ingredient is a Recipe
            if (ingredient instanceof Recipe recipe2)
            {
                //Ingredient is unexplored, run DFS on it
                if (recipeState.get(recipe2) == NEW)
                {
                    DFS(recipe2, rawIngredients, recipeState, menu);
                }
                //Now, ingredient should be either COMPLETED or Processing (Not possible)
                if (recipeState.get(recipe2) == PROCESSING)
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

class RestaurantBFS
{
    private final List<Recipe> recipeBook;
    private final Map<Recipe, List<Recipe>> dependencyGraph;
    private final List<Recipe> recipesTopSort;

    public RestaurantBFS(List<Recipe> recipeBook)
    {
        this.recipeBook = recipeBook;

        dependencyGraph = new HashMap<>();
        recipesTopSort = new ArrayList<>();

        Map<Recipe, Integer> indegree = new HashMap<>();

        recipeBook.forEach(k -> {
            dependencyGraph.put(k, new ArrayList<>());
            indegree.put(k, 0);
        });

        recipeBook.forEach(k -> {
            for (Ingredient ingredient : k.getIngredientList())
            {
                if (ingredient instanceof Recipe recipe)
                {
                    dependencyGraph.get(recipe).add(k);
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
            Recipe recipe = recipesTopSort.get(i);
            for (Recipe recipe2 : dependencyGraph.get(recipe))
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

    public Set<Recipe> getMenu(Set<Ingredient> rawIngredients)
    {
        Set<Recipe> menu = new LinkedHashSet<>();
        for (Recipe recipe : recipesTopSort)
        {
            boolean possible = true;
            for (Ingredient ingredient : recipe.getIngredientList())
            {
                if (ingredient instanceof Recipe recipe2)
                {
                    if (!menu.contains(recipe2))
                    {
                        possible = false;
                    }
                }
                else if (!rawIngredients.contains(ingredient))
                {
                    possible = false;
                }
                if (!possible)
                {
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


class Ingredient
{
    private final String name;

    public Ingredient(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return name;
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
        Ingredient that = (Ingredient) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name);
    }
}

class Recipe extends Ingredient
{
    private final List<Ingredient> ingredientList = new ArrayList<>();

    public Recipe(String name, List<Ingredient> ingredientList)
    {
        super(name);
        ingredientList = new ArrayList<>(ingredientList);
    }

    public List<Ingredient> getIngredientList()
    {
        return Collections.unmodifiableList(ingredientList);
    }
}

