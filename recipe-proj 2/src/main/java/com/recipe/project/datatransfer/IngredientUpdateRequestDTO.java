package com.recipe.project.datatransfer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.recipe.project.enums.UnitOfMeasure;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class IngredientUpdateRequestDTO {

    @NotNull(message = "The field \"id\" is required, because to update the ingredient its required to know which ingredient to be updated!")
    private Long id;

    private String description;

    private BigDecimal amount;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
    private UnitOfMeasure unitOfMeasure;
}
