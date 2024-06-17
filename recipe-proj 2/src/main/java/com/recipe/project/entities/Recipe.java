package com.recipe.project.entities;

import com.recipe.project.enums.TypeOfDish;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer servings;

    @Enumerated(value = EnumType.STRING)
    private TypeOfDish typeOfDish;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Instruction instruction;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private List<Ingredient> ingredients;

    public Recipe addIngredient(Ingredient ingredient) {
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }

    public void setInstruction(Instruction instruction) {
        if (instruction != null) {
            this.instruction = instruction;
            instruction.setRecipe(this);
        }
    }

}
