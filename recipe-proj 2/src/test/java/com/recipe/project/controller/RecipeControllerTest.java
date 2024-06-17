/*
package com.recipe.project.controller;

import com.recipe.project.datatransfer.*;
import com.recipe.project.entities.Recipe;
import com.recipe.project.enums.ConditionalParameter;
import com.recipe.project.enums.TypeOfDish;
import com.recipe.project.mapper.RecipeMapper;
import com.recipe.project.repository.RecipeRepository;
import com.recipe.project.repository.specification.RecipeSpecification;
import com.recipe.project.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RecipeControllerTest {

    private final RecipeRepository recipeRepository = mock(RecipeRepository.class);
    private final RecipeMapper recipeMapper = mock(RecipeMapper.class);

    private final RecipeSpecification recipeSpecification= mock(RecipeSpecification.class);
    Specification<Recipe> mockSpec;
    @Mock(extraInterfaces = Serializable.class)
    Root<Recipe> root;
    @Mock(extraInterfaces = Serializable.class)
    CriteriaQuery<?> query;
    @Mock(extraInterfaces = Serializable.class)
    CriteriaBuilder builder;
    @Mock(extraInterfaces = Serializable.class)
    Predicate predicate;

    private final RecipeService recipeService = mock(RecipeService.class);


    private RecipeController recipeController;

    @BeforeEach
    void setUp() {
        recipeController = new RecipeController(recipeService);
    }

//    @BeforeEach
//    void setUp() {
////        recipeService = new RecipeServiceImpl(
////                recipeRepository,
////                recipeMapper, recipeSpecification
////        );
//
//        mockSpec = (Specification<Recipe>) mock(Specification.class, withSettings().serializable());
//        when(mockSpec.toPredicate(root, query, builder)).thenReturn(predicate);
//    }

    @Test
    void fetchDetails() {
        List<Recipe> recipeResult = new ArrayList<>();
        Recipe recipe = new Recipe();
        recipe.setId(2L);
        recipeResult.add(recipe);
        recipe = new Recipe();
        recipe.setId(3L);
        recipeResult.add(recipe);

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

        when(recipeSpecification.getRecipeConditions(recipeFetchRequestDTO)).thenReturn(mockSpec);
        when(recipeRepository.findAll(mockSpec)).thenReturn(recipeResult);
        when(recipeService.findRecipes(recipeFetchRequestDTO)).thenReturn(recipeResult);

        //then
        List<Recipe> recipes = recipeController.fetchDetails(recipeFetchRequestDTO);

        //when
        assertEquals(2, recipes.size());
    }

    @Test
    void insertDetails() {
        RecipeInsertRequestDTO recipeInsertRequestDTO = new RecipeInsertRequestDTO();
        recipeInsertRequestDTO.setName("New Dish");
        recipeInsertRequestDTO.setInstruction("Boil");
        recipeInsertRequestDTO.setServings(4);
        recipeInsertRequestDTO.setTypeOfDish(TypeOfDish.VEGETARIAN);
        List<IngredientInsertRequestDTO> ingredients = new ArrayList<>();
        IngredientInsertRequestDTO ingredient = new IngredientInsertRequestDTO();
        ingredient.setDescription("Onion");
        ingredients.add(ingredient);
        recipeInsertRequestDTO.setIngredients(ingredients);

        Recipe recipeToSave = recipeMapper.convertToEntityForInsert(recipeInsertRequestDTO);

        Recipe recipeReturn = new Recipe();
        recipeReturn.setId(1L);
        when(recipeRepository.save(any())).thenReturn(recipeReturn);
        when(recipeService.saveRecipe(recipeInsertRequestDTO)).thenReturn(1L);
        assertEquals(HttpStatus.valueOf(201), recipeController.insertDetails(recipeInsertRequestDTO).getStatusCode());
    }

    @Test
    void modifyDetails() {
        RecipeUpdateRequestDTO recipeUpdateRequestDTO = new RecipeUpdateRequestDTO();
        recipeUpdateRequestDTO.setName("New Dish");
        recipeUpdateRequestDTO.setInstructions("Boil");
        recipeUpdateRequestDTO.setServings(4);
        recipeUpdateRequestDTO.setTypeOfDish(TypeOfDish.VEGETARIAN);
        IngredientUpdateRequestDTO ingredient = new IngredientUpdateRequestDTO();
        ingredient.setDescription("Onion");
        recipeUpdateRequestDTO.setIngredients(ingredient);

        Long id = 1L;

        Recipe recipeReturn = new Recipe();
        recipeReturn.setId(id);

        when(recipeRepository.save(any())).thenReturn(recipeReturn);
        when(recipeService.updateRecipe(id, recipeUpdateRequestDTO)).thenReturn(1L);
        assertEquals(1L, recipeController.modifyDetails(id, recipeUpdateRequestDTO).getBody());
    }

    @Test
    void deleteDetails() {
        //when
        recipeController.deleteDetails(1L);

        //then
        verify(recipeService, times(1)).deleteById(anyLong());
    }


    @Test
    void findAll() {
        //when

        List<Recipe> recipeResult = new ArrayList<>();
        Recipe recipe = new Recipe();
        recipe.setId(2L);
        recipeResult.add(recipe);
        recipe = new Recipe();
        recipe.setId(3L);
        recipeResult.add(recipe);
        when(recipeService.findAll(anyInt(), anyInt())).thenReturn(recipeResult);


        recipeController.findAll(1,1);
        //then
    }
}*/
