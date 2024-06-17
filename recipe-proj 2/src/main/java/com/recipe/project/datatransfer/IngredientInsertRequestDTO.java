package com.recipe.project.datatransfer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.recipe.project.enums.UnitOfMeasure;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class IngredientInsertRequestDTO {

    @NotBlank(message = "The field \"description\" is required")
    private String description;

    @NotNull(message = "The field \"amount\" is required")
    private BigDecimal amount;

    @NotNull(message = "The field \"unitOfMeasure\" is required")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
    private UnitOfMeasure unitOfMeasure;

}
