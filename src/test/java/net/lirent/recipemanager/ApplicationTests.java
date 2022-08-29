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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RecipeRepository repository;

    @Test
    void postRecipes() throws Exception{

        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient(null, "pasta", 100, "gr"),
                new Ingredient(null, "pistachio", 2, "cups"));

        List<Instruction> instructions = Arrays.asList(
                new Instruction(null, 1, "for the first step"),
                new Instruction(null, 2, "for the second step"));

        repository.save(new Recipe(null, "Recipe Test", Category.VEGETARIAN, 4, "Paolo", ingredients, instructions, null));

        this.mockMvc.perform(get("http://locahost:8080/api/v1/recipes/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$..title", hasItem(is("Recipe Test"))));
    }
    
    @Test
    @Sql({"/import-h2.sql"})
    void getRecipes() throws Exception {
        this.mockMvc.perform(get("http://locahost:8080/api/v1/recipes")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$..title", hasItem(is("Big night pizza"))));
    }

}
