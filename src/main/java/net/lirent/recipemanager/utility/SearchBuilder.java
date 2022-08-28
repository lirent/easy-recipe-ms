package net.lirent.recipemanager.utility;

import lombok.extern.slf4j.Slf4j;
import net.lirent.recipemanager.model.Ingredient;
import net.lirent.recipemanager.model.Instruction;
import net.lirent.recipemanager.model.Recipe;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Subquery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * To be able to define reusable Predicates we must use the Specification interface
 * and then is easy to create custom API for search
 * </p>
 *
 * @author Lirent
 */

@Slf4j
public class SearchBuilder {

    /**
     * <p>
     * Building the search specification from the data send by the user.
     * </p>
     *
     * @param title Recipe title
     * @param servings Nr of servings
     * @param category Recipe Category
     * @param includeIngredients Ingredients must have
     * @param excludeIngredients Ingredients without
     * @param instructions Directions
     * @return Specification obj
     */
    public static Specification<Recipe> build(String title,
                                              Integer servings,
                                              Category category,
                                              List<String> includeIngredients,
                                              List<String> excludeIngredients,
                                              String instructions) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Optional.ofNullable(title).ifPresent(t -> predicates.add(builder.like(root.get("title"),"%" + t + "%")));
            Optional.ofNullable(servings).ifPresent(s -> predicates.add(builder.equal(root.get("servings"), s)));
            Optional.ofNullable(category).ifPresent(c -> predicates.add(builder.equal(root.get("category"), c)));
            Optional.ofNullable(includeIngredients).ifPresent(i -> {
                if (!i.isEmpty()) {
                    Join<Recipe, Ingredient> join = root.join("ingredients");
                    predicates.add(join.get("name").in(i));
                }
            });
            Optional.ofNullable(excludeIngredients).ifPresent(i -> {
                if (!i.isEmpty()) {
                    Subquery<Long> result = query.subquery(Long.class);
                    Join<Recipe, Ingredient> ingredients = result.from(Recipe.class).join("ingredients");
                    result.select(ingredients.getParent().get("id"));
                    result.where(ingredients.get("name").in(i));
                    Predicate p = root.get("id").in(result.getSelection()).not();
                    predicates.add(p);
                }
            });
            Optional.ofNullable(instructions).ifPresent(i -> {
                Join<Recipe, Instruction> join = root.join("instructions");
                predicates.add(builder.like(join.get("text"), "%" + i + "%")); /* op % important */
            });

            return builder.and(predicates.toArray(new Predicate[0]));
        };

    }
}
