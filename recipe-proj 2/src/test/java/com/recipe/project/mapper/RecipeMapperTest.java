package com.recipe.project.mapper;

import com.recipe.project.datatransfer.IngredientInsertRequestDTO;
import com.recipe.project.datatransfer.IngredientUpdateRequestDTO;
import com.recipe.project.datatransfer.RecipeFetchRequestDTO;
import com.recipe.project.datatransfer.RecipeInsertRequestDTO;
import com.recipe.project.entities.Ingredient;
import com.recipe.project.entities.Instruction;
import com.recipe.project.entities.Recipe;
import com.recipe.project.enums.TypeOfDish;
import com.recipe.project.enums.UnitOfMeasure;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mapstruct.factory.Mappers.getMapper;

class RecipeMapperTest {

    private RecipeMapper recipeMapper = getMapper(RecipeMapper.class);


    @Test
    void shouldToConvertToEntity() {
        RecipeInsertRequestDTO recipeInsertRequestDTO = new RecipeInsertRequestDTO();
        recipeInsertRequestDTO.setInstruction("Boil");
        recipeInsertRequestDTO.setServings(4);
        recipeInsertRequestDTO.setTypeOfDish(TypeOfDish.VEGETARIAN);
        List<IngredientInsertRequestDTO> ingredients = new ArrayList<>();
        IngredientInsertRequestDTO ingredient = new IngredientInsertRequestDTO();
        ingredient.setAmount(BigDecimal.valueOf(4));
        ingredient.setDescription("Onion");
        ingredient.setUnitOfMeasure(UnitOfMeasure.TEASPOON);
        ingredients.add(ingredient);
        recipeInsertRequestDTO.setIngredients(ingredients);

        Recipe actual = recipeMapper.convertToEntityForInsert(recipeInsertRequestDTO);
        assertNotNull(actual);
    }

    @Test
    void shouldConvertToDto() {
        RecipeFetchRequestDTO actual = recipeMapper.convertToDto(null);
        assertNull(actual);
    }

    @Test
    void mapInstructionToStringTest() {
        Instruction actual = recipeMapper.mapInstructionToString("Boil");
        assertNotNull(actual);
    }

    @Test
    void mapInstructionTest() {
        Instruction instruction = new Instruction();
        String val = recipeMapper.mapInstruction(instruction);
        assertNull(val);
    }

    @Test
    void convertToEntity() {
        IngredientUpdateRequestDTO ingredientUpdateRequestDTO = new IngredientUpdateRequestDTO();
        Ingredient val = recipeMapper.convertToEntity(ingredientUpdateRequestDTO);
        assertNull(val.getId());
    }
}