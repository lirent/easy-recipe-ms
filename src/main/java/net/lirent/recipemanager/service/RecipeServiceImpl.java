package net.lirent.recipemanager.service;

import net.lirent.recipemanager.model.Recipe;
import net.lirent.recipemanager.repository.RecipeRepository;
import net.lirent.recipemanager.utility.Category;
import net.lirent.recipemanager.utility.SearchBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * The RecipeServiceImpl encapsulates all business behaviors for operations
 * on the Recipe entity model and some related entities.
 * </p>
 *
 * @author Lirent
 */

@Service
public class RecipeServiceImpl implements RecipeService{

    /**
     * The Spring Data repository for Recipe entities.
     */
    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public List<Recipe> searchRecipes(String title,
                                      Integer servings,
                                      Category category,
                                      List<String> includeIngredients,
                                      List<String> excludeIngredients,
                                      String instructions) {
        Specification<Recipe> search = SearchBuilder.build(title, servings, category, includeIngredients, excludeIngredients, instructions);
        return recipeRepository.findAll(search);
    }



    @Override
    public Optional<Recipe> getRecipeById(Long id) {
        return recipeRepository.findById(id);
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe updateRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }
}
