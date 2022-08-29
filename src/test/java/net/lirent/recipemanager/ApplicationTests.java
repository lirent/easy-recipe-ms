package net.lirent.recipemanager;

import net.lirent.recipemanager.model.Ingredient;
import net.lirent.recipemanager.model.Instruction;
import net.lirent.recipemanager.model.Recipe;
import net.lirent.recipemanager.repository.RecipeRepository;
import net.lirent.recipemanager.utility.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ApplicationTests {
    public static final String HOST_8080_API = "http://locahost:8080/api/v1/recipes/";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RecipeRepository repository;

    /**
     * Testing post recipe
     * @throws Exception errors
     */
    @Test
    void postRecipes() throws Exception{
        long id = 1;
        var ingredients = Arrays.asList(
                new Ingredient(null, "pasta", 100, "gr"),
                new Ingredient(null, "pistachio", 2, "cups"));

        var instructions = Arrays.asList(
                new Instruction(null, 1, "for the first step"),
                new Instruction(null, 2, "for the second step"));

        repository.save(new Recipe(null, "Recipe Test", Category.VEGETARIAN, 4, "Paolo", ingredients, instructions, null));

        this.mockMvc.perform(get(HOST_8080_API + id)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$..title", hasItem(is("Recipe Test"))));
    }

    /**
     * Testing get recipe with sql sample data import
     * @throws Exception errors
     */
    @Test
    @Sql({"/import-h2.sql"})
    void getRecipes() throws Exception {
        this.mockMvc.perform(get(HOST_8080_API)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$..title", hasItem(is("Big night pizza"))));
    }

    /**
     * Testing delete recipe with sql sample data import
     * @throws Exception errors
     */
    @Test
    @Sql({"/import-h2.sql"})
    void deleteRecipe() throws Exception {
        long id = 1;
        this.mockMvc.perform(delete(HOST_8080_API+ id)).andDo(print()).andExpect(status().isNoContent());
    }

    /**
     * Testing update recipe sql sample data import
     * @throws Exception errors
     */
    @Test
    @Sql({"/import-h2.sql"})
    void updateRecipe() throws Exception {
        long id = 1;
        String updatedContent = "Big big night pizza";

        this.mockMvc.perform(patch(HOST_8080_API+ id)
                .contentType(MediaType.APPLICATION_JSON)
                        .content(getArticleInJson(updatedContent)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$..title", hasItem(is(updatedContent))));
    }

    private String getArticleInJson(String content) {
        return "{\"title\":\"" + content + "\"}";
    }
}
