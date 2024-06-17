package com.recipe.project.mapper;

import com.recipe.project.datatransfer.IngredientUpdateRequestDTO;
import com.recipe.project.datatransfer.RecipeFetchRequestDTO;
import com.recipe.project.datatransfer.RecipeInsertRequestDTO;
import com.recipe.project.entities.Ingredient;
import com.recipe.project.entities.Instruction;
import com.recipe.project.entities.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface RecipeMapper {

    @Mapping(target = "servings", source = "servings")
    @Mapping(source = "instruction", target = "instruction", qualifiedByName = "mapInstructionToString")
    Recipe convertToEntityForInsert(RecipeInsertRequestDTO recipeInsertRequestDTO);

    @Named("mapInstructionToString")
    default Instruction mapInstructionToString(String value) {
        Instruction instruction = new Instruction();
        instruction.setRecipeInstructions(value);
        return instruction;
    }

    @Named("mapInstruction")
    default String mapInstruction(Instruction value) {
        return value.getRecipeInstructions();
    }

    @Mapping(source = "instruction", target = "instruction", qualifiedByName = "mapInstruction")
    RecipeFetchRequestDTO convertToDto(Recipe recipe);

    @Mapping(source = "instruction", target = "instruction", qualifiedByName = "mapInstructionToString")
    Recipe convertToEntity(RecipeInsertRequestDTO recipeInsertRequestDTO);

    Ingredient convertToEntity(IngredientUpdateRequestDTO ingredientUpdateRequestDTO);

}
