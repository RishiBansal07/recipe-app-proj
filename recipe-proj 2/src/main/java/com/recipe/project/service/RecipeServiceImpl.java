package com.recipe.project.service;

import com.recipe.project.datatransfer.IngredientUpdateRequestDTO;
import com.recipe.project.datatransfer.RecipeFetchRequestDTO;
import com.recipe.project.datatransfer.RecipeInsertRequestDTO;
import com.recipe.project.datatransfer.RecipeUpdateRequestDTO;
import com.recipe.project.entities.Ingredient;
import com.recipe.project.entities.Recipe;
import com.recipe.project.exception.NotFoundException;
import com.recipe.project.mapper.RecipeMapper;
import com.recipe.project.repository.RecipeRepository;
import com.recipe.project.repository.specification.RecipeSpecification;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;


@Slf4j
@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;

    private final RecipeSpecification recipeSpecification;


    /**
     * Fetching the recipe based on the input parameters
     *
     * @param recipeFetchRequestDTO
     * @return
     */
    @Override
    public List<Recipe> findRecipes(RecipeFetchRequestDTO recipeFetchRequestDTO) {
        List<Recipe> recipes = recipeRepository.findAll(recipeSpecification.getRecipeConditions(recipeFetchRequestDTO));
        if (recipes.size() <= 0) {
            throw new NotFoundException("Recipe Not found");
        }
        return recipes;
    }

    /**
     * Delete by ID from the existing DB
     *
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }

    /**
     * Saving the recipe based on the insert request
     *
     * @param recipeInsertRequestDTO
     * @return
     */
    @Override
    @Transactional
    public Long saveRecipe(RecipeInsertRequestDTO recipeInsertRequestDTO) {
        Recipe recipeToSave = recipeMapper.convertToEntityForInsert(recipeInsertRequestDTO);
        recipeToSave.getIngredients().forEach(ingredients -> ingredients.setRecipe(recipeToSave));
        recipeToSave.getInstruction().setRecipe(recipeToSave);
        Recipe recipe = recipeRepository.save(recipeToSave);
        return recipe.getId();
    }

    /**
     * Updating the existing recipe with /id and as a input body with the values to be modified
     *
     * @param id
     * @param recipeInsertRequestDTO
     * @return
     */
    @Override
    public Long updateRecipe(Long id, RecipeUpdateRequestDTO recipeInsertRequestDTO) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("The required ID [%s] your searching is not found on the DB", id)));
        Recipe recipeNew = mergeWithNewValues(recipeInsertRequestDTO, id, recipe);
        recipeRepository.save(recipeNew);
        return id;
    }

    /**
     * Finding all the values from the DB based on the paging strategy
     *
     * @param page
     * @param count
     * @return
     */
    @Override
    public List<Recipe> findAll(Integer page, Integer count) {
        return recipeRepository.findAll(PageRequest.of(page - 1, count)).getContent();
    }

    private Recipe mergeWithNewValues(RecipeUpdateRequestDTO recipeInsertRequestDTO, Long id, Recipe recipe) {
        recipe.setId(id);
        if (nonNull(recipeInsertRequestDTO.getServings())) {
            recipe.setServings(recipeInsertRequestDTO.getServings());
        }
        if (StringUtils.hasLength(recipeInsertRequestDTO.getName())) {
            recipe.setName(recipeInsertRequestDTO.getName());
        }
        if (StringUtils.hasLength(String.valueOf(recipeInsertRequestDTO.getTypeOfDish()))) {
            recipe.setTypeOfDish(recipeInsertRequestDTO.getTypeOfDish());
        }
        if (!ObjectUtils.isEmpty(recipeInsertRequestDTO.getInstructions())) {
            recipe.getInstruction().setRecipeInstructions(recipeInsertRequestDTO.getInstructions());
        }
        if (!ObjectUtils.isEmpty(recipeInsertRequestDTO.getIngredients())) {
            IngredientUpdateRequestDTO modifyIngredient = recipeInsertRequestDTO.getIngredients();
            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(modifyIngredient.getId()))
                    .findFirst();

            if (ingredientOptional.isPresent()) {
                Ingredient ingredientFound = ingredientOptional.get();
                if (nonNull(modifyIngredient.getAmount())) {
                    ingredientFound.setAmount(modifyIngredient.getAmount());
                }
                if (StringUtils.hasLength(modifyIngredient.getDescription())) {
                    ingredientFound.setDescription(modifyIngredient.getDescription());
                }
                if (StringUtils.hasLength(String.valueOf(modifyIngredient.getUnitOfMeasure()))) {
                    ingredientFound.setUnitOfMeasure(modifyIngredient.getUnitOfMeasure());
                }
            } else {
                //add new Ingredient
                Ingredient ingredient = recipeMapper.convertToEntity(modifyIngredient);
                ingredient.setId(null);
                ingredient.setRecipe(recipe);
                recipe.addIngredient(ingredient);
            }
        }
        return recipe;
    }
}
