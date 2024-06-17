package com.recipe.project.datatransfer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.recipe.project.enums.TypeOfDish;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RecipeFetchRequestDTO {

    private Integer servings;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
    private TypeOfDish typeOfDish;
    private String instruction;
    private List<IngredientFetchRequestDTO> ingredientList;
}
