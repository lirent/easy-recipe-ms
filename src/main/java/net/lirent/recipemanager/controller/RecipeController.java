package net.lirent.recipemanager.controller;

import lombok.extern.slf4j.Slf4j;
import net.lirent.recipemanager.model.Recipe;
import net.lirent.recipemanager.service.RecipeServiceImpl;
import net.lirent.recipemanager.utility.Category;
import net.lirent.recipemanager.utility.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * The RecipeController class is a RESTful web service controller. The <code>@RestController</code> annotation informs
 * Spring that each <code>@RequestMapping</code> method returns a <code>@ResponseBody</code>.
 * </p>
 *
 * @author Lirent
 */

@Slf4j
@RestController
@RequestMapping("/api/v1/recipes")
public class RecipeController {

    @Autowired
    private RecipeServiceImpl recipeService;

    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return new ResponseEntity<>(recipeService.getAllRecipes(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
        return new ResponseEntity<>(recipeService.addRecipe(recipe), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable("id") long id) {
        var recipe = recipeService.getRecipeById(id);
        if (recipe.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(recipe.get(), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@RequestBody Recipe recipe, @PathVariable("id") long id) {
        var recipeDb = recipeService.getRecipeById(id);
        if (recipeDb.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        var recipeUpdated = recipeDb.get();
        var ignoredFields = Utils.getNullPropertyNames(recipe);
        log.debug("ignoredFields:{}", ignoredFields);
        BeanUtils.copyProperties(recipe, recipeUpdated, ignoredFields);

        return new ResponseEntity<>(recipeService.updateRecipe(recipeUpdated), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable("id") long id) {
        //recipe not-found
        if (!recipeService.getRecipeById(id).isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        recipeService.deleteRecipe(id);
        log.warn("Recipe with id={} deleted", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Recipe>> searchRecipe(@RequestParam(required = false) String title,
                                                     @RequestParam(required = false) Integer servings,
                                                     @RequestParam(required = false) Category category,
                                                     @RequestParam(required = false) List<String> includeIngredients,
                                                     @RequestParam(required = false) List<String> excludeIngredients,
                                                     @RequestParam(required = false) String instructions) {
        var recipeList = recipeService.searchRecipes(title, servings, category, includeIngredients, excludeIngredients, instructions);
        //no recipe found
        if (recipeList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        log.info("Search returned {} results", recipeList.size());
        return new ResponseEntity<>(recipeList, HttpStatus.OK);
    }
}
