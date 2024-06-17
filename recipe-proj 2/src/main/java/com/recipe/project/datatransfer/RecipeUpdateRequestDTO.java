package com.recipe.project.datatransfer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.recipe.project.enums.TypeOfDish;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeUpdateRequestDTO {

    private String name;

    private Integer servings;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
    private TypeOfDish typeOfDish;

    private String instructions;

    @Valid
    private IngredientUpdateRequestDTO ingredients;
}
