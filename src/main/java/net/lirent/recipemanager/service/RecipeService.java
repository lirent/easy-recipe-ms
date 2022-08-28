package net.lirent.recipemanager.service;

import net.lirent.recipemanager.model.Recipe;
import net.lirent.recipemanager.utility.Category;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * The RecipeService interface defines all public business behaviors for managing the recipes.
 * </p>
 * <p>
 * This interface should be injected into RecipeService clients.
 * </p>
 *
 * @author Lirent
 */
public interface RecipeService {

    /**
     * <p>
     * Find all Recipe entities.
     * </p>
     * @return A list of Recipes.
     */
    List<Recipe> getAllRecipes();

    /**
     * <p>
     * Find Recipes based on the given parameters.
     * </p>
     * @param title Search parameters for title
     * @param servings Nr of
     * @param category Type of dish
     * @param includeIngredients must have
     * @param excludeIngredients without
     * @param instructions directions
     * @return Result of the search
     */
    List<Recipe> searchRecipes(String title,
                               Integer servings,
                               Category category,
                               List<String> includeIngredients,
                               List<String> excludeIngredients,
                               String instructions);

    /**
     * <p>
     * Find a single Recipe entity by a primary key identifier.
     * </p>
     * @param id Long primary key identifier
     * @return An Optional Recipe entity
     */
    Optional<Recipe> getRecipeById(Long id);

    /**
     * <p>
     * Add a new Recipe entity in the database.
     * </p>
     * @param recipe Recipe obj to be persisted
     * @return A persisted Recipe object or <code>null</code> if a problem occurred.
     */
    Recipe addRecipe(Recipe recipe);

    /**
     * <p>
     * Update a Recipe previously persisted in the database.
     * </p>
     * @param recipe Recipe obj to be updated
     * @return An updated Recipe object or <code>null</code> if a problem occurred.
     */
    Recipe updateRecipe(Recipe recipe);

    /**
     * <p>
     * Removes a previously persisted Recipe entity from the database.
     * </p>
     * @param id A Long primary key identifier
     */
    void deleteRecipe(Long id);
}
