package com.recipe.project.datatransfer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.recipe.project.enums.TypeOfDish;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeInsertRequestDTO {

    @NotBlank(message = "The field \"name\" is required")
    private String name;

    @NotNull(message = "The field \"servings\" is required")
    private Integer servings;

    @NotNull(message = "The field \"typeOfDish\" is required")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
    private TypeOfDish typeOfDish;

    @NotBlank(message = "The field \"instruction\" is required")
    private String instruction;

    @Valid
    @NotEmpty(message = "The field \"ingredients\" is required, Because a recipe cannot be without ingredients!")
    private List<IngredientInsertRequestDTO> ingredients = new ArrayList<>();

}

