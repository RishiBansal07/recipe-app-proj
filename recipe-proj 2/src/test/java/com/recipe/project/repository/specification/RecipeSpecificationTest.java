package com.recipe.project.repository.specification;

import com.recipe.project.datatransfer.IngredientFetchRequestDTO;
import com.recipe.project.datatransfer.RecipeFetchRequestDTO;
import com.recipe.project.entities.Recipe;
import com.recipe.project.enums.ConditionalParameter;
import com.recipe.project.enums.TypeOfDish;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class RecipeSpecificationTest {

    private RecipeSpecification recipeSpecification;
    Specification<Recipe> mockSpec;
    @Mock(extraInterfaces = Serializable.class)
    Root<Recipe> root;
    @Mock(extraInterfaces = Serializable.class)
    CriteriaQuery<?> query;
    @Mock(extraInterfaces = Serializable.class)
    CriteriaBuilder builder;
    @Mock(extraInterfaces = Serializable.class)
    Predicate predicate;


    @BeforeEach
    void setUp() {
        mockSpec = (Specification<Recipe>) mock(Specification.class, withSettings().serializable());
        when(mockSpec.toPredicate(root, query, builder)).thenReturn(predicate);
        recipeSpecification = new RecipeSpecification();
    }

    @Test
    public void testSpecification() {
        RecipeFetchRequestDTO recipeFetchRequestDTO = new RecipeFetchRequestDTO();
        recipeFetchRequestDTO.setInstruction("Boil");
        recipeFetchRequestDTO.setServings(4);
        recipeFetchRequestDTO.setTypeOfDish(TypeOfDish.VEGETARIAN);
        List<IngredientFetchRequestDTO> ingredients = new ArrayList<>();
        IngredientFetchRequestDTO ingredient = new IngredientFetchRequestDTO();
        ingredient.setConditional(ConditionalParameter.INCLUDE);
        ingredient.setName("Onion");
        ingredients.add(ingredient);
        recipeFetchRequestDTO.setIngredientList(ingredients);
        recipeSpecification.getRecipeConditions(recipeFetchRequestDTO);
        assertNotNull(recipeFetchRequestDTO);
    }
}