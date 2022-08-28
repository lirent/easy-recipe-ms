package net.lirent.recipemanager.repository;

import net.lirent.recipemanager.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * The RecipeRepository interface is a Spring Data JPA data repository for
 * Recipe entities.
 * </p>
 * <p>
 * The RecipeRepository provides all the data access behaviors exposed by
 * <code>JpaRepository</code> and additional custom behaviors may be defined
 * in this interface.
 * </p>
 *
 * @author Lirent
 */
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>, JpaSpecificationExecutor<Recipe> {
}
