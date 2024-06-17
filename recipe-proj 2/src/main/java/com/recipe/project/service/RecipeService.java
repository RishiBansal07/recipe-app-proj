package com.recipe.project.service;

import com.recipe.project.datatransfer.RecipeFetchRequestDTO;
import com.recipe.project.datatransfer.RecipeInsertRequestDTO;
import com.recipe.project.datatransfer.RecipeUpdateRequestDTO;
import com.recipe.project.entities.Recipe;

import java.util.List;

public interface RecipeService {

    List<Recipe> findRecipes(RecipeFetchRequestDTO recipeFetchRequestDTO);

    void deleteById(Long id);

    Long saveRecipe(RecipeInsertRequestDTO recipeInsertRequestDTO);

    Long updateRecipe(Long id, RecipeUpdateRequestDTO recipeUpdateRequestDTO);

    List<Recipe> findAll(Integer page, Integer count);
}

