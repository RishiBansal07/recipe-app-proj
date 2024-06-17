package com.recipe.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.recipe.project.enums.UnitOfMeasure;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private BigDecimal amount;

    @Enumerated(value = EnumType.STRING)
    private UnitOfMeasure unitOfMeasure;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Recipe recipe;

}
